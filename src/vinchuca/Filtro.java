package vinchuca;

import java.util.List;
import java.util.stream.Collectors;

    public abstract class Filtro {

    public List<Muestra> aplicarFiltro(List<Muestra> muestras) {
        return muestras.stream()
                       .filter(m -> this.cumple(m))
                       .collect(Collectors.toList());
    }

    public abstract boolean cumple(Muestra muestra);
}
