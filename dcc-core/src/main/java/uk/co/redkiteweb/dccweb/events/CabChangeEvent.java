package uk.co.redkiteweb.dccweb.events;

import uk.co.redkiteweb.dccweb.data.Cab;

public class CabChangeEvent implements SendEvent {

    private final Cab cab;

    public CabChangeEvent(final Cab cab) {
        this.cab = cab;
    }

    @Override
    public String getUrl() {
        return "/cab";
    }

    @Override
    public Object sendObject() {
        return cab;
    }
}
