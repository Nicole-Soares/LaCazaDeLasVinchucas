package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MuestraTest {

	
    
    private Muestra muestra;
    private Estado estadoBasico;
    private Persona personaBasica;
    private Persona personaExperta;
    private Filtro filtro;
    private ManejadorMuestra manejador;
    private Ubicacion ubicacion;
    private LocalDate fecha;
    private Opinion opinionBasica;
    private Opinion opinionBasicaDos;
    private Opinion opinionExperta;
    private Opinion opinionExpertaDos;
    private Opinion opinionExpertaTres;
    
   
    @BeforeEach
    public void setup(){
    	
    	personaBasica = mock(Persona.class);
    	personaExperta = mock(Persona.class);
    	ubicacion = mock(Ubicacion.class);
    	filtro = mock(Filtro.class);
    	fecha = LocalDate.now();
    	manejador = new ManejadorMuestra();
    	estadoBasico = new EstadoBasico();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, filtro, manejador);
    	opinionBasica = mock(Opinion.class);
    	opinionBasicaDos = mock(Opinion.class);
    	opinionExperta = mock(Opinion.class);
    	opinionExpertaDos = mock(Opinion.class);
    	opinionExpertaTres = mock(Opinion.class);
    
    }
   
   @Test
   public void testeandoLaCargaDeUnaOpinionDePersonaBasicaEnEstadoBasicoLaMuestra() {
	   when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
	   muestra.cargarOpinion(opinionBasica);
	   assertTrue(muestra.getOpiniones().contains(opinionBasica));
   }
   
   @Test
   public void testeandoLaCargaDeUnaOpinionDePersonaExpertaEnEstadoBasicoLaMuestra() {
	   when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	   muestra.cargarOpinion(opinionExperta);
	   
	 
	   assertTrue(muestra.getOpiniones().contains(opinionExperta));
   }
   
   @Test
   public void testeandoLaCargaDeUnaOpinionDePersonaExpertaEnEstadoBasicoDeLaMuestraYDespuesLaDeUnaPersonaBasica() {
	   when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	   muestra.cargarOpinion(opinionExperta);
	   
	   when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
	   muestra.cargarOpinion(opinionBasica);
	   assertFalse(muestra.getOpiniones().contains(opinionBasica));
   }
   
   @Test
   public void testeandoLaCargaDeDosOpinionesDiferentesDeExpertos() {
	    when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.CHINCHEFOLIADA);

	    when(opinionExpertaTres.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaTres.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA); // tercera distinta

	    muestra.cargarOpinion(opinionExperta);     // carga 1
	    muestra.cargarOpinion(opinionExpertaDos);  // carga 2 (diferente)
	    muestra.cargarOpinion(opinionExpertaTres); // carga 3 (debería permitirla)

	    // Verificamos que la tercera fue agregada
	    assertEquals(3, muestra.getOpiniones().size());

   }
   
   @Test
   public void testeandoLaCargaDeDosOpinionesIgualesDeExpertosHaciendoQueMuestraEsteVerificada() {
	    when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

	    when(opinionExpertaTres.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaTres.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA); // tercera distinta

	    muestra.cargarOpinion(opinionExperta);     // carga 1
	    muestra.cargarOpinion(opinionExpertaDos);  // carga 2 
	    muestra.cargarOpinion(opinionExpertaTres); // carga 3 (no la agrega)

	    assertFalse(muestra.getOpiniones().contains(opinionExpertaTres)); // esta en estado verificada, no agrega mas

   }
   
   @Test
   public void testeandoResultadoActualConPersonasBasicas() {
	   when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
	   muestra.cargarOpinion(opinionBasica);
	   
	   when(opinionBasicaDos.esOpinionDeExperto()).thenReturn(false);
	   muestra.cargarOpinion(opinionBasicaDos);
	   
	   assertEquals(muestra. resultadoActual(), opinionBasicaDos);

   }
  
   /*public void testNoSePuedeCargarOpinionDeBasicoLuegoDeExperto() {
       // Creo una muestra con estado basico
       Opinion opinionExperto = mock(Opinion.class);
       when(opinionExperto.esOpinionDeExperto()).thenReturn(true);

       Opinion opinionBasico = mock(Opinion.class);
       when(opinionBasico.esOpinionDeExperto()).thenReturn(false);

       muestra.cargarOpinionEnEstadoBasico(opinionExperto);

       // Ahora que la muestra cambió de estado por un experto, el estado actual
       // solo acepta expertos. Intentamos cargar un básico:
       muestra.cargarOpinion(opinionBasico);

       // Verificamos que no se agregó (comportamiento esperado del nuevo estado)
       assertFalse(muestra.getOpiniones().contains(opinionBasico));
   }*/
}
