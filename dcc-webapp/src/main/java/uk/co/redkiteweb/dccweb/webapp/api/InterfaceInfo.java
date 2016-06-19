package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;

/**
 * Created by shawn on 17/06/16.
 */
@RestController
public class InterfaceInfo {

    private DccInterfaceFactory dccInterfaceFactory;

    @Autowired
    public void setDccInterfaceFactory(final DccInterfaceFactory dccInterfaceFactory) {
        this.dccInterfaceFactory = dccInterfaceFactory;
    }

    @RequestMapping("/interface/status")
    public DccInterfaceStatus getStatus() {
        return dccInterfaceFactory.getInstance().getInterfaceStatus();
    }

}
