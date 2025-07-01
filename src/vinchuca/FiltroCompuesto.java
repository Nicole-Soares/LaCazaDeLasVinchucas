package vinchuca;

import java.util.ArrayList;
import java.util.List;

public abstract class FiltroCompuesto extends Filtro{
    
    private List<Filtro> filtros;

    public FiltroCompuesto() {
        this.filtros = new ArrayList<>();
    }
    public List<Filtro> getFiltros(){
        return this.filtros;
    }
    public FiltroCompuesto addFiltro(Filtro filtro) {
        this.filtros.add(filtro);
        return this;
    }
    public void removeFiltro(Filtro filtro) {
        this.filtros.remove(filtro);
    }
}
