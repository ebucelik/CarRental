package ac.at.fhcampuswien.carrental.expections;

import java.sql.SQLException;

public class RentalUpdateException extends SQLException{
    public RentalUpdateException(String message) {
        super(message);
    }
}
