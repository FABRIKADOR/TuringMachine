package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise7GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular los pasos
    private char[] tape; // Cinta binaria
    private int currentIndex; // Índice actual en la cinta
    private int onesCount; // Número de 1's
    private int aCount; // Número de A's cambiadas

    public Exercise7GUI() {
        super("Ejercicio 7: Transformar A’s en B’s Según 1’s (Visual)");
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
        if (!input.matches("1*A*[B]*")) {
            tapeArea.setText("Entrada no válida: formato esperado 1*A*[B]* (1's seguidos de A's y opcionalmente B's).");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Contar los 1's al inicio y preparar la cinta
        onesCount = (int) input.chars().takeWhile(ch -> ch == '1').count();
        int firstAIndex = input.indexOf('A');

        // Validar que haya suficientes A's para procesar
        if (firstAIndex == -1 || onesCount == 0) {
            tapeArea.setText("No hay suficientes A's o 1's para procesar.");
            resultLabel.setText("Resultado: " + input);
            return;
        }

        // Inicializar valores
        tape = input.toCharArray();
        currentIndex = firstAIndex; // Iniciar desde el primer 'A'
        aCount = 0;
        tapeArea.setText("Inicio: " + input + "\n");
        resultLabel.setText("Procesando...");

        // Configurar temporizador para simular pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < tape.length && aCount < onesCount) {
                    if (tape[currentIndex] == 'A') {
                        tape[currentIndex] = 'B'; // Cambiar 'A' por 'B'
                        aCount++;
                    }

                    // Actualizar cinta y mostrar paso actual
                    tapeArea.append(String.format("Paso %d: %s\n", aCount, new String(tape)));
                    currentIndex++;
                } else {
                    // Finalizar procesamiento
                    timer.stop();
                    String result = new String(tape);
                    tapeArea.append("Final: " + result + "\n");
                    resultLabel.setText("Resultado: " + result);
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
