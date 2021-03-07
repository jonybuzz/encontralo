package com.jonybuzz.encontralo.controller.rest;

import com.jonybuzz.encontralo.dto.SeleccionablesDto;
import com.jonybuzz.encontralo.service.SeleccionablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeleccionablesRestController {

    @Autowired
    SeleccionablesService seleccionablesService;

    @GetMapping("/api/seleccionables")
    public SeleccionablesDto obtenerSeleccionables() {
        return seleccionablesService.obtenerSeleccionables();
    }
}
