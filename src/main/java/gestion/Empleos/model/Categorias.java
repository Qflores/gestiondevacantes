package gestion.Empleos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorias")
public class Categorias {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id=null;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String desc;
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void resetId() {
		this.id = null;
	}
	
	public Categorias(int id) {
		super();
		this.id = id;
	}

	public Categorias() {	}

	public Categorias(String nombre, String desc) {
		super();
		this.nombre = nombre;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Categorias [id=" + id + ", nombre=" + nombre + ", desc=" + desc + "]";
	}
	
	
}
