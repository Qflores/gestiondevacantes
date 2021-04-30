package gestion.Empleos.ServiceJpa;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.Empleos.model.Usuario;
import gestion.Empleos.repository.IUsuarioRepository;
import gestion.Empleos.service.IUsuariosService;

@Service
public class UsuarioServiceJpa implements IUsuariosService {

	@Autowired
	private IUsuarioRepository repoUsuario;
	
	@Override
	public List<Usuario> buscarTodas() {
		// TODO Auto-generated method stub
		return repoUsuario.findAll();
	}

	@Override
	public Usuario searchById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Usuario> optional = repoUsuario.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		repoUsuario.save(usuario);
	}

	@Override
	public List<Usuario> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteUsuario(Integer id) {
		
		Optional<Usuario> optional = repoUsuario.findById(id);
		
		if(optional.isPresent()) {
			
			repoUsuario.deleteById(id);;
			System.out.println("usuario eliminado");
		}
		else System.out.println("El usuariono se encontró");
		
	}

	@Override
	public Usuario buscarPorusername(String username) {
		
		return repoUsuario.findByUsername(username);
	}

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		repoUsuario.save(usuario);
		
	}

}
