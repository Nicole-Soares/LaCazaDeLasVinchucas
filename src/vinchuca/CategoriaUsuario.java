package vinchuca;

public abstract class CategoriaUsuario {

	public abstract boolean esExperto();
	protected abstract void verificarCategoria(Usuario usuario,long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas);
	protected abstract void evaluarParaCargarOpinionMuestraBasica(Muestra muestra, Opinion opinion); // agregar uml
	protected abstract void evaluarParaCargarOpinionMuestraExperto(Muestra muestra, Opinion opinion); // agregar uml
	protected abstract void evaluarOpinion(Muestra muestra, Opinion opinion, Opinion opinionAChequearTipo);
	//protected abstract Opinion evaluarOpinionParaResultadoExperto(Opinion opinion, Muestra muestra);

}
