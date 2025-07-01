package vinchuca;

public class FiltroMuestraExperta implements FiltroVerificacion{

	@Override
	public boolean cumple(Muestra muestra) {
		
		return muestra.cumpleVerificacionExperta();
	}

}
