package uk.co.redkiteweb.dccweb.decoders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;
import uk.co.redkiteweb.dccweb.decoders.model.CVValueOption;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;

/**
 * Created by shawn on 16/09/16.
 */
@Component
@Scope("prototype")
public class DecoderDefinition {

    private static final Logger LOGGER = LogManager.getLogger(DecoderDefinition.class);

    private Document decoderDefDocument;
    private String definitionsPath;
    private Collection<CVDefinition> cvDefinitions;

    @Value("${dccweb.definitions.path}")
    public void setDefinitionsPath(final String definitionsPath) {
        this.definitionsPath = definitionsPath;
    }

    public void setDecoderDefFile(final String decoderDefFile) throws DefinitionException {
        try {
            final InputStream xmlStream = getInput(decoderDefFile);
            if (xmlStream != null) {
                final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                this.decoderDefDocument = documentBuilder.parse(xmlStream);
                buildCVDefinitions();
            } else {
                throw new DefinitionException(String.format("File Not Found %s", decoderDefFile));
            }
        } catch (IOException | SAXException | ParserConfigurationException exception) {
            throw new DefinitionException(exception);
        }
    }

    private InputStream getInput(final String decoderDefFile) throws FileNotFoundException {
        InputStream xmlStream;
        final String fullFileName = String.format("%s/%s", definitionsPath, decoderDefFile);
        LOGGER.info("Loading definitions file from: {}", fullFileName);
        final File definitionsFile = new File(fullFileName);
        if (definitionsFile.exists()) {
            xmlStream = new FileInputStream(definitionsFile);
        } else {
            xmlStream = DecoderDefinition.class.getResourceAsStream(decoderDefFile);
        }
        return xmlStream;
    }

    private void buildCVDefinitions() throws DefinitionException {
        cvDefinitions = new TreeSet<>();
        buildLocalCvDefinitions();
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
        if (value == null || value.isEmpty()) {
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

    public CVValue getCVValue(final String valueName) throws DefinitionException {
        final Optional<CVValue> value = getCVValues().stream().filter(cvValue -> cvValue.getName().equals(valueName)).findFirst();
        if (value.isPresent()) {
            return value.get();
        } else {
            throw new DefinitionException(String.format("CV Value %s is not found.", valueName));
        }
    }

    public Collection<CVValue> getCVValues() {
        final Collection<CVValue> cvValues = new TreeSet<>();
        cvDefinitions.forEach(cvDefinition -> cvValues.addAll(cvDefinition.getValues()));
        return cvValues;
    }

    public Collection<CVDefinition> getCvDefinitions() {
        return cvDefinitions;
    }

    private NodeList getCVNodes() throws DefinitionException {
        return (NodeList) getXPathValue("//cv", XPathConstants.NODESET);
    }

    private Object getXPathValue(final String xPathString, final QName returnType) throws DefinitionException {
        try {
            return getXPathExpression(xPathString).evaluate(decoderDefDocument, returnType);
        } catch (XPathExpressionException exception) {
            throw new DefinitionException(exception);
        }
    }

    private static XPathExpression getXPathExpression(final String xPathString) throws XPathExpressionException {
        final XPathFactory xPathFactory = XPathFactory.newInstance();
        final XPath xPath = xPathFactory.newXPath();
        return xPath.compile(xPathString);
    }

    public static Integer getOrderValue(final String value) {
        final String[] values = value.split(",");
        return Integer.parseInt(values[0]);
    }

}
