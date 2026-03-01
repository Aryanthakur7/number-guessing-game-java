import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StandardNumberGuessingGame extends JFrame implements ActionListener {
    private int targetNumber;
    private int attempts;

    private JTextField guessField;
    private JButton guessButton;
    private JButton restartButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;

    public StandardNumberGuessingGame() {
        // Set up the JFrame
        setTitle("Number Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Initialize the target number and attempt counter
        targetNumber = generateRandomNumber();
        attempts = 0;

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        // Title label
        JLabel titleLabel = new JLabel("Number Guessing Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center panel for input and feedback
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Instruction label
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:", JLabel.CENTER);
        centerPanel.add(instructionLabel);

        // Input panel with text field and guess button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        centerPanel.add(inputPanel);

        // Feedback label
        feedbackLabel = new JLabel("", JLabel.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        feedbackLabel.setForeground(Color.BLUE);
        centerPanel.add(feedbackLabel);

        // Bottom panel for attempts and restart button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Attempts label
        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomPanel.add(attemptsLabel);

        // Restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());
        bottomPanel.add(restartButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = guessField.getText();

        // Validate input
        try {
            int userGuess = Integer.parseInt(input);
            if (userGuess < 1 || userGuess > 100) {
                feedbackLabel.setText("Enter a number between 1 and 100!");
                return;
            }

            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (userGuess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (userGuess > targetNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText("Correct! The number was " + targetNumber + ".");
                JOptionPane.showMessageDialog(this, "Congratulations! You guessed the number in " + attempts + " attempts.", "You Win!", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    // Generate a random number between 1 and 100
    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    // Reset the game
    private void resetGame() {
        targetNumber = generateRandomNumber();
        attempts = 0;
        attemptsLabel.setText("Attempts: 0");
        feedbackLabel.setText("");
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StandardNumberGuessingGame game = new StandardNumberGuessingGame();
            game.setVisible(true);
        });
    }
}
