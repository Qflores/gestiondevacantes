package gestion.Empleos.ServiceJpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.Empleos.model.Categorias;
import gestion.Empleos.repository.ICategoriasRepository;
import gestion.Empleos.service.ICategoriasService;

@Service
public class CategoriaServiceJpa implements ICategoriasService {

	@Autowired
	private ICategoriasRepository repoCate;
	
	@Override
	public void guardar(Categorias categoria) {
		// TODO Auto-generated method stub
		
		repoCate.save(categoria);

	}

	@Override
	public List<Categorias> buscarTodas() {
		// TODO Auto-generated method stub
		return repoCate.findAll();
	}

	@Override
	public Categorias bucarById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
