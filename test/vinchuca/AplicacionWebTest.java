package vinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AplicacionWebTest {

	private AplicacionWeb aplicacionWeb;
	private ManejadorMuestras manejadorDeMuestra;
	private ManejadorZonasCobertura manejadorZona;
	private ManejadorUsuario manejadorUsuarios;
	private ManejadorOpiniones manejadorOpiniones;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	private Usuario usuario;
	private ManejadorMuestraVerificada manejador;
	private Estado estadoBasico;
	private TipoDeOpinion tipo;
	
	@BeforeEach
	public void setup() {
		manejadorDeMuestra = mock(ManejadorMuestras.class);
		manejadorZona = mock(ManejadorZonasCobertura.class);
		manejadorUsuarios = mock(ManejadorUsuario.class);
		manejadorOpiniones = mock(ManejadorOpiniones.class);
		ubicacion = mock(Ubicacion.class);
		aplicacionWeb = new AplicacionWeb(manejadorDeMuestra, manejadorZona, manejadorUsuarios, manejadorOpiniones);
		fecha = LocalDate.now();
		usuario = mock(Usuario.class);
		manejador = mock(ManejadorMuestraVerificada.class);
		estadoBasico = mock(EstadoBasico.class);
		tipo = TipoDeOpinion.VINCHUCA;
	}
	
	@Test
	public void testeandoLaCreacionDeZona() {
		when(manejadorZona.crearYRegistrarZonaDeCobertura("nombre", ubicacion, 2)).thenReturn(new ZonaCobertura("nombre", ubicacion, 2));
		assertEquals(aplicacionWeb.crearYRegistrarZonaDeCobertura("nombre", ubicacion, 2).getNombre(), "nombre");
		
	}
	
	@Test
	public void testeandoLaCreacionDeMuestra() {
		when(manejadorDeMuestra.crearYRegistrarMuestra( "especie", "foto", fecha, ubicacion, usuario,  manejador)).thenReturn(new Muestra("especie", "foto", fecha, ubicacion, estadoBasico, usuario,  manejador));
		assertEquals(aplicacionWeb.crearYRegistrarMuestra("especie", "foto", fecha, ubicacion, usuario,  manejador).getEspecieDeVinchuca(), "especie");
		
	}
	
	@Test
	public void testeandoLaCreacionDeOpiniones() {
	
		Opinion opinionEsperada = new Opinion(tipo, fecha, usuario); 

        when(manejadorOpiniones.crearYRegistrarOpiniones(tipo, fecha, usuario))
            .thenReturn(opinionEsperada);
		
       Opinion opinionObtenida = aplicacionWeb.crearYRegistrarOpiniones(tipo, fecha, usuario);
		
       assertEquals(tipo, opinionObtenida.getTipo());
       
        verify(manejadorOpiniones).crearYRegistrarOpiniones(tipo, fecha, usuario);
	}
		
	@Test
	public void testeandoLaCreacionDeUsuarios() {
	
		/*Usuario usuarioEsperada = new Usuario(); 

        when(manejadorUsuarios.crearYRegistrarUsuario())
            .thenReturn(usuarioEsperada);*/
		
      aplicacionWeb.crearYRegistrarUsuario();
		
       
        verify(manejadorUsuarios, times(1)).crearYRegistrarUsuario();
       
	}
	
	@Test
	public void testeandoLaCreacionDeUsuariosProfesionales() {
		
      aplicacionWeb.crearYRegistrarUsuariosProfesionales();
		
       
        verify(manejadorUsuarios, times(1)).crearYRegistrarUsuariosProfesionales();
       
	}
	
	@Test
	public void testeandoGetMuestra() {
		
		when(manejadorDeMuestra.crearYRegistrarMuestra( "especie", "foto", fecha, ubicacion, usuario,  manejador)).thenReturn(new Muestra("especie", "foto", fecha, ubicacion, estadoBasico, usuario,  manejador));
		
		List <Muestra>lista = Arrays.asList(new Muestra("especie", "foto", fecha, ubicacion, estadoBasico, usuario,  manejador));
		when(manejadorDeMuestra.getListaDeMuestras()).thenReturn(lista);
		aplicacionWeb.crearYRegistrarMuestra("especie", "foto", fecha, ubicacion, usuario,  manejador).getEspecieDeVinchuca();
       
		
        assertEquals(aplicacionWeb.getMuestras().size(),1);
        
       
	}
	
	public void testeandoFiltro() {
		
		Muestra m = mock(Muestra.class);
		List<Muestra> muestras = Arrays.asList(m);                                                                                                                                                                                                                                                                          ;
		TipoFiltro tipoFiltro = mock(FiltroFechaMuestra.class);
		Filtro filtro = new Filtro( tipoFiltro);
		
		verify(filtro, times(1)).aplicarFiltro(muestras);
		
       
	}
	
}
