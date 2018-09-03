package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;

public class AccessoryUpdateEvent implements RunMacroEvent, SendEvent {

    private final AccessoryDecoder accessoryDecoder;

    public AccessoryUpdateEvent(final AccessoryDecoder accessoryDecoder) {
        this.accessoryDecoder = accessoryDecoder;
    }

    @Override
    public Integer getMacroId() {
        return accessoryDecoder.getMacroId();
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
