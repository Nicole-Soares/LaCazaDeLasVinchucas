package vinchuca;

public class FiltroMuestraBasica implements FiltroVerificacion{

	@Override
	public boolean cumple(Muestra muestra) {
		
		return muestra.cumpleVerificacionBasica();
	}

	
}
