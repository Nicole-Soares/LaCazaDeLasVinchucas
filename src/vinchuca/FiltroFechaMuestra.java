package vinchuca;

import java.util.Date;

public class FiltroFechaMuestra implements TipoFiltro {

	private Date fecha;
	
	public FiltroFechaMuestra(Date fecha) {
		this.fecha = fecha;
	}
	
	
	public boolean cumple(Muestra muestra) {
		
		return muestra.getFechaCreacion().after(fecha);
	}

}
