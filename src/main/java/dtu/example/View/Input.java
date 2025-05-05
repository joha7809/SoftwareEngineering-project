package dtu.example.View;

public class Input {
    // This class is used to represent user input. The class is used by the UI-controller for passing input from a scanner
    // to this data-type. This makes calling commands easier.
    
    public String name;
    public String[] args;

    public Input(String name, String args[]) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString(){
        return "CMD-name: "+ this.name + " ARGS: " + this.args;
    }
}
