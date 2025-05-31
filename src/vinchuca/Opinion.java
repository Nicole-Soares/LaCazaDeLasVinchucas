package vinchuca;

import java.util.Date;

public enum Opinion {

    VINCHUCA(0, new Date(), new Persona()), // persona empieza con desconocdio ??
    CHINCHEFOLIADA(0, new Date(), new Persona()),
    FITHUFOLIADA(0, new Date(), new Persona()),
    NINGUNA(0, new Date(),new Persona()),
    IMAGENPOCOCLARA(0, new Date(), new Persona());
	
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
