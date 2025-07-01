package vinchuca;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

public class FiltroMuestraBasicaTest {

	private FiltroVerificacion filtroMuestraBasica;
	private Muestra muestra;
	private Estado estadoBasico;
	private Usuario personaBasica;
	private ObservadorMuestra manejador;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	
	  
	
	@BeforeEach
	public void setUp() {
		filtroMuestraBasica = new FiltroMuestraBasica();
		personaBasica = new Usuario("Daenerys");
		ubicacion = mock(Ubicacion.class);
    	fecha = LocalDate.now();
    	manejador = mock(ObservadorMuestra.class);//new ManejadorMuestra();
    	estadoBasico = new EstadoBasico();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
	       
	}
	
	public void testeandoQueCumplaLaMuestra() {
		assertTrue(filtroMuestraBasica.cumple(muestra));
	}
}
