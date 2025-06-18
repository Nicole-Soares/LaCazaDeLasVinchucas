package vinchuca;

public class Tipoinsecto implements TipoFiltro {


	private String insecto;


	public boolean cumple(Muestra muestra) {
		
		return this.insecto == muestra.getEspecie();
	}
	
	
	public Tipoinsecto(String insecto) {
		this.insecto = insecto;
	}
	
	

}
