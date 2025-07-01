package vinchuca;

public class FiltroOR extends FiltroCompuesto{

    @Override
    public boolean cumple(Muestra muestra) {
        //Con que un filtro cumpla devuelve true
        for (Filtro f : this.getFiltros()) {
            if(f.cumple(muestra)) {
                return true;
            }
        }
        return false;
    }
}
