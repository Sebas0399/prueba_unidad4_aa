package com.uce.edu.demo.repository.modelo;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
	@Id
	@Column(name ="prod_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_id_seq")
	@SequenceGenerator(name = "prod_id_seq", sequenceName = "prod_id_seq", allocationSize = 1)
	private Integer id;
	@Column(name="prod_codigo_barras")
	private String codigoBarras;
	@Column(name="prod_nombre")
	private String nombre;
	@Column(name="prod_categoria")

	private String categoria;
	@Column(name="prod_stock")

	private Integer stock;
	@Column(name="prod_precio")

	private BigDecimal precio;
	@OneToMany(mappedBy ="producto",cascade = CascadeType.ALL)
	private List<DetalleVenta>detalles;
	//set y get

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public List<DetalleVenta> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", categoria="
				+ categoria + ", stock=" + stock + ", precio=" + precio + ", detalles=" + detalles + "]";
	}
	
	
}
