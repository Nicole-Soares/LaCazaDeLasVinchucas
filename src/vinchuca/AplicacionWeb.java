package vinchuca;

import java.util.ArrayList;
import java.util.List;


public class AplicacionWeb {

	private List<Muestra> muestras;
	private List<ZonaCobertura> zonas;
	private List<Usuario> usuarios;
	private List<Opinion> opiniones;
	
	
	public AplicacionWeb() {
		super();
		this.muestras = new ArrayList<Muestra>();
		this.zonas = new ArrayList<ZonaCobertura>();
		this.usuarios = new ArrayList<Usuario>();
		this.opiniones = new ArrayList<Opinion>();
	}
	
	public void registrarZonaDeCobertura(ZonaCobertura zona) {
		zonas.add(zona);
		
	}
	
	public void registrarMuestra(Muestra muestra) {
	
		muestras.add(muestra);
		this.notificarMuestraNueva(muestra);
		
	}
	
	private void notificarMuestraNueva(Muestra muestra) {
		for(ZonaCobertura zona : zonas) {
			zona.avisarNuevaMuestra(muestra);
		}
		
	}

	public void registrarOpinion(Opinion opinion){
		opiniones.add(opinion);
		
		
	}
	
	public void registrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	
	public List<Muestra> getMuestras(){
		return muestras;
	}

	public List<ZonaCobertura> zonasQueSolapan(ZonaCobertura zona){
		return this.zonas.stream().filter(z -> z.seSolapaCon(zona)).toList();
	}
	
	public List<Muestra> filtrarMuestras(Filtro filtro){
		
		
		return filtro.aplicarFiltro(muestras);
	}

	public List<ZonaCobertura> getZonas() {
		
		return zonas;
	}

	public List<Usuario> getUsuarios() {
		
		return usuarios;
	}

	public List<Opinion> getOpiniones() {
	
		return opiniones;
	}
}
