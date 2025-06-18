package vinchuca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManejadorMuestraTest {

	

    private Muestra muestra;
    private ManejadorMuestra manejador;
    private ZonaCobertura zonaCobertura;
    
   
    @BeforeEach
    public void setup(){
    	manejador = new ManejadorMuestra();
    	muestra = mock(Muestra.class);
    	zonaCobertura = mock(ZonaCobertura.class);
    
    }
    
   
    @Test
	public void testeandoLaSuscripcionDeUnaZona() {
	      when(zonaCobertura.contiene(muestra)).thenReturn(true);
	      manejador.suscribir(zonaCobertura, muestra);

	      assertTrue(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	}
	
	@Test
	public void testeandoLaNoSuscripcionDeUnaZona() {
	      when(zonaCobertura.contiene(muestra)).thenReturn(false);
	      manejador.suscribir(zonaCobertura, muestra);

	      assertFalse(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	}
	
	@Test
	public void testeandoLaDesuscripcionDeUnaZona() {
	      
		 manejador.desuscribir(zonaCobertura);

	      assertFalse(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	 }
	
	@Test
	public void testeandoLaNotificacionVerificadaAUnaZona() {
		  when(zonaCobertura.contiene(muestra)).thenReturn(true);
	      manejador.suscribir(zonaCobertura, muestra);
		  manejador.notificarMuestraVerificada(muestra);

		  verify(zonaCobertura, times(1)).avisarMuestraVerificada(muestra);
	      //assertFalse(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	 }
	
	@Test
	public void testeandoLaNotificacionNuevadaAUnaZona() {
		  when(zonaCobertura.contiene(muestra)).thenReturn(true);
	      manejador.suscribir(zonaCobertura, muestra);
		  manejador.notificarMuestraNueva(muestra);

		  verify(zonaCobertura, times(1)).avisarNuevaMuestra(muestra);
	      //assertFalse(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	 }
 
}
