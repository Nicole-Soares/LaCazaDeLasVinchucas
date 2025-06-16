package vinchuca;

public class Organizacion implements Observador{
	private int cantTrabajadores;
	private Ubicacion ubicacion;
	private TipoOrg tipo;
	private FuncionalidadExterna funcionalidadNuevaMuestra;
	private FuncionalidadExterna funcionalidadValidacionMuestra;
	
	public Organizacion(int cantTrabajadores, Ubicacion ubicacion, TipoOrg tipo,
				FuncionalidadExterna funcionalidadNuevaMuestra, FuncionalidadExterna funcionalidadValidacionMuestra) {
		this.setCantTrabajadores(cantTrabajadores);
		this.setUbicacion(ubicacion);
		this.setTipo(tipo);
		this.funcionalidadNuevaMuestra = funcionalidadNuevaMuestra;
		this.funcionalidadValidacionMuestra = funcionalidadValidacionMuestra;
	}
	@Override
	public void serNotificadoNuevaMuestra(ZonaCobertura zona, Muestra muestra) {
		this.funcionalidadNuevaMuestra.nuevoEvento(this, zona, muestra);
	}
	@Override
	public void serNotificadoMuestraVerificada(ZonaCobertura zona, Muestra muestra) {
		this.funcionalidadValidacionMuestra.nuevoEvento(this, zona, muestra);
	}
	public void cambiarFuncionalidadNuevaMuestra(FuncionalidadExterna nuevaFuncionalidad) {
		this.funcionalidadNuevaMuestra = nuevaFuncionalidad;
	}
	public void cambiarFuncionalidadValidacionMuestra(FuncionalidadExterna nuevaFuncionalidad) {
		this.funcionalidadValidacionMuestra = nuevaFuncionalidad;
	}
	public int getCantTrabajadores() {
		return cantTrabajadores;
	}
	public void setCantTrabajadores(int cantTrabajadores) {
		this.cantTrabajadores = cantTrabajadores;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public TipoOrg getTipo() {
		return tipo;
	}
	public void setTipo(TipoOrg tipo) {
		this.tipo = tipo;
	}
}
