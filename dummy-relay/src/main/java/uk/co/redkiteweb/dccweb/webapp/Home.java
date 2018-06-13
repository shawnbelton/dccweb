package uk.co.redkiteweb.dccweb.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shawn on 04/07/17.
 */
@Controller
public class Home {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces = "text/html")
    public String getHome() {
        return  "../static/index";
    }

}
