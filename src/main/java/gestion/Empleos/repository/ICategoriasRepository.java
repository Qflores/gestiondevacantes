package gestion.Empleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.Empleos.model.Categorias;

//CrudRepository
//PagingAndSortingRepository extiende a CrudRepository
//JpaRepository extiende a PagingAndSortingRepository

//public interface CategoriasRepository extends CrudRepository<Categorias, Integer> {
public interface ICategoriasRepository extends JpaRepository<Categorias, Integer> {

	
}
