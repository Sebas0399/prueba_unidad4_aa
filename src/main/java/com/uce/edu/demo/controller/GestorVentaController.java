package com.uce.edu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Stock;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.service.IGestorVentaService;

@Controller
@RequestMapping("/ventas/")
public class GestorVentaController {
	@Autowired
	private IGestorVentaService gestorVenta;
	
	//Producto
	@GetMapping("/nuevoProducto")
	public String nuevaCita(Producto p) {

		return "vistaInsertarProducto";
	}
	
	@PostMapping("/insertarProducto")

	public String insertar(Producto p) {

		this.gestorVenta.ingresarProducto(p);
		return "vistaInsertarProducto";

	}
	//venta
	@GetMapping("/nuevoVenta")
	public String nuevaVenta(Venta v) {

		return "vistaRealizarVenta";
	}
	@PostMapping("/insertarVenta")
	public String insertarVenta(Venta v) {

		this.gestorVenta.realizarVenta(null, null, null);
		return "vistaInsertarProducto";

	}
	//stock
	@GetMapping("/stock")
	public String stock(Venta v) {

		return "vistaStockProducto";
	}
	@GetMapping("/stock/{codigoBarras}")
	public String buscarCita(@PathVariable("codigoBarras") String codigoBarras, Model modelo) {

		Stock s = this.gestorVenta.reporteStock(codigoBarras);

		modelo.addAttribute("stock", s);

		return "vistaStock";
	}
}
