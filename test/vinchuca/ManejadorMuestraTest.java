package vinchuca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManejadorMuestraTest {

    private ManejadorMuestras manejadorMuestras;
    private Muestra muestraMock1;
    private Muestra muestraMock2;
    private Ubicacion ubicacionMock;
    private Usuario autorMock;
    private ManejadorMuestraVerificada manejadorMuestraVerificadaMock;

    @BeforeEach
    void setUp() {
        
        manejadorMuestras = new ManejadorMuestras();

        // Mocks de las dependencias
        muestraMock1 = mock(Muestra.class);
        muestraMock2 = mock(Muestra.class);
        ubicacionMock = mock(Ubicacion.class);
        autorMock = mock(Usuario.class);
        manejadorMuestraVerificadaMock = mock(ManejadorMuestraVerificada.class);
    }

    @Test
    void testConstructorInicializaListaDeMuestrasVacia() {
        // Verify
        assertNotNull(manejadorMuestras.getListaDeMuestras());
        assertTrue(manejadorMuestras.getListaDeMuestras().isEmpty());
    }

    @Test
    void testAgregarUnaMuestraAgregaCorrectamenteALaLista() {
        // Exercise
        manejadorMuestras.agregarUnaMuestra(muestraMock1);

        // Verify
        List<Muestra> muestras = manejadorMuestras.getListaDeMuestras();
        assertEquals(1, muestras.size());
        assertTrue(muestras.contains(muestraMock1));
    }

    @Test
    void testAgregarVariasMuestrasAgregaCorrectamenteALaLista() {
        // Exercise
        manejadorMuestras.agregarUnaMuestra(muestraMock1);
        manejadorMuestras.agregarUnaMuestra(muestraMock2);

        // Verify
        List<Muestra> muestras = manejadorMuestras.getListaDeMuestras();
        assertEquals(2, muestras.size());
        assertTrue(muestras.contains(muestraMock1));
        assertTrue(muestras.contains(muestraMock2));
    }

    @Test
    void testCrearYRegistrarMuestraCreaYAgregaUnaNuevaMuestra() {
        // Setup de datos de prueba
        String especie = "Vinchuca Doméstica";
        String foto = "foto123.jpg";
        LocalDate fecha = LocalDate.now();

        // Exercise: Llama al método a testear
        Muestra nuevaMuestra = manejadorMuestras.crearYRegistrarMuestra(
            especie, foto, fecha, ubicacionMock, autorMock, manejadorMuestraVerificadaMock
        );

        // Verify:
        // 1. La muestra devuelta no es nula
        assertNotNull(nuevaMuestra);

        // 2. La muestra fue agregada a la lista interna del manejador
        assertTrue(manejadorMuestras.getListaDeMuestras().contains(nuevaMuestra));
        assertEquals(1, manejadorMuestras.getListaDeMuestras().size());

        // Aunque Muestra es un mock en el setup, `crearYRegistrarMuestra` crea una instancia real de Muestra.
        // Podríamos verificar los atributos de `nuevaMuestra` si tuviéramos acceso a ellos (requeriría que Muestra tuviera getters para estos).
        // Sin embargo, el principal objetivo aquí es verificar que la muestra se crea y se agrega a la lista.
    }
    
    @Test
    void testGetListaDeMuestrasDevuelveLaListaCorrecta() {
        // Setup: Agregamos algunas muestras
        manejadorMuestras.agregarUnaMuestra(muestraMock1);
        manejadorMuestras.agregarUnaMuestra(muestraMock2);

        // Exercise
        List<Muestra> listaObtenida = manejadorMuestras.getListaDeMuestras();

        // Verify
        assertEquals(2, listaObtenida.size());
        assertTrue(listaObtenida.contains(muestraMock1));
        assertTrue(listaObtenida.contains(muestraMock2));
        // Asegúrate de que es la misma instancia de lista, no una copia
        assertSame(manejadorMuestras.getListaDeMuestras(), listaObtenida);
    }
}