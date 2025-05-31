package vinchuca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Muestra {

	private String especieDeVinchuca;
	private String foto;
	private String identificacion;
	private Date fechaMuestra;
	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private Estado estado;
	private Persona autor;
	private Filtro filtro;
	
	
	
	public Muestra(String especieDeVinchuca, String foto, String identificacion, Date fechaMuestra, Ubicacion ubicacion,
			List<Opinion> opiniones, Estado estado, Persona autor, Filtro filtro) {
		super();
		this.especieDeVinchuca = especieDeVinchuca;
		this.foto = foto;
		this.identificacion = identificacion;
		this.fechaMuestra = fechaMuestra;
		this.ubicacion = ubicacion;
		this.opiniones = opiniones;
		this.estado = estado;
		this.autor = autor;
		this.filtro = filtro;
	}
	
	public Muestra(String especieDeVinchuca, String foto, String identificacion, Date fechaMuestra, Ubicacion ubicacion, Estado estado, Persona autor, Filtro filtro) {
		super();
		this.especieDeVinchuca = especieDeVinchuca;
		this.foto = foto;
		this.identificacion = identificacion;
		this.fechaMuestra = fechaMuestra;
		this.ubicacion = ubicacion;
		this.opiniones = new ArrayList<>();
		this.estado = estado;
		this.autor = autor;
		this.filtro = filtro;
	}

	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}


	public List<Opinion> getOpiniones() {
		return opiniones;
	}


	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}


	public Persona getAutor() {
		return autor;
	}


	public void setAutor(Persona autor) {
		this.autor = autor;
	}


	public Filtro getFiltro() {
		return filtro;
	}


	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	
	public String getEspecie() {
		return especieDeVinchuca;
	}


	public void setEspecie(String especie) {
		this.especieDeVinchuca = especie;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public Date getFechaCreacion() {
		return fechaMuestra;

	}


	public void setFechaMuestra(Date fechaMuestra) {
		this.fechaMuestra = fechaMuestra;
	}


	public Date fechaUltimaOpinion() {
		 Opinion ultimoElemento = opiniones.get(opiniones.size() - 1);
		 return ultimoElemento.fechaOpinion();
		 
		
	}
	
	
	
}
