import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Editor extends JFrame implements ActionListener
{
    // -- Variable Assignments -- //
    
    // Following variables represent part of window
    JFrame frame;
    JTextField field;
    JTextArea area;
    JMenuBar menuBar;
    JPanel panel;
    JLabel label;
    
    // Wanted word
    JTextField find; 
    
    // Button
    JButton button; 
    
    // Object from Undo class
    Undo undoObject = new Undo();
    
    // A freak variable for chech status
    Boolean isChecked = false;
    
    // Object from ModeFactory class
    // Keeps user selected mode
    ModeFactory modes = new ModeFactory();
    
    // Object from StateChain class
    StateChain chain = new StateChain();

    
    public Editor() 
    {
        frame = new JFrame();
        frame.setTitle("Editor");
        // Assigning frame initalize location
        frame.setBounds(700, 300, 500 ,500);

        area = new JTextArea();
        area.setLineWrap(true);
        area.setBounds(50, 50, 200,200);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);

        field = new JTextField();
        field.setLocation(100,100);

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newx = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem close = new JMenuItem("Close");

        // File Menu Actions
        newx.addActionListener(this);
        save.addActionListener(this);
        open.addActionListener(this);
        close.addActionListener(this);

        fileMenu.add(newx);
        fileMenu.add(save);
        fileMenu.add(open);
        fileMenu.add(close);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        // Edit Menu Actions
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        
        // Undo Menu
        JMenu undoMenu = new JMenu("Undo");
        JMenuItem undo = new JMenuItem("Undo");
        
        // Undo Menu Actions
        undo.addActionListener(this);
        undoMenu.add(undo);
        
        
        // Chech Menu
        JMenu checkMenu = new JMenu("Check");
        JMenuItem fix = new JMenuItem("Fix All");
        
        // Check Menu Actions
        fix.addActionListener(this);
        checkMenu.add(fix);

        
        // Find Menu
        JMenu findMenu = new JMenu("Find");
        JMenuItem find = new JMenuItem("Find Word");
        
        // Find Menu Actions
        find.addActionListener(this);
        findMenu.add(find);

        panel = new JPanel();
        panel.setBounds(0, 400, 600, 50);
        label = new JLabel();

        // Mode Menu
        JMenu modeMenu = new JMenu("Mode");

        JMenuItem darkMode = new JMenuItem("Dark");
        JMenuItem lightMode = new JMenuItem("Light");
        JMenuItem vincentMode = new JMenuItem("Sky");
        JMenuItem natureMode = new JMenuItem("Turkey");

        // Mode Menu Actions
        darkMode.addActionListener(this);
        lightMode.addActionListener(this);
        vincentMode.addActionListener(this);
        natureMode.addActionListener(this);

        modeMenu.add(darkMode);
        modeMenu.add(lightMode);
        modeMenu.add(vincentMode);
        modeMenu.add(natureMode);
        
        
        // Help Menu
        JMenu help = new JMenu("Help");
        JMenuItem creators = new JMenuItem("Creators");
        
        // Help Menu Actions
        creators.addActionListener(this);
        help.add(creators);

        // Case of take a key
        area.addKeyListener(new KeyListener() 
        {
            @Override
            public void keyTyped(KeyEvent e) 
            {
                // Current text is adding to stack
                undoObject.execute(area.getText());

                // If text is checked label text will update and current state will pass to next state
                if(isChecked)
                {
                    chain.Update();
                    label.setText(chain.getName());
                    isChecked = false; // freak reset
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        panel.add(label);

        // Elements adding to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(undoMenu);
        menuBar.add(checkMenu);
        menuBar.add(findMenu);
        menuBar.add(modeMenu);
        menuBar.add(help);
        
        // Elements adding to frame
        frame.add(panel);
        frame.setJMenuBar(menuBar);
        frame.add(area);
        frame.setSize(500,500);
        frame.setVisible(true);
        
        // For exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        // As named command variable determines what user want to do
        String command = e.getActionCommand();
        
        // Cut - Copy - Paste
        if (command.equals("Cut"))
        {
            area.cut();
        }
        else if (command.equals("Copy"))
        {
            area.copy();
        }
        
        else if (command.equals("Paste"))
        {
            area.paste();
        }
        
        // Creating new area
        else if (command.equals("New"))
        {
            area.setText("");
        }
        
        // For opening a choosen file
        else if (command.equals("Open"))
        {
            JFileChooser fileCh = new JFileChooser("f:");
            
            int a = fileCh.showOpenDialog(null);
            
            if (a == JFileChooser.APPROVE_OPTION) 
            {
                File file = new File(fileCh.getSelectedFile().getAbsolutePath());
                
                try 
                {
                    String line = "";
                    String sline = "";
                    
                    FileReader fileRead = new FileReader(file);
                    BufferedReader bufferRead = new BufferedReader(fileRead);
                    
                    sline = bufferRead.readLine();
                    
                    while ((line = bufferRead.readLine()) != null) 
                    {
                        sline = sline + "\n" + line;
                    }
                    area.setText(sline);
                }
                catch (Exception evt) 
                {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
                
        }
        
        // Saving
        else if (command.equals("Save"))
        {
            
            JFileChooser fc = new JFileChooser("f:");
            int a = fc.showSaveDialog(null);

            if (a == JFileChooser.APPROVE_OPTION) 
            {
                File file = new File(fc.getSelectedFile().getAbsolutePath());

                try 
                {
                    FileWriter fileWrite = new FileWriter(file, false);
                    BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

                    bufferWrite.write(area.getText());
                    
                    // flush() for taking to buffer
                    bufferWrite.flush();
                    bufferWrite.close();
                }
                catch (Exception evt) 
                {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
        }
        
        // Closing
        else if (command.equals("Close"))
        {
            // Visitibility switching true to false
            frame.setVisible(false);
        }
        
        // Fixing
        else if (command.equals("Fix Words"))
        {
            
            String newText = Text.check(area.getText().toLowerCase());
            area.setText(newText);
            
            // state forwarding
            chain.Update();
            label.setText(chain.getName());
            isChecked = true;

        }
        
        // Undo process
        else if (command.equals("Undo"))
        {
            // Transaction is rolled back
            area.setText(undoObject.undo());
        }
        
        // Themes
        else if (command.equals("Dark")){modes.switchMode("Dark");}
        else if (command.equals("Light")) {modes.switchMode("Light");}
        else if (command.equals("Sky")) {modes.switchMode("Sky");}
        else if (command.equals("Turkey")) {modes.switchMode("Turkey");}
        
        // Find
        else if (command.equals("Find Word")) 
        {
            String findW = JOptionPane.showInputDialog(frame, "Find Word: ");
            boolean result = Text.find(area.getText(), findW);
        }
        
        // Creators
        else if (command.equals("Creators"))
        {
            JOptionPane.showMessageDialog(frame, "Created By:\n >> Yusuf INCI\n >> Bilal AKYUZ"
                    + "\n --------------  Ege University  --------------\n - Computer Engineering Department -");
        }
    }
}