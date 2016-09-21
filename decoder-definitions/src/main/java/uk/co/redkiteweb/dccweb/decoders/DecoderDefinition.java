package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shawn on 16/09/16.
 */
@Component
@Scope("prototype")
public class DecoderDefinition {

    private Document decoderDefDocument;

    public void setDecoderDefFile(final String decoderDefFile) throws DefinitionException {
        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final InputStream xmlStream = DecoderDefinition.class.getResourceAsStream(decoderDefFile);
            if (xmlStream != null) {
                this.decoderDefDocument = documentBuilder.parse(xmlStream);
            } else {
                throw new DefinitionException(String.format("File Not Found %s", decoderDefFile));
            }
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
