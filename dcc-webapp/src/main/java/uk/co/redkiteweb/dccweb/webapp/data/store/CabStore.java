package uk.co.redkiteweb.dccweb.webapp.data.store;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 12/09/16.
 */
@Component
public class CabStore {

    private final Map<Integer, Cab> cabStore;

    public CabStore() {
        cabStore = new HashMap<Integer, Cab>();
    }

    public void putCab(final Cab cab) {
        cabStore.put(cab.getTrain().getTrainId(), cab);
    }

    public Cab getCab(final Train train) {
        Cab cab = new Cab();
        if (cabStore.containsKey(train.getTrainId())) {
            cab = cabStore.get(train.getTrainId());
        } else {
            cab.setTrain(train);
            cab.setDirection("UP");
            cab.setSpeed(0);
            putCab(cab);
        }
        return cab;
    }

}
