public class SingletonEditor 
{
    // Variable assigned static becuase pcoess will be on one window
    private static Editor editor = new Editor();

    
    public static Editor showEditor() 
    {
        return editor;
    }

}
