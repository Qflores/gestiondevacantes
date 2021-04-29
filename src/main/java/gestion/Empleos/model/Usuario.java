package gestion.Empleos.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	private String username;
	@Column(name="nombre")
	private String nombre;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="estatus")
	private Integer estatus;
	@Column(name="fecharegistro")
	private Date fecharegistro;
	
	@ManyToMany(fetch = FetchType.EAGER)//en perfil usuario se guarda de forma automatica
	@JoinTable(	name="UsuarioPerfil", // tabal intermedia
				joinColumns = @JoinColumn(name="idUsuario"), //clave primaria
				inverseJoinColumns = @JoinColumn(name="idPerfil")) //clave forania
	private List<Perfil>  perfiles;
	
	
	public void agregar(Perfil temPerfil) {//agregar lista de perfiles a la lista de perfiles
		if (perfiles == null) {
			perfiles = new LinkedList<Perfil>();
		}		
		perfiles.add(temPerfil);
	}
	
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	public Date getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	
	
	

}
