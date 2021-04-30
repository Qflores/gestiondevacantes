package gestion.Empleos.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gestion.Empleos.model.Perfil;
import gestion.Empleos.model.Usuario;
import gestion.Empleos.service.IPerfilService;
import gestion.Empleos.service.IUsuariosService;

@Controller
@RequestMapping(value = "/u")
public class UsuarioController {
	
	@Autowired
	private PasswordEncoder passwordEncoder; // inyentando para incriptar contraseña
	
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
	private String usuarioFormulario(Usuario usuarios , Model md) {			
		
		Usuario usuario = new Usuario();
		md.addAttribute("usuario", usuario);
		
		md.addAttribute("perfiles", servicePerfil.listAllPerfil());		
		return "usuarios/formRegistro";		
	}
	
	@ModelAttribute
	private void setTemporal(Model md) {
		
		md.addAttribute("usuarios", servisUsuario.buscarTodas());
	}
	
	@PostMapping("/save")
	private String saveUsuario(Usuario user) {	
		
		String passPlane = user.getPassword();
		String passCryp = passwordEncoder.encode(passPlane);
		
		System.out.println("Contraseña: "+passCryp);
		//recuperando usuario del formulario
		user.setEstatus(1);
		user.setFecharegistro(new Date());
		user.setPassword(passCryp);		
		
		for(Perfil p : user.getPerfiles()) {
			System.out.println("id:" + p.getId());
			System.out.println("Perfil:" + p.getPerfil());				
		}
		try {
			
			servisUsuario.guardar(user);
			
		} catch (Exception e) {
			System.out.println("cause: "+e.getCause());
			System.out.println("mesage: "+e.getMessage());
			return "redirect:/u/create";
		}
		
		return "redirect:/login";
	}
	
	@DeleteMapping("/delete/{id}")
	private void  deleteUsuario(@PathVariable("id") Integer id) {
		servisUsuario.deleteUsuario(id);
	}
	
	
}
