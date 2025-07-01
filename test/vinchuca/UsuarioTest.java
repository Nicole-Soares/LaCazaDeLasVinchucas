package vinchuca; // Asegúrate de que el paquete sea el correcto

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays; 

public class UsuarioTest {

    private Usuario usuario;
    private Muestra muestraMock;
    private Opinion opinionMock;
    private CategoriaBasico categoriaBasicoMock; 
    private CategoriaExperto categoriaExpertoMock; 
    private LocalDate fechaActual;

    @BeforeEach
    void setUp() {
        
        usuario = new Usuario("Rhaenyra");

       
        muestraMock = mock(Muestra.class);
        opinionMock = mock(Opinion.class);
        categoriaBasicoMock = mock(CategoriaBasico.class); 
        categoriaExpertoMock = mock(CategoriaExperto.class); 
        fechaActual = LocalDate.now();

      
        when(opinionMock.getFechaDeOpinion()).thenReturn(fechaActual); 
        when(muestraMock.getFechaCreacion()).thenReturn(fechaActual);   
    }

   

 

    @Test
    void testUsuarioSeInicializaCorrectamente() {
       
        Usuario nuevoUsuario = new Usuario("Daenerys"); 

        assertNotNull(nuevoUsuario.getOpinionesEmitidas());
        assertTrue(nuevoUsuario.getOpinionesEmitidas().isEmpty());
        assertNotNull(nuevoUsuario.getMuestrasEmitidas());
        assertTrue(nuevoUsuario.getMuestrasEmitidas().isEmpty());
        
        assertEquals(nuevoUsuario.getNombre(), "Daenerys");
    }

    @Test
    void testGetOpinionesEmitidas() {
        usuario.agregarOpinion(opinionMock);
        assertEquals(1, usuario.getOpinionesEmitidas().size());
        assertTrue(usuario.getOpinionesEmitidas().contains(opinionMock));
    }

    @Test
    void testGetMuestrasEmitidas() {
        usuario.agregarMuestra(muestraMock);
        assertEquals(1, usuario.getMuestrasEmitidas().size());
        assertTrue(usuario.getMuestrasEmitidas().contains(muestraMock));
    }

    @Test
    void testCambiarCategoria() {
        // Asumiendo que el usuario ya está mockeado con categoriaBasicoMock en setUp
        usuario.cambiarCategoria(categoriaExpertoMock);
        assertEquals(categoriaExpertoMock, usuario.getCategoria());
    }

   

    @Test
    void testEsExpertoCuandoCategoriaEsExperto() {
       
        usuario.cambiarCategoria(categoriaExpertoMock);
        when(categoriaExpertoMock.esExperto()).thenReturn(true);

        assertTrue(usuario.esExperto());
        verify(categoriaExpertoMock, times(1)).esExperto(); 
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
        // Configurar la muestra para que el autor sea el propio usuario que intenta opinar
        when(muestraMock.getAutor()).thenReturn(usuario);
        // También aseguramos que no haya opinado antes, para que esta sea la excepción que se lance
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
        // Configurar para que las validaciones pasen
        when(muestraMock.yaOpino(usuario)).thenReturn(false);
        when(muestraMock.getAutor()).thenReturn(mock(Usuario.class)); // Autor es otro usuario
        
        // Simular el comportamiento de cargarOpinion en la muestra
        doNothing().when(muestraMock).cargarOpinion(opinionMock); // No hace nada

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

   

    // Tests de `verificarCategoria()`

    @Test
    void testVerificarCategoriaDelegaACategoriaActual() {
        // Configurar algunas opiniones y muestras para que verificarCategoria las cuente
        // No importa tanto el contenido, sino que se llamen a los getters correctos
        Opinion opinionAntigua = mock(Opinion.class);
        when(opinionAntigua.getFechaDeOpinion()).thenReturn(fechaActual.minusDays(31)); // Fuera de los 30 días
        Opinion opinionReciente1 = mock(Opinion.class);
        when(opinionReciente1.getFechaDeOpinion()).thenReturn(fechaActual.minusDays(10)); // Dentro de los 30 días
        Opinion opinionReciente2 = mock(Opinion.class);
        when(opinionReciente2.getFechaDeOpinion()).thenReturn(fechaActual.minusDays(5));  // Dentro de los 30 días

        Muestra muestraAntigua = mock(Muestra.class);
        when(muestraAntigua.getFechaCreacion()).thenReturn(fechaActual.minusDays(40)); // Fuera
        Muestra muestraReciente1 = mock(Muestra.class);
        when(muestraReciente1.getFechaCreacion()).thenReturn(fechaActual.minusDays(20)); // Dentro

        usuario.agregarOpinion(opinionAntigua);
        usuario.agregarOpinion(opinionReciente1);
        usuario.agregarOpinion(opinionReciente2);
        usuario.agregarMuestra(muestraAntigua);
        usuario.agregarMuestra(muestraReciente1);

        // Llamar al método a testear
        usuario.verificarCategoria();

        // Verificar que la categoría actual fue llamada con los conteos correctos
        // En este caso: 2 opiniones recientes, 1 muestra reciente
        verify(categoriaBasicoMock, times(1)).verificarCategoria(usuario, 2L, 1L);
    }
    
    // Tests para verificarCategoriaSiendoBasico (asumiendo que estos métodos son llamados por CategoriaBasico)
    @Test
    void testVerificarCategoriaSiendoBasicoCambiaAExperto() {
        // usuario ya es CategoriaBasico (mockeado)
        usuario.verificarCategoriaSiendoBasico(25L, 12L); // 25 opiniones > 20, 12 muestras > 10

        // Verificar que el usuario cambió su categoría a Experto
        // Como estamos testando Usuario, y CategoriaBasico lo llama,
        // esperamos que Usuario cambie su propia categoría.
        assertTrue(usuario.getCategoria() instanceof CategoriaExperto);
    }

    @Test
    void testVerificarCategoriaSiendoBasicoNoCambiaSiNoCumpleCondiciones() {
        // usuario ya es CategoriaBasico (mockeado)
        usuario.verificarCategoriaSiendoBasico(15L, 5L); // No cumple ninguna condición
        
        // La categoría sigue siendo CategoriaBasico (el mock original, o si se restablece en el constructor)
        // Para este test, necesitamos que el mock inicial no sea reemplazado por otro mock,
        // o que el constructor del usuario se use para que inicialice CategoriaBasico real.
        // Aquí, como el usuario fue mockeado para usar 'categoriaBasicoMock',
        // verificamos que no se haya llamado a 'cambiarCategoria' para cambiar a Experto.
        // O más fácil: crear un usuario real en el setup para este tipo de test.
        // Vamos a re-inicializar el usuario para este test para que use un CategoriaBasico real.
        Usuario usuarioReal = new Usuario("Federico");

        usuarioReal.verificarCategoriaSiendoBasico(15L, 5L);
        assertTrue(usuarioReal.getCategoria() instanceof CategoriaBasico);
    }

    // Tests para verificarCategoriaSiendoExperto
    @Test
    void testVerificarCategoriaSiendoExpertoCambiaABasico() {
        // Configurar el usuario para que sea experto inicialmente
        usuario.cambiarCategoria(new CategoriaExperto()); // Usamos una instancia real para este test
        
        usuario.verificarCategoriaSiendoExperto(10L, 5L); // Menos de 20 opiniones Y menos de 10 muestras

        assertTrue(usuario.getCategoria() instanceof CategoriaBasico);
    }

    @Test
    void testVerificarCategoriaSiendoExpertoNoCambiaSiCumpleCondiciones() {
        // Configurar el usuario para que sea experto inicialmente
        usuario.cambiarCategoria(new CategoriaExperto()); // Usamos una instancia real para este test
        
        usuario.verificarCategoriaSiendoExperto(25L, 12L); // Más de 20 opiniones Y más de 10 muestras

        assertTrue(usuario.getCategoria() instanceof CategoriaExperto);
    }
}
