package gestion.Empleos.ServiceJpa;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gestion.Empleos.model.Vacante;
import gestion.Empleos.repository.IVacantesRepository;
import gestion.Empleos.service.IVacanteService;

@Primary
@Service
public class VacanteServiceJpa  implements IVacanteService{

	@Autowired
	IVacantesRepository repoVacante;
	
	@Override
	public List<Vacante> buscarTodas() {
		
		return repoVacante.findAll();
	}

	@Override
	public Vacante searchById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Vacante> optional = repoVacante.findById(id) ;
		
		if (optional.isPresent()) {
			
			return optional.get();
		}
		return null;
	}

	@Override
	public void saveVacante(Vacante vacante) {
		// TODO Auto-generated method stub
		repoVacante.save(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return repoVacante.findByDestacado("Aprobada", 0);
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {//query by example
			
		//busca  por el campo especificado
		return repoVacante.findAll(example);
	}

	@Override
	public Page<Vacante> paginarVacante(Pageable page) {
				
		return  repoVacante.findAll(page);
		
	}

}
