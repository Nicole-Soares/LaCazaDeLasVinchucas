package vinchuca;

public class FiltroMuestraVerificada implements FiltroVerificacion {

	@Override
	public boolean cumple(Muestra muestra) {
		
		return muestra.cumpleVerificacion();
	}

}
