package vinchuca;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltroFechaIgualTest {

    private FiltroFechaIgual filtro;
    private LocalDate unaFecha;
    private LocalDate otraFecha;

    @BeforeEach
    void setUp() {
       filtro = new FiltroFechaIgual();
       unaFecha = LocalDate.of(2023, 1, 15);
       otraFecha = LocalDate.of(2023, 1, 20);
    }
    @Test
    void test01_filtroNoCumpleCuandoUnaFechaEsMenorQueOtra() {
        assertFalse(filtro.cumple(unaFecha, otraFecha));
    }
    @Test
    void test02_filtroNoCumpleCuandoUnaFechaEsMayorQueOtra() {
        assertFalse(filtro.cumple(otraFecha, unaFecha));
    }
    @Test
    void test03_filtroCumpleCuandoUnaFechaEsIgualQueOtra() {
        assertTrue(filtro.cumple(otraFecha, otraFecha));
    }
}
