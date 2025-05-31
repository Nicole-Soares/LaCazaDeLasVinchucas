package vinchuca;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Muestra {

	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private Persona autor;
	private Filtrado filtro;
	private Estado estado;
	private String especie;
	private String foto;
	private String identificacion;
	private Date fechaMuestra;
	private Date fechaUltimaOpinion;
	
	
	public Muestra(String especie, String foto, String identificacion, Date fechaMuestra, Ubicacion ubicacion, Persona autor) {
		
		//Seteamos fechaMuestra para poder testear, sino se setea solo con la fecha del dia en el que se crea la muestra
		
		this.autor = autor;
		this.ubicacion = ubicacion;
		this.foto = foto;
		this.identificacion = identificacion;
		this.especie = especie;
		this.fechaMuestra = fechaMuestra;
		
	}


	public Date getFechaCreacion() {
		
		return this.fechaMuestra;
	}


	public Date getFechaUltimaVotacion() {
		
		return this.fechaUltimaOpinion;
	}
	
	
	
}
