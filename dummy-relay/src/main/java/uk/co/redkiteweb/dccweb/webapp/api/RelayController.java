package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.model.RegisterDetails;
import uk.co.redkiteweb.dccweb.service.RelayService;

@RestController
@RequestMapping("/api/relay")
public class RelayController {

    private RelayService relayService;

    @Autowired
    public void setRelayService(final RelayService relayService) {
        this.relayService = relayService;
    }

    @PutMapping(value = "/register")
    public Boolean registerController(@RequestBody final RegisterDetails registerDetails) {
        relayService.registerWithServer(registerDetails);
        return Boolean.TRUE;
    }

}
