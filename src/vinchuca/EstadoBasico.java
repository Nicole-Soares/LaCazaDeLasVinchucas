package vinchuca;

public class EstadoBasico extends Estado{

	@Override
	protected void cargarOpinion(Muestra muestra, Opinion opinion) {
		//decirle a muestra que cualquiera puede escribir en este estado
		muestra.cargarOpinionEnEstadoBasico(opinion);
	}

	@Override
	public Opinion resultadoActual(Muestra muestra) {
		return muestra.resultadoFinalEnEstadoBasico();
		
	}

}
