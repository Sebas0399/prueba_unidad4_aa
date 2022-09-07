package com.uce.edu.demo.repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.DetalleVenta;

@Repository
@Transactional
public class DetalleVentaRepositoryImpl implements IDetalleVentaRepository{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void insertar(DetalleVenta detalle) {
		// TODO Auto-generated method stub
		this.entityManager.persist(detalle);
	}

	@Override
	public void actualizar(DetalleVenta detalle) {
		// TODO Auto-generated method stub
		this.entityManager.merge(detalle);
	}

	@Override
	public DetalleVenta buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(DetalleVenta.class, id);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		DetalleVenta dv=this.buscar(id);
		this.entityManager.remove(dv);
	}

}