package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise9GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular pasos
    private char[] tape; // Cinta binaria
    private int currentIndex; // Índice actual
    private boolean carry; // Acarreo

    public Exercise9GUI() {
        super("Ejercicio 9: Sucesor de un Número Binario (Visual)");
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

        // Preparar la cinta y las variables para el cálculo
        tape = (input + "_").toCharArray();
        currentIndex = tape.length - 2;
        carry = true; // Inicialmente, hay un acarreo para sumar 1

        tapeArea.setText("Inicio: " + String.valueOf(tape) + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para mostrar pasos
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex >= 0 && carry) {
                    if (tape[currentIndex] == '0') {
                        tape[currentIndex] = '1';
                        carry = false;
                    } else {
                        tape[currentIndex] = '0';
                    }

                    // Mostrar el paso actual
                    tapeArea.append(String.format("Paso (índice %d): %s\n", currentIndex, String.valueOf(tape)));
                    currentIndex--;
                } else {
                    if (carry) {
                        tape[0] = '1'; // Caso de overflow, añadir 1 al principio
                    }

                    // Finalizar procesamiento
                    timer.stop();
                    tapeArea.append("Final: " + String.valueOf(tape) + "\n");
                    resultLabel.setText("Resultado: " + String.valueOf(tape).trim());
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
