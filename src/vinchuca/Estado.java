package vinchuca;

public abstract class  Estado {

	protected abstract void cargarOpinion(Muestra muestra, Opinion opinion);

	public abstract TipoDeOpinion resultadoActual(Muestra muestra);

	protected abstract boolean cumpleVerificacionBasica();

	protected abstract boolean cumpleVerificacionExperta();

	protected abstract boolean cumpleVerificacion();

	
	

}
