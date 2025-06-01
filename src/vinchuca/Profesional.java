package vinchuca;

import java.util.List;

public class Profesional extends Persona{

	public Profesional(int cantidadDeEnvios, int cantidadDeRevisiones) {
		super(cantidadDeEnvios, cantidadDeRevisiones);
		this.categoria= new Experto();
		
	}

	public Profesional(int cantidadDeEnvios, int cantidadDeRevisiones, List<Opinion> opinionesEmitidas, List<Muestra> muestraEmitida) {
		super(cantidadDeEnvios, cantidadDeRevisiones, opinionesEmitidas,muestraEmitida );
		this.categoria= new Experto();
		
	}
	
	@Override
	public void cambiarCategoria(Categoria categoria) {
		System.out.println("No se puede cambiar el estado de un profesional");
	}
	
	
}
