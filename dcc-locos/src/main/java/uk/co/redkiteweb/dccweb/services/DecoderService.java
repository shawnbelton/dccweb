package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.LinkedMacro;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LinkedMacroRepository;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandlerFactory;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.Collection;

@Service
public class DecoderService {

    private DecoderHandlerFactory decoderHandlerFactory;
    private DecoderRepository decoderRepository;
    private DecoderFunctionRepository decoderFunctionRepository;
    private LinkedMacroRepository linkedMacroRepository;
    private LogStore logStore;

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
    }

    @Autowired
    public void setDecoderFunctionRepository(final DecoderFunctionRepository decoderFunctionRepository) {
        this.decoderFunctionRepository = decoderFunctionRepository;
    }

    @Autowired
    public void setLinkedMacroRepository(final LinkedMacroRepository linkedMacroRepository) {
        this.linkedMacroRepository = linkedMacroRepository;
    }

    @Autowired
    public void setDecoderHandlerFactory(final DecoderHandlerFactory decoderHandlerFactory) {
        this.decoderHandlerFactory = decoderHandlerFactory;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    public Decoder readDecoder() {
        return decoderHandlerFactory.createInstance().readDecoderOnProgram();
    }

    public Collection<DecoderSetting> readFull() {
        return decoderHandlerFactory.createInstance().readFullOnProgram();
    }

    public Boolean writeCVs(final Collection<DecoderSetting> decoderSettings) {
        decoderHandlerFactory.createInstance().writeSettingsToDecoder(decoderSettings);
        return Boolean.TRUE;
    }

    public Collection<Decoder> allDecoders() {
        return (Collection<Decoder>)decoderRepository.findAll();
    }

    public Decoder getById(final Integer decoderId) {
        return decoderRepository.findById(decoderId).orElse(null);
    }

    public Decoder addFunction(final DecoderFunction decoderFunction) {
        decoderFunctionRepository.save(decoderFunction);
        logStore.log("info", String.format("Decoder function %s with number %d added.", decoderFunction.getName(), decoderFunction.getNumber()));
        return getById(decoderFunction.getDecoderId());
    }

    public Decoder deleteFunction(final DecoderFunction decoderFunction) {
        decoderFunctionRepository.delete(decoderFunction);
        logStore.log("info", String.format("Decoder function %s with number %d removed.", decoderFunction.getName(), decoderFunction.getNumber()));
        return getById(decoderFunction.getDecoderId());
    }

    public Decoder linkMacro(final LinkedMacro linkedMacro) {
        linkedMacroRepository.save(linkedMacro);
        logStore.log("info", String.format("Macro %d linked.", linkedMacro.getMacroId()));
        return getById(linkedMacro.getDecoderId());
    }

    public Decoder unlinkMacro(final LinkedMacro linkedMacro) {
        linkedMacroRepository.delete(linkedMacro);
        logStore.log("info", String.format("Macro %d unlinked.", linkedMacro.getMacroId()));
        return getById(linkedMacro.getDecoderId());
    }

}
