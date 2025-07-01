package vinchuca;

import java.time.LocalDate;

public class FiltroFechaMenor implements FiltroFecha{

    @Override
    public boolean cumple(LocalDate unaFecha, LocalDate otraFecha) {
        return unaFecha.isBefore(otraFecha);
    }
}