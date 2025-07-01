package vinchuca;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltroFechaMenorTest {
    
    private FiltroFechaMenor filtro;
    private LocalDate unaFecha;
    private LocalDate otraFecha;

    @BeforeEach
    void setUp() {
       filtro = new FiltroFechaMenor();
       unaFecha = LocalDate.of(2023, 1, 15);
       otraFecha = LocalDate.of(2023, 1, 20);
    }
    @Test
    void test01_filtroCumpleCuandoUnaFechaEsMenorQueOtra() {
        assertTrue(filtro.cumple(unaFecha, otraFecha));
    }
    @Test
    void test02_filtroNoCumpleCuandoUnaFechaEsMayorQueOtra() {
        assertFalse(filtro.cumple(otraFecha, unaFecha));
    }
    @Test
    void test03_filtroNoCumpleCuandoUnaFechaEsIgualQueOtra() {
        assertFalse(filtro.cumple(otraFecha, otraFecha));
    }
}