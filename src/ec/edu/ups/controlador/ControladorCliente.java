/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Empresa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author surfa
 */
public class ControladorCliente {
     private List<Cliente> datos;
    private Cliente seleccionado;
    
    public ControladorCliente() {
        datos = new ArrayList<Cliente>();
        seleccionado = null;
    }
   
    public boolean crear(long id, String nombre, String apellido, String cedula) {
        Cliente cliente = new Cliente(id, nombre, apellido, cedula); 
        return datos.add(cliente); 
    }
    public boolean crear(long id, String nombre, String apellido, String cedula, Empresa empresa) {
        Cliente cliente = new Cliente(id, nombre, apellido, cedula, empresa); 
        empresa.getListaCliente().add(cliente);
        return datos.add(cliente); 
    }
    public Cliente buscar(String cedula){
        for (Cliente cliente : datos) { 
            if(cliente.getCedula().equals(cedula) == true){ 
                return cliente; 
            }
        }
        return null;
    }
    public boolean actualizar(String cedula, String nombre, String apellido) {
        Cliente cliente = this.buscar(cedula);
        if(cliente != null) {
            int posicion = datos.indexOf(cliente); 
            cliente.setNombre(nombre); 
            cliente.setApellido(apellido); 
            datos.set(posicion, cliente); 
            return true;
        }
        return false;
    }
    public boolean eliminar(String cedula) {
        Cliente cliente = this.buscar(cedula);
        if(cliente != null){
            cliente.getEmpresa().getListaCliente().remove(cliente);
            return datos.remove(cliente);
        }
        return false;
    }
    public void imprimir(){
        for (Cliente cliente : datos) {
            System.out.println(cliente);
        }
    }

    public List<Cliente> getDatos() {
        return datos;
    }

    public void setDatos(List<Cliente> datos) {
        this.datos = datos;
    }

    public Cliente getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cliente seleccionado) {
        this.seleccionado = seleccionado;
    }
}
