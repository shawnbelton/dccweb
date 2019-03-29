package uk.co.redkiteweb.dccweb.decoders.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class ValueValueTypeTest {

    private ValueType value;
    private CVValue cvValue;

    @Before
    public void setup() {
        final CVHandler cvHandler = mock(CVHandler.class);
        when(cvHandler.readCV(anyInt())).thenReturn(1);
        cvValue = mock(CVValue.class);
        when(cvValue.getName()).thenReturn("Name");
        when(cvValue.getCvNumber()).thenReturn("1,2");
        when(cvValue.getId()).thenReturn("id1");
        value = new ValueValueType();
        value.setCVValue(cvValue);
        value.setCVReader(cvHandler);
    }

    @Test
    public void testGetValue() {
        assertEquals(new Integer(257), value.getValue());
    }

    @Test
    public void testGetWithMask() {
        when(cvValue.getMask()).thenReturn(255);
        assertEquals(new Integer(1), value.getValue());
    }

    @Test
    public void testGetSetting() {
        assertNotNull(value.getSetting());
    }

    @Test
    public void testCVValueNoMask() {
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        decoderSettings.add(createDecoderSetting("Flag", 1));
        decoderSettings.add(createDecoderSetting("Name", 1));
        assertEquals(new Integer(0), value.getCVValue(1, decoderSettings));
    }

    @Test
    public void testCVValueWithMask() {
        when(cvValue.getMask()).thenReturn(127);
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        decoderSettings.add(createDecoderSetting("Flag", 1));
        decoderSettings.add(createDecoderSetting("Name", 1));
        assertEquals(new Integer(129), value.getCVValue(2, decoderSettings));
    }

    private DecoderSetting createDecoderSetting(final String name, final Integer value) {
        final DecoderSetting decoderSetting = new DecoderSetting();
        decoderSetting.setName(name);
        decoderSetting.setNewValue(value);
        return decoderSetting;
    }
}
