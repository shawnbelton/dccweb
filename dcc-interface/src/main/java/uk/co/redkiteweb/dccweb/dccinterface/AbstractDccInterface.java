package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shawn on 17/06/16.
 */
public class AbstractDccInterface implements DccInterface {

    private DccInterfaceStatus dccInterfaceStatus;

    @Autowired
    public void setDccInterfaceStatus(final DccInterfaceStatus dccInterfaceStatus) {
        this.dccInterfaceStatus = dccInterfaceStatus;
    }

    @Override
    public void initialise() {
        dccInterfaceStatus.setDisconnected();
    }
}
