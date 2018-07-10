package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.Macro;

public class AccessoryUpdateEvent implements RunMacroEvent, SendEvent {

    private final AccessoryDecoder accessoryDecoder;

    public AccessoryUpdateEvent(final AccessoryDecoder accessoryDecoder) {
        this.accessoryDecoder = accessoryDecoder;
    }

    @Override
    public Macro getMacro() {
        return accessoryDecoder.getMacro();
    }

    @Override
    public String getUrl() {
        return "/accessory";
    }

    @Override
    public Object sendObject() {
        return accessoryDecoder;
    }
}
