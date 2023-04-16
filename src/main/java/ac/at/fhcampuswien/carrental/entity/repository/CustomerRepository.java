package ac.at.fhcampuswien.carrental.entity.repository;

import ac.at.fhcampuswien.carrental.entity.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
/*
    Customer findByeMail(String eMail);

    boolean existsByeMail(String eMail);*/

    boolean existsByeMail(String email);

    Customer findByeMail(String email);

    @Query(value = "SELECT c.id FROM customers c WHERE c.e_mail = :email", nativeQuery = true)
    Long findIdByeMailIgnoreCase(@Param("email") String email);

}


