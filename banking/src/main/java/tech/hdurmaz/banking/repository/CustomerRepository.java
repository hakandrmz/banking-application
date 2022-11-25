package tech.hdurmaz.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.hdurmaz.banking.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByIdentityNumber(String identityNumber);

    Customer findByIdentityNumber(String identityNumber);

    Customer getCustomerByIdentityNumber(String identityNumber);
}
