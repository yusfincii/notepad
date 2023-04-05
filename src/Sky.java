import java.awt.*;

public class Sky implements Mode
{
    // Constructor without parameter
    // Calls show method for display
    public Sky() 
    {
        show();
    }
    
    @Override
    public void show() 
    {   
        // Sky mode properties
        SingletonEditor.showEditor().menuBar.setBackground(Color.WHITE);
        SingletonEditor.showEditor().area.setBackground(Color.CYAN);
        SingletonEditor.showEditor().area.setForeground(Color.WHITE);
        SingletonEditor.showEditor().panel.setBackground(Color.WHITE);
        SingletonEditor.showEditor().label.setForeground(Color.WHITE);
        
        // i<7 because menubar have 7 items
        for (int p=0; p<7; p++)
        {
            SingletonEditor.showEditor().menuBar.getMenu(p).setForeground(Color.CYAN);
        }
    }
}
