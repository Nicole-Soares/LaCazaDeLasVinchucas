package vinchuca;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FechaUltimaVotacion implements TipoFiltro {

	private LocalDate fecha; // Cambia el tipo a LocalDate
	

	
	public FechaUltimaVotacion(LocalDate fechaFiltro) { // El constructor acepta LocalDate
		this.fecha = fechaFiltro; // Simplemente asigna el LocalDate
	}

	public boolean cumple(Muestra muestra) {
		// Asumiendo que muestra.fechaUltimaOpinion() devuelve un LocalDate
		return muestra.fechaUltimaOpinion().isAfter(fecha);
	}
}
