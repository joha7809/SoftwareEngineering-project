
package dtu.example.Controller.command_base;

import java.util.HashMap;
import java.util.Map;

import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;


/**
 * This abstract class is to be used for more advanced commands, such as editing projects, activities and so on.
 * The class automatically tries to parses the String[] input to its PositionalArguents and KeywordArguments. 
 * 
 * @author Johannes
 */
public abstract class KeywordCommand<T> implements CommandInterface<T>{
    
    //Johannes
    // Override normal command and pass arguments correctly
    @Override
    public CommandResult<T> execute(String[] input) throws IllegalArgumentException{
        // Firstly we validate the structure of the input
        validateInputForm(input);

        Map<String, String> kwargs = parseKwargs(input);

        String[] positionalArgs = filterPositionalArguments(input);

        return execute(positionalArgs, kwargs);
    }

    //Johannes
    // The command that is to be implemented
    public abstract CommandResult<T> execute(String[] args, Map<String, String> kwargs);

    //Johannes
    // Helper function for automatically parsing PositionalArgument from input
    // This happens after the input is validated for correct format (first positional then keyword arguments)
    private String[] filterPositionalArguments(String[] input){
        
        String[] posArgs = java.util.Arrays.stream(input).filter(arg -> !arg.contains("=")).toArray(String[]::new);
        return posArgs;
    }

    //Johannes
    private Map<String, String> parseKwargs(String[] input){
        Map<String, String> kwargs = new HashMap<>();
        for (String token : input) {
            if (isKwarg(token)){
                //Token contains "=", split it in two
                String[] keyValue = token.split("=", 2);
                if (keyValue.length != 2 || keyValue[0].isEmpty() || keyValue[1].isEmpty()) {
                    // Handle errors if any of the items are empty.
                    throw new IllegalArgumentException("Invalid keyword argument: " + token);
                }
                kwargs.put(keyValue[0], keyValue[1]);
            }
        }
        return kwargs;
    }

    //Johannes
    private boolean isKwarg(String string){
        if (string.contains("=")) {
            return true;
        } else {
            return false;
        }
    }

    //Johannes
    // Validates the input -- If kwarg is found before a positional Argument
    // or a positional argument is found after a kwarg, the input is invalid
    // and should throw an error
    private void validateInputForm(String[] input) throws IllegalArgumentException{
        boolean found_kwargs = false;
        for (String token : input) {
            if (isKwarg(token)){
                found_kwargs = true; // We found keyword arg, everything else should be keyword also
            } else if (found_kwargs) {
                throw new IllegalArgumentException("Positional arguments are not allowed after keyword arguments: " + token);
            }
        }
    }
}
