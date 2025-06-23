package vinchuca;

import java.time.LocalDate;

public class AplicacionWeb {

	private ManejadorMuestras manejadorDeMuestra;
	private ManejadorZonasCobertura manejadorZona;
	private ManejadorUsuario manejadorUsuarios;
	private ManejadorOpiniones manejadorOpiniones;
	
	
	public AplicacionWeb(ManejadorMuestras manejadorMuestra, ManejadorZonasCobertura manejadorZona, ManejadorUsuario manejadorUsuarios, ManejadorOpiniones manejadorOpiniones) {
		super();
		this.manejadorDeMuestra = manejadorMuestra;
		this.manejadorZona = manejadorZona;
		this.manejadorUsuarios = manejadorUsuarios;
		this.manejadorOpiniones = manejadorOpiniones;
	}
	
	public ZonaCobertura crearYRegistrarZonaDeCobertura(String nombre, Ubicacion ubicacion, double radio) {
		ZonaCobertura zonaNueva = manejadorZona.crearYRegistrarZonaDeCobertura(nombre, ubicacion, radio);
		return zonaNueva;
	}
	
	public Muestra crearYRegistrarMuestra(String especieDeVinchuca, String foto, LocalDate fechaMuestra, Ubicacion ubicacion, Estado estado, Usuario autor, ManejadorMuestraVerificada manejadorMuestra) {
		Muestra muestra = manejadorDeMuestra.crearYRegistrarMuestra(especieDeVinchuca, foto, fechaMuestra, ubicacion, estado, autor, manejadorMuestra);
		manejadorZona.notificarMuestraNueva(muestra);
		return muestra;
	}
	
	public Opinion crearYRegistrarOpiniones(TipoDeOpinion tipo, LocalDate fecha, Usuario persona){
		Opinion opinion = manejadorOpiniones.crearYRegistrarOpiniones(tipo, fecha, persona);
		
		return opinion;
	}
	
	public Usuario crearYRegistrarUsuario() {
		Usuario usuario = manejadorUsuarios.crearYRegistrarUsuario();
		return usuario;
	}
	
	public UsuarioProfesional crearYRegistrarUsuariosProfesionales() {
		UsuarioProfesional usuarioProfesional = manejadorUsuarios.crearYRegistrarUsuariosProfesionales();
		return usuarioProfesional;
	}
}
