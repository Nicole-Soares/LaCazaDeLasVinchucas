package vinchuca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UsuarioTest {

	
	@Test
	public void testEsUsuarioBasico() {
		
		Usuario p = new Usuario();
		
		assertTrue(p.esBasico());
		
		
	}
	
	
	@Test
	public void testEsUsuarioProfesional() {
		
		Usuario p = new UsuarioProfesional();
		
		assertFalse(p.esBasico());
		
		
	}
	
	
	@Test
	public void testPersonaTieneUnaOpinionYUnaMuestraEmitida() {
		
		Muestra m = mock(Muestra.class);
		Opinion o = mock(Opinion.class);
		
		
		Usuario p = new Usuario();
		p.agregarMuestra(m);
		p.agregarOpinion(o);
		
		assertEquals(1, p.getOpinionesEmitidas().size());
		assertEquals(1, p.getMuestrasEmitidas().size());
	}

	@Test
	public void testPersonaEnviaUnaMuestraYOpina() {
		
		//List<Opinion> opiniones = new ArrayList<>();
		//List<Muestra> muestras = new ArrayList<>();
		Usuario autorMuestra = new Usuario();
		Opinion o = mock(Opinion.class);
		Muestra m = mock(Muestra.class);
		
		Usuario p = new Usuario();
		
		when(m.getFechaCreacion()).thenReturn((LocalDate.now().minusDays(5)));
		when(m.getAutor()).thenReturn(autorMuestra);
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
		CategoriaBasico b = mock(CategoriaBasico.class);
		Opinion o = mock(Opinion.class);
		Muestra m = mock(Muestra.class);
		
		UsuarioProfesional p = new UsuarioProfesional();
		//UsuarioProfesional p2 = new UsuarioProfesional();
		
		
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
	
	
	
	@Test
    public void testVerificarCategoriaCambiaACategoriaExperto() {
		
        Usuario persona = new Usuario();
        
        // Creamos 21 opiniones de la persona
        for (int i = 0; i < 21; i++) {
            Opinion opinionReciente = mock(Opinion.class);
            when(opinionReciente.getFechaDeOpinion()).thenReturn((LocalDate.now().minusDays(5)));
            // Agrego opinion sin verificar
            persona.agregarOpinion(opinionReciente);
        }

        // Creamos 10 muestras de la persona
        for (int i = 0; i < 10; i++) {
            Muestra muestraReciente = mock(Muestra.class);
            when(muestraReciente.getFechaCreacion()).thenReturn((LocalDate.now().minusDays(5)));
            when(muestraReciente.getAutor()).thenReturn(persona);
            // Agrego muestra sin verificar
            persona.agregarMuestra(muestraReciente);
        }
        
        // Verifico para chequear que siga siendo Basico, ya que le falta una muestra
        // para ser experto
        persona.verificarCategoria();
        assertFalse(persona.esExperto());

        // Creo y envio la muestra que le falta a la persona para ser experta
        Muestra muestraReciente = mock(Muestra.class);
        when(muestraReciente.getFechaCreacion()).thenReturn((LocalDate.now().minusDays(5)));
        when(muestraReciente.getAutor()).thenReturn(persona);
        persona.enviarMuestra(muestraReciente);
        
        // La persona deberia ser experta
        assertTrue(persona.esExperto());
        assertFalse(persona.esBasico());
        
        
    }
	
	/*
	@Test
	public void testVerificarQuePersonaNoPuedeOpinarEnSuMuestra() {
        Usuario persona = new Usuario();
        
        // Creo la muestra de la persona
		Muestra muestraReciente = mock(Muestra.class);
        when(muestraReciente.getAutor()).thenReturn(persona);
        persona.enviarMuestra(muestraReciente);
        
        // Creo la opinion de la persona
        Opinion opinionReciente = mock(Opinion.class);
        when(opinionReciente.getFechaDeOpinion()).thenReturn((LocalDate.now().minusDays(5)));
        when(opinionReciente.getPersona()).thenReturn(persona);
        // Agrego opinion sin verificar
        persona.opinarSobre(muestraReciente, opinionReciente);
        
        
	}
	
	@Test
	public void testVerificarQueNoSePuedeOpinarMasDeUnaVezEnMuestra() {
		
	}
	*/
	
	
	
	
}
