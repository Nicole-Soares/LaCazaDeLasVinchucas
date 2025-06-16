package vinchuca;

public interface Observador {
	
	public void serNotificadoNuevaMuestra(ZonaCobertura zona, Muestra muestra);
	public void serNotificadoMuestraVerificada(ZonaCobertura zona, Muestra muestra);
}
