package vinchuca;

public class CategoriaBasico extends CategoriaUsuario {

	@Override
	public boolean esExperto() {
		
		return false;
	}

	@Override
	public boolean esBasico() {
		
		return true;
	}

	@Override
	protected void verificarCategoria(Usuario usuario, long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas) {
		if (cantidadDeOpiniones > 20 && cantidadDeMuestrasEnviadas > 10) {
			usuario.cambiarCategoria(new CategoriaExperto());
		}
		
		
	}

}
