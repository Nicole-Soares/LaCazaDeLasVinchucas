package vinchuca;

public class NivelDeVerificacion implements TipoFiltro {

	
	private String nivel;

	public boolean cumple(Muestra muestra) {
		
		return nivel == muestra.getEstado().nombre();
	}

	public NivelDeVerificacion(String nivel) {
		this.nivel = nivel;
	}
	
}
