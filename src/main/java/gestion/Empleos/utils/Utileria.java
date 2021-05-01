package gestion.Empleos.utils;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	
	public static String guardarArchivos(MultipartFile multiPart, String ruta) {
		
		//obtenermos el nombre del archivo
		String nombreOrigen = multiPart.getOriginalFilename();
		
		//quitar los espacios y agregamos 5 caracteres aleatorios
		nombreOrigen = ""+randomAlphaNumeric(5)+nombreOrigen.replace(" ", "-");
		
		//geenrar 
		
		try {
			
			//Generando nombre del archivo
			File imagenFile =new File(ruta + nombreOrigen);			
			System.out.println("Nombre Archivo: "+imagenFile.getAbsolutePath() );
			
			//guardado fisico del archivo			
			multiPart.transferTo(imagenFile);
			
			return nombreOrigen;
			
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			return null;
		}		
	}
	
	public static String randomAlphaNumeric(int count) {
		String caracteres = "abcdefghijklmnopqrsc1234567890";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * caracteres.length());
			builder.append(caracteres.charAt(character));			
		}
		
		return ""+builder.toString();
	}

}
