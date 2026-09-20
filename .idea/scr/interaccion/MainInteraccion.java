package interaccion;

import gestion.GestorParqueadero;
import java.util.Scanner;

public class MainInteraccion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorParqueadero gestor = new GestorParqueadero();
        boolean ejecutando = true;

        System.out.println("=== SISTEMA DE PARQUEADERO DE MOTOS ===");

        while (ejecutando) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar ingreso de moto");
            System.out.println("2. Registrar salida de moto");
            System.out.println("3. Ver reporte del día");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Ingrese Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingrese ID del Dueño: ");
                    String id = scanner.nextLine();
                    gestor.registrarIngresoMoto(placa, marca, id);
                    break;
                case 2:
                    System.out.print("Ingrese Placa de la moto a retirar: ");
                    String placaSalida = scanner.nextLine();
                    System.out.println("Seleccione Medio de Pago (1. EFECTIVO, 2. NEQUI): ");
                    int optPago = scanner.nextInt();
                    MedioPago medio = (optPago == 2) ? MedioPago.NEQUI : MedioPago.EFECTIVO;
                    gestor.registrarSalidaMoto(placaSalida, medio);
                    break;
                case 3:
                    gestor.generarReporteDia();
                    break;
                case 4:
                    ejecutando = false;
                    System.out.println("Cerrando sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
