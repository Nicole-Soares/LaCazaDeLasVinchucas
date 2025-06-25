package vinchuca;

public class TipoInsecto implements TipoFiltro {


	private String insecto;


	public boolean cumple(Muestra muestra) {
		
		return this.insecto == muestra.getEspecieDeVinchuca();
	}
	
	
	public TipoInsecto(String insecto) {
		this.insecto = insecto;
	}
	
	

}
