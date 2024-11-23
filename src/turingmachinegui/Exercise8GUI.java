package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise8GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular los pasos
    private char[] word1; // Primera palabra
    private char[] word2; // Segunda palabra
    private int currentIndex; // Índice actual para comparación
    private boolean areEqual; // Indicador de igualdad

    public Exercise8GUI() {
        super("Ejercicio 8: Comparar Dos Palabras Separadas por # (Visual)");
        setResizable(false);

        // Mejorar el diseño visual
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        processButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        processButton.setBackground(new Color(45, 140, 240));
        processButton.setForeground(Color.WHITE);
        processButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tapeArea.setFont(new Font("Courier New", Font.BOLD, 20)); // Estilo de cinta
        tapeArea.setBackground(new Color(240, 240, 240));
        tapeArea.setForeground(Color.BLACK);

        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setForeground(new Color(30, 100, 200));
    }

    @Override
    protected void processInput() {
        String input = inputField.getText().trim();

        // Validar entrada
        if (!input.matches("[012]+#[012]+")) {
            tapeArea.setText("Entrada no válida: deben ser dos palabras en {0, 1, 2} separadas por #.");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Dividir las palabras y prepararlas para comparación
        String[] parts = input.split("#");
        word1 = parts[0].toCharArray();
        word2 = parts[1].toCharArray();

        // Validar longitud de las palabras
        if (word1.length != word2.length) {
            tapeArea.setText("Las palabras tienen diferentes longitudes.\n");
            resultLabel.setText("Resultado: Palabras diferentes.");
            return;
        }

        // Inicializar variables para la simulación
        currentIndex = 0;
        areEqual = true;
        tapeArea.setText("Inicio: " + input + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para comparar carácter por carácter
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < word1.length) {
                    // Comparar los caracteres actuales
                    if (word1[currentIndex] != word2[currentIndex]) {
                        areEqual = false;
                    }

                    // Mostrar el progreso
                    tapeArea.append(String.format("Paso %d: Comparando '%c' y '%c' → %s\n",
                            currentIndex + 1, word1[currentIndex], word2[currentIndex],
                            (areEqual ? "Iguales hasta ahora" : "Diferentes")));
                    currentIndex++;
                } else {
                    // Finalizar la comparación
                    timer.stop();
                    if (areEqual) {
                        tapeArea.append("Palabras iguales.\n");
                        resultLabel.setText("Resultado: Palabras iguales.");
                    } else {
                        tapeArea.append("Palabras diferentes.\n");
                        resultLabel.setText("Resultado: Palabras diferentes.");
                    }
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
