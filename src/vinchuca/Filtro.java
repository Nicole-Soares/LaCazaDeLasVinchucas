package vinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class Filtro {
	
	
	private TipoFiltro filtroAUsar;
	
	
	public Filtro( TipoFiltro tipo) {
		this.filtroAUsar = tipo;
	}
	
	public List<Muestra> aplicarFiltro(List<Muestra> muestras) {
        return muestras.stream()
                       .filter(m -> filtroAUsar.cumple(m))
                       .collect(Collectors.toList());
    }
	// Aplico el filtro a toda la lista de muestras, y las que cumplen, se devuelven
	
	
	public void setFiltro(TipoFiltro filtro) {
		this.filtroAUsar = filtro;
	}
	
	
}
