package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise10GUI extends TuringMachineBase {
    private Timer timer; // Temporizador para simular pasos
    private char[] tape; // Cinta binaria
    private int currentIndex; // Índice del bit actual
    private boolean borrow; // Indicador de acarreo
    private StringBuilder tapeLog; // Registro de pasos

    public Exercise10GUI() {
        super("Ejercicio 10: Antecesor de un Número Binario (Visual)");
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
        if (!input.matches("[01]+") || input.equals("0")) {
            tapeArea.setText("Entrada no válida: debe ser un número binario mayor que 0.");
            resultLabel.setText("Error: Entrada inválida.");
            return;
        }

        // Preparar la cinta y los valores iniciales
        tape = (input + "_").toCharArray();
        tapeLog = new StringBuilder("Inicio: " + String.valueOf(tape) + "\n");
        currentIndex = tape.length - 2; // Comenzar desde el penúltimo bit
        borrow = true; // Iniciar con acarreo

        // Configurar temporizador para simular pasos
        timer = new Timer(500, new ActionListener() { // 500 ms entre pasos
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex >= 0 && borrow) {
                    if (tape[currentIndex] == '1') {
                        tape[currentIndex] = '0';
                        borrow = false; // Acarreo resuelto
                    } else {
                        tape[currentIndex] = '1';
                    }

                    // Agregar el paso al log
                    tapeLog.append("Paso: " + String.valueOf(tape) + "\n");
                    tapeArea.setText(tapeLog.toString());
                    currentIndex--;
                } else {
                    // Finalizar procesamiento
                    timer.stop();

                    // Quitar ceros no significativos
                    int start = 0;
                    while (start < tape.length && tape[start] == '0') start++;
                    String result = new String(tape, start, tape.length - start).trim();

                    tapeLog.append("Final: " + result + "\n");
                    tapeArea.setText(tapeLog.toString());
                    resultLabel.setText("Resultado: " + result);
                }
            }
        });

        timer.start(); // Iniciar temporizador
    }
}
