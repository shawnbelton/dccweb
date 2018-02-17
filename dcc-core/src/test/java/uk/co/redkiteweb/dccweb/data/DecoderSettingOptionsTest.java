package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DecoderSettingOptionsTest {

    private DecoderSettingOption decoderSettingOption;

    @Before
    public void setup() {
        decoderSettingOption = new DecoderSettingOption();
        decoderSettingOption.setId("Id");
        decoderSettingOption.setOption("Option");
        decoderSettingOption.setValue(1);
    }

    @Test
    public void testId() {
        assertEquals("Id", decoderSettingOption.getId());
    }

    @Test
    public void testOption() {
        assertEquals("Option", decoderSettingOption.getOption());
    }

    @Test
    public void testValue() {
        assertEquals(new Integer(1), decoderSettingOption.getValue());
    }
}
