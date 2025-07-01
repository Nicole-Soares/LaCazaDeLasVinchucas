package vinchuca;

public class EstadoBasico extends Estado{

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		//decirle a muestra que cualquiera puede escribir en este estado
		muestra.cargarOpinionEnEstadoBasico(opinion);
	}

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		return muestra.resultadoFinalEnEstadoBasico();
		
	}

	@Override
	protected boolean cumpleVerificacionBasica() {
		
		return true;
	}

	@Override
	protected boolean cumpleVerificacionExperta() {
		
		return false;
	}

	@Override
	protected boolean cumpleVerificacion() {
	
		return false;
	}

	
	
}
