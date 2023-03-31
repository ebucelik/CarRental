package ac.at.fhcampuswien.carrental.entity.models;

import ac.at.fhcampuswien.carrental.entity.helper.Hashing;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.UUID;


@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String eMail;
    private String firstName;
    private String lastName;
    private byte[] password;
    private byte[] salt;
    //private UUID session;
    private String phoneNumber;
    private String dateOfBirth;
    private float customerChosenCurrency;

    public Customer(Long id, String eMail, String firstName, String lastName, String password, String phoneNumber, String dateOfBirth) {
        this.id = id;
        this.eMail = eMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        ArrayList<byte[]> list = Hashing.generateHash(password);
        this.salt = list.get(0);
    }

    public void setPassword(String password) {
        ArrayList<byte[]> list = Hashing.generateHash(password);
        this.salt = list.get(0);
        this.password = list.get(1);
    }
}
