package vinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class Filtro {
	
	private List<Muestra> muestras;
	private TipoFiltro filtroAUsar;
	
	
	public Filtro(List<Muestra> muestras) {
		this.muestras = muestras;
	}
	
	public List<Muestra> aplicarFiltro(Filtro filtro) {
        return muestras.stream()
                       .filter(m -> filtroAUsar.cumple(m))
                       .collect(Collectors.toList());
    }
	// Aplico el filtro a toda la lista de muestras, y las que cumplen, se devuelven
	
	
	public void setFiltro(TipoFiltro filtro) {
		this.filtroAUsar = filtro;
	}
	
	
}
