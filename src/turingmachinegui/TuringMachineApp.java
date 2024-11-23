package turingmachinegui;

import javax.swing.*;
import java.awt.*;

public class TuringMachineApp extends JFrame {
    public TuringMachineApp() {
        setTitle("Simulador de Máquinas de Turing");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Botones para cada ejercicio
        for (int i = 1; i <= 10; i++) {
            JButton exerciseButton = new JButton("Ejercicio " + i);
            int exerciseNumber = i; // Variable para la lambda
            exerciseButton.addActionListener(e -> openExercise(exerciseNumber));
            add(exerciseButton);
        }
    }

    private void openExercise(int exerciseNumber) {
        JFrame exerciseFrame;
        switch (exerciseNumber) {
            case 1:
                exerciseFrame = new Exercise1GUI();
                break;
            case 2:
                exerciseFrame = new Exercise2GUI();
                break;
            case 3:
                exerciseFrame = new Exercise3GUI();
                break;
            case 4:
                exerciseFrame = new Exercise4GUI();
                break;
            case 5:
                exerciseFrame = new Exercise5GUI();
                break;
            case 6:
                exerciseFrame = new Exercise6GUI();
                break;
            case 7:
                exerciseFrame = new Exercise7GUI();
                break;
            case 8:
                exerciseFrame = new Exercise8GUI();
                break;
            case 9:
                exerciseFrame = new Exercise9GUI();
                break;
            case 10:
                exerciseFrame = new Exercise10GUI();
                break;
            default:
                throw new IllegalArgumentException("Ejercicio no válido.");
        }
        exerciseFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TuringMachineApp app = new TuringMachineApp();
            app.setVisible(true);
        });
    }
}
