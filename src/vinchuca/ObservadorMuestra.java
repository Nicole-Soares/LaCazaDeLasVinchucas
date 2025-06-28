package vinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ObservadorMuestra {

	private List <ZonaCobertura> listaDeZonasCobertura;
	

	public ObservadorMuestra() {
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
	

	public List<ZonaCobertura> getListaDeSuscriptores() {
		return listaDeZonasCobertura;
	}


	
}
