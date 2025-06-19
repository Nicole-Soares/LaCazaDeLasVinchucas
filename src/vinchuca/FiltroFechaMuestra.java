package vinchuca;

import java.time.LocalDate;

public class FiltroFechaMuestra implements TipoFiltro {

	private LocalDate fecha;
	
	public FiltroFechaMuestra(LocalDate fechaFiltro) {
		this.fecha = fechaFiltro;
	}
	
	
	public boolean cumple(Muestra muestra) {
		
		return muestra.getFechaCreacion().isAfter(fecha);
	}

}
