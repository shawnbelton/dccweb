package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.services.RelayControllerService;

import java.util.List;

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

    @PostMapping(value = "/update")
    public @ResponseBody Integer updateController(@RequestBody final RelayController relayController) {
        return relayControllerService.updateController(relayController).getValue();
    }

    @GetMapping(value = "/all")
    public @ResponseBody List<RelayController> getAllControllers() {
        return relayControllerService.getAllControllers();
    }

    @PostMapping(value = "/save")
    public @ResponseBody List<RelayController> save(@RequestBody final RelayController relayController) {
        return relayControllerService.save(relayController);
    }

    @PostMapping(value = "/update-value")
    public @ResponseBody List<RelayController> updateValue(@RequestBody final RelayController relayController) {
        return relayControllerService.updateValue(relayController);
    }
}
