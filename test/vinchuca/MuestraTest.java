package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class MuestraTest {


	
    
    private Muestra muestra;
    private Estado estadoBasico;
    private Usuario personaBasica;
    private Usuario personaExperta;
    private ObservadorMuestra manejador;
    private Ubicacion ubicacion;
    private Ubicacion ubicacion2;
    private LocalDate fecha;
    private LocalDate fecha2;
    private Opinion opinionBasica;
    private Opinion opinionBasicaDos;
    private Opinion opinionBasicaTres;
    private Opinion opinionBasicaCuatro;
    private Opinion opinionExperta;
    private Opinion opinionExpertaDos;
    private Opinion opinionExpertaTres;
    private ZonaCobertura zonaCobertura;
    private TipoDeOpinion tipo;
    private TipoDeOpinion tipo2;
    
    @BeforeEach
    public void setup(){
    	
    	personaBasica = new Usuario("Daenerys");
    	personaExperta = new UsuarioProfesional("Daemon");
    	ubicacion = mock(Ubicacion.class);
    	ubicacion2 = mock(Ubicacion.class);
    	fecha = LocalDate.now();
    	fecha2 = LocalDate.of(2024, 3, 15);
    	manejador = mock(ObservadorMuestra.class);//new ManejadorMuestra();
    	estadoBasico = new EstadoBasico();
    	tipo = TipoDeOpinion.VINCHUCA;
    	tipo2 = TipoDeOpinion.CHINCHEFOLIADA;
    	muestra = new Muestra("chinche", "foto", fecha, ubicacion,estadoBasico, personaBasica, manejador);
    	opinionBasica = new Opinion( tipo,  fecha, personaBasica);
    	opinionBasicaDos =  new Opinion( tipo,  fecha, personaBasica);
    	opinionBasicaTres = new Opinion( tipo2,  fecha, personaBasica);
    	opinionBasicaCuatro = new Opinion( tipo2,  fecha, personaBasica);
    	opinionExperta = new Opinion( tipo,  fecha2, personaExperta);
    	opinionExpertaDos = new Opinion( tipo2,  fecha, personaExperta);
    	opinionExpertaTres =  new Opinion( tipo,  fecha, personaExperta);
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
    	
   	  
   	    muestra.cargarOpinion(opinionExperta);    
   	    muestra.cargarOpinion(opinionExpertaTres);  
   	   
   	    verify(manejador, times(1)).notificarMuestraVerificada(muestra);

    }
    
    @Test
    public void testeandoFechaDeLaUltimaOpinion() {
    	
    	
        muestra.cargarOpinion(opinionBasica);
 	    muestra.cargarOpinion(opinionExperta);
 	   
 	    assertEquals(muestra.fechaUltimaOpinion(), fecha2);

    }
   
    @Test
    public void testeandoLaCargaDeUnaOpinionDePersonaBasicaEnEstadoBasicoLaMuestra() {
	  
	   muestra.cargarOpinion(opinionBasica);
	   assertTrue(muestra.getOpiniones().contains(opinionBasica));
    }
   
    @Test
    public void testeandoLaCargaDeUnaOpinionDePersonaExpertaEnEstadoBasicoLaMuestra() {
	   
	   muestra.cargarOpinion(opinionExperta);
	   
	 
	   assertTrue(muestra.getOpiniones().contains(opinionExperta));
    }
   
    @Test
    public void testeandoLaCargaDeUnaOpinionDePersonaExpertaEnEstadoBasicoDeLaMuestraYDespuesLaDeUnaPersonaBasica() {
	   
	   muestra.cargarOpinion(opinionExperta);
	   
	  
	   muestra.cargarOpinion(opinionBasica);
	   assertFalse(muestra.getOpiniones().contains(opinionBasica));
    }
   
    @Test
    public void testeandoLaCargaDeDosOpinionesDiferentesDeExpertos() {
	 
	    muestra.cargarOpinion(opinionExperta);     // carga 1
	    muestra.cargarOpinion(opinionExpertaDos);  // carga 2 (diferente)
	    muestra.cargarOpinion(opinionExpertaTres); // carga 3 (debería permitirla)

	    // Verificamos que la tercera fue agregada
	    assertEquals(3, muestra.getOpiniones().size());

    }
   
     //chequeamos estado verificado no acepta mas opiniones
    @Test
    public void testeandoLaCargaDeDosOpinionesIgualesDeExpertosHaciendoQueMuestraEsteVerificada() {
	    
	    muestra.cargarOpinion(opinionExperta);     // carga 1
	    muestra.cargarOpinion(opinionExpertaTres); // mismo tipo  
	    muestra.cargarOpinion(opinionExpertaDos);  // (no la agrega)

	    assertFalse(muestra.getOpiniones().contains(opinionExpertaDos)); // esta en estado verificada, no agrega mas

    }
   
    @Test
    public void testeandoResultadoActualConPersonasBasicasYMuestraEnEstadoBasico() {
	  
	   
	   muestra.cargarOpinion(opinionBasica);
	   muestra.cargarOpinion(opinionBasicaDos);
	   
	   assertEquals(muestra.resultadoActual(), TipoDeOpinion.VINCHUCA);

    }
    
    @Test
    public void testeandoResultadoActualSinOpinionesYMuestraEnEstadoBasico() {
	  
	   
	  
	   
	   assertEquals(muestra.resultadoActual(), TipoDeOpinion.NINGUNA);

    }
   
    @Test
    public void testeandoResultadoActualConPersonasBasicasCreandoEmpateYMuestraEnEstadoBasico() {
	  
	   
	   muestra.cargarOpinion(opinionBasica);
	   muestra.cargarOpinion(opinionBasicaDos);
	   
	   muestra.cargarOpinion(opinionBasicaTres);
	   muestra.cargarOpinion(opinionBasicaCuatro);
	   
	   assertEquals(muestra.resultadoActual(), TipoDeOpinion.EMPATE);

    }
    @Test
    public void testeandoResultadoActualConDosPersonasBasicasQueCoincidenYUnaQueNoYMuestraEnEstadoBasico() {
	 
	   muestra.cargarOpinion(opinionBasica);
	   
	   muestra.cargarOpinion(opinionBasicaDos);
	  
	   muestra.cargarOpinion(opinionBasicaTres);
	   
	   
	   assertEquals(muestra.resultadoActual(), TipoDeOpinion.VINCHUCA);

    }
   
    @Test
    public void testeandoResultadoActualConPersonasExpertasQueNoCoincidenYMuestraEnEstadoExperto() {
    	
      
	    muestra.cargarOpinion(opinionExperta);
	    muestra.cargarOpinion(opinionExpertaDos);
	   
		assertEquals(muestra.resultadoActual(), TipoDeOpinion.EMPATE);

    }
    
    @Test
    public void testeandoResultadoActualConPersonasBasicaYExpertasYMuestraEnEstadoExperto() {
    	
    	
  	    muestra.cargarOpinion(opinionBasica);
    	
	    muestra.cargarOpinion(opinionExperta);
	   
	 
	   
		assertEquals(muestra.resultadoActual(), TipoDeOpinion.VINCHUCA);

    }
    
   
    @Test
    public void testeandoResultadoActualConPersonasExpertasYMuestraEnEstadoVerificado() {
   	
      
	    muestra.cargarOpinion(opinionExperta);
	   
	    muestra.cargarOpinion(opinionExpertaTres);
	   
		assertEquals(muestra.resultadoActual(), TipoDeOpinion.VINCHUCA);

    }
    
    @Test
    public void testeandoResultadoActualConPersonasBasicaYExpertasYMuestraEnEstadoVerificado() {
	   
	  
	   muestra.cargarOpinion(opinionBasica);
	   muestra.cargarOpinion(opinionExperta);// PASA A EXPERTO
	   muestra.cargarOpinion(opinionExpertaTres);//sigue en experto porque no coinciden
	   muestra.cargarOpinion(opinionExpertaDos); // pasa a verificado
	   
	  assertEquals(muestra.resultadoActual(), TipoDeOpinion.VINCHUCA);
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
  
	@Test
	public void testeandoUsuarioYaOpino() {
	      
		  muestra.cargarOpinion(opinionBasica);

	      
	      assertTrue(muestra.yaOpino(personaBasica)); 
	 }
}
