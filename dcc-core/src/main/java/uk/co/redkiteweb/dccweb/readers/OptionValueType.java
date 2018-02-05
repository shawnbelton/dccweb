package uk.co.redkiteweb.dccweb.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
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
            if (optionNode instanceof Element){
                decoderSettingOptions.add(createDecoderSettingOption(optionNode, getId()));
            }
        }
        return decoderSettingOptions;
    }

    private static DecoderSettingOption createDecoderSettingOption(final Node optionNode, final String id) {
        final DecoderSettingOption decoderSettingOption = new DecoderSettingOption();
        decoderSettingOption.setValue(Integer.parseInt(optionNode.getAttributes().getNamedItem("value").getTextContent()));
        decoderSettingOption.setOption(optionNode.getTextContent());
        decoderSettingOption.setId(String.format("%s%d", id, decoderSettingOption.getValue()));
        return decoderSettingOption;
    }
}
