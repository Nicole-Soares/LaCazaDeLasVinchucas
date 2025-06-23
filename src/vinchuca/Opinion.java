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


		public CategoriaUsuario getCategoria() {
			return categoria;
		}

	

		// la catgoria de la persona cambia, la de la opinion no
		public boolean esOpinionDeExperto() {
			return categoria.esExperto();
		}

	   
	}

	
	
	
	
	

