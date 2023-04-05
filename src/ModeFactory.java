// Factory pattern
public class ModeFactory
{
    // Method provides theme applications
    public Mode switchMode(String mode) 
    {
        // With following this if-else condition statements
        // user will choose a mode and it will create an object
        // from choosen theme mode.
        
        if ("Dark".equalsIgnoreCase(mode))        
            return new Dark();          

        else if ("Light".equalsIgnoreCase(mode))      
            return new Light();

        else if ("Sky".equalsIgnoreCase(mode))
            return new Sky();            

        else if ("Turkey".equalsIgnoreCase(mode))
            return new Turkey();
        else
            // case of exception
            return null;
    }
}

