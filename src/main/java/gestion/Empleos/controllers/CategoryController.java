package gestion.Empleos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gestion.Empleos.model.Categorias;
import gestion.Empleos.service.ICategoriasService;



@Controller
@RequestMapping(value = "/c")
public class CategoryController {
	
//	@Qualifier("categoriaServiceJpa")
	@Autowired
	ICategoriasService serviceCategoria;

	
	@RequestMapping(value= "/index", method= RequestMethod.GET)
	public String getIndex(Model md) {
		
		List<Categorias> lista = serviceCategoria.buscarTodas(); 
		
		//System.out.println("Categorias : " +lista);
		md.addAttribute("categorias", lista);
		return "categorias/listacategorias";
	}
	
	@RequestMapping(value="search/{id}", method= RequestMethod.GET)
	public String crear(@PathVariable("id") int id, Model md) {
		
		//System.out.println("el id es: "+ id);
		Categorias cat = serviceCategoria.bucarById(id);
		md.addAttribute("categoria", cat);
		
		return "categorias/formcategorias";
	}
	
	@RequestMapping(value="/create", method= RequestMethod.GET)
	public String crearCategoria(Model md, Categorias categoria) {
		
		md.addAttribute("categoria", categoria);
		
		return "categorias/formcategorias";
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("desc") String desc, Categorias cat, RedirectAttributes attributes) {
		//System.out.println("nombre: "+nombre);
		//System.out.println("nombre: "+desc);
		Categorias c = new Categorias();
		c.setId(0);
		c.setNombre(nombre);
		c.setDesc(desc);
		
		serviceCategoria.guardar(c);
		
		attributes.addFlashAttribute("msg","La categoria se guardo de forma correcta");
		
		return "redirect:/c/index";
		
	}
	
	

}
