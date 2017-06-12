package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.data.repositories.RelayControllerRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.List;

/**
 * Created by shawn on 31/05/17.
 */
@Service
public class RelayControllerService {

    private RelayControllerRepository relayControllerRepository;
    private LogStore logStore;
    private NotificationService notificationService;
    private AsyncWebService asyncWebService;

    @Autowired
    public void setRelayControllerRepository(final RelayControllerRepository relayControllerRepository) {
        this.relayControllerRepository = relayControllerRepository;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setAsyncWebService(final AsyncWebService asyncWebService) {
        this.asyncWebService = asyncWebService;
    }

    public RelayController updateController(final RelayController relayController) {
        RelayController controller = relayControllerRepository.findOne(relayController.getControllerId());
        if (controller == null) {
            controller = new RelayController();
            controller.setControllerId(relayController.getControllerId());
            controller.setControllerName(relayController.getControllerId());
            controller.setValue(0);
        }
        controller.setIpAddress(relayController.getIpAddress());
        relayControllerRepository.save(controller);
        logStore.log("info", String.format("Relay controller %s updated.", controller.getControllerName()));
        return controller;
    }

    public List<RelayController> save(final RelayController relayController) {
        relayControllerRepository.save(relayController);
        logStore.log("info", String.format("Relay controller %s saved.", relayController.getControllerName()));
        return getAllControllers();
    }

    public List<RelayController> getAllControllers() {
        return (List<RelayController>)relayControllerRepository.findAll();
    }

    public List<RelayController> updateValue(final RelayController relayController) {
        final RelayController updateValue = relayControllerRepository.findOne(relayController.getControllerId());
        updateValue.setValue(relayController.getValue());
        relayControllerRepository.save(updateValue);
        logStore.log("info", String.format("Relay Controller %s relays updated.", updateValue.getControllerName()));
        notify(relayController);
        return getAllControllers();
    }

    private void updateRelay(final RelayController relayController) {
        asyncWebService.asyncWebCall(String.format("http://%s/setrelay/%d", relayController.getIpAddress(), relayController.getValue()));
    }

    public void setRelay(final String controllerId, final int number) {
        final RelayController relayController = relayControllerRepository.findOne(controllerId);
        if (relayController!=null) {
            Integer relayValue = relayController.getValue();
            relayValue |= (int)Math.pow(2,number - 1);
            relayController.setValue(relayValue);
            relayControllerRepository.save(relayController);
            logStore.log("info", String.format("Relay %d set on %s", number, relayController.getControllerName()));
            notify(relayController);
        }
    }

    public void unsetRelay(final String controllerId, final int number) {
        final RelayController relayController = relayControllerRepository.findOne(controllerId);
        if (relayController!=null) {
            Integer relayValue = relayController.getValue();
            relayValue &= (0xff ^ (int)Math.pow(2,number - 1));
            relayController.setValue(relayValue);
            relayControllerRepository.save(relayController);
            logStore.log("info", String.format("Relay %d unset on %s", number, relayController.getControllerName()));
            notify(relayController);
        }
    }

    private void notify(final RelayController relayController) {
        notificationService.createNotification("RELAY", "");
        updateRelay(relayController);
    }
}
