// This class represent unchecked cases
public class UncheckedState implements State
{
    @Override
    // Unchecked states changes to checked state with this update method
    public void update(StateChain stateChain) 
    {
        // New state is checked
        stateChain.setState(new CheckedState());
        stateChain.setName("Checked");
    }
}
