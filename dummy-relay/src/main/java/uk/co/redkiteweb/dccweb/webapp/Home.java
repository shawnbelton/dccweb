package uk.co.redkiteweb.dccweb.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by shawn on 04/07/17.
 */
@Controller
public class Home {

    @GetMapping(value = {"/"}, produces = "text/html")
    public String getHome() {
        return  "../static/index";
    }

}
