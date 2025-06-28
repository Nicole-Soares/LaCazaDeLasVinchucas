package vinchuca;

public abstract class  Estado {

	protected abstract void cargarOpinion(Muestra muestra, Opinion opinion);

	public abstract TipoDeOpinion resultadoActual(Muestra muestra);

	public abstract String nombre();
	

}
