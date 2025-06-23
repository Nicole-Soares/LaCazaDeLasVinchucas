package vinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManejadorOpiniones {

	private List<Opinion> listaDeOpiniones;
	
	
	
	public ManejadorOpiniones() {
		super();
		this.listaDeOpiniones = new ArrayList<Opinion>();
	}

	

	public List<Opinion> getListaDeOpiniones() {
		return listaDeOpiniones;
	}


	public void agregarUnaOpinion(Opinion opinion) { // es package pero no me lo toma eclipse
		
		listaDeOpiniones.add(opinion);
	}

	public Opinion crearYRegistrarOpiniones(TipoDeOpinion tipo, LocalDate fecha, Usuario persona) {
		Opinion opinionNueva = new Opinion(tipo, fecha, persona);
		this.agregarUnaOpinion(opinionNueva);
		return (opinionNueva);
	}

}
