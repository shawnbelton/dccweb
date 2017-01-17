package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.Cab;

/**
 * Created by shawn on 17/01/17.
 */
@Service
public class AsyncCabService {

    private CabService cabService;

    @Autowired
    public void setCabService(final CabService cabService) {
        this.cabService = cabService;
    }

    @Async
    public void updateCab(final Cab cab) {
        cabService.updateCab(cab);
    }

    @Async
    public void updateCabFunctions(final Cab cab) {
        cabService.updateCabFunctions(cab);
    }


}
