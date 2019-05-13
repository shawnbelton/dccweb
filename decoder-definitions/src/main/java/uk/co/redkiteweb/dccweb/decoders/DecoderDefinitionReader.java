package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;
import uk.co.redkiteweb.dccweb.decoders.model.CVValueOption;

import javax.xml.namespace.QName;
import javax.xml.xpath.*;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;

@Component
@Scope("prototype")
public class DecoderDefinitionReader {

    private Document definitionDocument;
    private DecoderDefinitionReaderFactory readerFactory;
    private final Collection<CVDefinition> cvDefinitions;

    public DecoderDefinitionReader() {
        cvDefinitions = new TreeSet<>();
    }

    @Autowired
    public void setReaderFactory(DecoderDefinitionReaderFactory readerFactory) {
        this.readerFactory = readerFactory;
    }

    public void setDefinitionDocument(Document definitionDocument) {
        this.definitionDocument = definitionDocument;
    }

    public Collection<CVDefinition> readDefinitions() throws DefinitionException {
        buildLocalCvDefinitions();
        buildIncludedDefinitions();
        return cvDefinitions;
    }

    private void buildLocalCvDefinitions() throws DefinitionException {
        final NodeList cvNodes = getCVNodes();
        for(int index = 0; index < cvNodes.getLength(); index++) {
            addCV((Element) cvNodes.item(index));
        }
    }

    private void buildIncludedDefinitions() throws DefinitionException {
        final NodeList includeNodes = getIncludeNodes();
        for (int index = 0; index < includeNodes.getLength(); index++) {
            addIncludedCVs((Element) includeNodes.item(index));
        }
    }

    private void addCV(final Element cvElement) {
        final CVDefinition cv = new CVDefinition();
        cv.setNumber(cvElement.getAttribute("number"));
        cv.setValues(fetchValues(cvElement.getElementsByTagName("value"), cv.getNumber()));
        cvDefinitions.add(cv);
    }

    private void addIncludedCVs(final Element includeElement) throws DefinitionException {
        final Collection<CVDefinition> includedDefinitions = readerFactory.newInstance(includeElement.getAttribute("definition")).readDefinitions();
        mergeDefinitions(includedDefinitions);
    }

    private void mergeDefinitions(final Collection<CVDefinition> toBeMerged) {
        for(final CVDefinition merged : toBeMerged) {
            final Optional<CVDefinition> existing = cvDefinitions.stream().filter(definition -> definition.getNumber().equals(merged.getNumber())).findFirst();
            if (existing.isPresent()) {
                mergeValues(existing.get(), merged);
            } else {
                cvDefinitions.add(merged);
            }
        }
    }

    private void mergeValues(final CVDefinition existing, final CVDefinition merge) {
        for(final CVValue cvValue : merge.getValues()) {
            final Optional<CVValue> existingValue = existing.getValues().stream().filter(value -> value.getId().equals(cvValue.getId())).findFirst();
            if (existingValue.isPresent()) {
                mergeOptions(existingValue.get(), cvValue);
            } else {
                existing.getValues().add(cvValue);
            }
        }
    }

    private void mergeOptions(final CVValue existing, final CVValue merge) {
        for(final CVValueOption cvOption : merge.getOptions()) {
            final Optional<CVValueOption> existingOption = existing.getOptions().stream().filter(option -> option.getValue().equals(cvOption.getValue())).findFirst();
            if (!existingOption.isPresent()) {
                existing.getOptions().add(cvOption);
            }
        }
    }

    private Collection<CVValue> fetchValues(final NodeList valueList, final String cvNumber) {
        final Collection<CVValue> values = new TreeSet<>();
        for(int index = 0; index < valueList.getLength(); index++) {
            addCVValue(values, (Element) valueList.item(index), cvNumber);
        }
        return values;
    }

    private void addCVValue(final Collection<CVValue> values, final Element valueElement, final String cvNumber) {
        final CVValue value = new CVValue();
        value.setId(valueElement.getAttribute("id"));
        value.setCvNumber(cvNumber);
        value.setName(valueElement.getAttribute("name"));
        value.setType(CVValue.Type.valueOf(valueElement.getAttribute("type").toUpperCase()));
        value.setBit(valueToInteger(valueElement.getAttribute("bit")));
        value.setLow(valueToInteger(valueElement.getAttribute("low")));
        value.setHigh(valueToInteger(valueElement.getAttribute("high")));
        value.setMask(valueToInteger(valueElement.getAttribute("mask")));
        value.setReadMask(valueToInteger(valueElement.getAttribute("readMask")));
        value.setOptions(fetchOptions(valueElement.getElementsByTagName("option")));
        values.add(value);
    }

    private Integer valueToInteger(final String value) {
        final Integer integer;
        if (value.isEmpty()) {
            integer = null;
        } else {
            integer = Integer.parseInt(value);
        }
        return integer;
    }

    private Collection<CVValueOption> fetchOptions(final NodeList optionsList) {
        final Collection<CVValueOption> options = new TreeSet<>();
        for(int index = 0; index < optionsList.getLength(); index++) {
            addCVOption(options, (Element) optionsList.item(index));
        }
        return options;
    }

    private void addCVOption(final Collection<CVValueOption> options, final Element optionElement) {
        final CVValueOption option = new CVValueOption();
        option.setValue(Integer.parseInt(optionElement.getAttribute("value")));
        option.setName(optionElement.getTextContent());
        options.add(option);
    }

    private NodeList getCVNodes() throws DefinitionException {
        return (NodeList) getXPathValue("//cv", XPathConstants.NODESET);
    }

    private NodeList getIncludeNodes() throws DefinitionException {
        return (NodeList) getXPathValue("//include", XPathConstants.NODESET);
    }

    private Object getXPathValue(final String xPathString, final QName returnType) throws DefinitionException {
        try {
            return getXPathExpression(xPathString).evaluate(definitionDocument, returnType);
        } catch (XPathExpressionException exception) {
            throw new DefinitionException(exception);
        }
    }

    private static XPathExpression getXPathExpression(final String xPathString) throws XPathExpressionException {
        final XPathFactory xPathFactory = XPathFactory.newInstance();
        final XPath xPath = xPathFactory.newXPath();
        return xPath.compile(xPathString);
    }

}
