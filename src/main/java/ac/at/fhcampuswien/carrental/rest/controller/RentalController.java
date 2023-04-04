package ac.at.fhcampuswien.carrental.rest.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/bookings")
@Tag(name = "Booking", description = "Endpoints for managing bookings.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RentalController {

/*    @GetMapping
    public ResponseEntity<Void> getMyBookings()
    {return null;}
    @GetMapping("/{bookingid}")
    public ResponseEntity<Void> getMyBookingById()
    {return null;}
    @PostMapping
    public ResponseEntity<Void> createBooking()
    {return null;}

    @PutMapping("/{bookingid}")
    public ResponseEntity<Void> updateBooking()
    {return null;}

    @PutMapping("/{bookingid}")
    public ResponseEntity<Void> removeBooking()
    {return null;}
    */

}
