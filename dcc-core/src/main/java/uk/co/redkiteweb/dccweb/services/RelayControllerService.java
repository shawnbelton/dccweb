package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.data.repositories.RelayControllerRepository;

/**
 * Created by shawn on 31/05/17.
 */
@Service
public class RelayControllerService {

    private RelayControllerRepository relayControllerRepository;

    @Autowired
    public void setRelayControllerRepository(final RelayControllerRepository relayControllerRepository) {
        this.relayControllerRepository = relayControllerRepository;
    }

    public Integer updateController(final RelayController relayController) {
        RelayController controller = relayControllerRepository.findOne(relayController.getControllerId());
        if (controller == null) {
            controller = new RelayController();
            controller.setControllerId(relayController.getControllerId());
            controller.setControllerName(relayController.getControllerId());
            controller.setValue(0);
        }
        controller.setIpAddress(relayController.getIpAddress());
        relayControllerRepository.save(controller);
        return controller.getValue();
    }

}
