package vinchuca;

import java.time.LocalDate;


public class Opinion {
	    private TipoDeOpinion tipo;
	    private LocalDate fechaDeOpinion;
	    private Usuario persona;
	    private CategoriaUsuario categoria;

	    public Opinion(TipoDeOpinion tipo, LocalDate fecha, Usuario persona) {
	    	
	        this.tipo = tipo;
	        this.fechaDeOpinion = fecha;
	        this.persona = persona;
	        this.categoria = persona.getCategoria();
	    }

		public TipoDeOpinion getTipo() {
			return tipo;
		}


		public LocalDate getFechaDeOpinion() {

			return fechaDeOpinion;
		}

	
		public Usuario getPersona() {
			return persona;
		}


		/*public CategoriaUsuario getCategoria() {
			return categoria;
		}

	*/

		// la catgoria de la persona cambia, la de la opinion no
		public boolean fueEmitidaPorExperto() {
			return categoria.esExperto();
		}
		
		//
		
		public void evaluarParaCargarOpinionMuestraBasica(Muestra muestra) {
			categoria.evaluarParaCargarOpinionMuestraBasica(muestra, this);
			
		}
		
		//el double dispatch con categoria, lo llama categoria
		public void procesarCargaEnMuestraBasicaConOpinionExperto(Muestra muestra) {
			muestra.procesarCargaEnMuestraBasicaConOpinionExperto();
			
		}

		public void procesarCargaEnMuestraBasicaConOpinionBasica(Muestra muestra) {
			muestra.procesarCargaEnMuestraBasicaConOpinionBasica();
			
		}
		
		//
		public void evaluarParaCargarOpinionMuestraExperto(Muestra muestra) {
			categoria.evaluarParaCargarOpinionMuestraExperto(muestra, this);
			
		}

	   
	
		public void procesarCargaEnMuestraExpertaConOpinionExperto(Muestra muestra) {
			muestra.procesarCargaEnMuestraExpertaConOpinionExperto(this);
			
		}
		
		public void procesarCargaEnMuestraExpertaConOpinionBasica(Muestra muestra) {
			muestra.procesarCargaEnMuestraExpertaConOpinionBasica(this);
			
		}

		//
		public void evaluarOpinion(Muestra muestra, Opinion opinionAChequearTipo) {
			categoria.evaluarOpinion(muestra, this, opinionAChequearTipo);
			
		}

		public void evaluadaEnExperta(Muestra muestra, Opinion opinionAChequearTipo) {
			muestra.chequearConOpinionExperta(this, opinionAChequearTipo);
			
		}

		public void evaluadaEnBasica(Muestra muestra, Opinion opinionAChequearTipo) {
			muestra.chequearConOpinionBasica(this, opinionAChequearTipo);
			
		}

		/*public Opinion evaluarOpinionParaResultadoExperto(Muestra muestra) {
			return categoria.evaluarOpinionParaResultadoExperto(this, muestra);
			
		}

		public Opinion evaluadaEnExperta(Muestra muestra) {
			return muestra.opinionExpertaParaResultadoExperto(this);
			
		}

		public Opinion evaluadaEnBasica(Muestra muestra) {
			
			return muestra.opinionBasicaParaResultadoExperto(this);
		}

		*/

		

		

		
}

	
	
	
	
	

