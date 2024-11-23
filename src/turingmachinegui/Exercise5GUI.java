package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise5GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular los pasos en tiempo real
    private StringBuilder tape; // Cinta binaria en formato StringBuilder
    private StringBuilder result; // Resultado en unaria
    private int currentIndex; // Índice del carácter actual

    public Exercise5GUI() {
        super("Ejercicio 5: Contador Unario de Caracteres (Visual)");
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
        if (!input.matches("[abcABC]*")) {
            tapeArea.setText("Entrada no válida: solo caracteres a, b, c (o A, B, C).");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Reiniciar valores
        tape = new StringBuilder(input);
        result = new StringBuilder();
        currentIndex = 0;
        tapeArea.setText("Inicio: " + tape + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para simular pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < tape.length()) {
                    // Agregar un "1" por cada carácter leído
                    result.append("1");
                    tapeArea.append(String.format("Paso %d: %s → %s\n", currentIndex + 1, tape, result));
                    currentIndex++;
                } else {
                    // Finalizar procesamiento
                    timer.stop();
                    resultLabel.setText("Resultado: " + result);
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
