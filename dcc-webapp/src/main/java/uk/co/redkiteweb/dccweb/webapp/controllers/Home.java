package uk.co.redkiteweb.dccweb.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by shawn on 04/07/17.
 */
@Controller
public class Home {

    @GetMapping(value = {"/",
            "/engine-shed",
            "/decoders",
            "/decoders/read",
            "/decoders/settings",
            "/accessories",
            "/settings",
            "/macro-edit",
            "/performance",
            "/blocks",
            "/relays"}, produces = "text/html")
    public String getHome() {
        return  "../static/index";
    }

    @GetMapping(value = {
            "/decoders/{id}",
            "/accessories/{id}",
            "/macro-edit/{id}",
            "/blocks/{id}",
            "/relays/{id}"
    }, produces = "text/html")
    public String getWithId(@PathVariable final String id) {
        return "../static/index";
    }

}
