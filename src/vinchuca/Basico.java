package vinchuca;

public class Basico extends Categoria {

	@Override
	public boolean esExperto() {
		
		return false;
	}

	@Override
	public boolean esBasico() {
		
		return true;
	}

}
