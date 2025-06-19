package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZonaCoberturaTest {
    private ZonaCobertura zona;
    private Ubicacion epicentro;
    private Muestra muestraDentro;
    private Muestra muestraFuera;
    private Ubicacion ubicacionDentro;
    private Ubicacion ubicacionFuera;
    private Observador observador;

    @BeforeEach
    void setUp() {
        epicentro = mock(Ubicacion.class);
        ubicacionDentro = mock(Ubicacion.class);
        ubicacionFuera = mock(Ubicacion.class);

        muestraDentro = mock(Muestra.class);
        muestraFuera = mock(Muestra.class);

        when(muestraDentro.getUbicacion()).thenReturn(ubicacionDentro);
        when(muestraFuera.getUbicacion()).thenReturn(ubicacionFuera);

        
        when(epicentro.distanciaEntre(ubicacionDentro)).thenReturn(5.0);
        when(epicentro.distanciaEntre(ubicacionFuera)).thenReturn(20.0);

        zona = new ZonaCobertura("Zona Sur", epicentro, 10.0);

        observador = mock(Observador.class);
    }

    @Test
    void test01_AgregarMuestraDentroDelRadio() {

        zona.avisarNuevaMuestra(muestraDentro); //este método agrega una muestra.
        
        assertEquals(zona.getMuestras().size(), 1);
    }

    @Test
    void test02_NoAgregaMuestraFueraDelRadio() {
        zona.agregarInteresado(observador);

        zona.avisarNuevaMuestra(muestraFuera);

        // No se notifica porque la muestra no estaba dentro del rango
        verify(observador, never()).serNotificadoNuevaMuestra(any(), any());
    }

    @Test
    void test03_RemoverMuestra() {
        zona.avisarNuevaMuestra(muestraDentro);
        zona.removeMuestra(muestraDentro);
        assertEquals(zona.getMuestras().size(), 0);
    }

    @Test
    void test04_ZonasQueSolapan() {
        ManejadorZonasCobertura manejador = mock(ManejadorZonasCobertura.class);
        List<ZonaCobertura> resultado = new ArrayList<>();
        resultado.add(mock(ZonaCobertura.class));

        when(manejador.zonasQueSolapan(zona)).thenReturn(resultado);

        assertEquals(resultado, zona.zonasQueSolapan(manejador));
    }

    @Test
    void test05_SeSolapaCon() {
        Ubicacion otraUbicacion = mock(Ubicacion.class);
        ZonaCobertura otraZona = new ZonaCobertura("Zona Este", otraUbicacion, 7.0);

        when(otraUbicacion.distanciaEntre(epicentro)).thenReturn(12.0);
        when(epicentro.distanciaEntre(otraUbicacion)).thenReturn(12.0);

        assertTrue(zona.seSolapaCon(otraZona));
    }

    @Test
    void test06_NoSeSolapaCon() {
        Ubicacion otraUbicacion = mock(Ubicacion.class);
        ZonaCobertura otraZona = new ZonaCobertura("Otra", otraUbicacion, 2.0);

        when(otraUbicacion.distanciaEntre(epicentro)).thenReturn(20.0);
        when(epicentro.distanciaEntre(otraUbicacion)).thenReturn(20.0);

        assertFalse(zona.seSolapaCon(otraZona));
    }

    @Test
    void test07_Getters() {
    	zona.agregarInteresado(observador);
    	zona.avisarNuevaMuestra(muestraDentro);
    	
        assertEquals("Zona Sur", zona.getNombre());
        assertEquals(epicentro, zona.getEpicentro());
        assertEquals(10.0, zona.getRadio());
        assertEquals(zona.getMuestras().size(), 1);
        assertEquals(zona.getInteresados().size(), 1);
    }
    @Test
    void test07_setearNombreZona() {
    	zona.setNombre("Zona Este");
    	assertEquals(zona.getNombre(), "Zona Este");
	}
    @Test
    void test08_AgregarYSacarInteresado() {
        zona.agregarInteresado(observador);
        zona.sacarInteresado(observador);

        zona.avisarNuevaMuestra(muestraDentro);

        // No debería recibir notificaciones si se desuscribió
        verify(observador, never()).serNotificadoNuevaMuestra(any(), any());
    }

    @Test
    void test09_AvisarMuestraVerificada() {
        zona.agregarInteresado(observador);
        zona.avisarMuestraVerificada(muestraDentro);
        verify(observador).serNotificadoMuestraVerificada(zona, muestraDentro);
    }
    @Test
    void test10_AvisarNuevaMuestra() {
    	zona.agregarInteresado(observador);
    	zona.avisarNuevaMuestra(muestraDentro);
    	 //La muestra se acepta al estar dentro del rango, por lo que se notifica a los interesados.
        verify(observador).serNotificadoNuevaMuestra(zona, muestraDentro);
        assertTrue(zona.getMuestras().contains(muestraDentro));
    }
}
