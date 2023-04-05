// This class represents checked cases
public class CheckedState implements State
{
    @Override
    // Checked states changes to unchecked state with this update method
    public void update(StateChain stateChain) 
    {
        // New state will be unchecked
        stateChain.setState(new UncheckedState());
        stateChain.setName("Unchecked");
    }
}
