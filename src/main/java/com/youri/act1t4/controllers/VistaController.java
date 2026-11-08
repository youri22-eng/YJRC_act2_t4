package com.youri.act1t4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    // Endpoint 4: muestra una página HTML con datos enviados desde el controlador
    @GetMapping("/bienvenida")
    public String bienvenida(Model model) {
        model.addAttribute("titulo", "Servidor en línea");
        model.addAttribute("autor", "Youri Ruiz Chavez");
        model.addAttribute("proyecto", "act1t4");
        return "bienvenida";
    }
}