/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Empresa;
import ec.edu.ups.modelo.Vehiculo;
import java.util.Scanner;

/**
 *
 * @author surfa
 */
public class VistaGeneral {
    private VistaCliente vistaCliente;
    private VistaEmpresa vistaEmpresa;
    private VistaVehiculo vistaVehiculo;
    private VistaServicio vistaServicio;
    private Scanner teclado;

    public VistaGeneral() {
        vistaEmpresa = new VistaEmpresa();
        vistaCliente = new VistaCliente(vistaEmpresa.getControladorEmpresa()); 
        vistaVehiculo = new VistaVehiculo(vistaCliente.getControladorCliente());
        vistaServicio = new VistaServicio(vistaVehiculo.getControladorVehiculo());
        teclado = new Scanner(System.in);
    }

    public void menu() {
        int opcion = 0;
        do {
            System.out.println("Seleccione una opción");
            System.out.println("1. Empresa");
            System.out.println("2. Cliente");
            System.out.println("3. Vehiculo");
            System.out.println("4. Servicio");
            System.out.println("5. Salir");
            opcion = teclado.nextInt();
            switch(opcion){
                case 1: vistaEmpresa.menu(); break;
                case 2: this.cliente(); break;
                case 3: this.vehiculo(); break;
                case 4: this.servicio();break;
            }
        } while (opcion < 5);
    }
    public void cliente() {
        System.out.println("Seleccione una empresa para gestionar los clientes");
        Empresa empresa = vistaEmpresa.buscar();
        if(empresa != null){ 
            vistaEmpresa.asignarSelecionado(empresa);
            vistaCliente.menu();
        }else {
            System.out.println("No exite la empresa");
            this.cliente();
        }
    }
    public void vehiculo(){
        System.out.println("Seleccione una propietario para gestionar los vechiculos");
        Cliente cliente = vistaCliente.buscar();
        if(cliente != null){ 
            vistaCliente.asignarSeleccionado(cliente);
            vistaVehiculo.menu();
        }else {
            System.out.println("No exite la empresa");
            this.vehiculo();
        }
    }
    public void servicio(){
        System.out.println("Selecione un vehiculo");
        Vehiculo vehiculo = vistaVehiculo.buscar();
        if(vehiculo != null){
            vistaVehiculo.asignarSeleccionado(vehiculo);
            vistaServicio.menu();
        }else {
            System.out.println("No existe vehiculo");
            this.menu();
        }
    }
}
