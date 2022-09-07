package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.DetalleVenta;

public interface IDetalleVentaRepository {
public void insertar(DetalleVenta detalle);
public void actualizar(DetalleVenta detalle);
public DetalleVenta buscar(Integer id);
public void borrar(Integer id);
}