package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroNivelDeVerificacionTest {


	private FiltroVerificacion filtroMuestraVerificada;
	private FiltroVerificacion filtroMuestraExperta;
	private FiltroVerificacion filtroMuestraBasica;
	private FiltroNivelDeVerificacion filtroNivelDeVerificacion;
	private Muestra muestra;
	private Muestra muestra2;
	private Muestra muestra3;
	private Estado estadoBasico;
	private Estado estadoVerificado;
	private Estado estadoExperto;
	private Usuario personaBasica;
	private ObservadorMuestra manejador;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	
	  
	
	@BeforeEach
	public void setUp() {
		filtroMuestraVerificada = new FiltroMuestraVerificada();
		filtroMuestraExperta = new FiltroMuestraExperta();
		filtroMuestraBasica = new FiltroMuestraBasica();
		filtroNivelDeVerificacion = new FiltroNivelDeVerificacion(filtroMuestraVerificada);
		personaBasica = new Usuario("Daenerys");
		ubicacion = mock(Ubicacion.class);
    	fecha = LocalDate.now();
    	manejador = mock(ObservadorMuestra.class);
    	estadoBasico = new EstadoBasico();
    	estadoVerificado = new EstadoVerificado();
    	estadoExperto = new EstadoExperto();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
    	muestra2 = new Muestra("chinche", "foto", fecha, ubicacion,estadoVerificado, personaBasica, manejador);
    	muestra3 = new Muestra("chinche", "foto", fecha, ubicacion,estadoExperto, personaBasica, manejador);
	}
	
	@Test
	public void testeandoQueCumplaLaMuestraConFiltroBasico() {
		assertTrue(filtroMuestraBasica.cumple(muestra));
	}
	@Test
	public void testeandoQueCumplaLaMuestraExperto() {
		assertTrue(filtroMuestraExperta.cumple(muestra3));
	}
	
	@Test
	public void testeandoQueCumplaLaMuestraVerificaa() {
		assertTrue(filtroMuestraVerificada.cumple(muestra2));
	}
	
	@Test
	public void testeandoCambioDeFiltroDeVerificacion() {
		filtroNivelDeVerificacion.cambiarFiltroVerificacion(filtroMuestraBasica);
		assertEquals(filtroNivelDeVerificacion.getFiltoVerificacion(), filtroMuestraBasica);
	}
}
