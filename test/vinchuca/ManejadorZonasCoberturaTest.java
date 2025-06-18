package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManejadorZonasCoberturaTest {

	 private ManejadorZonasCobertura manejador;
	    private ZonaCobertura zona1;
	    private ZonaCobertura zona2;
	    private ZonaCobertura zona3;
	    
	    private Ubicacion ubicacion1;
	    private Ubicacion ubicacion2;
	    private Ubicacion ubicacion3;

	    @BeforeEach
	    void setUp() {
	        manejador = new ManejadorZonasCobertura();
	        
	        ubicacion1 = mock(Ubicacion.class);
	        ubicacion2 = mock(Ubicacion.class);
	        ubicacion3 = mock(Ubicacion.class);
	        
	        when(ubicacion1.distanciaEntre(ubicacion1)).thenReturn(0.0);
	        when(ubicacion1.distanciaEntre(ubicacion2)).thenReturn(8.0);
	        when(ubicacion1.distanciaEntre(ubicacion3)).thenReturn(20.0);
	        
	        when(ubicacion2.distanciaEntre(ubicacion1)).thenReturn(8.0);
	        when(ubicacion3.distanciaEntre(ubicacion1)).thenReturn(20.0);
	        
	        zona1 = manejador.crearYRegistrarZonaDeCobertura("Zona Norte", ubicacion1, 10.0);
	        zona2 = manejador.crearYRegistrarZonaDeCobertura("Zona Este", ubicacion2, 5.0); //solapa
	        zona3 = manejador.crearYRegistrarZonaDeCobertura("Zona Oeste", ubicacion3, 3.0); //no solapa

	    }
	    @Test
	    void test01_CrearYRegistrarZona() {
	        assertTrue(manejador.zonasQueSolapan(zona1).contains(zona1)); // se solapa consigo misma 
	    }
	    @Test
	    void test02_EliminarZona() {

	        manejador.eliminarZona(zona1);

	        List<ZonaCobertura> zonas = manejador.zonasQueSolapan(zona1);
	        assertFalse(zonas.contains(zona1));
	    }
	    @Test
	    void test03_ZonasQueSolapan() {

	        List<ZonaCobertura> solapadas = manejador.zonasQueSolapan(zona1);

	        assertTrue(solapadas.contains(zona1)); //se solapa consigo misma
	        assertTrue(solapadas.contains(zona2)); //distancia <= suma de radios (distancia = 8), (suma de radios = 15)
	        assertFalse(solapadas.contains(zona3)); //distancia = 20, es mayor a la suma de radios (13)
	    }
	    @Test
	    void test04_Getters() {
	    	assertEquals(manejador.getZonasCobertura().size(), 3);
	    }
}
