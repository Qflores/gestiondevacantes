package gestion.Empleos.service;

import java.util.List;

import gestion.Empleos.model.Categorias;



public interface ICategoriasService {
	
	void guardar (Categorias categoria);
	
	List<Categorias> buscarTodas();
	
	Categorias bucarById(Integer id);
	
	

}
