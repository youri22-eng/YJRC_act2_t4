
package com.youri.act1t4.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class infoRestController {

    // Endpoint 1: regresa el nombre del proyecto y del autor (texto)
    @GetMapping("/proyecto")
    public String proyecto() {
        return "Proyecto: act1t4 - Autor: Youri Ruiz Chavez";
    }

    // Endpoint 2: regresa una lista simulada (se convierte a JSON)
    @GetMapping("/materias")
    public List<String> materias() {
        return List.of("Programación Avanzada", "Bases de Datos", "Redes", "Ingeniería de Software");
    }

    // Endpoint 3: regresa un objeto simple con varios datos (se convierte a JSON)
    @GetMapping("/estado")
    public Map<String, Object> estado() {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("proyecto", "act1t4");
        respuesta.put("autor", "Youri Ruiz Chavez");
        respuesta.put("puerto", 8081);
        respuesta.put("activo", true);
        return respuesta;
    }
}