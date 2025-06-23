package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManejadorUsuarioTest {


	private ManejadorUsuario manejadorUsuario;
	
	
	@BeforeEach
	public void setup() {
			manejadorUsuario = new ManejadorUsuario();
		   
		
	}
	
	 @Test
	    public void testeAgregar() {
	        // Verificar el tamaño inicial
	       assertEquals(0, manejadorUsuario.getListaDeUsuarios().size());
	       
	        // Crear y registrar una opinión
	       manejadorUsuario.crearYRegistrarUsuario();

	        // Verificar que la lista tiene un elemento
	        assertEquals(1, manejadorUsuario.getListaDeUsuarios().size());

	 }
	
	

	    @Test
	    public void testCrearYRegistrarOpinionDeExperto() {
	       
	    	// Creamos y registramos la opinión
	    	manejadorUsuario.crearYRegistrarUsuariosProfesionales();

	        assertEquals(1, manejadorUsuario.getListaDeUsuarios().size());
	    }
}
