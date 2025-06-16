package vinchuca;

import java.util.ArrayList;
import java.util.List;

public class ManejadorZonasCobertura {
	private List<ZonaCobertura> zonasDeCobertura;
	
	public ManejadorZonasCobertura() {
		this.zonasDeCobertura = new ArrayList<ZonaCobertura>();
	}
	public ZonaCobertura crearYRegistrarZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
		ZonaCobertura nuevaZona = new ZonaCobertura(nombre, epicentro, radio);
		this.zonasDeCobertura.add(nuevaZona);
		return nuevaZona;
	}
	public ManejadorZonasCobertura eliminarZona(ZonaCobertura zona) {
		this.zonasDeCobertura.remove(zona);
		return this;
	}
	public List<ZonaCobertura> zonasQueSolapan(ZonaCobertura zona){
		return this.zonasDeCobertura.stream().filter(z -> z.seSolapaCon(zona)).toList();
	}
	public List<ZonaCobertura> getZonasCobertura(){
		return this.zonasDeCobertura;
	}
}
