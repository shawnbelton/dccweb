package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.services.RelayControllerService;

/**
 * Created by shawn on 31/05/17.
 */
@RestController
@RequestMapping("/api/relay-controller")
public class RelayControllers {

    private RelayControllerService relayControllerService;

    @Autowired
    public void setRelayControllerService(final RelayControllerService relayControllerService) {
        this.relayControllerService = relayControllerService;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Integer updateController(@RequestBody final RelayController relayController) {
        return relayControllerService.updateController(relayController);
    }


}
