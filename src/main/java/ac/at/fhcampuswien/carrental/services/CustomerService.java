package ac.at.fhcampuswien.carrental.services;

import ac.at.fhcampuswien.carrental.models.Customer;

public interface CustomerService {
    Customer register(Customer customer);

    Customer login(String email,
                   String password);
}


