package vinchuca;

import java.time.LocalDate;

public class FiltroFechaMuestra extends Filtro {
	
	private FiltroFecha filtroFecha;
	private LocalDate fecha;

	public FiltroFechaMuestra(FiltroFecha filtroFecha, LocalDate fecha) {
		this.filtroFecha = filtroFecha;
		this.fecha = fecha;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return filtroFecha.cumple(muestra.getFechaCreacion(), fecha);
	}
}
