package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IDetalleVentaRepository;
import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Reporte;
import com.uce.edu.demo.repository.modelo.Stock;
import com.uce.edu.demo.repository.modelo.Venta;
@Service
public class GestorVentaImpl implements IGestorVentaService {
	@Autowired
	private IProductoRepository productoRepo;
	@Autowired
	private IVentaRepository ventaRepo;
	
	@Autowired
	private IDetalleVentaRepository detalleVentaRepo;
	@Override
	public void ingresarProducto(Producto p) {
		// TODO Auto-generated method stub
		try {
			Producto producto = this.productoRepo.buscarCodigo(p.getCodigoBarras());
			producto.setStock(producto.getStock() + p.getStock());
			this.productoRepo.actualizar(producto);
		}

		catch (EmptyResultDataAccessException e) {
			System.out.println("No existe");
			this.productoRepo.insertar(p);
		}
	}
	@Override
	public Stock reporteStock(String codigoBarras) {
		// TODO Auto-generated method stub
		Stock s=this.productoRepo.revisarStock(codigoBarras);
		return s;
		
	}
	@Override
	public void realizarVenta(List<ProductoSencillo> productosLista, String cedula, String nroVenta) {
		// TODO Auto-generated method stub
		Venta v=new Venta();
		v.setCedulaCliente(cedula);
		v.setNumero(nroVenta);
		v.setFecha(LocalDateTime.now());
		productosLista.forEach((producto)->{
			Producto p=this.productoRepo.buscarCodigo(producto.getCodigoBarras());
			if(p.getStock()<producto.getCantidad()) {
				throw new RuntimeException();
			}
			else {
				DetalleVenta dv=new DetalleVenta();
				dv.setCantidad(producto.getCantidad());
				dv.setPrecioUnitario(p.getPrecio());
				dv.setProducto(p);
				dv.setSubtotal(p.getPrecio().multiply(new BigDecimal(producto.getCantidad())));
				dv.setVenta(v);
				this.detalleVentaRepo.insertar(dv);
				
				p.setStock(p.getStock()-producto.getCantidad());
				this.productoRepo.actualizar(p);
				
				if(v.getTotalVenta()==null) {
					v.setTotalVenta(new BigDecimal(0));
				}
				v.setTotalVenta(v.getTotalVenta().add(dv.getSubtotal()));
				
			}
		});
		this.ventaRepo.insertar(v);
		
	}

	public List<ProductoSencillo>convertir(List<Producto>lista){
		List<ProductoSencillo>nuevaLista = new ArrayList<>();
		lista.stream().forEach((producto)->{
			ProductoSencillo p=new ProductoSencillo();
			p.setCantidad(producto.getStock());
			p.setCodigoBarras(producto.getCodigoBarras());
			nuevaLista.add(p);
		});;
		return nuevaLista;
		
	}
	@Override
	public List<Reporte> reporteVentas(LocalDateTime fechaVenta, String categoria, Integer cantidad) {
		// TODO Auto-generated method stub
		List<Reporte>nuevaLista=new ArrayList<>();
		this.ventaRepo.ventaFecha(fechaVenta);
		return nuevaLista;
	}
	
}

	
