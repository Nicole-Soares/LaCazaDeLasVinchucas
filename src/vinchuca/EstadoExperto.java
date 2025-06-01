package vinchuca;

public class EstadoExperto extends Estado {

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		// TODO Auto-generated method stub
        muestra.cargarOpinionEnEstadoExperto(opinion);
	}

	@Override
	public Opinion resultadoActual(Muestra muestra) {
		return muestra.resultadoFinalEnEstadoExperto();
		
	}

}
