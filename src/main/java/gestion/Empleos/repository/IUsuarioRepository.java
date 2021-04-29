package gestion.Empleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.Empleos.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	void deleteById(Integer id) ;
}
