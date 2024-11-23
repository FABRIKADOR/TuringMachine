package turingmachinegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TuringMachineMenu extends JFrame {
    public TuringMachineMenu() {
        // Configuración de la ventana principal
        setTitle("Simulador de Máquina de Turing");
        setSize(900, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Encabezado estilizado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(45, 85, 145)); // Azul oscuro elegante
        JLabel headerLabel = new JLabel("Simulador de Máquina de Turing");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Panel central con botones para seleccionar ejercicios
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2, 20, 20)); // Más espacio entre botones
        centerPanel.setBackground(new Color(240, 240, 240)); // Fondo claro moderno

        // Crear botones estilizados con nombres descriptivos
        String[] exercises = {
            "Ejercicio 1: Complemento a 1",
            "Ejercicio 2: Sucesor en Codificación Unaria",
            "Ejercicio 3: Predecesor en Codificación Unaria",
            "Ejercicio 4: Paridad de un Número Binario",
            "Ejercicio 5: Contador Unario de Caracteres",
            "Ejercicio 6: Copia de una Cadena",
            "Ejercicio 7: Transformar A’s en B’s Según 1’s",
            "Ejercicio 8: Comparar Dos Palabras",
            "Ejercicio 9: Sucesor de un Número Binario",
            "Ejercicio 10: Antecesor de un Número Binario"
        };

        for (int i = 0; i < exercises.length; i++) {
            JButton exerciseButton = createStylishButton(exercises[i], i + 1);
            centerPanel.add(exerciseButton);
        }
        add(centerPanel, BorderLayout.CENTER);

        // Panel inferior con mensaje elegante
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(60, 60, 60)); // Gris oscuro
        JLabel footerLabel = new JLabel("Selecciona un ejercicio para explorar las capacidades de una Máquina de Turing.");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }

    // Método para crear botones estilizados
    private JButton createStylishButton(String text, int exerciseNumber) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(45, 140, 240)); // Azul elegante
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(30, 100, 200), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto de hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(30, 100, 200)); // Azul más oscuro al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(45, 140, 240)); // Azul original
            }
        });

        // Acción para abrir el ejercicio correspondiente
        button.addActionListener(e -> openExercise(exerciseNumber));

        return button;
    }

    // Método para abrir la ventana del ejercicio correspondiente
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
            TuringMachineMenu frame = new TuringMachineMenu();
            frame.setVisible(true);
        });
    }
}
