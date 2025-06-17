package vinchuca;

import java.time.LocalDate;
import java.util.Date;

public class FechaUltimaVotacion implements TipoFiltro {

	private Date fecha;
	
	public FechaUltimaVotacion(Date fechaFiltro) {
		this.fecha = fechaFiltro;
	}

	public boolean cumple(Muestra muestra) {
		return muestra.fechaUltimaOpinion().after(fecha);
	}

}
