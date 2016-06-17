package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shawn on 17/06/16.
 */
public abstract class AbstractDccInterface implements DccInterface {

    private DccInterfaceStatus dccInterfaceStatus;

    @Autowired
    public void setDccInterfaceStatus(final DccInterfaceStatus dccInterfaceStatus) {
        this.dccInterfaceStatus = dccInterfaceStatus;
    }

    @Override
    public DccInterfaceStatus.Status getInterfaceStatus() {
        return dccInterfaceStatus.getStatus();
    }

    @Override
    public void initialise() {
        dccInterfaceStatus.setDisconnected();
    }

    @Override
    public abstract void checkInterface();
}
