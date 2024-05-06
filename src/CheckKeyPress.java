import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class CheckKeyPress {


        public static void main() {

            // Create a new KeyListener object
            KeyListener listener = new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    // This method is called when a key is typed
                    System.out.println("Key typed: " + e.getKeyChar());
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // This method is called when a key is pressed
                    System.out.println("Key pressed: " + e.getKeyCode());
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // This method is called when a key is released
                    System.out.println("Key released: " + e.getKeyCode());
                }
            };

            // Add the KeyListener object to the frame
            Window.window.addKeyListener(listener);

            // Start the event loop
            Window.window.setVisible(true);
        }
    }

