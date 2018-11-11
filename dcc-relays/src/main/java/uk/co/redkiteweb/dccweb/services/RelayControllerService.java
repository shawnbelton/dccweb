package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.data.repositories.RelayControllerRepository;
import uk.co.redkiteweb.dccweb.events.RelayUpdateEvent;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.List;

/**
 * Created by shawn on 31/05/17.
 */
@Service
public class RelayControllerService {

    private RelayControllerRepository relayControllerRepository;
    private LogStore logStore;
    private EventBus eventBus;

    @Autowired
    public void setRelayControllerRepository(final RelayControllerRepository relayControllerRepository) {
        this.relayControllerRepository = relayControllerRepository;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Autowired
    public void setEventBus(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public RelayController updateController(final RelayController relayController) {
        RelayController controller = relayControllerRepository.findById(relayController.getControllerId()).orElse(null);
        if (controller == null) {
            controller = new RelayController();
            controller.setControllerId(relayController.getControllerId());
            controller.setControllerName(relayController.getControllerId());
            controller.setValue(0);
        }
        controller.setIpAddress(relayController.getIpAddress());
        relayControllerRepository.save(controller);
        logStore.log("info", String.format("Relay controller %s updated.", controller.getControllerName()));
        notify(controller);
        return controller;
    }

    public List<RelayController> save(final RelayController relayController) {
        relayControllerRepository.save(relayController);
        logStore.log("info", String.format("Relay controller %s saved.", relayController.getControllerName()));
        notify(relayController);
        return getAllControllers();
    }

    public List<RelayController> getAllControllers() {
        return (List<RelayController>)relayControllerRepository.findAll();
    }

    public List<RelayController> updateValue(final RelayController relayController) {
        final RelayController updateValue = relayControllerRepository.findById(relayController.getControllerId()).orElse(null);
        if (updateValue!=null) {
            updateValue.setValue(relayController.getValue());
            relayControllerRepository.save(updateValue);
            logStore.log("info", String.format("Relay Controller %s relays updated.", updateValue.getControllerName()));
            notify(relayController);
        }
        return getAllControllers();
    }

    public void setRelay(final String controllerId, final int number) {
        final RelayController relayController = relayControllerRepository.findById(controllerId).orElse(null);
        if (relayController!=null) {
            Integer relayValue = relayController.getValue();
            relayValue |= (int)Math.pow(2f,(double)(number - 1));
            relayController.setValue(relayValue);
            relayControllerRepository.save(relayController);
            logStore.log("info", String.format("Relay %d set on %s", number, relayController.getControllerName()));
            notify(relayController);
        }
    }

    public void unsetRelay(final String controllerId, final int number) {
        final RelayController relayController = relayControllerRepository.findById(controllerId).orElse(null);
        if (relayController!=null) {
            Integer relayValue = relayController.getValue();
            relayValue &= (0xff ^ (int)Math.pow(2f,(double)(number - 1)));
            relayController.setValue(relayValue);
            relayControllerRepository.save(relayController);
            logStore.log("info", String.format("Relay %d unset on %s", number, relayController.getControllerName()));
            notify(relayController);
        }
    }

    private void notify(final RelayController relayController) {
        eventBus.post(new RelayUpdateEvent(relayController));
    }

}
