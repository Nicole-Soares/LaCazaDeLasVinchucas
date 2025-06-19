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
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpinionTest {

	    private TipoDeOpinion tipoDeOpinion;
	    private Persona mockPersona;
	    private Categoria mockCategoria; 
	    private Opinion opinion;
	    private LocalDate fecha;

	    @BeforeEach 
	    void setUp() {
	       
	        tipoDeOpinion = TipoDeOpinion.VINCHUCA;
	        mockPersona = mock(Persona.class);
	        mockCategoria = mock(Categoria.class);
	        fecha = LocalDate.of(2023, 1, 15); 
	       
	      
	    }

	    @Test
	    void testeandoGetters() {
	    	when(mockPersona.getCategoria()).thenReturn(mockCategoria);
	    	opinion = new Opinion(tipoDeOpinion, fecha, mockPersona);
	    	assertEquals(opinion.getTipo(), TipoDeOpinion.VINCHUCA); // Ahora esto debe pasar si lo configuraste bien en setUp
        	assertEquals(opinion.getFechaDeOpinion(), fecha);
        	assertEquals(opinion.getPersona(), mockPersona);
        	assertEquals(opinion.getCategoria(), mockCategoria);
	    }

	  

	    @Test
	    void testEsOpinionDeExpertoRetornaTrueCuandoCategoriaEsExperto() {
	    
	         when(mockPersona.getCategoria()).thenReturn(mockCategoria);
	         when(mockCategoria.esExperto()).thenReturn(true);
	         // Creamos la opinión DESPUÉS de configurar el comportamiento de la persona
	         opinion = new Opinion(tipoDeOpinion, fecha, mockPersona);

	        assertTrue(opinion.esOpinionDeExperto());
	        
	     }

	   @Test
	    void testEsOpinionDeExpertoRetornaFalseCuandoCategoriaNoEsExperto() {
	      
	         when(mockPersona.getCategoria()).thenReturn(mockCategoria);
	         when(mockCategoria.esExperto()).thenReturn(false);
	         // Creamos la opinión DESPUÉS de configurar el comportamiento de la persona
	         opinion = new Opinion(tipoDeOpinion, fecha, mockPersona);

	       
	         assertFalse(opinion.esOpinionDeExperto());
	       
	    }

	    @Test
	    void testEsOpinionDeExpertoNoSeVeAfectadaPorCambioPosteriorDeCategoriaDePersona() {
	    
	    	Categoria mockCategoriaExperto = mock(Categoria.class);
	        when(mockCategoriaExperto.esExperto()).thenReturn(true);

	        // 2. Configurar mockPersona para que, al inicio, devuelva la Categoria Experto (A)
	        when(mockPersona.getCategoria()).thenReturn(mockCategoriaExperto);
	        
	        
	        Opinion opinion = new Opinion(tipoDeOpinion, fecha, mockPersona); 
	        
	        Categoria mockCategoriaNoExperto = mock(Categoria.class);
	        when(mockCategoriaNoExperto.esExperto()).thenReturn(false);

	        
	        when(mockPersona.getCategoria()).thenReturn(mockCategoriaNoExperto); 

	       
	        assertTrue(opinion.esOpinionDeExperto()); // <--- ESTO DEBERÍA SER TRUE
	        
	       
	        verify(mockPersona, atLeastOnce()).getCategoria(); 
	       
	        verify(mockCategoriaExperto).esExperto();
	        
	       
	    }
	
}
