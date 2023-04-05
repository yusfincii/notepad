import java.awt.*;

public class Light implements Mode
{
    // Constructor
    public Light() 
    {
        // calls show method
        show();
    }
    
    @Override
    public void show() 
    {
        SingletonEditor.showEditor().menuBar.setBackground(Color.LIGHT_GRAY);
        SingletonEditor.showEditor().area.setForeground(Color.BLACK);
        SingletonEditor.showEditor().area.setBackground(Color.WHITE);
        SingletonEditor.showEditor().panel.setBackground(Color.GRAY);
        
        // i<7 because menubar have 7 items
        for (int m=0; m<7; m++)
        {
            SingletonEditor.showEditor().menuBar.getMenu(m).setForeground(Color.BLACK);
        }
    }
}
