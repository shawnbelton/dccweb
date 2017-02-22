package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;

import java.util.List;

/**
 * Created by shawn on 22/02/17.
 */
@RestController
public class AccessoryDecoders {

    private AccessoryDecoderRepository accessoryDecoderRepository;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;

    @Autowired
    public void setAccessoryDecoderRepository(final AccessoryDecoderRepository accessoryDecoderRepository) {
        this.accessoryDecoderRepository = accessoryDecoderRepository;
    }

    @Autowired
    public void setAccessoryDecoderTypeRepository(final AccessoryDecoderTypeRepository accessoryDecoderTypeRepository) {
        this.accessoryDecoderTypeRepository = accessoryDecoderTypeRepository;
    }

    @RequestMapping("/accessory/decoder/type/all")
    public @ResponseBody List<AccessoryDecoderType> allAccessoryDecoderTypes() {
        return (List<AccessoryDecoderType>)accessoryDecoderTypeRepository.findAll();
    }

    @RequestMapping("/accessory/decoder/all")
    public @ResponseBody List<AccessoryDecoder> allAccessoryDecoders() {
        return (List<AccessoryDecoder>)accessoryDecoderRepository.findAll();
    }
}
