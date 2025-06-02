package vinchuca;

public class EstadoVerificado extends Estado {

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		muestra.cargarOpinionEnEstadoVerificado(opinion);
		
	}

	@Override
	public Opinion resultadoActual(Muestra muestra) {
		return muestra.resultadoFinalEnEstadoVerificado();
		
	}

	@Override
	public String nombre() {
		
		return "Verificado";
	}

	
	
	
}
