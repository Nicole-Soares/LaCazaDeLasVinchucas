package vinchuca;

public class NivelDeVerificacion implements TipoFiltro {

	
	private String nivel;

	public boolean cumple(Muestra muestra) {
		
		return nivel == muestra.getNombreEstado();
	}

	public NivelDeVerificacion(String nivel) {
		this.nivel = nivel;
	}
	
}
