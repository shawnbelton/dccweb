package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.services.AccessoryService;

import java.util.Collection;

/**
 * Created by shawn on 22/02/17.
 */
@RestController
@RequestMapping("/api/accessory/decoder")
public class AccessoryDecoders {

    private AccessoryService accessoryService;

    @Autowired
    public void setAccessoryService(final AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @GetMapping(value = "/type/all")
    public @ResponseBody Collection<AccessoryDecoderType> allAccessoryDecoderTypes() {
        return accessoryService.getAccessoryDecoderTypes();
    }

    @GetMapping(value = "/all")
    public @ResponseBody Collection<AccessoryDecoder> allAccessoryDecoders() {
        return accessoryService.getAccessoryDecoders();
    }

    @PostMapping(value = "/save")
    public @ResponseBody Collection<AccessoryDecoder> saveAccessoryDecoder(@RequestBody final AccessoryDecoder accessoryDecoder){
        return accessoryService.saveAccessoryDecoder(accessoryDecoder);
    }

    @PostMapping(value = "/operate")
    public @ResponseBody Collection<AccessoryDecoder> operateAccessory(@RequestBody final AccessoryOperation accessoryOperation) {
        accessoryService.operateService(accessoryOperation);
        return accessoryService.getAccessoryDecoders();
    }
}
