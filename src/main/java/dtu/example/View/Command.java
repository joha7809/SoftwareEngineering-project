package dtu.example.View;

public class Command {
    public String name;
    public String args;

    public Command(String name, String args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString(){
        return "CMD-name: "+ this.name + " ARGS: " + this.args;
    }
}
