package uk.co.redkiteweb.dccweb.decoders;

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
import java.util.TreeSet;

@Component
@Scope("prototype")
public class DecoderDefinitionReader {

    private Document definitionDocument;
    private final Collection<CVDefinition> cvDefinitions;

    public DecoderDefinitionReader() {
        cvDefinitions = new TreeSet<>();
    }

    public void setDefinitionDocument(Document definitionDocument) {
        this.definitionDocument = definitionDocument;
    }

    public Collection<CVDefinition> readDefinitions() throws DefinitionException {
        buildLocalCvDefinitions();
        return cvDefinitions;
    }

    private void buildLocalCvDefinitions() throws DefinitionException {
        final NodeList cvNodes = getCVNodes();
        for(int index = 0; index < cvNodes.getLength(); index++) {
            addCV((Element) cvNodes.item(index));
        }
    }

    private void addCV(final Element cvElement) {
        final CVDefinition cv = new CVDefinition();
        cv.setNumber(cvElement.getAttribute("number"));
        cv.setValues(fetchValues(cvElement.getElementsByTagName("value"), cv.getNumber()));
        cvDefinitions.add(cv);
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
        option.setName(optionElement.getAttribute("name"));
        options.add(option);
    }

    private NodeList getCVNodes() throws DefinitionException {
        return (NodeList) getXPathValue("//cv", XPathConstants.NODESET);
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
