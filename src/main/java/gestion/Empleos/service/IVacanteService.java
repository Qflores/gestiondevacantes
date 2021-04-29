package gestion.Empleos.service;


import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import gestion.Empleos.model.Vacante;



public interface IVacanteService {

	List<Vacante> buscarTodas();
	
	Vacante searchById(Integer id);
	
	void saveVacante(Vacante vacante);
	
	List<Vacante> buscarDestacadas();
	
	List<Vacante> buscarByExample(Example<Vacante> example);

	Page<Vacante> paginarVacante(Pageable page);
}
