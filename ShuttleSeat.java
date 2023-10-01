import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShuttleSeat extends JFrame {
    private JLabel nameLabel, contactLabel, emailLabel;
    private JTextField nameField, contactField, emailField;
    private JButton bookButton, calculateButton;
    private JCheckBox[] seatCheckBoxes;
    private JTextArea billArea;

    private double seatPrice = 500.0; // Price per seat

    public ShuttleSeat() {
        setTitle("Shuttle Seat Booking");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JPanel seatPanel = new JPanel(new GridLayout(3, 3));
        JPanel billPanel = new JPanel(new BorderLayout());

        nameLabel = new JLabel("Name:");
        contactLabel = new JLabel("Contact No:");
        emailLabel = new JLabel("Email:");

        nameField = new JTextField();
        contactField = new JTextField();
        emailField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        seatCheckBoxes = new JCheckBox[9];
        String[] seatNames = {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};

        for (int i = 0; i < 9; i++) {
            seatCheckBoxes[i] = new JCheckBox(seatNames[i]);
            seatPanel.add(seatCheckBoxes[i]);
        }

        bookButton = new JButton("Book");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedSeats = new StringBuilder();
                for (int i = 0; i < 9; i++) {
                    if (seatCheckBoxes[i].isSelected()) {
                        selectedSeats.append(seatNames[i]).append(", ");
                    }
                }

                String message = "Name: " + nameField.getText() + "\n" +
                        "Contact No: " + contactField.getText() + "\n" +
                        "Email: " + emailField.getText() + "\n" +
                        "Selected Seats: " + selectedSeats.toString();

                JOptionPane.showMessageDialog(null, message, "Booking Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        calculateButton = new JButton("Calculate Total");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0.0;
                int selectedCount = 0;

                for (int i = 0; i < 9; i++) {
                    if (seatCheckBoxes[i].isSelected()) {
                        selectedCount++;
                    }
                }

                total = seatPrice * selectedCount;

                billArea.setText("Total Seats: " + selectedCount + "\n" +
                        "Seat Price: /-" + seatPrice + "\n" +
                        "Total Amount: /-" + total);
            }
        });

        billArea = new JTextArea();
        billArea.setEditable(false);

        billPanel.add(new JScrollPane(billArea), BorderLayout.CENTER);
        billPanel.add(calculateButton, BorderLayout.SOUTH);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(seatPanel, BorderLayout.CENTER);
        mainPanel.add(bookButton, BorderLayout.SOUTH);
        mainPanel.add(billPanel, BorderLayout.EAST);

        add(mainPanel);
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShuttleSeat().setVisible(true);
            }
        });
    }
  }

