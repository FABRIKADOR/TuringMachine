package turingmachinegui;

import javax.swing.*;
import java.awt.*;

public abstract class TuringMachineBase extends JFrame {
    protected JTextArea tapeArea;
    protected JTextField inputField;
    protected JButton processButton;
    protected JLabel resultLabel;

    public TuringMachineBase(String title) {
        setTitle(title);
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para entrada
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(20);
        processButton = new JButton("Procesar");
        inputPanel.add(new JLabel("Entrada:"));
        inputPanel.add(inputField);
        inputPanel.add(processButton);
        add(inputPanel, BorderLayout.NORTH);

        // area central para la cinta
        tapeArea = new JTextArea(10, 40);
        tapeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tapeArea);
        add(scrollPane, BorderLayout.CENTER);

        // Etiqueta inferior para resultados
        resultLabel = new JLabel("Resultado: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // Configurar acción para el botón de procesar
        processButton.addActionListener(e -> processInput());
    }

    // Método abstracto para implementar en cada ejercicio
    protected abstract void processInput();
}
