package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Venta;

@Repository
@Transactional
public class VentaRepositoryImpl implements IVentaRepository{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void insertar(Venta venta) {
		// TODO Auto-generated method stub
		this.entityManager.persist(venta);
	}

	@Override
	public void actualizar(Venta venta) {
		// TODO Auto-generated method stub
		this.entityManager.merge(venta);
	}

	@Override
	public Venta buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Venta.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		Venta v=this.buscar(id);
		this.entityManager.remove(v);
	}

	@Override
	public List<Venta> listarVentas() {
		// TODO Auto-generated method stub
		TypedQuery<Venta>myQuery=this.entityManager.createQuery("SELEC v FROM Venta v", Venta.class);
		return myQuery.getResultList();
	}
	@Override
	public List<Venta>ventaFecha(LocalDateTime fecha){
		TypedQuery<Venta>myQuery=this.entityManager.createQuery("SELEC v FROM Venta v WHERE v.fecha:=fecha", Venta.class);
		return myQuery.getResultList();
	}

}