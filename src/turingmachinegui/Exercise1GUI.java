package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise1GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular los pasos en tiempo real
    private char[] tape; // Cinta binaria
    private int currentIndex = 0; // Índice actual en la cinta

    public Exercise1GUI() {
        super("Ejercicio 1: Complemento a 1 (Visual)");
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
        if (!input.matches("[01]+")) {
            tapeArea.setText("Entrada no válida: solo números binarios.");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Reiniciar valores
        tape = input.toCharArray();
        currentIndex = 0;
        tapeArea.setText("Inicio: " + String.valueOf(tape) + "\n");
        resultLabel.setText("Procesando...");
        
        // Configurar temporizador para mostrar los pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < tape.length) {
                    // Cambiar el carácter actual
                    tape[currentIndex] = (tape[currentIndex] == '0') ? '1' : '0';
                    
                    // Actualizar la cinta
                    tapeArea.append(String.format("Paso %d: %s\n", currentIndex + 1, String.valueOf(tape)));
                    currentIndex++;
                } else {
                    // Finalizar procesamiento
                    timer.stop();
                    resultLabel.setText("Resultado: " + String.valueOf(tape));
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
