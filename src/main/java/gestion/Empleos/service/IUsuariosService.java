package gestion.Empleos.service;

import java.util.List;

import gestion.Empleos.model.Usuario;

public interface IUsuariosService {

	List<Usuario> buscarTodas();
	
	Usuario searchById(Integer id);
	
	void saveUsuario(Usuario usuario);
	
	List<Usuario> buscarDestacadas();
	
	void deleteUsuario(Integer id);
}
