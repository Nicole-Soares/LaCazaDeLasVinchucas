package vinchuca;

public interface Sujeto {
	public void agregarInteresado(Observador observador);
	public void sacarInteresado(Observador observador);
	public void avisarNuevaMuestra(Muestra muestra);
	public void avisarMuestraVerificada(Muestra muestra);
}
