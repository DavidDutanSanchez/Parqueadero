/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorServicio;
import ec.edu.ups.controlador.ControladorVehiculo;
import ec.edu.ups.modelo.Servicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author surfa
 */
public class VistaServicio {
     private ControladorServicio controladorServicio;
    private ControladorVehiculo controladorVehiculo;
    private Scanner teclado;
    private SimpleDateFormat formatoFecha;
    public static String formato = "dd-MM-yyyy HH:mm";
    
    public VistaServicio(ControladorVehiculo controladorVehiculo){
        this.controladorVehiculo = controladorVehiculo;
        controladorServicio = new ControladorServicio();
        teclado = new Scanner(System.in);
        formatoFecha = new SimpleDateFormat(formato);
    }
    public void menu(){
        int opcion = 0;
        do {            
            System.out.println("Gestión del Servicio");
            System.out.println("1. Crear");
            System.out.println("2. Actualizar");
            System.out.println("3. Buscar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar");
            System.out.println("6. Salir");
            opcion = teclado.nextInt();
            switch(opcion){
                    case 1: this.crear(); break;
                    case 2: this.actualizar();break;
                    case 3: this.buscar(); break;
                    case 4: this.eliminar(); break;
                    case 5: this.listar(); break;
            }
        } while (opcion < 6);
    }
    public void crear(){
        System.out.println("Ingresar hora entrada: " + formato);
        teclado.nextLine();
        try {
            Date horaEntrada = formatoFecha.parse(teclado.nextLine());
            boolean resultado = controladorServicio.crear(horaEntrada, null, 0, controladorVehiculo.getSeleccionado());
            System.out.println("Creado " + resultado);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            this.crear();
        }
    }
    public Servicio buscar(){
        System.out.println("Ingrese hora entrada: " + formato);
        teclado.nextLine();
        try {
            Date horaEntrada = formatoFecha.parse(teclado.nextLine());
            Servicio servicio = controladorServicio.buscar(horaEntrada);
            System.out.println(servicio);
            return servicio;
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            this.menu();
        }
        return null;
    }
    public void actualizar(){
        Servicio servicio = this.buscar();
        System.out.println("Ingrese hora salida: " + formato);
        try {
            Date horaSalida = formatoFecha.parse(teclado.nextLine());
            boolean resultado = controladorServicio.actualizar(servicio.getHoraEntrada(), horaSalida);
            System.out.println("Resultado: " + resultado);
            System.out.println("Valor a pagar = " + servicio.getValorPagar());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            this.actualizar();
        }
    }
    public void eliminar(){
        Servicio servicio = this.buscar();
        if (servicio != null) {
            boolean resultado = controladorServicio.eliminar(servicio.getHoraEntrada());
            System.out.println("Elminado : " + resultado);
        }
    }
    public void listar(){
        for (Servicio dato : controladorServicio.getDatos()) 
            System.out.println(dato);
    }
}
