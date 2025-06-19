package vinchuca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Muestra {

	private String especieDeVinchuca;
	private String foto;
	private LocalDate fechaMuestra;
	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private Estado estado;
	private Persona autor;
	private Filtro filtro;
	private ManagerMuestraVerificada manejadorMuestra;
	
	

	public Muestra(String especieDeVinchuca, String foto, LocalDate fechaMuestra, Ubicacion ubicacion, Estado estado, Persona autor, ManagerMuestraVerificada manejadorMuestra ) {
		super();
		this.especieDeVinchuca = especieDeVinchuca;
		this.foto = foto;
		this.fechaMuestra = fechaMuestra;
		this.ubicacion = ubicacion;
		this.opiniones = new ArrayList<Opinion>();
		this.estado = estado;
		this.autor = autor;
		this.manejadorMuestra = manejadorMuestra;
	}

	
	
	public String getEspecieDeVinchuca() {
		return especieDeVinchuca;
	}

	public void setEspecieDeVinchuca(String especieDeVinchuca) {
		this.especieDeVinchuca = especieDeVinchuca;
	}


	public Ubicacion getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}


	
	public Estado getEstado() {
		return estado;
	}

	

	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public List<Opinion> getOpiniones() {
		return opiniones;
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



	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}




	public LocalDate getFechaCreacion() {
		return fechaMuestra;

	}


	public void setFechaMuestra(LocalDate fechaMuestra) {
		this.fechaMuestra = fechaMuestra;
	}

	

	public ManagerMuestraVerificada getManejadorMuestra() {
		return manejadorMuestra;
	}

	public void setManejadorMuestra(ManagerMuestraVerificada manejadorMuestra) {
		this.manejadorMuestra = manejadorMuestra;
	}

	public LocalDate fechaUltimaOpinion() {
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
		
		
	}
	

	public Opinion resultadoFinalEnEstadoBasico() {
		Map<TipoDeOpinion, Integer> conteo = new HashMap<>();
	
			for(Opinion opinion: opiniones) {
				if(this.seEncuentraEnMap(opinion, conteo)) {
					this.sumarUnoAlMapDeLaOpinion(opinion, conteo); // creo que no es necesario hacer =, se actualiza solo por el objeto
				}
				else {
					this.meterLaOpinionEnElMap(opinion, conteo);
				}
			}
	
			return this.opinionMasVecesRepetida(conteo);
	
	}
	
	

	public Opinion resultadoFinalEnEstadoExperto() {
		// recorro la lista y retorno la primera opinion de un experto, si estoy en el estado experto es porque no comentaron dos expertos lo mismo, por ende retorno el primero que encuentre
		
		
		List<Opinion> opinionesARecorrer = opiniones;
		
		while( !opinionesARecorrer.getFirst().esOpinionDeExperto()) {
			opinionesARecorrer.remove(0);
		}
		return opinionesARecorrer.getFirst();
		
		
	}
	
	public Opinion resultadoFinalEnEstadoVerificado() {
	    // Contamos cuántas veces aparece cada tipo de opinión entre los expertos
		//solo opiniones de expertos (los tipos)
	    Map<TipoDeOpinion, Long> conteoPorTipo = opiniones.stream()
	        .filter(Opinion::esOpinionDeExperto)
	        .collect(Collectors.groupingBy(Opinion::getTipo, Collectors.counting()));

	    //Buscamos el tipo con al menos dos opiniones
	    Opinion opinionDefinitiva = conteoPorTipo.entrySet().stream()
		        .filter(entry -> entry.getValue() >= 2)
		        .map(Map.Entry::getKey)
		        .findFirst()
		        .flatMap(tipo ->
		            opiniones.stream()
		                .filter(o -> o.getTipo() == tipo && o.esOpinionDeExperto())
		                .findFirst()
		        )
		        .orElse(null);
	    
	    return opinionDefinitiva;
	}

	private boolean seEncuentraEnMap(Opinion opinion, Map<TipoDeOpinion, Integer> opinionesEnMap) {
		
		return opinionesEnMap.containsKey(opinion.getTipo());
	}
	
	private void sumarUnoAlMapDeLaOpinion(Opinion opinion, Map<TipoDeOpinion, Integer> opinionesEnMap) {
		opinionesEnMap.put(opinion.getTipo(), opinionesEnMap.get(opinion.getTipo()) + 1);

		
	}
	
	private void meterLaOpinionEnElMap(Opinion opinion, Map<TipoDeOpinion, Integer> opinionesEnMap) {
		opinionesEnMap.put(opinion.getTipo(), 1);
		
	}
	
	private TipoDeOpinion tipoMasRepetido(Map<TipoDeOpinion, Integer> opinionesEnMap) {
	    TipoDeOpinion masRepetido = null;
	    int maxFrecuencia = 0;

	    for (Map.Entry<TipoDeOpinion, Integer> entry : opinionesEnMap.entrySet()) {
	        if (entry.getValue() > maxFrecuencia) {
	            maxFrecuencia = entry.getValue();
	            masRepetido = entry.getKey();
	        }
	    }

	    return masRepetido;
	}

	private Opinion opinionMasVecesRepetida(Map<TipoDeOpinion, Integer> opinionesEnMap) {
	    TipoDeOpinion tipo = tipoMasRepetido(opinionesEnMap);
	    //recorro la lista de opiniones y voy filtrando las opiniones del tipo mas repetido, devuelvo la primera opinion de ese tipo
	    return opiniones.stream()
	        .filter(o -> o.getTipo() == tipo)
	        .findFirst()
	        .orElse(null);
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
		
		
		if(opinion.esOpinionDeExperto()) {
			//chequeo que esa opinion no este ya hecha, si esta en este estado es porque ya algun experto anterior publico una opinion
			if(this.mismaOpinionYaPublicada(opinion)) {
				EstadoVerificado estadoVerificado = new EstadoVerificado();
				this.cambiarEstado(estadoVerificado);
				manejadorMuestra.notificarMuestraVerificada(this);
			}
			this.agregarOpinion(opinion);
			
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
	            .anyMatch(o -> o.esOpinionDeExperto() && o.getTipo() == opinion.getTipo());
	}

	

	
	public void suscribir(ZonaCobertura zona) {
		manejadorMuestra.suscribir(zona, this);
	}
	
	public void desuscribir(ZonaCobertura zona) {
		manejadorMuestra.desuscribir(zona);
	}
	
	/*public void notificarMuestraNueva() {
		manejadorMuestra.notificarMuestraNueva(this);
	}*/
	
	
}
