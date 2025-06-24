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




	public void agregarUnaMuestra(Muestra muestra) { // es privado pero si pongo package no me lo toma el eclipse
		this.listaDeMuestras.add(muestra);
	}




	public Muestra crearYRegistrarMuestra(String especieDeVinchuca, String foto, LocalDate fechaMuestra,
			Ubicacion ubicacion, Usuario autor, ManejadorMuestraVerificada manejadorMuestra) {
		
		Estado estadoBasico = new EstadoBasico();
		Muestra muestraNueva = new Muestra( especieDeVinchuca,  foto,  fechaMuestra, ubicacion,  estadoBasico,  autor,  manejadorMuestra);
		this.agregarUnaMuestra(muestraNueva);
		return (muestraNueva);
		
	}

}