package uk.co.redkiteweb.dccweb.data.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.data.CabFunctionComparator;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;

import java.util.*;

/**
 * Created by shawn on 12/09/16.
 */
@Component
public class CabStore {

    private DecoderRepository decoderRepository;
    private DecoderFunctionRepository decoderFunctionRepository;
    private TrainRepository trainRepository;
    private final Map<Integer, Cab> cabStore;

    public CabStore() {
        cabStore = new HashMap<Integer, Cab>();
    }

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
    }

    @Autowired
    public void setDecoderFunctionRepository(final DecoderFunctionRepository decoderFunctionRepository) {
        this.decoderFunctionRepository = decoderFunctionRepository;
    }

    @Autowired
    public void setTrainRepository(final TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public void putCab(final Cab cab) {
        cabStore.put(cab.getTrain().getTrainId(), cab);
    }

    public Cab getCab(final Integer trainId) {
        return getCab(trainRepository.findOne(trainId));
    }

    public Cab getCab(final Train train) {
        Cab cab = new Cab();
        if (cabStore.containsKey(train.getTrainId())) {
            cab = cabStore.get(train.getTrainId());
            cab.setTrain(train);
            buildSetCabFunctions(cab);
        } else {
            cab.setTrain(train);
            buildSetCabFunctions(cab);
            cab.setDirection("UP");
            cab.setSpeed(0);
            cab.setSteps("128");
            putCab(cab);
        }
        return cab;
    }

    private void buildSetCabFunctions(final Cab cab) {
        final Set<CabFunction> newCabFunctions = new TreeSet<CabFunction>(new CabFunctionComparator());
        if (cab.getTrain().getDecoder()!=null && cab.getTrain().getDecoder().getDecoderFunctions()!=null) {
            for(DecoderFunction decoderFunction : getDecoderFunctions(cab.getTrain().getDecoder())) {
                newCabFunctions.add(getCabFunction(decoderFunction, cab.getCabFunctions()));
            }
        }
        cab.setCabFunctions(newCabFunctions);
    }

    private List<DecoderFunction> getDecoderFunctions(final Decoder decoder) {
        return this.decoderFunctionRepository.findAllByDecoderId(decoder.getDecoderId());
    }

    private static CabFunction getCabFunction(final DecoderFunction decoderFunction, Set<CabFunction> cabFunctions) {
        CabFunction cabFunction = fetchCabFunction(cabFunctions, decoderFunction.getNumber());
        if (cabFunction == null) {
            cabFunction = getCabFunction(decoderFunction);
        }
        return cabFunction;
    }

    private static CabFunction fetchCabFunction(Set<CabFunction> cabFunctions, final Integer functionNumber) {
        CabFunction foundFunction = null;
        if (cabFunctions!=null) {
            for (CabFunction cabFunction : cabFunctions) {
                if (cabFunction.getNumber().equals(functionNumber)) {
                    foundFunction = cabFunction;
                }
            }
        }
        return foundFunction;
    }

    private static CabFunction getCabFunction(final DecoderFunction decoderFunction) {
        final CabFunction cabFunction = new CabFunction();
        cabFunction.setNumber(decoderFunction.getNumber());
        cabFunction.setName(decoderFunction.getName());
        cabFunction.setState(Boolean.FALSE);
        return cabFunction;
    }
}
