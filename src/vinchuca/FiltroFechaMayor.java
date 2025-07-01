package vinchuca;

import java.time.LocalDate;

public class FiltroFechaMayor implements FiltroFecha{

    @Override
    public boolean cumple(LocalDate unaFecha, LocalDate otraFecha) {
        return unaFecha.isAfter(otraFecha);
    }
}
