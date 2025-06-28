package vinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

	String nombre;
	List<Opinion> opinionesEmitidas;
	List<Muestra> muestrasEmitidas;
	CategoriaUsuario categoria;
	
	
	public Usuario(String nombre) {
		super();
		this.nombre = nombre;
		this.opinionesEmitidas = new ArrayList<>();
		this.muestrasEmitidas = new ArrayList<>();
		this.categoria = new CategoriaBasico();
	}


	public String getNombre() {
		return nombre;
	}
	
	public List<Opinion> getOpinionesEmitidas() {
		return opinionesEmitidas;
	}

	public void agregarOpinion(Opinion opinion) {
		opinionesEmitidas.add(opinion);
	}

	public List<Muestra> getMuestrasEmitidas() {
		return muestrasEmitidas;
	}

	public void agregarMuestra(Muestra muestra) {
		muestrasEmitidas.add(muestra);
	}
	
	
	
	public CategoriaUsuario getCategoria() {
		return categoria;
	}

	public void cambiarCategoria(CategoriaUsuario categoria) {
		this.categoria = categoria;
	}

	public void opinarSobre(Muestra muestra, Opinion opinion) {
        // No puede opinar más de 1 vez de una muestra
        if (muestra.yaOpino(this)) {
        	throw new IllegalStateException("Error: Ya ha opinado sobre esta muestra.");
        }
        
        // No puede opinar sobre su propia muestra
        if (muestra.getAutor().equals(this)) {
        	throw new IllegalArgumentException("Error: No se puede opinar sobre la propia muestra.");
        }
        
        // Si las validaciones pasan, se procede con la carga de la opinión
        muestra.cargarOpinion(opinion);
        this.opinionesEmitidas.add(opinion);
        this.verificarCategoria(); // Por ejemplo, si opinar cambia la categoría del usuario
    }
	
	public void enviarMuestra(Muestra muestra) {
		this.muestrasEmitidas.add(muestra);
		this.verificarCategoria();
	}
	
	public boolean esExperto() {
		return this.categoria.esExperto();
	}
	

	public void verificarCategoria() {
		// Ultimos 30 dias
		LocalDate fechaLimite = LocalDate.now().minusDays(30);
		
		
		long cantidadDeOpiniones = this.opinionesEmitidas.stream()
											.filter(o -> o.getFechaDeOpinion().isAfter(fechaLimite))
											.count();
		
		long cantidadDeMuestrasEnviadas = this.muestrasEmitidas.stream()
													.filter(m -> m.getFechaCreacion().isAfter(fechaLimite))
													.count();
		
		this.categoria.verificarCategoria(this,cantidadDeOpiniones, cantidadDeMuestrasEnviadas);
	}



	public void verificarCategoriaSiendoBasico(long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas) {
		if (cantidadDeOpiniones > 20 && cantidadDeMuestrasEnviadas > 10) {
			this.cambiarCategoria(new CategoriaExperto());
		}
		
	}



	public void verificarCategoriaSiendoExperto(long cantidadDeOpiniones, long cantidadDeMuestrasEnviadas) {
		if (cantidadDeOpiniones < 20 || cantidadDeMuestrasEnviadas < 10) {
			this.cambiarCategoria(new CategoriaBasico());
		}
		
		
	}
	
	
	
}
