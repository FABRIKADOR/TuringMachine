package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise2GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular los pasos en tiempo real
    private StringBuilder tape; // Cinta binaria en formato StringBuilder
    private int currentStep = 0; // Índice del paso actual

    public Exercise2GUI() {
        super("Ejercicio 2: Sucesor en Codificación Unaria (Visual)");
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
        if (!input.matches("1*")) {
            tapeArea.setText("Entrada no válida: solo números en unaria (1 o vacío).");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Reiniciar valores
        tape = new StringBuilder(input);
        currentStep = 0;
        tapeArea.setText("Inicio: " + tape + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para simular pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep == 0) {
                    // Agregar el "1" al final en el primer paso
                    tape.append("1");
                    tapeArea.append(String.format("Paso %d: %s\n", currentStep + 1, tape));
                    currentStep++;
                } else {
                    // Finalizar procesamiento
                    timer.stop();
                    resultLabel.setText("Resultado: " + tape);
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
