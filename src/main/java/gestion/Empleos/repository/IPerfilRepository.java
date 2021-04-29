package gestion.Empleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.Empleos.model.Perfil;

public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {

}
