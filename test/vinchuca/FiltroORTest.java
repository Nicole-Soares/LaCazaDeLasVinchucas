package vinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltroORTest {
    
    private FiltroOR filtroOR;
    private FiltroFechaMuestra filtroFechaMuestra;
    private FiltroTipoInsecto filtroTipoInsecto;
    private Muestra muestra;
    private Muestra muestra2;
    private List<Muestra> muestras = new ArrayList<Muestra>();
    
    @BeforeEach
    void setUp() {
        
    filtroFechaMuestra = mock(FiltroFechaMuestra.class);
    filtroTipoInsecto = mock(FiltroTipoInsecto.class);
    muestra = mock(Muestra.class);
    muestra2 = mock(Muestra.class);
    
    filtroOR = new FiltroOR();
    
    when(filtroFechaMuestra.cumple(muestra)).thenReturn(false);
    when(filtroTipoInsecto.cumple(muestra)).thenReturn(true);
    when(filtroFechaMuestra.cumple(muestra2)).thenReturn(false);
    when(filtroTipoInsecto.cumple(muestra2)).thenReturn(false);
    
    muestras.add(muestra);
    muestras.add(muestra2);
    
    }
    
    @Test
    void test01_filtroORRecibeUnFiltroTrueYUnoFalsePorLoCualCumple() {
        filtroOR.addFiltro(filtroFechaMuestra).addFiltro(filtroTipoInsecto);
        assertTrue(filtroOR.cumple(muestra));
    }
    @Test
    void test02_filtroORRecibeDosFiltrosFalsePorLoCualNoCumple() {
        filtroOR.addFiltro(filtroFechaMuestra).addFiltro(filtroFechaMuestra);
        assertFalse(filtroOR.cumple(muestra));
    }
    @Test
    void test03_filtroORRecibeDosFiltrosFalsePorLoCualNoCumple() {
        filtroOR.addFiltro(filtroTipoInsecto).addFiltro(filtroTipoInsecto);
        assertTrue(filtroOR.cumple(muestra));
    }
    @Test
    void test04_filtroOREliminaUnFiltro() {
        filtroOR.addFiltro(filtroTipoInsecto).addFiltro(filtroFechaMuestra);
        filtroOR.removeFiltro(filtroFechaMuestra);
        assertEquals(filtroOR.getFiltros().size(), 1);
    }
    @Test
    void test05_filtroORAplicaFiltros() {
        filtroOR.addFiltro(filtroFechaMuestra).addFiltro(filtroTipoInsecto);
        assertEquals(filtroOR.aplicarFiltro(muestras).size(), 1);
    }
}
