package vinchuca;

public class EstadoVerificado extends Estado {

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Opinion resultadoActual(Muestra muestra) {
		return muestra.resultadoActualEnEstadoVerificado();
		
	}

	
}
