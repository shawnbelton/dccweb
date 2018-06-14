package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.store.RelayControllerStore;

@RestController
public class DummyController {

    private RelayControllerStore relayControllerStore;

    @Autowired
    public void setRelayControllerStore(final RelayControllerStore relayControllerStore) {
        this.relayControllerStore = relayControllerStore;
    }

    @RequestMapping(value = "/setrelay/{value}", method = RequestMethod.PUT)
    public void setRelayValue(@PathVariable final Integer value) {
        relayControllerStore.setValue(value);
    }

}
