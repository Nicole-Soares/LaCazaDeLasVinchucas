package vinchuca;

public class EstadoVerificado extends Estado {

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		muestra.cargarOpinionEnEstadoVerificado(opinion);
		
	}

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		return muestra.resultadoFinalEnEstadoVerificado();
		
	}

	@Override
	protected boolean cumpleVerificacionBasica() {
		
		return false;
	}

	@Override
	protected boolean cumpleVerificacionExperta() {
		
		return false;
	}

	@Override
	protected boolean cumpleVerificacion() {
		
		return true;
	}

	
	
	
	
}
