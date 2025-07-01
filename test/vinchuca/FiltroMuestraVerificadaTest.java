package vinchuca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroMuestraVerificadaTest {

	private FiltroVerificacion filtroMuestraVerificada;
	private Muestra muestra;
	private Muestra muestra2;
	private Estado estadoBasico;
	private Estado estadoVerificado;
	private Usuario personaBasica;
	private ObservadorMuestra manejador;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	
	  
	
	@BeforeEach
	public void setUp() {
		filtroMuestraVerificada = new FiltroMuestraVerificada();
		personaBasica = new Usuario("Daenerys");
		ubicacion = mock(Ubicacion.class);
    	fecha = LocalDate.now();
    	manejador = mock(ObservadorMuestra.class);//new ManejadorMuestra();
    	estadoBasico = new EstadoBasico();
    	estadoVerificado = new EstadoVerificado();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
    	muestra2 = new Muestra("chinche", "foto", fecha, ubicacion,estadoVerificado, personaBasica, manejador);
	}
	
	@Test
	public void testeandoQueNoCumplaLaMuestra() {
		assertFalse(filtroMuestraVerificada.cumple(muestra));
	}
	
	@Test
	public void testeandoQueCumplaLaMuestra() {
		assertTrue(filtroMuestraVerificada.cumple(muestra2));
	}
}
