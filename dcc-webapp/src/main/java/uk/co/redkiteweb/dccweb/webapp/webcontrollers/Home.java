package uk.co.redkiteweb.dccweb.webapp.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shawn on 15/06/16.
 */
@Controller
public class Home {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/decoders/")
    public String decoderHome() {
        return "decoder/home";
    }

}
