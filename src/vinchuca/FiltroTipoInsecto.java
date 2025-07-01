package vinchuca;

public class FiltroTipoInsecto extends Filtro{

    private String insecto;

    public FiltroTipoInsecto(String insecto) {
        this.insecto = insecto;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return muestra.getEspecieDeVinchuca().equals(this.insecto);
    }
}