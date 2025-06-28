package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpinionTest {

    private Opinion opinion;
    private TipoDeOpinion tipo;
    private Usuario usuario;
    private CategoriaUsuario categoriaBasica; 
    private CategoriaUsuario categoriaExperto; 
    private Muestra muestra; 
    private Opinion otraOpinion; 

    @BeforeEach
    void setUp() {
        // Mocks básicos para las dependencias de Opinion
        tipo = mock(TipoDeOpinion.class);
        usuario = mock(Usuario.class);
        categoriaBasica = mock(CategoriaBasico.class); 
        categoriaExperto = mock(CategoriaExperto.class); 
        muestra = mock(Muestra.class);
        otraOpinion = mock(Opinion.class);

        
        when(usuario.getCategoria()).thenReturn(categoriaBasica); // Por defecto, es básica
    }

   
    @Test
    void testConstructorAndGetters() {
        LocalDate fecha = LocalDate.of(2023, 10, 26);
        
        // Crear la Opinion
        opinion = new Opinion(tipo, fecha, usuario);

        // Verificar que los getters devuelven los valores correctos
        assertEquals(tipo, opinion.getTipo());
        assertEquals(fecha, opinion.getFechaDeOpinion());
        assertEquals(usuario, opinion.getPersona());
        // Verificar que la categoría se inicializó correctamente desde el usuario
        // Nota: no tenemos un getCategoria() público en Opinion, lo cual es bueno para encapsulamiento.
        // Pero sí podemos verificar que esExperto() funcione como esperamos.
    }

    // --- Tests de esExperto() ---
    @Test
    void testEsExpertoWhenCategoriaEsExperto() {
        // Configurar el usuario para que devuelva una categoría de experto
        when(usuario.getCategoria()).thenReturn(categoriaExperto);
        when(categoriaExperto.esExperto()).thenReturn(true); // La categoría de experto dice que es experto

        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        assertTrue(opinion.esExperto(), "Una opinión de un experto debería devolver true en esExperto()");
        verify(categoriaExperto, times(1)).esExperto(); // Verificamos que se llamó a esExperto() en la categoría
    }

    @Test
    void testEsExpertoWhenCategoriaEsBasica() {
        // Ya configurado por defecto el usuario para que devuelva categoriaBasicaMock
        when(usuario.getCategoria()).thenReturn(categoriaBasica);
        when(categoriaBasica.esExperto()).thenReturn(false); // La categoría básica dice que NO es experto

        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        assertFalse(opinion.esExperto(), "Una opinión de un usuario básico debería devolver false en esExperto()");
        verify(categoriaBasica, times(1)).esExperto(); // Verificamos que se llamó a esExperto() en la categoría
    }

    // --- Tests de Double Dispatch para cargarOpinion en MuestraBasica ---
    @Test
    void testEvaluarParaCargarOpinionMuestraBasicaDelegatesToCategoria() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario); // usuarioMock tiene categoriaBasicaMock por defecto

        // Llamar al método de Opinion
        opinion.evaluarParaCargarOpinionMuestraBasica(muestra);

        // Verificar que Opinion delegó la llamada a categoriaBasicaMock
        // El any(Opinion.class) es crucial porque Opinion se pasa a sí misma como argumento
        verify(categoriaBasica, times(1)).evaluarParaCargarOpinionMuestraBasica(muestra, opinion);
        verifyNoMoreInteractions(categoriaBasica); // Asegura que no se llamaron otros métodos inesperados
    }

    @Test
    void testProcesarCargaEnMuestraBasicaConOpinionExpertoDelegatesToMuestra() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario); // La categoría no importa aquí, solo la llamada

        // Llamar al método de Opinion
        opinion.procesarCargaEnMuestraBasicaConOpinionExperto(muestra);

        // Verificar que Opinion delegó la llamada a muestraMock
        verify(muestra, times(1)).procesarCargaEnMuestraBasicaConOpinionExperto();
        verifyNoMoreInteractions(muestra);
    }

    @Test
    void testProcesarCargaEnMuestraBasicaConOpinionBasicaDelegatesToMuestra() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        // Llamar al método de Opinion
        opinion.procesarCargaEnMuestraBasicaConOpinionBasica(muestra);

        // Verificar que Opinion delegó la llamada a muestraMock
        verify(muestra, times(1)).procesarCargaEnMuestraBasicaConOpinionBasica();
        verifyNoMoreInteractions(muestra);
    }

    // --- Tests de Double Dispatch para cargarOpinion en MuestraExperto ---
    @Test
    void testEvaluarParaCargarOpinionMuestraExpertoDelegatesToCategoria() {
        // Configurar para que el usuario sea de tipo experto, si queremos simular ese flujo
        when(usuario.getCategoria()).thenReturn(categoriaExperto);
        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        // Llamar al método de Opinion
        opinion.evaluarParaCargarOpinionMuestraExperto(muestra);

        // Verificar que Opinion delegó la llamada a categoriaExpertoMock
        verify(categoriaExperto, times(1)).evaluarParaCargarOpinionMuestraExperto(muestra, opinion);
        verifyNoMoreInteractions(categoriaExperto);
    }

    @Test
    void testProcesarCargaEnMuestraExpertaConOpinionExpertoDelegatesToMuestra() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario); // La categoría no importa aquí

        // Llamar al método de Opinion
        opinion.procesarCargaEnMuestraExpertaConOpinionExperto(muestra);

        // Verificar que Opinion delegó la llamada a muestraMock, pasándose a sí misma
        verify(muestra, times(1)).procesarCargaEnMuestraExpertaConOpinionExperto(opinion);
        verifyNoMoreInteractions(muestra);
    }

    @Test
    void testProcesarCargaEnMuestraExpertaConOpinionBasicaDelegatesToMuestra() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        // Llamar al método de Opinion
        opinion.procesarCargaEnMuestraExpertaConOpinionBasica(muestra);

        // Verificar que Opinion delegó la llamada a muestraMock, pasándose a sí misma
        verify(muestra, times(1)).procesarCargaEnMuestraExpertaConOpinionBasica(opinion);
        verifyNoMoreInteractions(muestra);
    }

    // --- Tests de Double Dispatch para evaluarOpinion ---
    @Test
    void testEvaluarOpinionDelegatesToCategoria() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario); // Por defecto es básica

        // Llamar al método de Opinion
        opinion.evaluarOpinion(muestra, otraOpinion);

        // Verificar que Opinion delegó la llamada a categoriaBasicaMock, pasando todos los argumentos
        verify(categoriaBasica, times(1)).evaluarOpinion(muestra, opinion, otraOpinion);
        verifyNoMoreInteractions(categoriaBasica);
    }
    
    @Test
    void testEvaluadaEnExpertaDelegatesToMuestra() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        // Llamar al método de Opinion
        opinion.evaluadaEnExperta(muestra, otraOpinion);

        // Verificar que Opinion delegó la llamada a muestraMock
        verify(muestra, times(1)).chequearConOpinionExperta(opinion, otraOpinion);
        verifyNoMoreInteractions(muestra);
    }

    @Test
    void testEvaluadaEnBasicaDelegatesToMuestra() {
        opinion = new Opinion(tipo, LocalDate.now(), usuario);

        // Llamar al método de Opinion
        opinion.evaluadaEnBasica(muestra, otraOpinion);

        // Verificar que Opinion delegó la llamada a muestraMock
        verify(muestra, times(1)).chequearConOpinionBasica(opinion, otraOpinion);
        verifyNoMoreInteractions(muestra);
    }
}