package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;
import uk.co.redkiteweb.dccweb.decoders.types.ValueType;
import uk.co.redkiteweb.dccweb.decoders.types.ValueTypeFactory;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class DefinitionReaderTest {

    private DefinitionReader definitionReader;
    private DecoderDefinition decoderDefinition;
    private Collection<CVValue> cvValues;

    @Before
    public void setup() throws DefinitionException {
        final LogStore logStore = mock(LogStore.class);
        decoderDefinition = mock(DecoderDefinition.class);
        final ValueTypeFactory valueTypeFactory = mock(ValueTypeFactory.class);
        final CVValue cvValue = mock(CVValue.class);
        final ValueType valueType = mock(ValueType.class);
        final CVHandler cvHandler = mock(CVHandler.class);
        final Collection<CVDefinition> cvDefinitions = new HashSet<>();
        cvValues = new HashSet<>();
        cvValues.add(cvValue);
        final CVDefinition cvDefinition = mock(CVDefinition.class);
        cvDefinitions.add(cvDefinition);
        definitionReader = new DefinitionReader();
        definitionReader.setLogStore(logStore);
        definitionReader.setDecoderDefinition(decoderDefinition);
        definitionReader.setValueTypeFactory(valueTypeFactory);
        definitionReader.setCvHandler(cvHandler);
        when(decoderDefinition.getCVValue(anyString())).thenReturn(cvValue);
        when(decoderDefinition.getCvDefinitions()).thenReturn(cvDefinitions);
        when(cvDefinition.getNumber()).thenReturn("1");
        when(cvDefinition.getValues()).thenReturn(cvValues);
        when(valueTypeFactory.getInstance(any(CVValue.class), any(CVHandler.class))).thenReturn(valueType);
        when(valueType.getValue()).thenReturn(1);
    }

    @Test
    public void testReadNamedCV() throws DefinitionException {
        assertEquals(new Integer(1), definitionReader.readValue("NamedCV"));
    }

    @Test
    public void testReadAllValues() throws DefinitionException {
        when(decoderDefinition.getCVValues()).thenReturn(cvValues);
        assertNotNull(definitionReader.readAllValues());
    }

    @Test
    public void testDecoderDefFile() throws DefinitionException {
        definitionReader.setDecoderFile("/10-10.xml");
        verify(decoderDefinition, times(1)).setDecoderDefFile(anyString());
    }

    @Test
    public void testBuildCVs() throws DefinitionException {
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        final DecoderSetting decoderSetting = new DecoderSetting();

        decoderSettings.add(decoderSetting);
        assertNotNull(definitionReader.buildCVs(decoderSettings));
    }

}
