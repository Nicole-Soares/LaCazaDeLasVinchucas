package vinchuca;

public abstract class CategoriaUsuario {

	public abstract boolean esExperto();
	public abstract boolean esBasico();
	protected abstract void verificarCategoria(Usuario usuario,long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas);

}
