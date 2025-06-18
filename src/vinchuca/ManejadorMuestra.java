package vinchuca;

import java.util.ArrayList;
import java.util.List;

public class ManejadorMuestra {

	private List <ZonaCobertura> listaDeZonasCobertura;
	

	public ManejadorMuestra() {
		super();
		this.listaDeZonasCobertura = new ArrayList<ZonaCobertura>();
	}


	
	public void suscribir(ZonaCobertura zona, Muestra muestra) {
		if (zona.contiene(muestra)) {
			listaDeZonasCobertura.add(zona);
		}
	}
	
	public void desuscribir(ZonaCobertura zona) {
		
			listaDeZonasCobertura.remove(zona);
		
	}
	
	public void notificarMuestraVerificada(Muestra muestra) {
		for(ZonaCobertura zona: listaDeZonasCobertura) {
			zona.avisarMuestraVerificada(muestra);
		}
		
	}
	
	public void notificarMuestraNueva(Muestra muestra) {
		for(ZonaCobertura zona: listaDeZonasCobertura) {
			zona.avisarNuevaMuestra(muestra);
		}
		
	}


	public List<ZonaCobertura> getListaDeSuscriptores() {
		return listaDeZonasCobertura;
	}


	
}
