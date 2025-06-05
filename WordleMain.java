package WordleUI;

public class WordleMain {

    public static void main(String[] args) {

        WordleFileManager wfm = new WordleFileManager();
        String[] arrayPalabras = wfm.obtenerPalabras("palabras.txt");

        WordleGame wgp = new WordleGame(arrayPalabras);
        wgp.comenzarJuego();
    }
}