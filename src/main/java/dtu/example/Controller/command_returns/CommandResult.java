package dtu.example.Controller.command_returns;

//TODO: IMplement enum class for the wrapper
//TODO: Implement wrapper class CommandResult<T>
public class CommandResult<T> {
    ReturnTypes type;
    T value;

    public CommandResult(ReturnTypes type, T value){
        this.type = type;
        this.value = value;
    }
    public ReturnTypes getType(){
        return this.type;
    }
        
    public T getValue(){
        return this.value;
    }
}
