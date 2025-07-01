package vinchuca;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltroFechaMayorTest {

    private FiltroFechaMayor filtro;
    private LocalDate unaFecha;
    private LocalDate otraFecha;

    @BeforeEach
    void setUp() {
       filtro = new FiltroFechaMayor();
       unaFecha = LocalDate.of(2023, 1, 15);
       otraFecha = LocalDate.of(2023, 1, 20);
    }
    @Test
    void test01_filtroCumpleCuandoUnaFechaEsMayorQueOtra() {
        assertTrue(filtro.cumple(otraFecha, unaFecha));
    }
    @Test
    void test02_filtroNoCumpleCuandoUnaFechaEsMenorQueOtra() {
        assertFalse(filtro.cumple(unaFecha, otraFecha));
    }
    @Test
    void test03_filtroNoCumpleCuandoUnaFechaEsIgualQueOtra() {
        assertFalse(filtro.cumple(otraFecha, otraFecha));
    }
}
