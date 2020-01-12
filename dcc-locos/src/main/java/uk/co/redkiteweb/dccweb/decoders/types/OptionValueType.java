package uk.co.redkiteweb.dccweb.decoders.types;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.DecoderSettingOption;
import uk.co.redkiteweb.dccweb.decoders.model.CVValueOption;

import java.util.ArrayList;
import java.util.List;

@Component("optionValueType")
@Scope("prototype")
public class OptionValueType extends FlagValueType {

    @Override
    protected String getType() {
        return "option";
    }

    @Override
    protected List<DecoderSettingOption> getOptions() {
        final List<DecoderSettingOption> decoderSettingOptions = new ArrayList<>();
        for (final CVValueOption cvOption : getCVValue().getOptions()) {
            decoderSettingOptions.add(createDecoderSettingOption(cvOption, getId()));
        }
        return decoderSettingOptions;
    }

    private static DecoderSettingOption createDecoderSettingOption(final CVValueOption option, final String id) {
        final DecoderSettingOption decoderSettingOption = new DecoderSettingOption();
        decoderSettingOption.setValue(option.getValue());
        decoderSettingOption.setOption(option.getName());
        decoderSettingOption.setId(String.format("%s%d", id, decoderSettingOption.getValue()));
        return decoderSettingOption;
    }
}
