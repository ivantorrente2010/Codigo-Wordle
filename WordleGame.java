package WordleUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WordleGame {

    private final int MAX_TRIES = 6;
    private final int WORD_LENGTH = 5;

    private String[] archivoPalabras;
    private String palabraSecreta;
    private int intentoActual = 0;

    private JFrame frame;
    private JLabel[][] letrasLabels; // [fila][columna]
    private JTextField inputField;

    public WordleGame(String[] archivoPalabras) {
        this.archivoPalabras = archivoPalabras;
        this.palabraSecreta = seleccionarPalabraSecreta();
    }

    private String seleccionarPalabraSecreta() {
        Random rand = new Random();
        String palabra;
        do {
            palabra = archivoPalabras[rand.nextInt(archivoPalabras.length)];
        } while (palabra == null || palabra.length() != WORD_LENGTH);
        return palabra.toUpperCase();
    }

    public void comenzarJuego() {
        frame = new JFrame("WORDLE");
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(MAX_TRIES, WORD_LENGTH));
        letrasLabels = new JLabel[MAX_TRIES][WORD_LENGTH];

        // Inicializar la cuadrícula de letras
        for (int i = 0; i < MAX_TRIES; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {
                JLabel letra = new JLabel(" ", SwingConstants.CENTER);
                letra.setPreferredSize(new Dimension(60, 60));
                letra.setFont(new Font("Arial", Font.BOLD, 24));
                letra.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                letra.setOpaque(true);
                letra.setBackground(Color.WHITE);
                letrasLabels[i][j] = letra;
                gridPanel.add(letra);
            }
        }

        // Campo de entrada
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarIntento(inputField.getText().toUpperCase());
            }
        });

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void procesarIntento(String palabraUsuario) {
        if (palabraUsuario.length() != WORD_LENGTH) {
            JOptionPane.showMessageDialog(frame, "La palabra debe tener 5 letras.");
            return;
        }

        Color[] colores = WordleFeedBack.generarColores(palabraUsuario, palabraSecreta);

        // Mostrar letras y colores
        for (int i = 0; i < WORD_LENGTH; i++) {
            letrasLabels[intentoActual][i].setText(String.valueOf(palabraUsuario.charAt(i)));
            letrasLabels[intentoActual][i].setBackground(colores[i]);
        }

        if (palabraUsuario.equals(palabraSecreta)) {
            JOptionPane.showMessageDialog(frame, "¡Felicidades! Has adivinado la palabra.");
            reiniciarJuego();
        } else {
            intentoActual++;
            if (intentoActual >= MAX_TRIES) {
                JOptionPane.showMessageDialog(frame, "Has perdido. La palabra era: " + palabraSecreta);
                reiniciarJuego();
            }
        }

        inputField.setText("");
    }

    private void reiniciarJuego() {
        palabraSecreta = seleccionarPalabraSecreta();
        intentoActual = 0;

        for (int i = 0; i < MAX_TRIES; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {
                letrasLabels[i][j].setText(" ");
                letrasLabels[i][j].setBackground(Color.WHITE);
            }
        }

        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    public String getSecretWord() {
        return palabraSecreta;
    }

    public int getMAX_TRIES() {
        return MAX_TRIES;
    }
}
