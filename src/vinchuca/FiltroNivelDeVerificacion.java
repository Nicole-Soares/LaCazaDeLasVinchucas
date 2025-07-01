package vinchuca;

public class FiltroNivelDeVerificacion extends Filtro {

	
	private FiltroVerificacion filtoVerificacion;
	
	public FiltroNivelDeVerificacion(FiltroVerificacion filtoVerificacion) {
		this.filtoVerificacion = filtoVerificacion;
	}

	public boolean cumple(Muestra muestra) {
		
		
		return filtoVerificacion.cumple(muestra);
				
	}


	
}
