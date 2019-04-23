package uk.co.redkiteweb.dccweb.decoders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

@Component
public class DecoderDefinitionReaderFactory implements ApplicationContextAware {

    private static final Logger LOGGER = LogManager.getLogger(DecoderDefinitionReaderFactory.class);

    private ApplicationContext context;
    private String definitionsPath;

    @Value("${dccweb.definitions.path}")
    public void setDefinitionsPath(final String definitionsPath) {
        this.definitionsPath = definitionsPath;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public DecoderDefinitionReader newInstance(final String decoderDefinitionFile) throws DefinitionException {
        final DecoderDefinitionReader reader = context.getBean(DecoderDefinitionReader.class);
        reader.setDefinitionDocument(toXmlDocument(getInputStream(decoderDefinitionFile), decoderDefinitionFile));
        return reader;
    }

    private Document toXmlDocument(final InputStream inputStream, final String decoderDefFile) throws DefinitionException {
        if (null == inputStream) {
            throw new DefinitionException(String.format("File Not Found %s", decoderDefFile));
        } else {
            try {
                final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                return documentBuilder.parse(inputStream);
            } catch (IOException | SAXException | ParserConfigurationException exception) {
                throw new DefinitionException(exception);
            }
        }
    }

    private InputStream getInputStream(final String decoderDefinitionFile) {
        final File definitionFile = getFile(decoderDefinitionFile);
        InputStream definitionStream;
        try {
            definitionStream = new FileInputStream(definitionFile);
        } catch (final FileNotFoundException exception) {
            definitionStream = DecoderDefinitionReaderFactory.class.getResourceAsStream(decoderDefinitionFile);
        }
        return definitionStream;
    }

    private File getFile(final String decoderDefinitionFile) {
        final String fullFileName = String.format("%s/%s", definitionsPath, decoderDefinitionFile);
        LOGGER.info("Loading definitions file from: {}", fullFileName);
        return new File(fullFileName);
    }
}
