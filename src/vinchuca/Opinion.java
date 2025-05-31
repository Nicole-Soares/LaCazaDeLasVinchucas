package vinchuca;

import java.util.Date;

public enum Opinion {

    VINCHUCA(0, new Date(),new Persona(1)),
    CHINCHEFOLIADA(0, new Date(), new Persona(1)),
    FITHUFOLIADA(0, new Date(), new Persona(1)),
    NINGUNA(0, new Date(), new Persona(1)),
    IMAGENPOCOCLARA(0, new Date(), new Persona(1));
	
	int likes;
	Date fechaDeOpinion;
	Persona persona;

	private Opinion(int likes, Date fecha, Persona persona) {
		this.likes = likes;
		this.fechaDeOpinion = fecha;
		this.persona = persona;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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
	
	
	
	
	
}
