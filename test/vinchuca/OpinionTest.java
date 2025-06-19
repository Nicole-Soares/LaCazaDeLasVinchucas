package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpinionTest {

	    private TipoDeOpinion mockTipoDeOpinion;
	    private Persona mockPersona;
	    private Categoria mockCategoria; // Mock para la categoría de la persona

	    
	    private LocalDate fechaFija;

	    @BeforeEach // Este método se ejecuta antes de cada test
	    void setUp() {
	        // Inicializar los mocks
	        mockTipoDeOpinion = mock(TipoDeOpinion.class);
	        mockPersona = mock(Persona.class);
	        mockCategoria = mock(Categoria.class);
	        fechaFija = LocalDate.of(2023, 1, 15); 

	        // Configurar mockPersona para que devuelva mockCategoria cuando se llama a getCategoria()
	        // Esto es importante porque el constructor de Opinion accede a persona.categoria
	        when(mockPersona.getCategoria()).thenReturn(mockCategoria);
	        // También simulamos que la categoría de la persona es la mockCategoria
	        // (asumiendo que 'categoria' es un campo accesible o se inicializa así en Persona)
	        mockPersona.categoria = mockCategoria;
	    }

	    @Test
	    void testConstructorCreaOpinionConValoresCorrectos() {
	        // Ejercitar: Crear una instancia de Opinion
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Verificar: Asegurarse de que se creó correctamente y los getters devuelven los valores esperados
	        assertNotNull(opinion);
	        assertEquals(mockTipoDeOpinion, opinion.getTipo());
	        assertEquals(fechaFija, opinion.getFechaDeOpinion());
	        assertEquals(mockPersona, opinion.getPersona());
	        // La categoría de la opinión debe ser la categoría de la persona en el momento de la creación
	        assertEquals(mockCategoria, opinion.getCategoria());
	    }

	    @Test
	    void testConstructorLanzaExcepcionCuandoTipoDeOpinionEsNulo() {
	        // Verificar que se lanza una IllegalArgumentException
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Opinion(null, fechaFija, mockPersona);
	        });
	    }

	    @Test
	    void testConstructorLanzaExcepcionCuandoFechaEsNula() {
	        // Verificar que se lanza una IllegalArgumentException
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Opinion(mockTipoDeOpinion, null, mockPersona);
	        });
	    }

	    @Test
	    void testConstructorLanzaExcepcionCuandoPersonaEsNula() {
	        // Verificar que se lanza una IllegalArgumentException
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Opinion(mockTipoDeOpinion, fechaFija, null);
	        });
	    }

	    @Test
	    void testGetTipoDevuelveTipoCorrecto() {
	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Ejercitar y Verificar
	        assertEquals(mockTipoDeOpinion, opinion.getTipo());
	    }

	    @Test
	    void testGetFechaDeOpinionDevuelveFechaCorrecta() {
	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Ejercitar y Verificar
	        assertEquals(fechaFija, opinion.getFechaDeOpinion());
	    }

	    @Test
	    void testGetPersonaDevuelvePersonaCorrecta() {
	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Ejercitar y Verificar
	        assertEquals(mockPersona, opinion.getPersona());
	    }

	    @Test
	    void testGetCategoriaDevuelveCategoriaDeLaPersonaAlMomentoDeCreacion() {
	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Ejercitar y Verificar
	        assertEquals(mockCategoria, opinion.getCategoria());
	    }

	    @Test
	    void testSetCategoriaCambiaLaCategoriaDeLaOpinion() {
	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);
	        Categoria nuevaMockCategoria = mock(Categoria.class); // Una nueva categoría para establecer

	        // Ejercitar
	        opinion.setCategoria(nuevaMockCategoria);

	        // Verificar
	        assertEquals(nuevaMockCategoria, opinion.getCategoria());
	    }

	    @Test
	    void testEsOpinionDeExpertoRetornaTrueCuandoCategoriaEsExperto() {
	        // Configurar el mockCategoria para que 'esExperto()' devuelva true
	        when(mockCategoria.esExperto()).thenReturn(true);

	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Ejercitar y Verificar
	        assertTrue(opinion.esOpinionDeExperto());
	        // Verificamos que se llamó a esExperto() en la categoría asociada a la opinión
	        verify(mockCategoria, times(1)).esExperto();
	    }

	    @Test
	    void testEsOpinionDeExpertoRetornaFalseCuandoCategoriaNoEsExperto() {
	        // Configurar el mockCategoria para que 'esExperto()' devuelva false
	        when(mockCategoria.esExperto()).thenReturn(false);

	        // Configuración
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Ejercitar y Verificar
	        assertFalse(opinion.esOpinionDeExperto());
	        // Verificamos que se llamó a esExperto() en la categoría asociada a la opinión
	        verify(mockCategoria, times(1)).esExperto();
	    }

	    @Test
	    void testEsOpinionDeExpertoNoSeVeAfectadaPorCambioPosteriorDeCategoriaDePersona() {
	        // Configurar la categoría inicial de la persona como Experto
	        when(mockCategoria.esExperto()).thenReturn(true);
	        Opinion opinion = new Opinion(mockTipoDeOpinion, fechaFija, mockPersona);

	        // Simular un cambio en la categoría de la persona DESPUÉS de crear la opinión
	        Categoria otraMockCategoria = mock(Categoria.class);
	        when(otraMockCategoria.esExperto()).thenReturn(false); // Esta nueva categoría no es experta
	        mockPersona.categoria = otraMockCategoria; // La persona ahora tiene otra categoría

	        // Verificar que la opinión sigue reflejando la categoría original al momento de su creación
	        // y no la categoría actual de la persona.
	        assertTrue(opinion.esOpinionDeExperto());
	        // Asegurarse de que el método esExperto() se llamó en la *categoría original* de la opinión
	        verify(mockCategoria, times(1)).esExperto();
	        // Asegurarse de que el método esExperto() NO se llamó en la *nueva categoría* de la persona
	        verify(otraMockCategoria, never()).esExperto();
	    }
	
}
