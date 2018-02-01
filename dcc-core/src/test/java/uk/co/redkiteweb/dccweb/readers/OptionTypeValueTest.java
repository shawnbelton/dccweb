package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class OptionTypeValueTest {

    private OptionValueType optionValueType;

    @Before
    public void setup() {
        optionValueType = new OptionValueType();
    }

    @Test
    public void testType() {
        assertEquals("option", optionValueType.getType());
    }

    @Test
    public void testDecoderSetting() {
        assertFalse(optionValueType.getSetting().getDecoderSettingOptions().isEmpty());
    }

}
