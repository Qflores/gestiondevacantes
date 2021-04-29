package gestion.Empleos.ServiceJpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gestion.Empleos.model.Perfil;
import gestion.Empleos.model.Usuario;
import gestion.Empleos.repository.IPerfilRepository;
import gestion.Empleos.service.IPerfilService;
import gestion.Empleos.service.IUsuariosService;


@Service
public class PerfilServiceJpa implements IPerfilService  {

	@Autowired
	private IPerfilRepository repousuario;
	
	@Override
	public List<Perfil> listAllPerfil() {
		
		List<Perfil> lista = repousuario.findAll();
		
		return lista;
	}

	

	
	
}
