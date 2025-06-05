package WordleUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class WordleFileManager {

    public static String arrayPalabras[] = new String[100];

    // Este es el metodo para obtener las palabras del archivo palabras.txt
    public static String[] obtenerPalabras(String ruta) {

        try (
                FileReader lector = new FileReader(ruta);
                BufferedReader buffLector = new BufferedReader(lector)) {
            String linea;
            int indice = 0;
            // Se hace un bucle while primero para asegurarse de que no lea valor nulo
            while ((linea = buffLector.readLine()) != null) {

                // se hace un bucle for para ir introduciendo las lineas leidas en cada indice
                // del array
                arrayPalabras[indice] = linea;
                indice++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " +
                    e.getMessage());
        }
        return arrayPalabras;
    }
}

    

