package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class DecoderSettingTest {

    private DecoderSetting decoderSetting;

    @Before
    public void setup() {
        decoderSetting = new DecoderSetting();
        decoderSetting.setName("SettingName");
        decoderSetting.setType("SettingType");
        decoderSetting.setValue(1);
        decoderSetting.setDecoderSettingOptions(new ArrayList<>());
    }

    @Test
    public void testName() {
        assertEquals("SettingName", decoderSetting.getName());
    }

    @Test
    public void testType() {
        assertEquals("SettingType", decoderSetting.getType());
    }

    @Test
    public void testValue() {
        assertEquals(new Integer(1), decoderSetting.getValue());
    }

    @Test
    public void testDecoderSettingOptions() {
        assertNotNull(decoderSetting.getDecoderSettingOptions());
    }
}
