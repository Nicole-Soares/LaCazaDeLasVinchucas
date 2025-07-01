package vinchuca;

public class EstadoExperto extends Estado {

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		
        muestra.cargarOpinionEnEstadoExperto(opinion);
	}

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		return muestra.resultadoFinalEnEstadoExperto();
		
	}

	@Override
	protected boolean cumpleVerificacionBasica() {
		
		return false;
	}

	@Override
	protected boolean cumpleVerificacionExperta() {
		
		return true;
	}

	@Override
	protected boolean cumpleVerificacion() {
		
		return false;
	}

	
}

