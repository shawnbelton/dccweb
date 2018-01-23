package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DecoderSettingTest {

    private DecoderSetting decoderSetting;

    @Before
    public void setup() {
        decoderSetting = new DecoderSetting();
        decoderSetting.setName("SettingName");
        decoderSetting.setType("SettingType");
        decoderSetting.setValue(1);
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
}
