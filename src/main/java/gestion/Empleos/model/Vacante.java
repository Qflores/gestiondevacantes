package gestion.Empleos.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacantes")
public class Vacante {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descr;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="salario")
	private Double salario;
	
	@Column(name="estatus")
	private String estatus; 
	
	@Column(name="destacado")
	private Integer destacado;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="detalles")
	private String detalle;
	
	//@Transient //ignora este campo
	@OneToOne	//(mappedBy = "id", cascade = CascadeType.ALL)
	@JoinColumn(name="idCategoria")
	private Categorias categoria;
	
	
	public Vacante() {}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getDestacado() {
		return destacado;
	}

	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
		
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public void reset() {
		this.imagen = null;
	}

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descr=" + descr + ", fecha=" + fecha + ", salario="
				+ salario + ", estatus=" + estatus + ", destacado=" + destacado + ", imagen=" + imagen + ", detalle="
				+ detalle + ", categoria=" + categoria + "]";
	}
	
	

}
