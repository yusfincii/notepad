public class StateChain 
{
    private State currentState;
    
    // This variable represents styring kind of current state
    private String name;
    
    // Constructor
    public StateChain() 
    {
        // App initializing with unchecked case
        currentState = new UncheckedState();
    }

    // Current state assignment
    public void setState(State state) 
    {
        currentState = state;
    }
    
    // State updater method
    public void Update() 
    {
        currentState.update(this);
    }
    
    // Set and Get method for name variable
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    
}
