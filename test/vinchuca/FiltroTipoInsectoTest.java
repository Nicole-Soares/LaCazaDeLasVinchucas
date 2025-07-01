package vinchuca;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class FiltroTipoInsectoTest {
    
    private Muestra muestra;
    private FiltroTipoInsecto filtroTipoInsecto;
    
    @BeforeEach
    void setUp() {
        muestra = mock(Muestra.class);
        filtroTipoInsecto = new FiltroTipoInsecto("PTHIAFOLIADA");
        when(muestra.getEspecieDeVinchuca()).thenReturn("PTHIAFOLIADA");
    }
    @Test
    void test01_filtroTipoInsectoCumple() {
        assertTrue(filtroTipoInsecto.cumple(muestra));
    }
}