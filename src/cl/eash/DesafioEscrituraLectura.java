/*
 * Proyecto que permite la creacion y lectura de un fichero, con su respectivo directorio
 */

package cl.eash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;

public class DesafioEscrituraLectura {
	public static String directorio;
	public static String fichero;
	public static Scanner tecla = new Scanner(System.in);

//Variables de clases 
	public static void main(String[] args) {

		System.out.println("Ingrese el nombre del directorio a crear");
		directorio = tecla.nextLine();
		//Ingreso de nombre del directorio

		System.out.println("Ingrese el nombre del archivo a crear, se creará con formato .txt");
		fichero = tecla.nextLine();
		//ingreso del nombre del archivo

		crearArchivo(directorio, fichero);

		tecla.close();
	}

	public static void crearArchivo(String nombre, String archivo) {
		//Metodo que permite la creacion y/o validacion del directorio y archivo
		File directorio = new File("src/" + nombre);
		try {
			//validacion de la existencia del directorio, si no existe lo crea
			if (directorio.exists() == false) {
					directorio.mkdir();
				System.out.println("Directorio Creado");
			} else {
				System.out.println("Directorio ya existe");
			}
		} catch (Exception e) {
			System.out.println("Error al crear el directorio");
		}

		// Creacion del fichero
		File fichero = new File("src/" + nombre + "/" + archivo + ".txt");
		// Validacion de existencia del fichero,si no existe lo crea (con los datos solicitados en las instrucciones), si existe le pedira al usuario ingresar el texto a buscar
		if (fichero.exists()) {
			System.out.println("El fichero ya existe");
			
			String texto = null;
			System.out.println("Ingrese el texto a buscar");
			texto = tecla.nextLine();
			//Se invoca el metodo para buscar el texto ingresado
			buscarTexto(fichero, texto);

		} else {
			System.out.println("El fichero ingresado no existe");
			System.out.println("Se creará un nuevo fichero con el nombre indicado");
			//Arreglo con los datos a llenar el archivo
			ArrayList<String> lista = new ArrayList<String>();
			lista.add("Perro");
			lista.add("Gato");
			lista.add("Juan");
			lista.add("Daniel");
			lista.add("Juan");
			lista.add("Gato");
			lista.add("Perro");
			lista.add("Camila");
			lista.add("Daniel");
			lista.add("Camila");
			
			//creacion del archivo
			FileWriter fileW = null;
			try {
				fileW = new FileWriter(fichero);
				BufferedWriter bufferedWriter = new BufferedWriter(fileW);
				for (Iterator<?> iterator = lista.iterator(); iterator.hasNext();) {
					String valor = (String) iterator.next();
					bufferedWriter.write(valor + "\n");
				}

				bufferedWriter.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	private static void buscarTexto(File fichero2, String texto) {
//metodo que permite la busqueda y posterior muestra de coincidencias encontradas
		File nombreArchivo = fichero2;
		FileReader fileReader = null;
		// BufferedReader br = null;
		String thisLine = null;
		String textoBuscado = texto;
		int contador = 0;
		//Declaracion de variables
		
		try {
			fileReader = new FileReader(nombreArchivo);
			BufferedReader br = new BufferedReader(fileReader);
			//Lector del archivo
			// thisLine = bufferedReader.readLine();
			while ((thisLine = br.readLine()) != null) {

				if (thisLine.contains(textoBuscado)) {
					contador++;
					//Contador de repeticiones
				}
			}
			br.close();
			fileReader.close();
			System.out.println("Cantidad de repeticiones del texto -> " + contador);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
