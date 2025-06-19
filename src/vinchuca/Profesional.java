package vinchuca;

import java.util.List;

public class Profesional extends Persona{

	public Profesional() {
		super();
		this.categoria= new Experto();
		
	}

	public Profesional(List<Opinion> opinionesEmitidas, List<Muestra> muestraEmitida) {
		super(opinionesEmitidas,muestraEmitida );
		this.categoria= new Experto();
		
	}
	
	
	@Override
	public void cambiarCategoria(Categoria categoria) {
		System.out.println("No se puede cambiar el estado de un profesional");
	}
	
	

}
