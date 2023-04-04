package ac.at.fhcampuswien.carrental.expections;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}