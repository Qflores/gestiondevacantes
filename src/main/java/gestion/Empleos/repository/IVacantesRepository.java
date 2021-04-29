package gestion.Empleos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gestion.Empleos.model.Vacante;

public interface IVacantesRepository extends JpaRepository<Vacante, Integer> {
	//metodo para buscar por estatus
		List<Vacante> findByEstatus(String estatus); 
		
		
	/**
	 * metodo para buscar por dos criterios : 
	 */		
	
	
	@Query(value ="SELECT * FROM Vacantes v WHERE v.estatus = :estatus AND v.destacado = :destacado", nativeQuery = true)
	List<Vacante> findByDestacado(@Param("estatus") String estatus, @Param("destacado") int destacado);
	 
	
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	List<Vacante> findBySalarioBetween(Double s1, Double s2);
	/**
	 * 
	 * Pasando un array de busqueda
	 */
	List<Vacante> findByEstatusIn(String[] estatus);
		
		
}
