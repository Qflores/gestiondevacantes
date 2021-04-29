package gestion.Empleos.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import gestion.Empleos.model.Categorias;
import gestion.Empleos.model.Vacante;
import gestion.Empleos.service.ICategoriasService;
import gestion.Empleos.service.IUsuariosService;
import gestion.Empleos.service.IVacanteService;


@Controller
public class HomeController {
	
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
	
	@InitBinder // si viene vacio un campo lo sete a null
	public void initBindin(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute //agregamos atributos al modelo vacantes y estaran disponibles para todo los metodos de este controlador
	private void setGenericos(Model md) {
		Vacante search = new Vacante();
		search.reset(); //borramos imagen por defecto
		
		md.addAttribute("vacantes", serviceVacante.buscarDestacadas());
		md.addAttribute("categorias", serviceCategoria.buscarTodas());
		md.addAttribute("search", search);
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
	
	


}
