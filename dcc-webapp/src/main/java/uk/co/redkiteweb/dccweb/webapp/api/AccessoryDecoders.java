package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;
import uk.co.redkiteweb.dccweb.services.AccessoryService;

import java.util.List;

/**
 * Created by shawn on 22/02/17.
 */
@RestController
@RequestMapping("/api/accessory/decoder")
public class AccessoryDecoders {

    private AccessoryDecoderRepository accessoryDecoderRepository;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;
    private AccessoryService accessoryService;

    @Autowired
    public void setAccessoryDecoderRepository(final AccessoryDecoderRepository accessoryDecoderRepository) {
        this.accessoryDecoderRepository = accessoryDecoderRepository;
    }

    @Autowired
    public void setAccessoryDecoderTypeRepository(final AccessoryDecoderTypeRepository accessoryDecoderTypeRepository) {
        this.accessoryDecoderTypeRepository = accessoryDecoderTypeRepository;
    }

    @Autowired
    public void setAccessoryService(final AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @RequestMapping(value = "/type/all", method = RequestMethod.GET)
    public @ResponseBody List<AccessoryDecoderType> allAccessoryDecoderTypes() {
        return (List<AccessoryDecoderType>)accessoryDecoderTypeRepository.findAll();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<AccessoryDecoder> allAccessoryDecoders() {
        return (List<AccessoryDecoder>)accessoryDecoderRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody List<AccessoryDecoder> saveAccessoryDecoder(@RequestBody final AccessoryDecoder accessoryDecoder){
        accessoryDecoderRepository.save(accessoryDecoder);
        return allAccessoryDecoders();
    }

    @RequestMapping(value = "/operate", method = RequestMethod.POST)
    public @ResponseBody Boolean operateAccessory(@RequestBody final AccessoryOperation accessoryOperation) {
        accessoryService.operateService(accessoryOperation);
        return Boolean.TRUE;
    }
}
