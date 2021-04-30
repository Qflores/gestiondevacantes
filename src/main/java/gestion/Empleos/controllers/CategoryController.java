package gestion.Empleos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@RequestMapping(value="/search/{id}", method= RequestMethod.GET)
	public String crear(@PathVariable("id") int id, Model md) {
		
		//System.out.println("el id es: "+ id);
		Categorias cat = serviceCategoria.bucarById(id);
		
		System.out.println("cat: "+ cat);
		System.out.println("id: "+ id);
		
		md.addAttribute("categoria", cat);
		
		return "categorias/formcategorias";
	}
	
	@RequestMapping(value="/create", method= RequestMethod.GET)
	public String crearCategoria(Model md, Categorias categoria) {
		
		md.addAttribute("categoria", categoria);
		
		return "categorias/formcategorias";
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST)
	public String guardar(Categorias cat, RedirectAttributes attributes) {		
		String msg= "";
		if(cat.getId() ==null) msg = "La Categoría Se guardó con Éxito";
		else msg = "La Categoría con Id: "+  cat.getId() +" se Actualizó con éxito";
		serviceCategoria.guardar(cat);		
		attributes.addFlashAttribute("msg", msg);
		
		return "redirect:/c/index";
		
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(Model md, @PathVariable("id") int id, RedirectAttributes ra) {
		
		System.out.println(" cat id: "+ id);		
		serviceCategoria.eliminarCategoria(id);		
		ra.addFlashAttribute("msg", "La categoría fue eliminada!.");
		
		return "redirect:/c/index";
	}
	

}
