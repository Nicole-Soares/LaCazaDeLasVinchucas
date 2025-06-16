package vinchuca;

import java.util.ArrayList;
import java.util.List;

public class ManejadorMuestra {

	private List <ZonaCobertura> listaDeZonasCobertura;
	
	
	
	
	public ManejadorMuestra(List<ZonaCobertura> listaDeZonasCobertura) {
		super();
		this.listaDeZonasCobertura = listaDeZonasCobertura;
	}


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
	
	public void notificar(Muestra muestra) {
		for(ZonaCobertura zona: listaDeZonasCobertura) {
			zona.avisarMuestraVerificada(muestra);
		}
		
	}

	
}
