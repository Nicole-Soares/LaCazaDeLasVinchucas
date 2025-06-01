package vinchuca;

public class Experto extends Categoria{

	@Override
	public boolean esExperto() {
		
		return true;
	}

	@Override
	public boolean esBasico() {
		
		return false;
	}

}
