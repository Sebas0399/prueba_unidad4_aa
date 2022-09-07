package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Stock;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.merge(producto);
	}

	@Override
	public Producto buscarCodigo(String codigo) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras=:codigo", Producto.class);
		myQuery.setParameter("codigo", codigo);
		return myQuery.getSingleResult();
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		Producto p = this.buscarId(id);
		this.entityManager.remove(p);
	}

	@Override
	public Producto buscarId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Producto.class, id);
	}

	public Stock revisarStock(String codigoBarras) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> producto = cq.from(Producto.class);
        Predicate codigoWhere = cb.equal(producto.get("codigoBarras"), codigoBarras);
        
        cq.where(codigoWhere);

        TypedQuery<Producto> query = this.entityManager.createQuery(cq);
        Producto p=query.getSingleResult();
        Stock s=new Stock();
        s.setCodigoBarras(p.getCodigoBarras());
        s.setNombre(p.getNombre());
        s.setStock(p.getStock());
		return  s;
	}

}