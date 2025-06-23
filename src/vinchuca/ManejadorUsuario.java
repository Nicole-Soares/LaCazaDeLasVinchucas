package vinchuca;

import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuario {

	private List<Usuario> listaDeUsuarios;

	public ManejadorUsuario() {
		super();
		this.listaDeUsuarios = new ArrayList<Usuario>();
	}

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void agregarUsuario(Usuario usuario) {
		this.listaDeUsuarios.add(usuario);
	}

	public Usuario crearYRegistrarUsuario() {
		Usuario usuarioNuevo= new Usuario();
		this.agregarUsuario(usuarioNuevo);
		return usuarioNuevo;
	}

	public UsuarioProfesional crearYRegistrarUsuariosProfesionales() {
		UsuarioProfesional usuarioProfesionalNuevo= new UsuarioProfesional();
		this.agregarUsuario(usuarioProfesionalNuevo);
		return usuarioProfesionalNuevo;
	}
	
	
}
