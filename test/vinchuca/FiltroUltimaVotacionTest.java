package vinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltroUltimaVotacionTest {

	
	private FiltroUltimaVotacion filtroUltimaVotacion;
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

	   when(muestra.fechaUltimaOpinion()).thenReturn(unaFecha);
	   when(muestra2.fechaUltimaOpinion()).thenReturn(fechaMayor);
	   when(muestra3.fechaUltimaOpinion()).thenReturn(fechaMenor);


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
	void test01_filtroUltimaVotacionRecibeUnFiltroFechaMenorYCumpleCuandoLaFechaDeLaMuestraEsMenorQueOtra() {
		//inicializo el filtro con el filtroFechaMenor y una fecha mayor
		filtroUltimaVotacion = new FiltroUltimaVotacion(filtroFechaMenor, fechaMayor);
		assertTrue(filtroUltimaVotacion.cumple(muestra));
	}
	@Test
	void test02_filtroFechaMuestraRecibeUnFiltroFechaMayorYCumpleCuandoLaFechaDeLaMuestraEsMayorQueOtra() {
		//inicializo el filtro con el filtroFechaMayor y una fecha menor
		filtroUltimaVotacion = new FiltroUltimaVotacion(filtroFechaMayor, fechaMenor);
		assertTrue(filtroUltimaVotacion.cumple(muestra));
	}
	@Test
	void test03_filtroFechaMuestraRecibeUnFiltroFechaIgualYCumpleCuandoLaFechaDeLaMuestraEsIgualQueOtra() {
		//inicializo el filtro con el filtroFechaIgual y una fecha igual
		filtroUltimaVotacion = new FiltroUltimaVotacion(filtroFechaIgual, unaFecha);
		assertTrue(filtroUltimaVotacion.cumple(muestra));
	}
	@Test
	void test04_filtroFechaMuestraRecibeUnFiltroFechaMenorYAplicaElFiltroEnUnaListaDeMuestras(){
		filtroUltimaVotacion = new FiltroUltimaVotacion(filtroFechaMenor, fechaMayor);
		assertEquals(filtroUltimaVotacion.aplicarFiltro(muestras).size() , 2);
	}
	@Test
	void test05_filtroFechaMuestraRecibeUnFiltroFechaMayorYAplicaElFiltroEnUnaListaDeMuestras(){
		filtroUltimaVotacion = new FiltroUltimaVotacion(filtroFechaMayor, fechaMenor);
		assertEquals(filtroUltimaVotacion.aplicarFiltro(muestras).size() , 2);
	}
	@Test
	void test05_filtroFechaMuestraRecibeUnFiltroFechaIgualYAplicaElFiltroEnUnaListaDeMuestras(){
		filtroUltimaVotacion = new FiltroUltimaVotacion(filtroFechaIgual, unaFecha);
		assertEquals(filtroUltimaVotacion.aplicarFiltro(muestras).size() , 1);
	}
}