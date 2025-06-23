package vinchuca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZonaCobertura implements Sujeto{
    private String nombre;
    private Ubicacion epicentro;
    private double radio;
    private List<Muestra> muestras;
    private Set<Observador> interesados;
    
    public ZonaCobertura(String nombre, Ubicacion epicentro, double radio) {
        this.nombre = nombre;
        this.epicentro = epicentro;
        this.radio = radio;
        this.muestras = new ArrayList<>();
        this.interesados = new HashSet<>();
    }
    public List<ZonaCobertura> zonasQueSolapan(ManejadorZonasCobertura manejador){
        return manejador.zonasQueSolapan(this);
    }
    public void addMuestra(Muestra muestra) {
            this.muestras.add(muestra);
    }
    public boolean contiene(Muestra muestra) {
        return this.epicentro.distanciaEntre(muestra.getUbicacion()) <= this.radio;
    }
    public ZonaCobertura removeMuestra(Muestra muestra) {
        this.muestras.remove(muestra);
        return this;
    }
    private double distanciaA(ZonaCobertura otraZona) {
        return this.epicentro.distanciaEntre(otraZona.getEpicentro());
    }
    public boolean seSolapaCon(ZonaCobertura otraZona) {
        //Si la distancia entre los epicentros es menor o igual que la suma de sus radios, 
        //entonces las zonas de cobertura se solapan.
        return distanciaA(otraZona) <= (getRadio() + otraZona.getRadio());
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }
    public Ubicacion getEpicentro() {
        return this.epicentro;
    }
    public double getRadio() {
        return this.radio;
    }
    public List<Muestra> getMuestras(){
        return this.muestras;
    }
    public Set<Observador> getInteresados(){
        return this.interesados;
    }
    @Override
    public void agregarInteresado(Observador observador) {
        this.interesados.add(observador);
    }
    @Override
    public void sacarInteresado(Observador observador) {
        this.interesados.remove(observador);
    }
    @Override
    public void avisarNuevaMuestra(Muestra muestra) {
        if(this.contiene(muestra)) {
           this.addMuestra(muestra);
           for(Observador interesado : this.interesados) {
               interesado.serNotificadoNuevaMuestra(this, muestra);
           }
        }
    }
    @Override
    public void avisarMuestraVerificada(Muestra muestra) {
        for(Observador interesado : this.interesados) {
            interesado.serNotificadoMuestraVerificada(this, muestra);
        }
    }
}