package uk.co.redkiteweb.dccweb.data.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.data.CabFunctionComparator;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;

import java.util.*;

/**
 * Created by shawn on 12/09/16.
 */
@Component
public class CabStore {

    private DecoderFunctionRepository decoderFunctionRepository;
    private LocoRepository locoRepository;
    private final Map<Integer, Cab> cabStore;

    public CabStore() {
        cabStore = new HashMap<Integer, Cab>();
    }

    @Autowired
    public void setDecoderFunctionRepository(final DecoderFunctionRepository decoderFunctionRepository) {
        this.decoderFunctionRepository = decoderFunctionRepository;
    }

    @Autowired
    public void setLocoRepository(final LocoRepository locoRepository) {
        this.locoRepository = locoRepository;
    }

    public void putCab(final Cab cab) {
        cabStore.put(cab.getLoco().getLocoId(), cab);
    }

    public Cab getCab(final Integer locoId) {
        return getCab(locoRepository.findOne(locoId));
    }

    public Cab getCab(final Loco loco) {
        final Loco reloadLoco = locoRepository.findOne(loco.getLocoId());
        Cab cab = new Cab();
        if (cabStore.containsKey(loco.getLocoId())) {
            cab = cabStore.get(loco.getLocoId());
            cab.setLoco(reloadLoco);
            buildSetCabFunctions(cab);
        } else {
            cab.setLoco(reloadLoco);
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
        if (cab.getLoco().getDecoder()!=null) {
            for(DecoderFunction decoderFunction : getDecoderFunctions(cab.getLoco().getDecoder())) {
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
