package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroANDTest {

    private FiltroAND filtroAND;
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
    
    filtroAND = new FiltroAND();
    
    when(filtroFechaMuestra.cumple(muestra)).thenReturn(false);
    when(filtroTipoInsecto.cumple(muestra)).thenReturn(true);
    when(filtroFechaMuestra.cumple(muestra2)).thenReturn(true);
    when(filtroTipoInsecto.cumple(muestra2)).thenReturn(true);
    
    muestras.add(muestra);
    muestras.add(muestra2);
    
    }
    
    @Test
    void test01_filtroANDRecibeUnFiltroTrueYUnoFalsePorLoNoCualCumple() {
        filtroAND.addFiltro(filtroFechaMuestra).addFiltro(filtroTipoInsecto);
        assertFalse(filtroAND.cumple(muestra));
    }
    @Test
    void test02_filtroANDRecibeDosFiltrosFalsePorLoCualNoCumple() {
        filtroAND.addFiltro(filtroFechaMuestra).addFiltro(filtroFechaMuestra);
        assertFalse(filtroAND.cumple(muestra));
    }
    @Test
    void test03_filtroANDRecibeDosFiltrosTruePorLoCualCumple() {
        filtroAND.addFiltro(filtroTipoInsecto).addFiltro(filtroTipoInsecto);
        assertTrue(filtroAND.cumple(muestra));
    }
    @Test
    void test04_filtroANDEliminaUnFiltro() {
        filtroAND.addFiltro(filtroTipoInsecto).addFiltro(filtroFechaMuestra);
        filtroAND.removeFiltro(filtroFechaMuestra);
        assertEquals(filtroAND.getFiltros().size(), 1);
    }
    @Test
    void test05_filtroORAplicaFiltros() {
        filtroAND.addFiltro(filtroFechaMuestra).addFiltro(filtroTipoInsecto);
        assertEquals(filtroAND.aplicarFiltro(muestras).size(), 1);
    }
}