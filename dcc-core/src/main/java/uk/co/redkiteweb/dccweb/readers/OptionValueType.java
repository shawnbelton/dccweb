package uk.co.redkiteweb.dccweb.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.co.redkiteweb.dccweb.data.DecoderSettingOption;

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
        final NodeList optionNodes = getValueNode().getChildNodes();
        for(int index = 0; index < optionNodes.getLength(); index++) {
            final Node optionNode = optionNodes.item(index);
            decoderSettingOptions.add(createDecoderSettingOption(optionNode));
        }
        return decoderSettingOptions;
    }

    private DecoderSettingOption createDecoderSettingOption(final Node optionNode) {
        final DecoderSettingOption decoderSettingOption = new DecoderSettingOption();
        decoderSettingOption.setValue(Integer.parseInt(optionNode.getAttributes().getNamedItem("value").getTextContent()));
        decoderSettingOption.setOption(optionNode.getAttributes().getNamedItem("option").getTextContent());
        return decoderSettingOption;
    }
}
