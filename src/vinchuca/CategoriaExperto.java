package vinchuca;

public class CategoriaExperto extends CategoriaUsuario{

	@Override
	public boolean esExperto() {
		
		return true;
	}

	@Override
	public boolean esBasico() {
		
		return false;
	}

	@Override
	protected void verificarCategoria(Usuario usuario, long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas) {
		if (cantidadDeOpiniones < 20 || cantidadDeMuestrasEnviadas < 10) {
			usuario.cambiarCategoria(new CategoriaBasico());
		}
		
		
	}
}
