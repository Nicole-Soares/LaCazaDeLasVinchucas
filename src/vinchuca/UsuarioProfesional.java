package vinchuca;

import java.util.List;

public class UsuarioProfesional extends Usuario{

	
	public UsuarioProfesional() {
		super();
		this.categoria= new CategoriaExperto();
		
	}
	
	
	@Override
	public void cambiarCategoria(CategoriaUsuario categoria) {
		System.out.println("No se puede cambiar el estado de un profesional");
	}
	
	@Override
	public void verificarCategoria() {
		
	}

}
