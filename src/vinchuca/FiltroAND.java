package vinchuca;

public class FiltroAND extends FiltroCompuesto{

    @Override
    public boolean cumple(Muestra muestra) {
        //Devuelve true si todos los filtros cumplen.
        for (Filtro f : this.getFiltros()) {
            if(!f.cumple(muestra)) {
                return false;
            }
        }
        return true;
    }
}
