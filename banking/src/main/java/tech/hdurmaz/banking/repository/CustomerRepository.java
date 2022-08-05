package tech.hdurmaz.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.hdurmaz.banking.models.Customer;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByIdentityNumber(String identityNumber);
    Customer findByIdentityNumber(String identityNumber);

}
