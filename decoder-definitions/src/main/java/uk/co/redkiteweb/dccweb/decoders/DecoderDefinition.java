package uk.co.redkiteweb.dccweb.decoders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;

/**
 * Created by shawn on 16/09/16.
 */
@Component
@Scope("prototype")
public class DecoderDefinition {

    private static final Logger LOGGER = LogManager.getLogger(DecoderDefinition.class);

    private Document decoderDefDocument;
    private String definitionsPath;


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

    public Node getValueNode(final String valueName) throws DefinitionException {
        return (Node) getXPathValue(String.format("//value[@name='%s']", valueName), XPathConstants.NODE);
    }

    public NodeList getValueNodes() throws DefinitionException {
        return (NodeList) getXPathValue("//value", XPathConstants.NODESET);
    }

    public NodeList getCVNodes() throws DefinitionException {
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
}
