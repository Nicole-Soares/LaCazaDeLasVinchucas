package vinchuca;


public class UsuarioProfesional extends Usuario{

	
	public UsuarioProfesional(String nombre) {
		super(nombre);
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
