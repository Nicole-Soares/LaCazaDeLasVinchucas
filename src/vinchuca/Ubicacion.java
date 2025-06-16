package vinchuca;

import java.util.List;

public class Ubicacion {

	private final double latitud;
	private final double longitud;
	
	public Ubicacion() {
		this.latitud = 0;
		this.longitud = 0;
	}
	
	public Ubicacion(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}
	public double distanciaEntre(Ubicacion otraUbicacion) {
		//Raiz cuadrada de ((x1 - x)^2 + ((y1 - y)^2);
	    return Math.sqrt(Math.pow(otraUbicacion.getLongitud() - longitud, 2) 
	    			   + Math.pow(otraUbicacion.getLatitud() - latitud, 2)); 
	}
	public double getLatitud() {
		return this.latitud;
	}	
	public double getLongitud() {
		return this.longitud;
	}
	public List<Ubicacion> ubicacionesA(List<Ubicacion> ubicaciones, double distanciaMax){
		return ubicaciones.stream()
						  .filter(ubicacion -> this.distanciaEntre(ubicacion) <= distanciaMax)
						  .toList();
	}
	public List<Muestra> cercanasA(Muestra muestra, List<Muestra> muestras, double distanciaMax){
		//Me guardo la ubicacion de la muestra a evaluar
        Ubicacion ubicacionDeLaMuestra = muestra.getUbicacion();
        //Filtro aquellas que estan en el rango dado
        return muestras.stream()
                .filter(m -> ubicacionDeLaMuestra.distanciaEntre(m.getUbicacion()) <= distanciaMax)
                .toList();
	}
}
