package vinchuca;

import java.util.Date;

public enum Opinion {

    VINCHUCA( new Date(), new Persona(0, 0)), // persona empieza con desconocdio ??
    CHINCHEFOLIADA( new Date(), new Persona(0, 0)),
    FITHUFOLIADA( new Date(), new Persona(0, 0)),
    NINGUNA( new Date(),new Persona(0, 0)),
    IMAGENPOCOCLARA( new Date(), new Persona(0,0));
	
	
	Date fechaDeOpinion;
	Persona persona;

	private Opinion( Date fecha, Persona persona) {
		this.fechaDeOpinion = fecha;
		this.persona = persona;
	}

	

	public Date getFechaDeOpinion() {
		return fechaDeOpinion;
	}

	public void setFechaDeOpinion(Date fechaDeOpinion) {
		this.fechaDeOpinion = fechaDeOpinion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public boolean esOpinionDeExperto() {
		return this.persona.esExperto();
	}



	
	
	
	
	
}
