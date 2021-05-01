package gestion.Empleos.ServiceJpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.Empleos.model.Perfil;
import gestion.Empleos.repository.IPerfilRepository;
import gestion.Empleos.service.IPerfilService;


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
