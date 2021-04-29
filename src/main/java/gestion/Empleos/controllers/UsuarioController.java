package gestion.Empleos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gestion.Empleos.model.Usuario;
import gestion.Empleos.service.IPerfilService;
import gestion.Empleos.service.IUsuariosService;

@Controller
@RequestMapping(value = "/u")
public class UsuarioController {
	
	@Autowired
	private IUsuariosService servisUsuario;
	
	@Autowired
	private IPerfilService servicePerfil;

	@GetMapping("/index")
	private String getAll( Model md){		
		//md.addAttribute("usuarios", servisUsuario.buscarTodas());		
		return "usuarios/listusuarios";
	}
	
	@GetMapping("/create")
	private String usuarioFormulario(Usuario usuario , Model md) {		
		md.addAttribute("perfiles", servicePerfil.listAllPerfil());
		return "usuarios/formRegistro";		
	}
	
	@PostMapping("/save")
	private String saveUsuario(Usuario user) {		
		servisUsuario.saveUsuario(user);
		System.out.println("Save: "+user);
		return "";
	}
	
	@DeleteMapping("/delete/{id}")
	private void  deleteUsuario(@PathVariable("id") Integer id) {
		servisUsuario.deleteUsuario(id);
	}
	
	@ModelAttribute
	private void setTemporal(Model md) {
		md.addAttribute("usuarios", servisUsuario.buscarTodas());
	}
}
