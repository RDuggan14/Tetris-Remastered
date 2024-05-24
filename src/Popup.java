import javax.swing.*;
import java.io.IOException;

public class Popup {
    private static int[] Scores;

    static {
        try {
            Scores = new int[]{Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 2)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 4)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 6)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 10)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 12)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 14)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 16)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 18)),Integer.parseInt(FileHandler.readSpecificLine(Window.FilePath, 20))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Popup() throws IOException {
    }

    public static void main() {
        // Create the frame
        JFrame frame = new JFrame("Name Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

        // Create the panel to hold components
        JPanel panel = new JPanel();

        // Create a label
        JLabel label = new JLabel("Please enter your name:");
        panel.add(label);

        // Create a text field
        JTextField textField = new JTextField(15);
        panel.add(textField);

        // Create a button
        JButton button = new JButton("Submit");
        panel.add(button);

        // Add an action listener to the button
        button.addActionListener(e -> {
            // Get the name from the text field
            String name = textField.getText();
            int Placement = 11;
            String TempName = "";
            String TempValue = "";
            String TempName1 = "";
            String TempValue2 = "";
            // Display the greeting message
            for(int i = 0; i < Scores.length; i++){
                if(Window.Score > Scores[i]){
                    if(i < Placement){
                        Placement = i;
                    }
                }
            }
            if(Placement != 11){
                Placement = Placement*2;


                for(int i = 0; i < Placement-1  ; i++){
                    if(i % 2 == 0) {
                       int r = 20 - i;
                        try {
                            FileHandler.writeToSpecificLine(Window.FilePath, r-1, FileHandler.readSpecificLine(Window.FilePath, r -3));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            FileHandler.writeToSpecificLine(Window.FilePath, r,FileHandler.readSpecificLine(Window.FilePath, r - 2));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
                try {
                    FileHandler.writeToSpecificLine(Window.FilePath, Placement, TempValue);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    FileHandler.writeToSpecificLine(Window.FilePath, Placement-1, TempName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            JOptionPane.showMessageDialog(frame, "Congrats, " + name + ", your score was " + Placement);

            // Close the frame
            frame.dispose();
        });

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
