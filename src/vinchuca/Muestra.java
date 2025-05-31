package vinchuca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		 return ultimoElemento.getFechaDeOpinion();
		 
		
	}
	
	public void cargarOpinion(Opinion opinion, Persona persona) {
		// tengo que ver si la opinion recibida es de una persona que pueda opinar segun el estado actual de su muestra
		estado.cargarOpinion(this, persona, opinion);
	}
	
	public Opinion resultadoActual() {
		
		
		//recorrer la lista e ir creando un map
		Map<Opinion, Integer> opinionesEnMap = new HashMap<>();
		
		for(Opinion opinion: opiniones) {
			if(this.seEncuentraEnMap(opinion, opinionesEnMap)) {
				this.sumarUnoAlMapDeLaOpinion(opinion, opinionesEnMap); // creo que no es necesario hacer =, se actualiza solo por el objeto
			}
			else {
				 this.meterLaOpinionEnElMap(opinion, opinionesEnMap);
			}
		}
		
		return this.opinionMasVecesRepetida(opinionesEnMap);
		
	}
	

	

	

	private boolean seEncuentraEnMap(Opinion opinion, Map<Opinion, Integer> opinionesEnMap) {
		
		return opinionesEnMap.containsKey(opinion);
	}
	
	private void sumarUnoAlMapDeLaOpinion(Opinion opinion, Map<Opinion, Integer> opinionesEnMap) {
		 opinionesEnMap.put(opinion, opinionesEnMap.get(opinion) + 1);
		
	}
	
	private void meterLaOpinionEnElMap(Opinion opinion, Map<Opinion, Integer> opinionesEnMap) {
		opinionesEnMap.put(opinion, 1);
		
	}
	
	private Opinion opinionMasVecesRepetida(Map<Opinion, Integer> opinionesEnMap) {
		    Opinion masRepetida = null;
		    int maxFrecuencia = 0;

		    for (Map.Entry<Opinion, Integer> entry : opinionesEnMap.entrySet()) {
		        if (entry.getValue() > maxFrecuencia) {
		            maxFrecuencia = entry.getValue();
		            masRepetida = entry.getKey();
		        }
		    }

		    return masRepetida;
	}

	/**if(!opiniones.isEmpty()) {
	
	Opinion opinionConMasLikes = opiniones.getFirst();	
	/*opiniones.removeFirst();
;			for(Opinion opinion: opinionesSinElPrimerElemento) {
				
	}
	for(int i =1; i <= opiniones.size(); i++) { // empieza en 1 para evitar el primero
		Opinion opinion = opiniones.get(i);
		if(opinion.getLikes() > opinionConMasLikes.getLikes()) {
			opinionConMasLikes = opinion;
			
		}
	}
	
	return opinionConMasLikes;
}

return null;
*/

	public void cargarOpinionEnEstadoBasico(Persona persona, Opinion opinion) {
		this.agregarOpinion(opinion);
		
		
	}

	private void agregarOpinion(Opinion opinion) {
		opiniones.add(opinion);
		
	}
	
	
	
}
