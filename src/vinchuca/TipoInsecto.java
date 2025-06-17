package vinchuca;

public class TipoInsecto implements TipoFiltro {

	private String insecto;


	public TipoInsecto(String insecto) {
		this.insecto = insecto;
	}
	

	public boolean cumple(Muestra muestra) {
		
		return this.insecto == muestra.getEspecie();
	}
	
	
	

}
