package vinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltroFechaMuestraTest {

	private FiltroFechaMuestra filtroFechaMuestra;
	private FiltroFecha filtroFechaMenor;
	private FiltroFecha filtroFechaMayor;
	private FiltroFecha filtroFechaIgual;
	
	private LocalDate unaFecha;
	private LocalDate fechaMenor;
	private LocalDate fechaMayor;
	
	private Muestra muestra;
	private Muestra muestra2;
	private Muestra muestra3;
	
	private List<Muestra> muestras = new ArrayList<>();

	@BeforeEach
	void setUp() {
	   filtroFechaMenor = mock(FiltroFechaMenor.class);
	   filtroFechaMayor = mock(FiltroFechaMayor.class);
	   filtroFechaIgual = mock(FiltroFechaIgual.class);

	   unaFecha = LocalDate.of(2023, 1, 15);
	   fechaMenor = LocalDate.of(2023, 1, 14);
	   fechaMayor = LocalDate.of(2023, 1, 16);
	   muestra = mock(Muestra.class);
	   muestra2 = mock(Muestra.class);
	   muestra3 = mock(Muestra.class);

	   when(muestra.getFechaCreacion()).thenReturn(unaFecha);
	   when(muestra2.getFechaCreacion()).thenReturn(fechaMayor);
	   when(muestra3.getFechaCreacion()).thenReturn(fechaMenor);


       when(filtroFechaMenor.cumple(unaFecha, fechaMayor)).thenReturn(true);
       when(filtroFechaMenor.cumple(fechaMenor, fechaMayor)).thenReturn(true);
       when(filtroFechaMenor.cumple(fechaMenor, unaFecha)).thenReturn(true);


       when(filtroFechaMayor.cumple(unaFecha, fechaMenor)).thenReturn(true);
       when(filtroFechaMayor.cumple(fechaMayor, unaFecha)).thenReturn(true);
       when(filtroFechaMayor.cumple(fechaMayor, fechaMenor)).thenReturn(true);


       
       when(filtroFechaIgual.cumple(unaFecha, unaFecha)).thenReturn(true);
       when(filtroFechaIgual.cumple(fechaMenor, fechaMenor)).thenReturn(true);
       when(filtroFechaIgual.cumple(fechaMayor, fechaMayor)).thenReturn(true);

       
       
       muestras.add(muestra);
       muestras.add(muestra2);
       muestras.add(muestra3);

	}
	@Test
	void test01_filtroFechaMuestraRecibeUnFiltroFechaMenorYCumpleCuandoLaFechaDeLaMuestraEsMenorQueOtra() {
		//inicializo el filtro con el filtroFechaMenor y una fecha mayor
		filtroFechaMuestra = new FiltroFechaMuestra(filtroFechaMenor, fechaMayor);
		assertTrue(filtroFechaMuestra.cumple(muestra));
	}
	@Test
	void test02_filtroFechaMuestraRecibeUnFiltroFechaMayorYCumpleCuandoLaFechaDeLaMuestraEsMayorQueOtra() {
		//inicializo el filtro con el filtroFechaMayor y una fecha menor
		filtroFechaMuestra = new FiltroFechaMuestra(filtroFechaMayor, fechaMenor);
		assertTrue(filtroFechaMuestra.cumple(muestra));
	}
	@Test
	void test03_filtroFechaMuestraRecibeUnFiltroFechaIgualYCumpleCuandoLaFechaDeLaMuestraEsIgualQueOtra() {
		//inicializo el filtro con el filtroFechaIgual y una fecha igual
		filtroFechaMuestra = new FiltroFechaMuestra(filtroFechaIgual, unaFecha);
		assertTrue(filtroFechaMuestra.cumple(muestra));
	}
	@Test
	void test04_filtroFechaMuestraRecibeUnFiltroFechaMenorYAplicaElFiltroEnUnaListaDeMuestras(){
		filtroFechaMuestra = new FiltroFechaMuestra(filtroFechaMenor, fechaMayor);
		assertEquals(filtroFechaMuestra.aplicarFiltro(muestras).size() , 2);
	}
	@Test
	void test05_filtroFechaMuestraRecibeUnFiltroFechaMayorYAplicaElFiltroEnUnaListaDeMuestras(){
		filtroFechaMuestra = new FiltroFechaMuestra(filtroFechaMayor, fechaMenor);
		assertEquals(filtroFechaMuestra.aplicarFiltro(muestras).size() , 2);
	}
	@Test
	void test05_filtroFechaMuestraRecibeUnFiltroFechaIgualYAplicaElFiltroEnUnaListaDeMuestras(){
		filtroFechaMuestra = new FiltroFechaMuestra(filtroFechaIgual, unaFecha);
		assertEquals(filtroFechaMuestra.aplicarFiltro(muestras).size() , 1);
	}
}
