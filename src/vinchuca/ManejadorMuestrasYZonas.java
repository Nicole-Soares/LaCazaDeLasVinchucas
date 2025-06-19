package vinchuca;

import java.time.LocalDate;

public class ManejadorMuestrasYZonas {

	private ManejadorMuestras manejadorDeMuestra;
	private ManejadorZonasCobertura manejadorZona;
	
	public ManejadorMuestrasYZonas(ManejadorMuestras manejadorMuestra, ManejadorZonasCobertura manejadorZona) {
		super();
		this.manejadorDeMuestra = manejadorMuestra;
		this.manejadorZona = manejadorZona;
	}
	
	public ZonaCobertura crearYRegistrarZonaDeCobertura(String nombre, Ubicacion ubicacion, double radio) {
		return manejadorZona.crearYRegistrarZonaDeCobertura(nombre, ubicacion, radio);
	}
	
	public Muestra crearYRegistrarMuestra(String especieDeVinchuca, String foto, LocalDate fechaMuestra, Ubicacion ubicacion, Estado estado, Persona autor, ManejadorMuestraVerificada manejadorMuestra) {
		Muestra muestra = manejadorDeMuestra.crearYRegistrarMuestra(especieDeVinchuca, foto, fechaMuestra, ubicacion, estado, autor, manejadorMuestra);
		manejadorZona.notificarMuestraNueva(muestra);
		return muestra;
	}
	
}
