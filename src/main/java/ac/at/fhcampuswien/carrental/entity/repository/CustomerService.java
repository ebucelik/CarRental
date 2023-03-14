package ac.at.fhcampuswien.carrental.entity.repository;

import ac.at.fhcampuswien.carrental.rest.models.Customer;

public interface CustomerService {
    Customer register(Customer customer);

    Customer login(String email,
                   String password);
}


