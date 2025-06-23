package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManejadorOpinionesTest {

	
	private ManejadorOpiniones manejadorOpiniones;
	private TipoDeOpinion tipo;
	private LocalDate fecha;
	private Usuario usuario;
	private Usuario usuarioExperto;
	
	@BeforeEach
	public void setup() {
		    manejadorOpiniones = new ManejadorOpiniones();
		    usuario = new Usuario();
		    usuarioExperto = new UsuarioProfesional();
		    tipo = TipoDeOpinion.VINCHUCA; 
		    fecha = LocalDate.of(2025, 6, 22);
		   
		    /*
		    when(usuario.getCategoria()).thenReturn(categoriaBasica);
           // Configurar el mock de usuario experto para que devuelva la categoría experta
		    when(usuarioExperto.getCategoria()).thenReturn(categoriaExperta);*/
           
		
	}
	
	 @Test
	    public void testeAgregar() {
	        // Verificar el tamaño inicial
	       assertEquals(0, manejadorOpiniones.getListaDeOpiniones().size());
	       
	        // Crear y registrar una opinión
	       manejadorOpiniones.crearYRegistrarOpiniones(tipo, fecha, usuario);

	        // Verificar que la lista tiene un elemento
	        assertEquals(1, manejadorOpiniones.getListaDeOpiniones().size());

	 }
	
	

	    @Test
	    public void testCrearYRegistrarOpinionDeExperto() {
	       
	    	// Creamos y registramos la opinión
	        manejadorOpiniones.crearYRegistrarOpiniones(tipo, fecha, usuarioExperto);

	        assertEquals(1, manejadorOpiniones.getListaDeOpiniones().size());
	    }
	    
}
