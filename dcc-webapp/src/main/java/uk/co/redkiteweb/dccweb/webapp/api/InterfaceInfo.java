package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
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
public class InterfaceInfo {

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @RequestMapping("/interface/status")
    public @ResponseBody DccInterfaceStatus getStatus() {
        return dccInterface.getInterfaceStatus();
    }

    @RequestMapping("/interfaces")
    public List<uk.co.redkiteweb.dccweb.dccinterface.data.InterfaceInfo> getInterfaces() {
        return dccInterface.getInterfaces();
    }

}
