package gestion.Empleos.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestion.Empleos.model.Categorias;
import gestion.Empleos.model.Vacante;
import gestion.Empleos.service.ICategoriasService;
import gestion.Empleos.service.IVacanteService;
import gestion.Empleos.utils.Utileria;


@Controller
@RequestMapping(value = "/v")
public class VacantesControlles {
	
	//inyectando valor desde properties para guardar las imagenes
	
	@Value("${rutaimagen}")
	private String rutas;

	//@Qualifier("vacanteServiceJpa")
	@Autowired	
	private IVacanteService serviceVacante;
	
	@Autowired	
	private ICategoriasService serviceCategoria;
		
	@GetMapping("/index")
	public String createVacante(Model md) {
		
		List<Vacante> lista =  serviceVacante.buscarDestacadas();
		
		md.addAttribute("vacantes",lista);		
		
		return "vacantes/listavacantes";
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vacante> lista = serviceVacante.paginarVacante(page);
		model.addAttribute("vacantes", lista);
		return "vacantes/listavacantes";
	}
	
	@GetMapping("/f")
	public String createVacante(Vacante vacante, Model md) {
		
		List<Categorias> lista = serviceCategoria.buscarTodas();
		
		md.addAttribute("categorias", lista);
		return "vacantes/vformulario";
	}
	
	 
	//BindingResult result, //para controlar el error de conversion texto a double
	//RedirectAttributes attributes, //pasar mensaje en redireccion
	//@RequestParam("archivoImagen") MultipartFile multiPart
	
	@PostMapping("/save")
	public String saveVacante(@RequestParam("archivoImagen") MultipartFile multiPart, 
								Vacante vacante, 
								BindingResult result, 
								RedirectAttributes attributes) {
		
		//controlamos tipo de dato double
		if(result.hasErrors()) {
			// solo para evr en la consola
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}			
			return "vacantes/vformulario";			
		}
		
		
		if(!multiPart.isEmpty()) {
			//ruta = /empleos/img-vacantes/
			
			String ruta = "c:/empleos/img-vacantes/";
			//guardamos el archivo en el dico duro
			String nombreImagen = Utileria.guardarArchivos(multiPart, ruta);
			if(nombreImagen != null) {
				//procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
				System.out.println("Success: El archivo se guardo con exito");
			}else {
				System.out.println("el archivo no se guardo correctamente");
			}
		}
		
		//no funciona en redirect
		//md.addAttribute("msg", "Se guardó la vacante");
		
		//guardamos el objeto vacante
		serviceVacante.saveVacante(vacante);	
		// regresamos la lista de objetos
		attributes.addFlashAttribute("msg", "Registro Guardado");
		//System.out.println("vacantes:" + vacante);	
		return "redirect:/v/index";
	}
	
	
	//Buscar por id
	//QueryString
	@GetMapping(value="/q")
	public String queryString(@RequestParam("id") int id, Model md) {	
		
		Vacante v = serviceVacante.searchById(id);
		
		md.addAttribute("id", id);
		md.addAttribute("v", v);
		
		return "vacantes/detalle";
	}
	//por parametro+
	@GetMapping(value="/l/{id}")
	public String getIndex(@PathVariable("id")  int id, Model md) {	
		
		Vacante v = serviceVacante.searchById(id);		
		md.addAttribute("id", id);
		md.addAttribute("v", v);
		
		System.out.println("El di es: "+ id);	
		
		return "vacantes/detalle";
	}
	
	//convertir string a fecha y evitar error de conversion
	@InitBinder
	public void coverFecha(WebDataBinder webBinder) {
		
		SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy");		
		webBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdt, false));
		
	}
	
}
