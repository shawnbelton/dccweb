package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.model.RelayController;

public class RelayUpdateEvent implements SendEvent {

    private final RelayController relayController;

    public RelayUpdateEvent(final RelayController relayController) {
        this.relayController = relayController;
    }

    @Override
    public String getUrl() {
        return "/relays";
    }

    @Override
    public Object sendObject() {
        return relayController;
    }

    public String getUpdateUrl() {
        return String.format("http://%s/setrelay/%d", relayController.getIpAddress(), relayController.getValue());
    }
}
