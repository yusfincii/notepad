import java.awt.*;

public class Dark implements Mode
{
    // Constructor
    public Dark() 
    {
        // calls display method
        show();
    }
    
    @Override
    public void show() 
    {
        // Dark Mode properties
        SingletonEditor.showEditor().area.setBackground(Color.BLACK);
        SingletonEditor.showEditor().menuBar.setBackground(Color.GRAY);
        SingletonEditor.showEditor().area.setForeground(Color.GREEN);
        SingletonEditor.showEditor().panel.setBackground(Color.DARK_GRAY);
        
        // i<7 because menubar have 7 items
        for (int k=0; k < 7; k++)
        {
            SingletonEditor.showEditor().menuBar.getMenu(k).setForeground(Color.GREEN);
        }
    }
}
