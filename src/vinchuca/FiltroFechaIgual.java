package vinchuca;

import java.time.LocalDate;

public class FiltroFechaIgual implements FiltroFecha {

    @Override
    public boolean cumple(LocalDate unaFecha, LocalDate otraFecha) {
        return unaFecha.isEqual(otraFecha);
    }
}