import java.awt.*;
import javax.swing.*;

/**
A class to create a Frame on the screen
*/
public class Window extends JFrame {

  /**
  Initialize the attributes
  @param name the title of the frame
  @param canvas the canvas that will be contained inside the frame
  */
  public Window(String name, PCanvas canvas){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(name);
    setResizable(false);
    setLayout(new BorderLayout());
    add(canvas);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }

}
