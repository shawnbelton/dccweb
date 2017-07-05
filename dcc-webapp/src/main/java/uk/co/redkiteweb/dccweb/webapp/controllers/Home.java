package uk.co.redkiteweb.dccweb.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shawn on 04/07/17.
 */
@Controller
public class Home {

    @RequestMapping(value = {"/", "/engine-shed","/decoders", "/accessories","/settings",
    "/macro-edit", "/performance", "/blocks", "/relays"}, method = RequestMethod.GET, produces = "text/html")
    public String getHome() {
        return  "../static/index";
    }

}
