package productos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class app_ventas {
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Venta> ventas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("MENU\n1. registrar producto\n2. Consultar venta \n3. salir");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    registrarVenta();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    public static void registrarProducto() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Ingrese el código del producto:");
        int codigo = scanner.nextInt();

        scanner.nextLine(); // Limpiar buffer del scanner

        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la existencia del producto:");
        int existencia = scanner.nextInt();

        System.out.println("Ingrese la fecha de registro del producto (dd/MM/yyyy):");
        Date fechaRegistro = null;
        try {
            fechaRegistro = dateFormat.parse(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();

        productos.add(new Producto(codigo, nombre, existencia, fechaRegistro, precio));
        System.out.println("Producto registrado exitosamente.");
    }

    public static void registrarVenta() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados. Registre al menos uno antes de realizar una venta.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Ingrese la fecha de la venta (dd/MM/yyyy):");
        Date fechaVenta = null;
        try {
            fechaVenta = dateFormat.parse(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Productos disponibles:");
        for (Producto producto : productos) {
            System.out.println("Código: " + producto.getCodigo() + ", Nombre: " + producto.nombre +
                    ", Precio: " + producto.getPrecio());
        }

        System.out.println("Ingrese el código del producto vendido:");
        int codigoProducto = scanner.nextInt();

        Producto productoVendido = null;
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigoProducto) {
                productoVendido = producto;
                break;
            }
        }

        if (productoVendido != null) {
            System.out.println("Ingrese la cantidad vendida:");
            int cantidad = scanner.nextInt();

            if (cantidad > productoVendido.existencia) {
                System.out.println("No hay suficiente existencia para completar la venta.");
                return;
            }

            double subtotal = productoVendido.getPrecio() * cantidad;

            Venta venta = new Venta(fechaVenta, codigoProducto, cantidad, subtotal);
            ventas.add(venta);

            productoVendido.existencia -= cantidad;

            System.out.println("Venta registrada exitosamente.");
            System.out.println("Subtotal: " + venta.getSubtotal());
            System.out.println("IVA (19%): " + (venta.getTotal() - venta.getSubtotal()));
            System.out.println("Total: " + venta.getTotal());
        } else {
            System.out.println("El código de producto ingresado no existe.");
        }
    }
}
