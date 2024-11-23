package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise6GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular los pasos en tiempo real
    private StringBuilder originalTape; // Cinta original
    private StringBuilder copiedTape; // Resultado de la copia
    private int currentIndex; // Índice del carácter actual

    public Exercise6GUI() {
        super("Ejercicio 6: Copia de una Cadena (Visual)");
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
        if (!input.matches("[ABCabc]*")) {
            tapeArea.setText("Entrada no válida: solo caracteres A, B, C (o a, b, c).");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Reiniciar valores
        originalTape = new StringBuilder(input);
        copiedTape = new StringBuilder(input); // Comenzamos con la cinta original
        currentIndex = 0;
        tapeArea.setText("Inicio: " + originalTape + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para simular pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < originalTape.length()) {
                    // Copiar el carácter actual al final de la cinta
                    copiedTape.append(originalTape.charAt(currentIndex));
                    tapeArea.append(String.format("Paso %d: %s\n", currentIndex + 1, copiedTape));
                    currentIndex++;
                } else {
                    // Finalizar procesamiento
                    timer.stop();
                    resultLabel.setText("Resultado: " + copiedTape);
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
