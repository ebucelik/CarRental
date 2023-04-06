package ac.at.fhcampuswien.carrental.expections;

public class BookingFailedException extends Exception{
    public BookingFailedException(String message) {
        super(message);
    }
}
