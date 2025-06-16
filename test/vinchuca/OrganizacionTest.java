package vinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrganizacionTest {

	 FuncionalidadExterna funcNueva;
	 FuncionalidadExterna funcVerificada;
	 ZonaCobertura zona;
	 Muestra muestra;
	 TipoOrg tipo;
	 Ubicacion ubicacion;
	 Organizacion organizacion;
	
	@BeforeEach
	void setUp() {
    	funcNueva = mock(FuncionalidadExterna.class);
    	funcVerificada = mock(FuncionalidadExterna.class);
    	zona = mock(ZonaCobertura.class);
    	muestra = mock(Muestra.class);
    	tipo = mock(TipoOrg.class);
    	ubicacion = mock(Ubicacion.class);

    	organizacion = new Organizacion(10, ubicacion, tipo, funcNueva, funcVerificada); //SUT
	}
	@Test
    void test01_SerNotificadoNuevaMuestra() {
	    organizacion.serNotificadoNuevaMuestra(zona, muestra);
	    verify(funcNueva).nuevoEvento(organizacion, zona, muestra);
	}
	@Test
    void test02_SerNotificadoMuestraVerificada() {
        organizacion.serNotificadoMuestraVerificada(zona, muestra);
        verify(funcVerificada).nuevoEvento(organizacion, zona, muestra);
	}
	@Test
    void test03_CambiarFuncionalidadNuevaMuestra() {
        FuncionalidadExterna nueva = mock(FuncionalidadExterna.class);
        organizacion.cambiarFuncionalidadNuevaMuestra(nueva);

        organizacion.serNotificadoNuevaMuestra(zona, muestra);
        verify(nueva).nuevoEvento(organizacion, zona, muestra);
    }
	@Test
	void test04_CambiarFuncionalidadValidacionMuestra() {
	    FuncionalidadExterna nueva = mock(FuncionalidadExterna.class);
	    organizacion.cambiarFuncionalidadValidacionMuestra(nueva);

	    organizacion.serNotificadoMuestraVerificada(zona, muestra);
	    verify(nueva).nuevoEvento(organizacion, zona, muestra);
	    }
	@Test
	void test05_Getters() {
        assertEquals(10, organizacion.getCantTrabajadores());
        assertEquals(ubicacion, organizacion.getUbicacion());
        assertEquals(tipo, organizacion.getTipo());

    }
	@Test
	void test06_setters(){
		Ubicacion nuevaUbicacion = mock(Ubicacion.class);
        TipoOrg nuevoTipo = mock(TipoOrg.class);
        organizacion.setCantTrabajadores(20);
        organizacion.setUbicacion(nuevaUbicacion);
        organizacion.setTipo(nuevoTipo);

        assertEquals(20, organizacion.getCantTrabajadores());
        assertEquals(nuevaUbicacion, organizacion.getUbicacion());
        assertEquals(nuevoTipo, organizacion.getTipo());
	}
}

