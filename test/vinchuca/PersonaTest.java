package vinchuca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PersonaTest {

	
	
	@Test
	public void testEsPersonaBasico() {
		
		Persona p = new Persona();
		
		assertTrue(p.esBasico());
		
		assertTrue(p.getCategoria().esBasico());
	}
	
	
	@Test
	public void testEsPersonaExperto() {
		
		Muestra m = mock(Muestra.class);
		List<Muestra> muestras = Arrays.asList(m); 
		Opinion o = mock(Opinion.class);
		List<Opinion> opiniones = Arrays.asList(o); 
		
		Persona p = new Persona(opiniones, muestras);
		
		Experto e = mock(Experto.class);
		
		when(e.esExperto()).thenReturn(true);
		
		p.cambiarCategoria(e);
		
		assertTrue(p.esExperto());
		
	}
	
	@Test
	public void testPersonaTieneUnaOpinionYUnaMuestraEmitida() {
		
		Muestra m = mock(Muestra.class);
		List<Muestra> muestras = Arrays.asList(m); 
		Opinion o = mock(Opinion.class);
		List<Opinion> opiniones = Arrays.asList(o); 
		
		Persona p = new Persona(opiniones, muestras);
		
		assertEquals(1, p.getOpinionesEmitidas().size());
		assertEquals(1, p.getMuestrasEmitidas().size());
	}
	
	@Test
	public void testPersonaAgregaUnaMuestraYOpinion() {
		
		
		Muestra m = mock(Muestra.class);
		Opinion o = mock(Opinion.class);
		
		List<Muestra> muestras = new ArrayList<>();
		List<Opinion> opiniones = new ArrayList<>();
		
		muestras.add(m);
		opiniones.add(o);
		
		Persona p = new Persona(opiniones, muestras);
		
		
		Muestra m2 = mock(Muestra.class);
		Opinion o2 = mock(Opinion.class);
		
		p.agregarMuestra(m2);
		p.agregarOpinion(o2);
		
		assertEquals(2, p.getOpinionesEmitidas().size());
		assertEquals(2, p.getMuestrasEmitidas().size());
		
		
		
	}
	
	
	@Test
    public void testVerificarCategoriaCambiaACategoriaExperto() {
		
        Opinion opinionReciente = mock(Opinion.class);
        when(opinionReciente.getFechaDeOpinion()).thenReturn((LocalDate.now().minusDays(5)));

        Muestra muestraReciente = mock(Muestra.class);
        when(muestraReciente.getFechaCreacion()).thenReturn((LocalDate.now().minusDays(5)));

    
        List<Opinion> opiniones = new ArrayList<>();
        List<Muestra> muestras = new ArrayList<>();
        
        for (int i = 0; i < 21; i++) {
            opiniones.add(opinionReciente);
        }

        
        for (int i = 0; i < 11; i++) {
            muestras.add(muestraReciente);
        }

      
        Persona persona = new Persona(opiniones, muestras);

        assertFalse(persona.esExperto());

        persona.verificarCategoria();

        assertTrue(persona.esExperto());
        assertFalse(persona.esBasico());
    }
	
	@Test
	public void testPersonaEnviaUnaMuestraYOpina() {
		
		List<Opinion> opiniones = new ArrayList<>();
		List<Muestra> muestras = new ArrayList<>();
		Opinion o = mock(Opinion.class);
		Muestra m = mock(Muestra.class);
		
		Persona p = new Persona(opiniones, muestras);
		
		when(m.getFechaCreacion()).thenReturn((LocalDate.now().minusDays(5)));
		when(o.getFechaDeOpinion()).thenReturn((LocalDate.now().minusDays(5)));
		
		p.enviarMuestra(m);
		p.opinarSobre(m, o);
		
		assertEquals(1, p.getOpinionesEmitidas().size());
		assertEquals(1, p.getMuestrasEmitidas().size());
		
		
	}
	
	
	@Test
	public void testUnProfesionalEsExpertoSiempre() {
		
		List<Opinion> opiniones = new ArrayList<>();
		List<Muestra> muestras = new ArrayList<>();
		Basico b = mock(Basico.class);
		Opinion o = mock(Opinion.class);
		Muestra m = mock(Muestra.class);
		
		Profesional p = new Profesional();
		Profesional p2 = new Profesional(opiniones, muestras);
		
		
		assertTrue(p.esExperto());
		
		 for (int i = 0; i < 21; i++) {
	            opiniones.add(o);
	        }

	        
		 for (int i = 0; i < 11; i++) {
	         	muestras.add(m);
	        }
		
		when(m.getFechaCreacion()).thenReturn((LocalDate.now().minusDays(5)));
		when(o.getFechaDeOpinion()).thenReturn((LocalDate.now().minusDays(5)));
		
		p.verificarCategoria();
		
		assertTrue(p.esExperto());
		
		p.cambiarCategoria(b);
	}
	
	
	
	
}
