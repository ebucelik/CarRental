package ac.at.fhcampuswien.carrental.expections;

public class CurrencyServiceNotAvailableException extends Exception {
    public CurrencyServiceNotAvailableException(String message) {
        super(message);
    }
}
