package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise4GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular pasos en tiempo real
    private char[] tape; // Cinta binaria
    private int currentIndex = 0; // Índice del carácter actual
    private long countOnes = 0; // Contador de '1's

    public Exercise4GUI() {
        super("Ejercicio 4: Paridad de un Número Binario (Visual)");
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
        countOnes = 0; // Reiniciar contador de '1's
        tapeArea.setText("Inicio: " + String.valueOf(tape) + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para simular pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < tape.length) {
                    // Contar los '1's
                    if (tape[currentIndex] == '1') {
                        countOnes++;
                    }

                    // Mostrar el progreso
                    tapeArea.append(String.format("Paso %d: Analizando '%c' | Total de '1's: %d\n",
                            currentIndex + 1, tape[currentIndex], countOnes));
                    currentIndex++;
                } else {
                    // Determinar el bit de paridad
                    char parityBit = (countOnes % 2 == 0) ? '0' : '1';
                    String result = new String(tape) + parityBit;

                    // Mostrar resultado final
                    timer.stop();
                    tapeArea.append("Bit de paridad calculado: " + parityBit + "\n");
                    tapeArea.append("Cinta final: " + result + "\n");
                    resultLabel.setText("Resultado: " + result);
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
