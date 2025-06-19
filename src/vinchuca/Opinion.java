package vinchuca;

import java.time.LocalDate;


public class Opinion {
	    private TipoDeOpinion tipo;
	    private LocalDate fechaDeOpinion;
	    private Persona persona;
	    private Categoria categoria;

	    public Opinion(TipoDeOpinion tipo, LocalDate fecha, Persona persona) {
	    	
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

	
		public Persona getPersona() {
			return persona;
		}


		public Categoria getCategoria() {
			return categoria;
		}

	

		// la catgoria de la persona cambia, la de la opinion no
		public boolean esOpinionDeExperto() {
			return categoria.esExperto();
		}

	   
	}

	
	
	
	
	

