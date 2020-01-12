package uk.co.redkiteweb.dccweb.decoders.types;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;
import uk.co.redkiteweb.dccweb.decoders.model.CVValueOption;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class OptionTypeValueTest {

    private OptionValueType optionValueType;

    @Before
    public void setup() {
        final CVHandler cvHandler = mock(CVHandler.class);
        when(cvHandler.readCV(anyInt())).thenReturn(1);
        final CVValue cvValue = createCVValue();
        optionValueType = new OptionValueType();
        optionValueType.setCVValue(cvValue);
        optionValueType.setCVReader(cvHandler);
    }

    @Test
    public void testType() {
        assertEquals("option", optionValueType.getType());
    }

    @Test
    public void testDecoderSetting() {
        assertFalse(optionValueType.getSetting().getDecoderSettingOptions().isEmpty());
    }

    private CVValue createCVValue() {
        final CVValue cvValue = new CVValue();
        cvValue.setId("id1");
        cvValue.setName("name");
        cvValue.setCvNumber("1");
        cvValue.setBit(ImmutableList.of(0));
        cvValue.setOptions(createOptions());
        return cvValue;
    }

    private Collection<CVValueOption> createOptions() {
        final Collection<CVValueOption> options = new HashSet<>();
        options.add(createOption(0,"Off"));
        options.add(createOption(1, "On"));
        return options;
    }

    private CVValueOption createOption(final Integer value, final String name) {
        final CVValueOption option = new CVValueOption();
        option.setValue(value);
        option.setName(name);
        return option;
    }
}
