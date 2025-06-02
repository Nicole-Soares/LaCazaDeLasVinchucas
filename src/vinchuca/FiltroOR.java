package vinchuca;

public class FiltroOR implements TipoFiltro {

	private TipoFiltro filtro1;
	private TipoFiltro filtro2;
	
	public FiltroOR(TipoFiltro filtro1, TipoFiltro filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}
	
	
	public boolean cumple(Muestra muestra) {
		
		return this.filtro1.cumple(muestra) || this.filtro2.cumple(muestra);
	}

}
