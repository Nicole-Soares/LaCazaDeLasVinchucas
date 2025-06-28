package vinchuca;

public class CategoriaExperto extends CategoriaUsuario{

	@Override
	public boolean esExperto() {
		
		return true;
	}



	@Override
	protected void verificarCategoria(Usuario usuario, long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas) {
		usuario.verificarCategoriaSiendoExperto( cantidadDeOpiniones,  cantidadDeMuestrasEnviadas);
		
	}

	//agregar uml
	@Override
	protected void evaluarParaCargarOpinionMuestraBasica(Muestra muestra, Opinion opinion) {
		opinion.procesarCargaEnMuestraBasicaConOpinionExperto(muestra);
		//muestra.procesarCargaEnMuestraBasicaConOpinionExperto();
		
	}

	@Override
	protected void evaluarParaCargarOpinionMuestraExperto(Muestra muestra, Opinion opinion) {
		opinion.procesarCargaEnMuestraExpertaConOpinionExperto(muestra);
		//muestra.procesarCargaEnMuestraExpertaConOpinionExperto( opinion);
		
	}

	@Override
	protected void evaluarOpinion(Muestra muestra, Opinion opinion, Opinion opinionAChequearTipo) {
		opinion.evaluadaEnExperta(muestra, opinionAChequearTipo);
		
	}
	
	/*@Override
	protected Opinion evaluarOpinionParaResultadoExperto(Opinion opinion, Muestra muestra) {
		return opinion.evaluadaEnExperta(muestra);
		
	}
	*/
}
