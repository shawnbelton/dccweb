package uk.co.redkiteweb.dccweb.webapp.data.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;
import uk.co.redkiteweb.dccweb.webapp.data.CabFunction;
import uk.co.redkiteweb.dccweb.webapp.data.CabFunctionComparator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
            cab.setTrain(train);
        } else {
            cab.setTrain(train);
            cab.setCabFunctions(createSetCabFunctions(train));
            cab.setDirection("UP");
            cab.setSpeed(0);
            cab.setSteps("128");
            putCab(cab);
        }
        return cab;
    }

    private static Set<CabFunction> createSetCabFunctions(final Train train) {
        final Set<CabFunction> cabFunctions = new TreeSet<CabFunction>(new CabFunctionComparator());
        if (train.getDecoder()!=null && train.getDecoder().getDecoderFunctions()!=null) {
            for(DecoderFunction decoderFunction : train.getDecoder().getDecoderFunctions()) {
                cabFunctions.add(createCabFunction(decoderFunction));
            }
        }
        return cabFunctions;
    }

    private static CabFunction createCabFunction(final DecoderFunction decoderFunction) {
        final CabFunction cabFunction = new CabFunction();
        cabFunction.setNumber(decoderFunction.getNumber());
        cabFunction.setName(decoderFunction.getName());
        cabFunction.setState(Boolean.FALSE);
        return cabFunction;
    }
}
