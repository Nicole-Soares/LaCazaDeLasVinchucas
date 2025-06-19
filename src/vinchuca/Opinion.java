package vinchuca;

import java.time.LocalDate;


public class Opinion {
	    private TipoDeOpinion tipo;
	    private LocalDate fechaDeOpinion;
	    private Persona persona;
	    private Categoria categoria;

	    public Opinion(TipoDeOpinion tipo, LocalDate fecha, Persona persona) {
	    	if (tipo == null || fecha == null || persona == null) {
	            throw new IllegalArgumentException("Los valores no pueden ser nulos");
	        }
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

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		// la catgoria de la persona cambia, la de la opinion no
		public boolean esOpinionDeExperto() {
			return categoria.esExperto();
		}

	   
	}

	
	
	
	
	

