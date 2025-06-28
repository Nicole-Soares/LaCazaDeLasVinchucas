package vinchuca;

public class CategoriaBasico extends CategoriaUsuario {

	@Override
	public boolean esExperto() {
		
		return false;
	}

	
	@Override
	protected void verificarCategoria(Usuario usuario, long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas) {
		usuario.verificarCategoriaSiendoBasico( cantidadDeOpiniones,  cantidadDeMuestrasEnviadas);
		
		
	}

	@Override
	protected void evaluarParaCargarOpinionMuestraBasica(Muestra muestra, Opinion opinion) {
		
		opinion.procesarCargaEnMuestraBasicaConOpinionBasica(muestra);
		//no hace nada si la opinion es basica
	}

	@Override
	protected void evaluarParaCargarOpinionMuestraExperto(Muestra muestra, Opinion opinion) {
		
		opinion.procesarCargaEnMuestraExpertaConOpinionBasica(muestra);
		
		//no hace nada si la opinion es basica, ni agrega ni tiene que chequear si hay otra opinion igual para pasar a verificado
		
	}

	@Override
	protected void evaluarOpinion(Muestra muestra, Opinion opinion, Opinion opinionAChequearTipo) {
		
		opinion.evaluadaEnBasica(muestra, opinionAChequearTipo);
	}

	/*@Override
	protected Opinion evaluarOpinionParaResultadoExperto(Opinion opinion, Muestra muestra) {
		return opinion.evaluadaEnBasica(muestra);
		
	}
*/
}
