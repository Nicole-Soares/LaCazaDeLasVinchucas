package vinchuca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroMuestraExpertaTest {


	private FiltroVerificacion filtroMuestraExperta;
	private Muestra muestra;
	private Muestra muestra2;
	private Estado estadoBasico;
	private Estado estadoExperto;
	private Usuario personaBasica;
	private ObservadorMuestra manejador;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	
	  
	
	@BeforeEach
	public void setUp() {
		filtroMuestraExperta = new FiltroMuestraExperta();
		personaBasica = new Usuario("Daenerys");
		ubicacion = mock(Ubicacion.class);
    	fecha = LocalDate.now();
    	manejador = mock(ObservadorMuestra.class);//new ManejadorMuestra();
    	estadoBasico = new EstadoBasico();
    	estadoExperto = new EstadoExperto();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
    	muestra2 = new Muestra("chinche", "foto", fecha, ubicacion,estadoExperto, personaBasica, manejador);
	}
	
	@Test
	public void testeandoQueNoCumplaLaMuestra() {
		assertFalse(filtroMuestraExperta.cumple(muestra));
	}
	
	@Test
	public void testeandoQueCumplaLaMuestra() {
		assertTrue(filtroMuestraExperta.cumple(muestra2));
	}
}
