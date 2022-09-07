package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Venta;

public interface IVentaRepository {
public void insertar(Venta venta);
public void actualizar(Venta venta);
public Venta buscar(Integer id);
public void eliminar(Integer id);
public List<Venta>listarVentas();
public List<Venta>ventaFecha(LocalDateTime fecha);
}
