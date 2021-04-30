package gestion.Empleos.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.net.httpserver.HttpServer;

import gestion.Empleos.model.Categorias;
import gestion.Empleos.model.Perfil;
import gestion.Empleos.model.Usuario;
import gestion.Empleos.model.Vacante;
import gestion.Empleos.service.ICategoriasService;
import gestion.Empleos.service.IUsuariosService;
import gestion.Empleos.service.IVacanteService;


@Controller
public class HomeController {
	
	@Autowired
	private PasswordEncoder passwordEncoder; // inyentando para incriptar contraseña
	
	@Autowired
	private ICategoriasService serviceCategoria;
	
	//@Qualifier("vacanteServiceJpa")
	@Autowired
	private IVacanteService serviceVacante;
	
	@Autowired
	private IUsuariosService serviceUsuarios;
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;*/
	
	
	@GetMapping("/")
	public String home(Model md) {			
		//en lugar se reemplazo con el metodo setGenericos();
		
		return "home";
	}	
	
	@GetMapping("/login")
	public String login(Model md) {			
		//en lugar se reemplazo con el metodo setGenericos();
		
		return "formLogin";
	}	
	
	@ModelAttribute //agregamos atributos al modelo vacantes y estaran disponibles para todo los metodos de este controlador
	private void setGenericos(Model md) {
		Vacante search = new Vacante();
		search.reset(); //borramos imagen por defecto
		
		md.addAttribute("vacantes", serviceVacante.buscarDestacadas());
		md.addAttribute("categorias", serviceCategoria.buscarTodas());
		md.addAttribute("search", search);
	}
	
	@PostMapping("/signup")
	public String registrarse(Usuario usuario, RedirectAttributes attributes) {
		
		// Recuperamos el password en texto plano
		String pwdPlano = usuario.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		// Hacemos un set al atributo password (ya viene encriptado)
		usuario.setPassword(pwdEncriptado);	
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFecharegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		/*Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		Perfil p = new Perfil();
		p.setId(2); // Perfil USUARIO
		usuario.agregar(p);*/
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login"; 
		//return "usuario/formRegistro";
	}
	
	@GetMapping("/index") //recumerando nombre del usuario
	public String mostrarindex(Authentication auth, HttpSession session) {
		
		
		String username = auth.getName();		
		
		for(GrantedAuthority rol : auth.getAuthorities()) {			
			System.out.println("Rol : " + rol.getAuthority());			
		}
		
		//recuperando informacion del usario
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = serviceUsuarios.buscarPorusername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
			
			System.out.println("usario: "+usuario);
		}
		
		Usuario user = serviceUsuarios.buscarPorusername(username);		
		
		
		return "redirect:/";
	}
	
	
	@GetMapping("/buscar")
	public String buscarVacantes(@ModelAttribute("search") Vacante vaca, Model md) {
		//bsuqueda con like		
		ExampleMatcher matcher = ExampleMatcher.matching()
				//where desc like '%?%';
				.withMatcher("descr", ExampleMatcher.GenericPropertyMatchers.contains());
		
		
		//buscar por descr y idcategoria
		Example<Vacante> example = Example.of(vaca, matcher);
		
		//si el idcategoria es cero  reseteamosa null
		if(vaca.getCategoria() != null) {
			if(vaca.getCategoria().getId() ==0) {				
				vaca.getCategoria().resetId();				
			}			
		}
		List<Vacante> lista = serviceVacante.buscarByExample(example);
		md.addAttribute("vacantes", lista);		
		return "home";
	}
	
	//retonsa un texto encriptado
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encript(@PathVariable("texto") String texto) {
		
		texto = "encryptado en Bcrypt: "+ passwordEncoder.encode(texto);
		
		return texto;
	}
	
	
	@InitBinder // si viene vacio un campo lo sete a null
	public void initBindin(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	
	
	
	@GetMapping("/tabla")
	public String mostrartabla(Model md) {
		
		List<Vacante> lista = serviceVacante.buscarTodas();			
		md.addAttribute("vaca",lista);		
		return "vacantes";
		
	}
	
	@GetMapping("/lista")
	public String getClientes(Model md) {
		
		List<String> lista = new LinkedList<String>();
		
		lista.add("Ingeniero de sistemas");
		lista.add("Auxiliar de contabilidad");
		lista.add("Vendedor de productos");
		lista.add("Arquitecto de computadoras");
		lista.add("Analista de sistemas");
		
		md.addAttribute("lista", lista);
		
		return "listado";
	}
	
	@RequestMapping("/detalle")
	public String mostrarDetalle(Model md) {
		
		Vacante v = new Vacante();
		v.setId(5);
		v.setNombre("Ingeniero de telecomunicaciones");
		v.setDescr("Se solicita ingeniero de telecomunicaciones");
		v.setFecha(new Date());
		v.setSalario(4365.55);
		v.setEstatus("");
		
		md.addAttribute("detalle", v);
		
		return "acerca";
	}
	
	@GetMapping("/logout")
	public String logout(
							HttpServletRequest request, 
							HttpServletResponse response, 
							Authentication auth) {
		
		SecurityContextLogoutHandler logouthandler = new SecurityContextLogoutHandler();
		logouthandler.logout(request, response, auth);
		return  "redirect:/login";
	}


}
