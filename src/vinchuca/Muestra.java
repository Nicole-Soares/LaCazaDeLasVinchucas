package vinchuca;
import java.util.Date;
import java.util.List;

public class Muestra {

	Ubicacion ubicacion;
	List<Opinion> opiniones;
	Persona autor;
	Filtrado filtro;
	Estado estado;
	String especie;
	String foto;
	String identificacion;
	Date fechaMuestra;
	
	
	public Muestra(String especie, String foto, String identificacion, Date fechaMuestra, Ubicacion ubicacion, Persona autor) {
		
		this.autor = autor;
		this.ubicacion = ubicacion;
		this.foto = foto;
		this.identificacion = identificacion;
		this.especie = especie;
		this.fechaMuestra = fechaMuestra;
		
	}
	
	
	
}
