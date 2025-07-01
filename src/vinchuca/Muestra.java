package vinchuca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Muestra {

	private String especieDeVinchuca;
	private String foto;
	private LocalDate fechaMuestra;
	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private Estado estado;
	private Usuario autor;
	private ObservadorMuestra manejadorMuestra;
	
	

	public Muestra(String especieDeVinchuca, String foto, LocalDate fechaMuestra, Ubicacion ubicacion, Estado estado, Usuario autor, ObservadorMuestra manejadorMuestra ) {
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


	
	/*public Estado getEstado() {
		return estado;
	}

	*/

	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public List<Opinion> getOpiniones() {
		return opiniones;
	}


	private void agregarOpinion(Opinion opinion) {
		opiniones.add(opinion);
		
	}

	public Usuario getAutor() {
		return autor;
	}


	public void setAutor(Usuario autor) {
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

	

	public ObservadorMuestra getManejadorMuestra() {
		return manejadorMuestra;
	}

	public void setManejadorMuestra(ObservadorMuestra manejadorMuestra) {
		this.manejadorMuestra = manejadorMuestra;
	}

	public LocalDate fechaUltimaOpinion() {
		 Opinion ultimoElemento = opiniones.get(opiniones.size() - 1);
		 return ultimoElemento.getFechaDeOpinion();
		 
		
	}
	
	
	/////////////////////////////////////////////////////////RESULTADO FINAL///////////////////////////////////////////////////////////////////////////////
	public TipoDeOpinion resultadoActual() {
		
		// tiene que tener en cuenta el estado
		return estado.resultadoActual(this);
	}
	

	public TipoDeOpinion resultadoFinalEnEstadoBasico() {
		Map<TipoDeOpinion, Integer> conteo = new HashMap<>();
	
			for(Opinion opinion: opiniones) {
				if(this.seEncuentraEnMap(opinion, conteo)) {
					this.sumarUnoAlMapDeLaOpinion(opinion, conteo); 
				}
				else {
					this.meterLaOpinionEnElMap(opinion, conteo);
				}
			}
	
			return this.opinionMasVecesRepetida(conteo);
	
	}
	
	

	public TipoDeOpinion resultadoFinalEnEstadoExperto() {
	
        List<Opinion> opinionesDeExpertos = this.opiniones.stream()
                .filter(opinion -> opinion.fueEmitidaPorExperto())
                .collect(Collectors.toList());

        // Si hay mas de 1 opinion de experto, es un empate.
        if(opinionesDeExpertos.size() == 1) {
        	return opinionesDeExpertos.getFirst().getTipo();
        }
        else {
            return TipoDeOpinion.EMPATE;
        }
		
	}
	
	
	
	public TipoDeOpinion resultadoFinalEnEstadoVerificado() {
		 // Contamos cuántas veces aparece cada tipo de opinión, solo de expertos.
        Map<TipoDeOpinion, Long> conteoPorTipo = opiniones.stream()
            .filter(opinion -> opinion.fueEmitidaPorExperto()) 
            .collect(Collectors.groupingBy(Opinion::getTipo, Collectors.counting())); // Contamos por tipo

        //  Buscamos el tipo con al menos dos opiniones de expertos
        //  la muestra ya está en estado Verificado, por ende existe el tipo
        
        TipoDeOpinion tipoDefinitivo = conteoPorTipo.entrySet().stream()
            .filter(entry -> entry.getValue() == 2) // Filtramos las entradas donde la cantidad es 2, ya que en verificado solo va a haber como min y max un tipo 2 veces repetido
            .map(Map.Entry::getKey) // Obtenemos solo el TipoDeOpinion de esas entradas
            .findFirst() // Tomamos el primer TipoDeOpinion que cumpla la condición
           
            // no se encuentra un tipo con >= 2 votos, retornamos NINGUNA (no deberia pasar), no deberia haber EMPATE tampoco
            .orElse(TipoDeOpinion.NINGUNA); 

        return tipoDefinitivo;
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
	

	private TipoDeOpinion opinionMasVecesRepetida(Map<TipoDeOpinion, Integer> tipos) {
	 
	
		// si esta vacio es ninguna
        if (tipos.isEmpty()) {
            return TipoDeOpinion.NINGUNA;
        }

        TipoDeOpinion tipoMasFrecuente = null;
        int cantidadDeVecesRepetidoDelTipoMasFrecuente = 0; 

        
        // Recorremos cada entrada (clave-valor) del mapa.
        for (Entry<TipoDeOpinion, Integer> tipo : tipos.entrySet()) {
            TipoDeOpinion tipoActual = tipo.getKey();
            int cantidadDeVecesRepetidoDelTipoActual = tipo.getValue();

           //actualizamos
            if (cantidadDeVecesRepetidoDelTipoActual > cantidadDeVecesRepetidoDelTipoMasFrecuente) {
            	cantidadDeVecesRepetidoDelTipoMasFrecuente = cantidadDeVecesRepetidoDelTipoActual;
                tipoMasFrecuente = tipoActual;
            }
        }

        // chequeamos si hay un empate.
        // Recorremos el mapa otra vez para ver si algún otro tipo tiene la misma cantidad de veces repetidas.
        int cantidadDeTiposConMismaCantidadDeVecesRepetidos = 0;
        for (int cantidad : tipos.values()) { // Iteramos solo sobre los valores 
            if (cantidad == cantidadDeVecesRepetidoDelTipoMasFrecuente) { // comparamos las veces que aparece el tipo actual con las veces que aparece el mas frecuente sacado antes
            	cantidadDeTiposConMismaCantidadDeVecesRepetidos++;
            }
        }

        //  Devolvemos el resultado: si solo un tipo tiene la frecuencia máxima, es el ganador;
        // de lo contrario, es un empate.
        if (cantidadDeTiposConMismaCantidadDeVecesRepetidos == 1) {
            return tipoMasFrecuente; // Hay un único ganador.
        } else {
            return TipoDeOpinion.EMPATE; // Hay empate (múltiples tipos con la misma frecuencia máxima).
        }
	}


	////////////////////////////////////////////////CARGA DE OPINIONES//////////////////////////////////////////////////////////
	
	public void cargarOpinion(Opinion opinion) {
		// tengo que ver si la opinion recibida es de una persona que pueda opinar segun el estado actual de su muestra
		estado.cargarOpinion(this,  opinion);
	}
	
	
	//se acepta cualquier opinion
	public void cargarOpinionEnEstadoBasico(Opinion opinion) {
		this.agregarOpinion(opinion);
		opinion.evaluarParaCargarOpinionMuestraBasica(this); // delegamos en opinion para saber si es una opinion de experto, si es de experto hay que cambiar estado
		
		
	}
	
	
		//esto viene de la opinon si es que es experto, si es basico no hace nada
		public void procesarCargaEnMuestraBasicaConOpinionExperto() {
			EstadoExperto estadoExperto = new EstadoExperto();
			this.cambiarEstado(estadoExperto);
			
		}
		//agregar uml
		public void procesarCargaEnMuestraBasicaConOpinionBasica() {
			//no hace nada, porque el cambio de estado sucede si una opinion es de experto
			
		}
	
	//solo acepta opiniones de expertos, si coinciden al menos dos opiniones de experto, pasa a verificado
	public void cargarOpinionEnEstadoExperto(Opinion opinion) { 
		
		opinion.evaluarParaCargarOpinionMuestraExperto(this);
		
	}
	
	//si estamos aca es porque la opinion nueva es de un experto
	public void procesarCargaEnMuestraExpertaConOpinionExperto(Opinion opinion) {
		
		this.mismaOpinionYaPublicada(opinion); // chequea para ver el cambio de estado
		this.agregarOpinion(opinion);// siempre la carga, ya que si estamos aca es una opinion de experto
		
	}
	
	public void procesarCargaEnMuestraExpertaConOpinionBasica(Opinion opinion) {
		// no hace nada porque las muestras en estado experto solo aceptan opiniones de expertos.
		
	}
	
	
	private void mismaOpinionYaPublicada(Opinion opinion) {
		
		for(Opinion opinionActual: opiniones) {
			opinionActual.evaluarOpinion(this, opinion); // para ver si la opinion actual es de experto
		}
	
	}
	
	public void chequearConOpinionExperta(Opinion opinionDeLaListaDeMuestra, Opinion opinionCargadaNueva) {
		
		if(opinionDeLaListaDeMuestra.getTipo() == opinionCargadaNueva.getTipo()) { // ya se que las dos son opiniones de experto, solo me falta chequear el tipo, si coincide, cambio estado a verificado
			EstadoVerificado estadoVerificado = new EstadoVerificado();
			this.cambiarEstado(estadoVerificado);
			manejadorMuestra.notificarMuestraVerificada(this);
		}
		
	}




	public void chequearConOpinionBasica(Opinion opinionDeLaListaDeMuestra, Opinion opinionCargadaNueva) {
		// no hace nada porque es una opinion basica y muestra esta en estado experto
		
	}

	public void cargarOpinionEnEstadoVerificado(Opinion opinion) {
		System.out.println("No se aceptan mas opiniones");
		
		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void cambiarEstado(Estado estado) {
		this.setEstado(estado);
		
	}

	
	
	public void suscribir(ZonaCobertura zona) {
		manejadorMuestra.suscribir(zona, this);
	}
	
	public void desuscribir(ZonaCobertura zona) {
		manejadorMuestra.desuscribir(zona);
	}



	public boolean yaOpino(Usuario usuario) {
		  // Itera sobre las opiniones y verifica si alguna fue emitida por el usuario dado
		  return this.opiniones.stream()
		             .anyMatch(o -> o.getPersona().equals(usuario)); 
		   
	}



	public boolean cumpleVerificacionBasica() {
		
		return estado.cumpleVerificacionBasica();
	}



	public boolean cumpleVerificacionExperta() {
		
		return estado.cumpleVerificacionExperta();
	}



	public boolean cumpleVerificacion() {
		
		return estado.cumpleVerificacion();
	}



	



	



	
}
