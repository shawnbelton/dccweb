package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;

import java.util.List;

/**
 * Created by shawn on 17/06/16.
 */
@RestController
@RequestMapping("/api/interface")
public class InterfaceInfo {

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @GetMapping(value = "/status")
    public @ResponseBody DccInterfaceStatus getStatus() {
        return dccInterface.getInterfaceStatus();
    }

    @GetMapping(value="/all")
    public List<uk.co.redkiteweb.dccweb.dccinterface.data.InterfaceInfo> getInterfaces() {
        return dccInterface.getInterfaces();
    }

}
