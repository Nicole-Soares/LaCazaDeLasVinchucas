package vinchuca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;


public class FiltroTest {


	
	@Test
	void testFiltroCreacion() {
		Muestra m = mock(Muestra.class);
		List<Muestra> muestras = Arrays.asList(m);                                                                                                                                                                                                                                                                          ;
		TipoFiltro tipoFiltro = mock(FiltroFechaMuestra.class);
		Filtro filtro = new Filtro( tipoFiltro);
		
		filtro.setFiltro(tipoFiltro);
		
		filtro.aplicarFiltro(muestras);
		
		verify(tipoFiltro).cumple(m);
		
	}
	
	
	@Test
	void testFiltroUltimaVotacion() {
		
		        Calendar cal = Calendar.getInstance();
		        cal.set(2025, Calendar.JUNE, 15);
		        
		        // 1. Obtén el Date de Calendar
		        Date fechaDate = cal.getTime(); 
		        
		        // 2. Convierte el Date a LocalDate
		        // Asegúrate de que la zona horaria sea consistente, ZoneId.systemDefault() es común.
		        LocalDate fechaFiltro = fechaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		        
		        Muestra m = mock(Muestra.class);
		        // List<Muestra> muestras = Arrays.asList(m); // 
		        
		        FechaUltimaVotacion filtro = new FechaUltimaVotacion(fechaFiltro);
		        
		        // TipoFiltro tipoFiltro = mock(FiltroFechaMuestra.class);
		        when(m.fechaUltimaOpinion()).thenReturn(fechaFiltro);
		        
	
		        assertFalse(filtro.cumple(m));
		        verify(m).fechaUltimaOpinion();
		    
	}
	
	@Test
	void testFiltroTipoInsecto() {
		
		Muestra m = mock(Muestra.class);                                                                                                                                                                                                                                                                      ;
		TipoInsecto filtro = new TipoInsecto("vinchuca");
		
		when(m.getEspecieDeVinchuca()).thenReturn("vinchuca");
		
		
		assertTrue(filtro.cumple(m));
		
	}
	
	
	@Test
	void testFiltroNivelDeVerificacion() {
		
		Muestra m = mock(Muestra.class);  
		EstadoBasico e = mock(EstadoBasico.class);    ;
		NivelDeVerificacion filtro = new NivelDeVerificacion("Basico");
		when(m.getEstado()).thenReturn(e);
		when(m.getEstado().nombre()).thenReturn("Basico");
		
		assertTrue(filtro.cumple(m));
		
	}
	
	@Test
	void testFiltroFechaMuestra() {
		 
		LocalDate fechaFiltro = LocalDate.now();;
		LocalDate fechaFiltroAntes = LocalDate.now().minusDays(1);
		
		Muestra m = mock(Muestra.class);                                                                                                                                                                                                                                                                  ;
		FiltroFechaMuestra filtro = new FiltroFechaMuestra(fechaFiltroAntes);
		
		when(m.getFechaCreacion()).thenReturn(fechaFiltro);
		
		assertTrue(filtro.cumple(m));
		
	}
	
	
	@Test
	void testFiltroCompuestoOR() {
		
		Muestra m = mock(Muestra.class);                       
		
		NivelDeVerificacion filtro1 = mock(NivelDeVerificacion.class);
		NivelDeVerificacion filtro2 = mock(NivelDeVerificacion.class);
	
		FiltroCompuesto filtro = new FiltroOR(filtro1,filtro2);
		
		when(filtro1.cumple(m)).thenReturn(true);
        when(filtro2.cumple(m)).thenReturn(true);
		
		assertTrue(filtro.cumple(m));
		
		
		verify(filtro1).cumple(m);
		
		
        when(filtro1.cumple(m)).thenReturn(false);
        when(filtro2.cumple(m)).thenReturn(true);
        

        assertTrue(filtro.cumple(m));


		
	}
	
	@Test
	void testFiltroCompuestoAND() {
		
		Muestra m = mock(Muestra.class);                       
		
		NivelDeVerificacion filtro1 = mock(NivelDeVerificacion.class);
		NivelDeVerificacion filtro2 = mock(NivelDeVerificacion.class);
	
		FiltroCompuesto filtro = new FiltroAND(filtro1,filtro2);
		
		when(filtro1.cumple(m)).thenReturn(true);
        when(filtro2.cumple(m)).thenReturn(false);
		
		assertFalse(filtro.cumple(m));
		
		
		verify(filtro1).cumple(m);
		
		
        when(filtro1.cumple(m)).thenReturn(true);
        when(filtro2.cumple(m)).thenReturn(true);
        

        assertTrue(filtro.cumple(m));


		
	}
	
	
}
