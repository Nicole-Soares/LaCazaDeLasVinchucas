package vinchuca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import vinchuca.Ubicacion;

public class UbicacionTest {

	private Ubicacion ubicacion;
	private Ubicacion otraUbicacion;
	private Ubicacion ubicacionCercana;
	private Ubicacion ubicacionLejana;
	
	@BeforeEach
	void setUp() {
		ubicacion = new Ubicacion(-25.00, 49.00);
		otraUbicacion = new Ubicacion(9.00, 100.00);
		ubicacionCercana = new Ubicacion(-24.00, 50.00);            
        ubicacionLejana = new Ubicacion(40.00, -80.00);    
        Muestra muestraCerca = mock(Muestra.class);
        Muestra muestraLejos = mock(Muestra.class);
        when(muestraCerca.getUbicacion()).thenReturn(ubicacionCercana);
        when(muestraLejos.getUbicacion()).thenReturn(ubicacionLejana);

	}
	@Test
	void test01_getLongitud() {
		assertEquals(ubicacion.getLongitud(), -25.00, 0.001);
	}
	@Test
	void test02_getLatitud() {
		assertEquals(ubicacion.getLatitud(), 49.00, 0.001);
	}
	@Test
	void test03_distanciaEntreUnaUbicacionYOtra() {
		assertEquals(ubicacion.distanciaEntre(otraUbicacion), 61.29 , 0.01);
	}
	@Test
	void test04_ubicacionesADevuelveSoloLasDentroDeLaDistancia() {
         	List<Ubicacion> todas = Arrays.asList(otraUbicacion, ubicacionCercana, ubicacionLejana);
         	double distanciaMax = 2.0; 
	        List<Ubicacion> cercanas = ubicacion.ubicacionesA(todas	, distanciaMax);

	        // Solo debería incluir a la ubicación "ubicacionCercana"
	        assertEquals(1, cercanas.size());
	        assertTrue(cercanas.contains(ubicacionCercana));
	        assertFalse(cercanas.contains(otraUbicacion));
	        assertFalse(cercanas.contains(ubicacionLejana));
	}
	@Test
	void test05_muestrasCercanasACiertaDistancia() {
		 Muestra muestraCerca = mock(Muestra.class);
	     Muestra muestraLejos = mock(Muestra.class);
	     when(muestraCerca.getUbicacion()).thenReturn(ubicacionCercana);
	     when(muestraLejos.getUbicacion()).thenReturn(ubicacionLejana);

        List<Muestra> muestras = Arrays.asList(muestraCerca, muestraLejos);
        List<Muestra> cercanas = ubicacion.cercanasA(muestraCerca, muestras, 2.0);
        assertEquals(1, cercanas.size());
        assertTrue(cercanas.contains(muestraCerca));
        assertFalse(cercanas.contains(muestraLejos));
        verify(muestraCerca, atLeastOnce()).getUbicacion();
        verify(muestraLejos, atLeastOnce()).getUbicacion();
	}
	@Test
	void test08_ubicacionSinParametrosInicializaCorrectamente() {
		Ubicacion ubicacionTest = new Ubicacion();
		assertEquals(ubicacionTest.getLongitud(), 0.0);
		assertEquals(ubicacionTest.getLatitud(), 0.0);
	}
	@Test
	void test07_ubicacionConParametrosInicializaCorrectamente() {
		Ubicacion ubicacionTest = new Ubicacion(1.0, 2.0);
		assertEquals(ubicacionTest.getLongitud(), 1.0);
		assertEquals(ubicacionTest.getLatitud(), 2.0);
	}
}
