package uk.co.redkiteweb.dccweb.decoders;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * Created by shawn on 16/09/16.
 */
public class DecoderDefinition {

    private Document decoderDefDocument;

    public DecoderDefinition(final String decoderDefFile) throws DefinitionException {
        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.decoderDefDocument = documentBuilder.parse(DecoderDefinition.class.getResourceAsStream(decoderDefFile));
        } catch (ParserConfigurationException exception) {
            throw new DefinitionException(exception);
        } catch (SAXException exception) {
            throw new DefinitionException(exception);
        } catch (IOException exception) {
            throw new DefinitionException(exception);
        }
    }

    public Node getValueNode(final String valueName) throws DefinitionException {
        Node valueNode;
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expression = xPath.compile(String.format("//value[@name='%s']", valueName));
            valueNode =  (Node) expression.evaluate(decoderDefDocument, XPathConstants.NODE);
        } catch (XPathExpressionException exception) {
            throw new DefinitionException(exception);
        }
        return valueNode;
    }

}
