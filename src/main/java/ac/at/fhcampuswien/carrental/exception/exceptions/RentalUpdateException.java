package ac.at.fhcampuswien.carrental.exception.exceptions;

import java.sql.SQLException;

public class RentalUpdateException extends SQLException {
    public RentalUpdateException(String message) {
        super(message);
    }
}
