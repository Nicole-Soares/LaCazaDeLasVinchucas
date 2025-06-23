package vinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {


	List<Opinion> opinionesEmitidas;
	List<Muestra> muestrasEmitidas;
	CategoriaUsuario categoria;
	
	
	public Usuario() {
		super();
		this.opinionesEmitidas = new ArrayList<>();
		this.muestrasEmitidas = new ArrayList<>();
		this.categoria = new CategoriaBasico();
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
		//no puede opinar mas de 1 vez de una muestra
		if (muestra.yaOpino(this)) { 
            System.out.println("Error: Ya ha opinado sobre esta muestra.");
            return;
		}
		//no puede opinar sobre su muestra
		 if (muestra.getAutor().equals(this)) { 
	            System.out.println("Error: No se puede opinar sobre la propia muestra.");
	            return;// para que salga
	        }
		muestra.cargarOpinion(opinion);
		this.opinionesEmitidas.add(opinion);
		this.verificarCategoria();
	}
	
	public void enviarMuestra(Muestra muestra) {
		this.muestrasEmitidas.add(muestra);
		this.verificarCategoria();
	}
	
	public boolean esExperto() {
		return this.categoria.esExperto();
	}
	
	public boolean esBasico() {
		return this.categoria.esBasico();
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
	
	
	
}
