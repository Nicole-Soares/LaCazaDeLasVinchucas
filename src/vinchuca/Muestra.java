package vinchuca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private ManejadorMuestra manejadorMuestra;
	
	
	
	public Muestra(String especieDeVinchuca, String foto, String identificacion, Date fechaMuestra, Ubicacion ubicacion,
			List<Opinion> opiniones, Estado estado, Persona autor, Filtro filtro, ManejadorMuestra manejadorMuestra) {
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
		this.manejadorMuestra = manejadorMuestra;
	}
	
	public Muestra(String especieDeVinchuca, String foto, String identificacion, Date fechaMuestra, Ubicacion ubicacion, Estado estado, Persona autor, Filtro filtro, ManejadorMuestra manejadorMuestra){
		super();
		this.especieDeVinchuca = especieDeVinchuca;
		this.foto = foto;
		this.identificacion = identificacion;
		this.fechaMuestra = fechaMuestra;
		this.ubicacion = ubicacion;
		this.opiniones = new ArrayList<Opinion>();
		this.estado = estado;
		this.autor = autor;
		this.filtro = filtro;
		this.manejadorMuestra = manejadorMuestra;
	}

	
	
	public String getEspecieDeVinchuca() {
		return especieDeVinchuca;
	}

	public void setEspecieDeVinchuca(String especieDeVinchuca) {
		this.especieDeVinchuca = especieDeVinchuca;
	}

	public Date getFechaMuestra() {
		return fechaMuestra;
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

	private void agregarOpinion(Opinion opinion) {
		opiniones.add(opinion);
		
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

	

	public ManejadorMuestra getManejadorMuestra() {
		return manejadorMuestra;
	}

	public void setManejadorMuestra(ManejadorMuestra manejadorMuestra) {
		this.manejadorMuestra = manejadorMuestra;
	}

	public Date fechaUltimaOpinion() {
		 Opinion ultimoElemento = opiniones.get(opiniones.size() - 1);
		 return ultimoElemento.getFechaDeOpinion();
		 
		
	}
	
	public void cargarOpinion(Opinion opinion) {
		// tengo que ver si la opinion recibida es de una persona que pueda opinar segun el estado actual de su muestra
		estado.cargarOpinion(this,  opinion);
	}
	
	public Opinion resultadoActual() {
		
		
		//recorrer la lista e ir creando un map
		// tiene que tener en cuenta el estado
		
		return estado.resultadoActual(this);
		/*Map<Opinion, Integer> opinionesEnMap = new HashMap<>();
		
		for(Opinion opinion: opiniones) {
			if(this.seEncuentraEnMap(opinion, opinionesEnMap)) {
				this.sumarUnoAlMapDeLaOpinion(opinion, opinionesEnMap); // creo que no es necesario hacer =, se actualiza solo por el objeto
			}
			else {
				 this.meterLaOpinionEnElMap(opinion, opinionesEnMap);
			}
		}
		
		return this.opinionMasVecesRepetida(opinionesEnMap);
		*/
		
	}
	

	public Opinion resultadoFinalEnEstadoBasico() {
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
	
	

	public Opinion resultadoFinalEnEstadoExperto() {
		// recorro la lista y retorno la primera opinion de un experto, si estoy en el estado experto es porque no comentaron dos expertos lo mismo, por ende retorno el primero que encuentre
		
		
			List<Opinion> opinionesARecorrer = opiniones;
			
			while(!opinionesARecorrer.isEmpty() && !opinionesARecorrer.getFirst().esOpinionDeExperto()) {
				opinionesARecorrer.remove(0);
			}
			return opinionesARecorrer.isEmpty() ? null : opinionesARecorrer.getFirst();
		
		
	}
	
	public Opinion resultadoFinalEnEstadoVerificado() {
		Map<Opinion, Long> conteoOpiniones = opiniones.stream()
		        .filter(Opinion::esOpinionDeExperto) // Filtramos solo opiniones de expertos
		        .collect(Collectors.groupingBy(op -> op, Collectors.counting())); // Contamos cuántas veces aparece cada opinión

		    return conteoOpiniones.entrySet().stream()
		        .filter(entry -> entry.getValue() >= 2) // Buscamos opiniones con al menos dos expertos
		        .map(Map.Entry::getKey) // Extraemos la opinión
		        .findFirst()
		        .orElse(null);
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


	public void cargarOpinionEnEstadoBasico(Opinion opinion) {
		//EstadoExperto estadoExperto = new EstadoExperto();
		this.agregarOpinion(opinion);
		if(opinion.esOpinionDeExperto()) {
			EstadoExperto estadoExperto = new EstadoExperto();
			this.cambiarEstado(estadoExperto);
		}
		
	}
	
	public void cargarOpinionEnEstadoExperto(Opinion opinion) { 
		
		//de otra manera le tengo que delegar a  opinion, opinion a persona, persona a su estado, el estado retornar algo
		if(opinion.esOpinionDeExperto()) {
			this.agregarOpinion(opinion);
		}
		
		if(this.mismaOpinionYaPublicada(opinion)) {
			EstadoVerificado estadoVerificado = new EstadoVerificado();
			this.cambiarEstado(estadoVerificado);
			manejadorMuestra.notificar(this);
		}
		
	}
	
	public void cargarOpinionEnEstadoVerificado(Opinion opinion) {
		System.out.println("No se aceptan mas opiniones");
		
	}

	private void cambiarEstado(Estado estado) {
		this.setEstado(estado);
		
	}

	private boolean mismaOpinionYaPublicada(Opinion opinion) {
		return opiniones.stream()
	            .anyMatch(o -> o.esOpinionDeExperto() && o.getTipo().equals(opinion.getTipo()));
	}

	
	public void suscribir(ZonaCobertura zona) {
		manejadorMuestra.suscribir(zona, this);
	}
	
	public void desuscribir(ZonaCobertura zona) {
		manejadorMuestra.desuscribir(zona);
	}
	

	

	

	

	
}
