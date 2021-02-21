package excepciones;

/**
 * 
 * @author 
 * @version 1.0
 * 
 */

import java.time.LocalDate;
import java.util.Scanner;

public class Excepciones {

	static Scanner entrada = new Scanner(System.in);
	boolean error = false;

	/**
	 * Control numerico entero para el menu de una aplicacion
	 * 
	 * @return Control
	 */
	public static int ControlMenu() {
		int valor = 0;
		try {
			valor = Integer.parseInt(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("Por favor seleccione una opcion correcta del menu.");
			valor = ControlMenu();
		}
		return valor;
	}

	/**
	 * Control numerico de un numero entero
	 * 
	 * @return Control
	 */
	public static int ControlEntero() {
		int valor = 0;
		try {
			valor = Integer.parseInt(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("Por favor introduzca solo numeros enteros.");
			valor = ControlEntero();
		}
		return valor;
	}

	/**
	 * Control numerico de un numero decimal
	 * 
	 * @return Control
	 */
	public static float ControlFloat() {
		float valor = 0;
		try {
			valor = Integer.parseInt(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("El valor introducido es incorrecto, por favor introduzca un dato valido");
			valor = ControlFloat();
		}
		return valor;

	}

	/**
	 * Control numerico de un numero decimal
	 * 
	 * @return Control
	 */
	public static double ControlDouble() {
		double valor = 0;
		try {
			valor = Double.parseDouble(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("El valor introducido es incorrecto, por favor introduzca un dato valido");
			valor = ControlDouble();
		}
		return valor;

	}

	/**
	 * Control de los procesos de tipo boolean.
	 * 
	 * @return Control
	 */
	public static boolean ControlBoolean() {
		boolean valor = false;
		try {
			valor = Boolean.parseBoolean(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("El valor introducido es incorrecto");
			valor = ControlBoolean();
		}
		return valor;

	}

	/**
	 * Control numerico de los numeros extremadamente largos.
	 * 
	 * @return Control
	 */
	public static long ControlLong() {
		long valor = 0;
		try {
			valor = Long.parseLong(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("El valor introducido es incorrecto");
			valor = ControlLong();
		}
		return valor;

	}

	/**
	 * Metodo para controlar las cadenas String
	 * 
	 * @return Control
	 */
	public static String ControlString() {
		String valor = null;
		try {
			valor = (entrada.nextLine());
		} catch (Exception e) {
			System.out.println("El valor introducido es incorrecto");
			valor = ControlString();
		}
		return valor;

	}

	/**
	 * Metodo para controlar los Nif
	 * 
	 * @return Control
	 */
	public static String ControlNif() {
		String NIF;
		System.out.println("Por favor introduzca su DNI");

		NIF = ControlString();
		String respuesta = NIF;

		boolean error = false;
		char c;
		do {
			error = false;
			while (respuesta.length() != 9) {
				System.out
						.println("La Longitud del DNI introducido no es valida. Por favor vuelva a introducir su DNI");
				respuesta = ControlString();
			}
			for (int i = 0; i < 8; i++) {
				c = respuesta.charAt(i);
				if (!Character.isDigit(c)) {
					error = true;
					break;
				}
			}
			if (!Character.isAlphabetic(respuesta.charAt(8)))
				error = true;
			if (error) {
				System.out.println("El DNI introducido no es válido. Por favor vuelva a introducir su DNI");
				respuesta = ControlString();
			}
		} while (error);
		return respuesta;
	}

	/**
	 * Metodo para controlar la fecha
	 * 
	 * @return Control
	 */
	public LocalDate ControlDate() {
		LocalDate date = null;
		do {
			System.out.println("Dia:");
			int dia = ControlEntero();
			System.out.println("Mes:");
			int mes = ControlEntero();
			System.out.println("Anio:");
			int anio = ControlEntero();
			try {
				date = LocalDate.of(anio, mes, dia);
				error = false;
			} catch (Exception ex) {
				System.out.println("Fecha no valida. Vuelva a introducir una fecha correcta.");
				error = true;
			}
		} while (error);
		return date;
	}

	/**
	 * Metodo para controlar que en una cadena de String no se introduzcan numeros.
	 * 
	 * @param a Value String
	 * @return Control
	 */
	public static String ControlTextoSinNumero(String a) {
		int cont = 0;
		do {
			if (a.toLowerCase().matches("^([a-z]+[ ]?){1,2}$") == false) {
				System.out.println("Introduzca solo letras, por favor.");
				a = entrada.nextLine();
			} else {
				cont = 1;
			}
		} while (cont == 0);
		return a;
	}
}
