package gestion.Empleos;

/*
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import gestion.Empleos.ServiceJpa.VacanteServiceJpa;
import gestion.Empleos.model.Categorias;
import gestion.Empleos.model.Perfil;
import gestion.Empleos.model.Usuario;
import gestion.Empleos.model.Vacante;
import gestion.Empleos.repository.ICategoriasRepository;
import gestion.Empleos.repository.IPerfilRepository;
import gestion.Empleos.repository.IUsuarioRepository;
import gestion.Empleos.repository.IVacantesRepository;
import gestion.Empleos.service.IVacanteService;*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class EmpleosApplication /*implements CommandLineRunner*/{
	
	public static void main(String[] args) {
		SpringApplication.run(EmpleosApplication.class, args);
	}
	
	/*@Autowired
	private IVacanteService vacanteService;
	
	//inyentar repositorio de Categorias
	@Autowired
	private ICategoriasRepository repoCategoria;
	
	//inyeccion de repositorio de Vacante
	@Autowired
	private IVacantesRepository repoVacante;
	
	//inyeccion de repositorio de usuario
	@Autowired
	private IUsuarioRepository repoUsuario;
	
	//inyeccion de repositorio de Perfil
	@Autowired
	private IPerfilRepository repoPerfil;*/
	
	
	
	/*
	//al iniciar el app se ejecuta esta aplicacion
	@Override
	public void run(String... args) throws Exception {
		System.out.println("ejemplos de jpa Hibernate");
		//guardar();
		//getAll("nombre"); //buscar todas las categorias
		//Actualizar();
		//buscarById();
		//Conteo(); // contar toda los registros
		//EliminarTodo(); //eliminar todo
		//dindAllById(); // buscar en el registro con una colecicond e ids
		//exitsById(); //Verificamos si existe el registro  en la db
		//saveAll(); //guardar una lista de categorias
		//paginarLosregistros();
		//--------------vacantes method------
		//saveVacante();
		//listAllVacantes();
		//--------------perfiles method------
		//createPerfil();
		//findAllPerfil();
		//CreateUsuarioConPerfil();
		//buscarUsuario();
		///findByEstatus();
		//findbyTwoParam();
		//findBySalarioBetween1();
		//buscarfindByEstatusIn();
		//findbyTwoParam();
		buscarTodo();
		//getPerfiles();
		
	}
	
	private void buscarTodo() {
	List<Vacante> list = vacanteService.buscarTodas();
		for(Vacante v : list) {
			System.out.println("Vaca: "+ v.getId()+ " "+v.getNombre());
			System.out.println("cat: "+v.getCategoria().getId()+ " "+v.getCategoria().getNombre());
		}
	}
	
	private void listarTodoLosVacantes() {
		
		List<Vacante> list = repoVacante.findAll();
		
		for(Vacante v : list) {
			System.out.println("cat: "+v.getCategoria());
		}
		
	}
	
	
	//buscar con un array de criterios
	private void buscarfindByEstatusIn() {
		
		String[] estatus = new String[] {"Eliminada", "Aprobada"};
		
		List<Vacante> list = repoVacante.findByEstatusIn(estatus); 
		for(Vacante v : list) {
			System.out.println(" "+ v.getId()+  " " +v.getEstatus() +" : " +v.getNombre()+ " "+ v.getEstatus());
		}
	}
	
	private void findBySalarioBetween1() {
		List<Vacante> list = repoVacante.findBySalarioBetween(655.5, 9999.5); 
		for(Vacante v : list) {
			System.out.println(" "+ v.getId()+  " " +v.getEstatus() +" : " +v.getNombre()+ " S/."+ v.getSalario());
		}
	}
	
	
	private void findbyTwoParam() {
		// primera forma query native
		List<Vacante> list = repoVacante.findByDestacado("Aprobada", 0);
		for(Vacante v : list) {
			System.out.println(" "+ v.getId()+  " " +v.getEstatus() +" : " +v.getNombre());
		}
		
		/*List<Vacante> list = repoVacante.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada"); 
		for(Vacante v : list) {
			System.out.println(" "+ v.getId()+  " " +v.getEstatus() +" : " +v.getNombre());
		}
		
	}*/
	/*
	private void findByEstatus() {
		List<Vacante> list = repoVacante.findByEstatus("Aprobada");
		System.out.println("Rows: "+ list.size());
		//System.out.println("vacantes; "+ list);
		for(Vacante v : list) {
			System.out.println(" "+ v.getId()+  " " +v.getEstatus() +" : " +v.getNombre());
			
			System.out.println("cat: "+ v.getCategoria());
		}
		
	}*/
	
	/**
	 * metodo para bscar un usario y desplegar sus perfiles asociados	 * 
	 */
	/*public void buscarUsuario() {
		
		Optional<Usuario> us =  repoUsuario.findById(1);
		
		if (us.isPresent()) {
	
			Usuario u = us.get();
			
			System.out.println("Usuario "+ u.getNombre()+ " "+ u.getEmail());
			
			for (Perfil p : u.getPerfiles()) {
				System.out.println("Perfiles "+p.getId()+ " " + p.getPerfil());
			}
		}else {
			System.out.println("No se encontró usuarios");
		}
	}
	*/
	/**
	 * Crear un usuario con 2 perfiles "Supervisor", "administrador"	 
	 */
	/*private void CreateUsuarioConPerfil() {
		Usuario u = new Usuario();
		u.setNombre("Elver");
		u.setEmail("thonny@gmail.com");
		u.setFecharegistro(new Date());
		u.setUsername("thonny");
		u.setPassword("1234");
		u.setEstatus(1);
		
		/*
		 *agreamos variosperfil en un usuario
		 
		Perfil p = new Perfil();
		p.setId(1);
		Perfil o = new Perfil();
		o.setId(2);
		
		u.agregar(p);
		u.agregar(o);
		
		
		repoUsuario.save(u);
		
	}*/
	
	/*private void findAllPerfil() {
		List<Perfil>  per =repoPerfil.findAll();
		
		for(Perfil p : per) {
			System.out.println(" "+p.getId()+ " "+ p.getPerfil());
		}
		
	}
	
	private void createPerfil() {
		//guardamos todo los perfiles en la db
		repoPerfil.saveAll(getPerfiles());
	}
	
	//lista de perfiles
	private List<Perfil> getPerfiles(){
		
		List<Perfil> list = new LinkedList<>();
		
		Perfil p = new Perfil();
		p.setPerfil("Supervisor");

		Perfil o = new Perfil();
		o.setPerfil("Administrador");
		
		Perfil i = new Perfil();
		i.setPerfil("Usuario");
		
		list.add(p);
		list.add(o);
		list.add(i);
		
		return list;
	}
	
	
	private void saveVacante() {
		
		Vacante v  = new Vacante();
		
		v.setNombre("Nuevo vacnate para sosftware");
		v.setDescr("desrrollod e software en Android");
		v.setDetalle("andorid, ios,  con java");
		v.setFecha(new Date());
		v.setSalario(56953.22);
		v.setEstatus("Aprobada");
		v.setDestacado(1);
		v.setImagen("logotipodelaempresa.jpg");
		v.setCategoria(new Categorias(1));
		
		v = repoVacante.save(v);
		
		System.out.println("v: "+v);
	}
	
	
	//buscar vacanets
	private void listAllVacantes() {
		//select v.id, v.nombre , v.idcategoria, c.nombre from vacantes v inner join categorias c on v.idcategoria = c.id;
		List<Vacante> lv = repoVacante.findAll(Sort.by("categoria").descending());
		
		for(Vacante v : lv) {System.out.println(" " +v.getId() + " " + v.getNombre() + " " +v.getCategoria().getId() + " "+v.getCategoria().getNombre());}
	}
	
	
	
	
	
	//buscar y paginar los registros	
	public void paginarLosregistros() {
		
		//paging list 
		Page<Categorias> l = repoCategoria.findAll(PageRequest.of(0, 15,Sort.by("nombre").ascending()));
		System.out.println("paginas: "+l.getTotalPages());
		System.out.println("Registro: "+l.getTotalElements());
		for(Categorias c: l) {
			System.out.println(" "+c.getId() + " "+ c.getNombre());
		}
	}
	
	//guardamos varios objetos en una sola consulta
	public void saveAll() {
		Categorias a = new Categorias("categoria 1"," descripcion 1");
		Categorias b = new Categorias("categoria 2"," descripcion 2");
		Categorias c = new Categorias("categoria 3"," descripcion 3");
		Categorias d = new Categorias("categoria 4"," descripcion 4");
		Categorias e = new Categorias("categoria 5"," descripcion 5");
		Categorias g = new Categorias("categoria 6"," descripcion 6");
		Categorias h = new Categorias("categoria 7"," descripcion 7");
		
		List<Categorias> lista = new LinkedList<Categorias>();
		lista.add(a);
		lista.add(b);
		lista.add(c);
		lista.add(d);
		lista.add(e);
		lista.add(g);
		lista.add(h);
		
		//repoCategorias.saveAll(lista);
	}

	// regresa si existe registro con el id ingresado
	public void exitsById(){
		
		Boolean existe = repoCategoria.existsById(19);		
		if (existe) System.out.println("se encontro el registro");
		else System.out.println("No se encontro el registro");
	}
	
	// recibe una collecion de ids para buscar en la db 
	private void dindAllById() {
		
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(11);
		ids.add(12);
		ids.add(13);
		ids.add(14);
		
		Iterable<Categorias> cat =  repoCategoria.findAllById(ids);
		
		for (Categorias c : cat) {
			System.out.println("Lsista de cats: " +c);			
		}	
		
	}
	
	//contar registro de la Db
	private void Conteo() {
		long count  = repoCategoria.count();
		System.out.println("total de resgitros son: "+count);	
	}
	
	private void EliminarTodo() {
		//Scanner sc = new Scanner(System.in);
		//String escaneo = sc.nextLine();
		System.out.println("metodo escaneado: ");
		//repoCategorias.deleteAll();
			
	}
	
	//save y actualizar
	private void Actualizar() {
		
		Optional<Categorias> optional =  repoCategoria.findById(16);
		
		if (optional.isPresent()) {
			
			Categorias catTemp  = optional.get();
			catTemp.setNombre("Ingeniero de naves");
			catTemp.setDesc("requirimeintos y tecnologias fundamentales a saber");
			
			//guardmos los datos actualizados
			repoCategoria.save(catTemp);
			
			System.out.println("Optional Save: "+ optional.get());
			
		}else {
			System.out.println("categoria no encntrada!");
		}
		
		
			
	}
	
	private void buscarById() {
		
		Optional<Categorias> optional =  repoCategoria.findById(16);
		
		if (optional.isPresent()) {
			
			System.out.println("optional: "+ optional.get());
		}else {
			System.out.println("categoria no encntrada!");
		}			
		
	}
	
	private void guardar() {
		Categorias c = new Categorias();
		
		c.setNombre("mecatronica");
		c.setDesc("Ingeniero mecanico de maquinas pesadas");
		
		repoCategoria.save(c);
		
		System.out.println("insertando un registro: "+repoCategoria);
		
	}

	private void eliminar() {
		
		int idCat = 16;
		repoCategoria.deleteById(idCat);
		System.out.println("Se elimino el registro  con id; "+idCat);
	}
	
	//buscar todas las categorias
	private void getAll(String orderBy) {
		orderBy = "nombre";
		System.out.println("insertando un registro");
		List<Categorias> lista = (List<Categorias>) repoCategoria.findAll(Sort.by(orderBy).descending());
		
		//ordenamiento por nombre
		for (Categorias c : lista) {
			System.out.println(" "+ c.getId() + " " +c.getNombre() +" "+ c.getDesc());			
		}
		
		//ordenamiento por id
		List<Categorias> list = (List<Categorias>) repoCategoria.findAll(Sort.by("id").ascending());
		for (Categorias c : list) {
			System.out.println(" "+ c.getId() + " " +c.getNombre() +" "+ c.getDesc());		
		}
	}*/
	
}
