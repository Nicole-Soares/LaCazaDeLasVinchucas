package vinchuca;

import java.util.ArrayList;
import java.util.List;

public class Persona {


	int cantidadDeEnvios;
	int cantidadDeRevisiones;
	List<Opinion> opinionesEmitidas;
	List<Muestra> muestrasEmitidas;
	Categoria categoria;
	
	
	public Persona(int cantidadDeEnvios, int cantidadDeRevisiones, List<Opinion> opinionesEmitidas,
			List<Muestra> muestrasEmitidas) {
		super();
		this.cantidadDeEnvios = cantidadDeEnvios;
		this.cantidadDeRevisiones = cantidadDeRevisiones;
		this.opinionesEmitidas = opinionesEmitidas;
		this.muestrasEmitidas = muestrasEmitidas;
		this.categoria = new Basico();
	}
	
	public Persona(int cantidadDeEnvios, int cantidadDeRevisiones) {
		super();
		this.cantidadDeEnvios = cantidadDeEnvios;
		this.cantidadDeRevisiones = cantidadDeRevisiones;
		this.opinionesEmitidas = new ArrayList<>();
		this.muestrasEmitidas = new ArrayList<>();
		this.categoria = new Basico();
	}

	public int getCantidadDeEnvios() {
		return cantidadDeEnvios;
	}

	public void agregarUnEnvio() {
		this.cantidadDeEnvios =+1;
	}

	public int getCantidadDeRevisiones() {
		return cantidadDeRevisiones;
	}

	public void agregarUnaRevision() {
		this.cantidadDeRevisiones =+ 1;
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

	public void opinarSobre() {
		
	}
	
	public void enviarMuestra() {
		
	}
	
	public boolean esExperto() {
		return this.categoria.esExperto();
	}
	
	public boolean esBasico() {
		return this.categoria.esBasico();
	}

}
