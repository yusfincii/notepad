// Implements command pattern
public class Undo implements Command
{

    // Constructor without parameter
    public Undo(){}
    
    
    @Override
    // Method removes and returns the top element on the stack
    public String undo() 
    {
        return (String) Command.myStack.pop();
    }

    // Method oushes the given parameter to the stack
    @Override
    public void execute(String a) 
    {
        Command.myStack.push(a);
    }
    
}
