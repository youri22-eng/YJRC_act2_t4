package com.youri.act1t4.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youri.act1t4.models.dto.ProductoDTO;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    // Recibe un JSON, lo convierte en ProductoDTO y responde con JSON
    @PostMapping
    public Map<String, Object> crear(@RequestBody ProductoDTO producto) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "Producto recibido correctamente");
        respuesta.put("producto", producto);
        return respuesta;
    }
}