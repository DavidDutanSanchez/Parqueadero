/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author surfa
 */
public class ControladorVehiculo {
      private List<Vehiculo> listaVehiculo;
    private Vehiculo seleccionado;
    
    public ControladorVehiculo(){
        listaVehiculo = new ArrayList();
        seleccionado = null;
    }
    public long generarId(){
        if(listaVehiculo.size() > 0) { 
            return listaVehiculo.get(listaVehiculo.size() -1 ).getId() + 1; 
        }else {
            return 1; 
        }
    }
    
    public boolean crear(String placa, String marca, String modelo){
        Vehiculo vehiculo = new Vehiculo(this.generarId(), placa, marca, modelo);
        return this.listaVehiculo.add(vehiculo);
    }
    public boolean crear(String placa, String marca, String modelo, Cliente propietario){
        Vehiculo vehiculo = new Vehiculo(this.generarId(), placa, marca, modelo, propietario);
        propietario.getListaVehiculo().add(vehiculo);
        return this.listaVehiculo.add(vehiculo);
    }
    public Vehiculo buscar(String placa){
        for (Vehiculo vehiculo : listaVehiculo) {
            if(vehiculo.getPlaca().equals(placa)){
                return vehiculo;
            }
        }
        return null;
    }
    public boolean actualizar(String placa, String marca, String modelo){
        Vehiculo vehiculo = this.buscar(placa);
        if(vehiculo != null){
            int posicion = listaVehiculo.indexOf(vehiculo);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            listaVehiculo.set(posicion, vehiculo);
            return true;
        }
        return false;
    }
    public boolean eliminar(String placa){
        Vehiculo vehiculo = this.buscar(placa);
        if(vehiculo != null) {
            vehiculo.getPropietario().getListaVehiculo().remove(vehiculo);
            return listaVehiculo.remove(vehiculo);
        }
        return false;
    }

    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    public Vehiculo getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Vehiculo seleccionado) {
        this.seleccionado = seleccionado;
    }
}
