import java.awt.*;

public class Turkey implements Mode
{
    // Constructor without parameter
    // Calls show method for display theme
    public Turkey() 
    {
        show();
    }

    @Override
    public void show() 
    {  
        // Color assigns
        SingletonEditor.showEditor().menuBar.setBackground(Color.RED);
        SingletonEditor.showEditor().area.setBackground(Color.WHITE);
        SingletonEditor.showEditor().area.setForeground(Color.RED);
        SingletonEditor.showEditor().label.setForeground(Color.WHITE);
        SingletonEditor.showEditor().panel.setBackground(Color.RED);
        
        // i<7 because menubar have 7 items
        for (int i=0; i<7; i++)
        {
            SingletonEditor.showEditor().menuBar.getMenu(i).setForeground(Color.WHITE);
        }
    }
}
