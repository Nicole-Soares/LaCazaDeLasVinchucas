package vinchuca; // Asegúrate de que el paquete sea el correcto

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays; 

public class UsuarioTest {

    private Usuario usuario;
    private Usuario nuevoUsuario;
    private Muestra muestraMock;
    private Muestra muestraMock2;
    private Opinion opinionMock;
    private CategoriaBasico categoriaBasicoMock; 
    private LocalDate fechaActual;
    private Muestra muestra;
    private Estado estadoBasico;
    private Usuario personaBasica;
    private Usuario personaExperta;
    private ObservadorMuestra manejador;
    private Ubicacion ubicacion;
    private LocalDate fecha;
   
    

    @BeforeEach
    void setUp() {
        
        usuario = new Usuario("Rhaenyra");
        nuevoUsuario = new Usuario("Daenerys"); 
        personaBasica = new Usuario("Daenerys");
    	personaExperta = new UsuarioProfesional("Daemon");
    	ubicacion = mock(Ubicacion.class);
    	fechaActual = LocalDate.now();
    	manejador = mock(ObservadorMuestra.class);
    	estadoBasico = new EstadoBasico();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
        muestraMock = mock(Muestra.class);
        muestraMock2 = mock(Muestra.class);
        opinionMock = mock(Opinion.class);
        categoriaBasicoMock = mock(CategoriaBasico.class); 
        fechaActual = LocalDate.now();

      
        when(opinionMock.getFechaDeOpinion()).thenReturn(fechaActual); 
        when(muestraMock.getFechaCreacion()).thenReturn(fechaActual);   
    }

   

 

    @Test
    void testUsuarioSeInicializaCorrectamente() {

       assertTrue(nuevoUsuario.getOpinionesEmitidas().isEmpty());
       assertTrue(nuevoUsuario.getMuestrasEmitidas().isEmpty());
       assertEquals(nuevoUsuario.getNombre(), "Daenerys");
    }

    @Test
    void testGetOpinionesEmitidas() {
        usuario.agregarOpinion(opinionMock);
        assertEquals(1, usuario.getOpinionesEmitidas().size());
        
    }

    @Test
    void testGetMuestrasEmitidas() {
        usuario.agregarMuestra(muestraMock);
        assertEquals(1, usuario.getMuestrasEmitidas().size());
        
    }

    @Test
    void testCambiarCategoria() {
        // Asumiendo que el usuario ya está mockeado con categoriaBasicoMock en setUp
    	for(int i=0; i <21; i++) {
    		  usuario.agregarOpinion(opinionMock);
    	}
    	for(int i=0; i <11; i++) {
    		 usuario.agregarMuestra(muestraMock);
  	}
    	usuario.opinarSobre(muestra, opinionMock);
    	usuario.enviarMuestra(muestraMock);
        assertTrue(usuario.esExperto());
    }

   


   // Tests de `opinarSobre()` (Manejo de Excepciones)

    @Test
    void testUsuarioNoPuedeOpinarSiYaOpinoSobreLaMuestra() {
        // Configurar la muestra para que diga que el usuario ya opinó
        when(muestraMock.yaOpino(usuario)).thenReturn(true);
        
        IllegalStateException thrown = assertThrows(
            IllegalStateException.class,
            () -> usuario.opinarSobre(muestraMock, opinionMock),
            "Se esperaba IllegalStateException si el usuario ya opinó"
        );
        
        assertEquals("Error: Ya ha opinado sobre esta muestra.", thrown.getMessage());
        
        // Verificar que ninguna otra acción se realizó
        verify(muestraMock, never()).cargarOpinion(any(Opinion.class));
        verify(categoriaBasicoMock, never()).verificarCategoria(any(Usuario.class), anyLong(), anyLong()); // verificarCategoria no debería ser llamado
        assertTrue(usuario.getOpinionesEmitidas().isEmpty()); // La opinión no se agregó
    }

    @Test
    void testUsuarioNoPuedeOpinarSobreSuPropiaMuestra() {
        
        when(muestraMock.getAutor()).thenReturn(usuario);
        
        when(muestraMock.yaOpino(usuario)).thenReturn(false); 
        
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> usuario.opinarSobre(muestraMock, opinionMock),
            "Se esperaba IllegalArgumentException si opina sobre su propia muestra"
        );
        
        assertEquals("Error: No se puede opinar sobre la propia muestra.", thrown.getMessage());
        
        // Verificar que ninguna otra acción se realizó
        verify(muestraMock, never()).cargarOpinion(any(Opinion.class));
        verify(opinionMock, never()).getFechaDeOpinion();
        verify(categoriaBasicoMock, never()).verificarCategoria(any(Usuario.class), anyLong(), anyLong());
        assertTrue(usuario.getOpinionesEmitidas().isEmpty());
    }

    @Test
    void testUsuarioPuedeOpinarConExitoSiLasValidacionesPasan() {
       
        when(muestraMock.yaOpino(usuario)).thenReturn(false);
        when(muestraMock.getAutor()).thenReturn(mock(Usuario.class)); // Autor es otro usuario
        
       
        doNothing().when(muestraMock).cargarOpinion(opinionMock); 

        usuario.opinarSobre(muestraMock, opinionMock);
        
        // Verificar que se realizó la carga de la opinión y se agregó
        verify(muestraMock, times(1)).cargarOpinion(opinionMock);
        assertTrue(usuario.getOpinionesEmitidas().contains(opinionMock));
        assertEquals(1, usuario.getOpinionesEmitidas().size());
        
        
    }

   

    // Tests de `enviarMuestra()`

    @Test
    void testUsuarioPuedeEnviarMuestra() {
        usuario.enviarMuestra(muestraMock);
        
        assertTrue(usuario.getMuestrasEmitidas().contains(muestraMock));
        assertEquals(1, usuario.getMuestrasEmitidas().size());
        
        
    }

   


    @Test
    void testVerificarCategoriaSiendoBasicoNoCambiaSiNoCumpleCondiciones() {
        // usuario ya es CategoriaBasico 
       
        for(int i=0; i <20; i++) {
        	usuario.agregarOpinion(opinionMock);
        }
        for(int i=0; i <10; i++) {
        	usuario.agregarMuestra(muestraMock);
        }
        usuario.opinarSobre(muestra, opinionMock);
  	
        assertFalse(usuario.esExperto());
    }

    
    @Test
    void testVerificarCategoriaSiendoExpertoCambiaABasico() {
       
        usuario.cambiarCategoria(new CategoriaExperto()); 
        
        usuario.verificarCategoriaSiendoExperto(10L, 5L); // Menos de 20 opiniones Y menos de 10 muestras

        assertTrue(usuario.getCategoria() instanceof CategoriaBasico);
    }

    @Test
    void testVerificarCategoriaSiendoExpertoNoCambiaSiCumpleCondiciones() {
        // Configurar el usuario para que sea experto inicialmente
        usuario.cambiarCategoria(new CategoriaExperto()); 
        
        usuario.verificarCategoriaSiendoExperto(25L, 12L); // Más de 20 opiniones Y más de 10 muestras

        assertTrue(usuario.getCategoria() instanceof CategoriaExperto);
    }
    
    @Test
    void testVerificarCategoriaSiendoExpertoPorUsuarioExpertoNoCambiaSiCumpleCondiciones() {
        // Configurar el usuario para que sea experto inicialmente
        personaExperta.cambiarCategoria(new CategoriaExperto()); 
        
        usuario.verificarCategoriaSiendoExperto(10L, 3L); 

        assertTrue(personaExperta.esExperto());
    }
}
