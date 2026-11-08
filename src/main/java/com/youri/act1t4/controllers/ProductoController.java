package com.youri.act1t4.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youri.act1t4.models.dto.ProductoDTO;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    // Video 17: @Value lee las propiedades de application.properties
    @Value("${config.autor}")
    private String autor;

    @Value("${config.proyecto}")
    private String proyecto;

    // Lista simulada de productos (en memoria)
    private final List<ProductoDTO> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new ProductoDTO(1, "Laptop", 12500.00, "Tecnologia"));
        productos.add(new ProductoDTO(2, "Mouse", 250.50, "Tecnologia"));
        productos.add(new ProductoDTO(3, "Refresco", 25.00, "Bebidas"));
        productos.add(new ProductoDTO(4, "Cafe", 45.50, "Bebidas"));
        productos.add(new ProductoDTO(5, "Cuaderno", 60.00, "Papeleria"));
        productos.add(new ProductoDTO(6, "Lapicero", 12.00, "Papeleria"));
    }

    // Video 13: @ModelAttribute a nivel de metodo comparte estos datos
    // con TODAS las vistas de este controlador
    @ModelAttribute("autor")
    public String agregarAutor() {
        return autor;
    }

    @ModelAttribute("proyecto")
    public String agregarProyecto() {
        return proyecto;
    }

    // Vista 1: lista de productos (Videos 9 y 12)
    // URL: /productos
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("titulo", "Lista de productos");
        model.addAttribute("productos", productos);
        return "lista";
    }

    // Video 14: @RequestParam recibe el valor que viene en la URL
    // URL: /productos/filtrar?categoria=Bebidas
    @GetMapping("/filtrar")
    public String filtrar(
            @RequestParam(name = "categoria", required = false, defaultValue = "") String categoria,
            Model model) {

        List<ProductoDTO> filtrados = new ArrayList<>();
        for (ProductoDTO p : productos) {
            if (categoria.isBlank() || p.getCategoria().equalsIgnoreCase(categoria.trim())) {
                filtrados.add(p);
            }
        }

        model.addAttribute("titulo", "Productos filtrados por categoria: " + categoria);
        model.addAttribute("productos", filtrados);
        return "lista";
    }

    // Video 15: @PathVariable toma el valor que va dentro de la ruta
    // URL: /productos/2
    @GetMapping("/{id}")
    public String detalle(@PathVariable("id") int id, Model model) {
        ProductoDTO encontrado = null;
        for (ProductoDTO p : productos) {
            if (p.getId() == id) {
                encontrado = p;
                break;
            }
        }

        model.addAttribute("titulo", "Detalle del producto");
        model.addAttribute("producto", encontrado);
        return "detalle";
    }

    // Vista 2: formulario vacio (Video 13)
    // URL: /productos/nuevo
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo producto");
        model.addAttribute("producto", new ProductoDTO());
        return "formulario";
    }

    // Video 13: @ModelAttribute a nivel de parametro recibe los datos del formulario
    // y los guarda en un objeto ProductoDTO automaticamente
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") ProductoDTO producto, Model model) {
        producto.setId(productos.size() + 1);
        productos.add(producto);

        model.addAttribute("titulo", "Producto recibido");
        return "resultado";
    }
}
