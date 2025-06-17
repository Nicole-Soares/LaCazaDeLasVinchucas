package vinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Persona {


	List<Opinion> opinionesEmitidas;
	List<Muestra> muestrasEmitidas;
	Categoria categoria;
	
	
	public Persona(List<Opinion> opinionesEmitidas,
			List<Muestra> muestrasEmitidas) {
		super();
		this.opinionesEmitidas = opinionesEmitidas;
		this.muestrasEmitidas = muestrasEmitidas;
		this.categoria = new Basico();
	}
	
	public Persona() {
		super();
		this.opinionesEmitidas = new ArrayList<>();
		this.muestrasEmitidas = new ArrayList<>();
		this.categoria = new Basico();
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
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void cambiarCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void opinarSobre(Muestra muestra, Opinion opinion) {
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
		LocalDate fechaLimite = LocalDate.now().minusDays(30);
		
		long cantidadDeOpinionesValidas = this.opinionesEmitidas.stream()
											.filter(o -> !o.getFechaDeOpinion().isBefore(fechaLimite))
											.count();
		
		long cantidadDeMuestrasEnviadasValidas = this.muestrasEmitidas.stream()
													.filter(m -> !m.getFechaCreacion().isBefore(fechaLimite))
													.count();
		
		if (cantidadDeOpinionesValidas > 20 && cantidadDeMuestrasEnviadasValidas > 10) {
			this.cambiarCategoria(new Experto());
		}
	}
	
	
	
}
