package vinchuca;

import java.util.Date;

public class FechaUltimaVotacion implements TipoFiltro {

	private Date fecha;
	
	public boolean cumple(Muestra muestra) {
		return muestra.fechaUltimaOpinion().after(fecha);
	}

}
