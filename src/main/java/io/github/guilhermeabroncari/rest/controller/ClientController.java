package io.github.guilhermeabroncari.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clients")
public class ClientController {
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String helloClient(@PathVariable("name") String clientName) {
    return String.format("Hello %s", clientName);
    }
}
