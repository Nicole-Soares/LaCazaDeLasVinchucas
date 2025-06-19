package vinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManejadorMuestras {

	private List<Muestra> listaDeMuestras;
	
	
	public ManejadorMuestras() {
		super();
		this.listaDeMuestras = new ArrayList<Muestra>();
	}

	

	
	public List<Muestra> getListaDeMuestras() {
		return listaDeMuestras;
	}




	public void agregarUnaMuestra(Muestra muestra) {
		this.listaDeMuestras.add(muestra);
	}




	public Muestra crearYRegistrarMuestra(String especieDeVinchuca, String foto, LocalDate fechaMuestra,
			Ubicacion ubicacion, Estado estado, Persona autor, ManejadorMuestraVerificada manejadorMuestra) {
		
		Muestra muestraNueva = new Muestra( especieDeVinchuca,  foto,  fechaMuestra, ubicacion,  estado,  autor,  manejadorMuestra);
		this.agregarUnaMuestra(muestraNueva);
		return (muestraNueva);
		
	}

}