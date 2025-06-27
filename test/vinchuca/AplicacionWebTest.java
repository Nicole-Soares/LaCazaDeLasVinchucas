package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AplicacionWebTest {

	private AplicacionWeb aplicacionWeb;
	private Muestra muestra;
	private ZonaCobertura zona;
	private ZonaCobertura zona2;
	private Usuario usuario;
	private Opinion opinion;
	private Filtro filtro;
	
	
	
	@BeforeEach
	public void setup() {
		muestra = mock(Muestra.class);
		zona = mock(ZonaCobertura.class);
		opinion = mock(Opinion.class);
		zona2 = mock(ZonaCobertura.class);
		aplicacionWeb = new AplicacionWeb();
		
		usuario = mock(Usuario.class);
	
	}
	
	
	@Test
	public void testeandoElRegistroDeMuestra() {
		aplicacionWeb.registrarMuestra(muestra);
		assertEquals(aplicacionWeb.getMuestras().size(),1);
		
	}
	
	@Test
	public void testeandoElAvisarDeZona() {
		aplicacionWeb.registrarZonaDeCobertura(zona);
		aplicacionWeb.registrarMuestra(muestra);
		
		verify(zona, times(1)).avisarNuevaMuestra(muestra);
	}
	
	@Test
	public void testeandoElRegistroDeZona() {
		aplicacionWeb.registrarZonaDeCobertura(zona);
		assertEquals(aplicacionWeb.getZonas().size(),1);
	}
	
	@Test
	public void testeandoElRegistroDeUsuario() {
		aplicacionWeb.registrarUsuario(usuario);
		assertEquals(aplicacionWeb.getUsuarios().size(),1);
	}
	
	@Test
	public void testeandoElRegistroDeOpinion() {
		aplicacionWeb.registrarOpinion(opinion);
		assertEquals(aplicacionWeb.getOpiniones().size(),1);
	}
	
	 @Test
	    void testeandoZonasQueSolapan() {
	      	aplicacionWeb.registrarZonaDeCobertura(zona2);
	        when(zona2.seSolapaCon(zona)).thenReturn(true);

	        assertEquals(aplicacionWeb.zonasQueSolapan(zona).size(), 1);
	    }
	
	/* @Test
	    void testeandoFiltro() {
		 	List<Muestra> muestras= new ArrayList<>();
		 	muestras.add(muestra);
		 	aplicacionWeb.registrarMuestra(muestra);
	      	aplicacionWeb.filtrarMuestras(filtro);
	        when(filtro.aplicarFiltro(muestras)).thenReturn(muestra);

	        assertEquals(aplicacionWeb.zonasQueSolapan(zona).size(), 1);
	    }
	
	*/
}
