package by.gsu.epamlab.exceptions;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
