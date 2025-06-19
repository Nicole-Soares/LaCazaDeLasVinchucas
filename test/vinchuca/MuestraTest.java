package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class MuestraTest {


	
    
    private Muestra muestra;
    private Estado estadoBasico;
    private Persona personaBasica;
    private Persona personaExperta;
    //private Filtro filtro;
    private ManagerMuestraVerificada manejador;
    private Ubicacion ubicacion;
    private Ubicacion ubicacion2;
    private LocalDate fecha;
    private Opinion opinionBasica;
    private Opinion opinionBasicaDos;
    private Opinion opinionBasicaTres;
    private Opinion opinionExperta;
    private Opinion opinionExpertaDos;
    private Opinion opinionExpertaTres;
    private ZonaCobertura zonaCobertura;
    
   
    @BeforeEach
    public void setup(){
    	
    	personaBasica = mock(Persona.class);
    	personaExperta = mock(Persona.class);
    	ubicacion = mock(Ubicacion.class);
    	ubicacion2 = mock(Ubicacion.class);
    	//filtro = mock(Filtro.class);
    	fecha = LocalDate.now();
    	manejador = mock(ManagerMuestraVerificada.class);//new ManejadorMuestra();
    	estadoBasico = new EstadoBasico();
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
    	opinionBasica = mock(Opinion.class);
    	opinionBasicaDos = mock(Opinion.class);
    	opinionBasicaTres = mock(Opinion.class);
    	opinionExperta = mock(Opinion.class);
    	opinionExpertaDos = mock(Opinion.class);
    	opinionExpertaTres = mock(Opinion.class);
    	zonaCobertura = mock(ZonaCobertura.class);
    
    }
    
    @Test
    public void testeandoGetters() {
    	assertEquals(muestra.getEspecieDeVinchuca(), "chinche");
    	assertEquals(muestra.getFoto(), "foto");
    	assertEquals(muestra.getUbicacion(), ubicacion);
    	assertEquals(muestra.getAutor(), personaBasica);
    	assertEquals(muestra.getFechaCreacion(), fecha);
    	//assertEquals(muestra.getFiltro(), filtro);
    	assertEquals(muestra.getManejadorMuestra(), manejador);
    }
    
    @Test
    public void testeandoSetters() {
    	muestra.setEspecieDeVinchuca("vinchuca");
    	assertEquals(muestra.getEspecieDeVinchuca(), "vinchuca");
    	muestra.setFoto("foto2");
    	assertEquals(muestra.getFoto(), "foto2");
    	muestra.setUbicacion(ubicacion2);
    	assertEquals(muestra.getUbicacion(), ubicacion2);
    	muestra.setAutor(personaExperta);
    	assertEquals(muestra.getAutor(), personaExperta);
    	muestra.setFechaMuestra(fecha);
    	assertEquals(muestra.getFechaCreacion(), fecha);
    	//muestra.setFiltro(filtro);
    	//assertEquals(muestra.getFiltro(), filtro);
    	muestra.setManejadorMuestra(manejador);
    	assertEquals(muestra.getManejadorMuestra(), manejador);
    }
   
  
    @Test
    public void testeandoNotificacionMuestraVerificada() {
    	when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
   	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

   	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
   	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

   	    muestra.cargarOpinion(opinionExperta);    
   	    muestra.cargarOpinion(opinionExpertaDos);  
   	   
   	    verify(manejador, times(1)).notificarMuestraVerificada(muestra);

    }
    
    @Test
    public void testeandoFechaDeLaUltimaOpinion() {
    	
    	
    	LocalDate fecha1 = LocalDate.of(2024, 3, 15);
    	LocalDate fecha2 = LocalDate.of(2024, 3, 15);
    	
    	when(opinionBasica.getFechaDeOpinion()).thenReturn(fecha1);
        when(opinionExperta.getFechaDeOpinion()).thenReturn(fecha2);
        
        muestra.cargarOpinion(opinionBasica);
 	    muestra.cargarOpinion(opinionExperta);
 	   
 	    assertEquals(muestra.fechaUltimaOpinion(), fecha2);

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
   
     //chequeamos estado verificado no acepta mas opiniones
    @Test
    public void testeandoLaCargaDeDosOpinionesIgualesDeExpertosHaciendoQueMuestraEsteVerificada() {
	    when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);

	    //ya esta verificada porque coinciden
	    when(opinionExpertaTres.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaTres.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA); // tercera distinta

	    muestra.cargarOpinion(opinionExperta);     // carga 1
	    muestra.cargarOpinion(opinionExpertaDos);  // carga 2 
	    muestra.cargarOpinion(opinionExpertaTres); // carga 3 (no la agrega)

	    assertFalse(muestra.getOpiniones().contains(opinionExpertaTres)); // esta en estado verificada, no agrega mas

    }
   
    @Test
    public void testeandoResultadoActualConPersonasBasicasYMuestraEnEstadoBasico() {
	   when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
	   when(opinionBasica.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	   
	   muestra.cargarOpinion(opinionBasica);
	   
	   when(opinionBasicaDos.esOpinionDeExperto()).thenReturn(false);
	   when(opinionBasicaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	   
	   muestra.cargarOpinion(opinionBasicaDos);
	   
	   assertEquals(muestra.resultadoActual(), opinionBasica);

    }
   
    @Test
    public void testeandoResultadoActualConDosPersonasBasicasQueCoincidenYUnaQueNoYMuestraEnEstadoBasico() {
	   when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
	   when(opinionBasica.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	   
	   muestra.cargarOpinion(opinionBasica);
	   
	   when(opinionBasicaDos.esOpinionDeExperto()).thenReturn(false);
	   when(opinionBasicaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	   
	   muestra.cargarOpinion(opinionBasicaDos);
	   
	   when(opinionBasicaTres.esOpinionDeExperto()).thenReturn(false);
	   when(opinionBasicaTres.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	   
	   muestra.cargarOpinion(opinionBasicaTres);
	   
	   
	   assertEquals(muestra.resultadoActual(), opinionBasica);

    }
   
    @Test
    public void testeandoResultadoActualConPersonasExpertasQueNoCoincidenYMuestraEnEstadoExperto() {
    	
        when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	    muestra.cargarOpinion(opinionExperta);
	   
	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	    muestra.cargarOpinion(opinionExpertaDos);
	   
		assertEquals(muestra.resultadoActual(), opinionExperta);

    }
    
    @Test
    public void testeandoResultadoActualConPersonasBasicaYExpertasYMuestraEnEstadoExperto() {
    	
    	when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
  	    when(opinionBasica.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
  	    muestra.cargarOpinion(opinionBasica);
    	
        when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	    muestra.cargarOpinion(opinionExperta);
	   
	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	    muestra.cargarOpinion(opinionExpertaDos);
	   
		assertEquals(muestra.resultadoActual(), opinionExperta);

    }
    
   
    @Test
    public void testeandoResultadoActualConPersonasExpertasYMuestraEnEstadoVerificado() {
   	
       when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	    muestra.cargarOpinion(opinionExperta);
	   
	    when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	    when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	    muestra.cargarOpinion(opinionExpertaDos);
	   
		assertEquals(muestra.resultadoActual(), opinionExperta);

    }
    
    @Test
    public void testeandoResultadoActualConPersonasBasicaYExpertasYMuestraEnEstadoVerificado() {
	   
	   when(opinionBasica.esOpinionDeExperto()).thenReturn(false);
	   when(opinionBasica.getTipo()).thenReturn(TipoDeOpinion.VINCHUCA);
	   muestra.cargarOpinion(opinionBasica);
	   
	   when(opinionExperta.esOpinionDeExperto()).thenReturn(true);
	   when(opinionExperta.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	   muestra.cargarOpinion(opinionExperta);
	   
	   when(opinionExpertaDos.esOpinionDeExperto()).thenReturn(true);
	   when(opinionExpertaDos.getTipo()).thenReturn(TipoDeOpinion.CHINCHEFOLIADA);
	   muestra.cargarOpinion(opinionExpertaDos);
	   
	   when(opinionExpertaTres.esOpinionDeExperto()).thenReturn(true);
	   when(opinionExpertaTres.getTipo()).thenReturn(TipoDeOpinion.FITHUFOLIADA);
	   muestra.cargarOpinion(opinionExpertaTres);
	   
	   assertEquals(muestra.resultadoActual(), opinionExperta);
    }
   
   
	   
	@Test
	public void testeandoLaSuscripcionDeUnaZona() {
	      when(zonaCobertura.contiene(muestra)).thenReturn(true);
	      muestra.suscribir(zonaCobertura);

	      verify(manejador, times(1)).suscribir(zonaCobertura, muestra);
	     // assertTrue(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	}
	
	
	@Test
	public void testeandoLaDesuscripcionDeUnaZona() {
	      
	      muestra.desuscribir(zonaCobertura);

	      verify(manejador, times(1)).desuscribir(zonaCobertura);
	      //assertFalse(manejador.getListaDeSuscriptores().contains(zonaCobertura)); 
	 }
  
}
