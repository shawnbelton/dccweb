package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

/**
 * Created by shawn on 03/03/17.
 */
@Service
public class AccessoryService {

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Async
    public void operateService(final AccessoryOperation accessoryOperation) {

    }

}
