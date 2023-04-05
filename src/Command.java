// For using stack structure
import java.util.Stack;

// Command Pattern
public interface Command 
{
    // This stack will keep all word processes
    public Stack myStack = new Stack();
    
    // Method will use for adding to stack
    void execute(String text);
    
    // For taking take back
    // Process removing from stack
    String undo();
}
