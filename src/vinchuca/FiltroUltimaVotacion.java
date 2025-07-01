package vinchuca;

import java.time.LocalDate;

public class FiltroUltimaVotacion extends Filtro{
	
	private FiltroFecha filtroFecha;
	private LocalDate fecha;
	
	
	public FiltroUltimaVotacion(FiltroFecha filtroFecha, LocalDate fecha) {
		this.filtroFecha = filtroFecha;
		this.fecha = fecha;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return this.filtroFecha.cumple(muestra.fechaUltimaOpinion(), fecha);
	}
}
