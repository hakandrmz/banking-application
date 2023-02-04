package tech.hdurmaz.credit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCheckRepository extends JpaRepository<CreditCheckHistory, Integer> {

    List<CreditCheckHistory> findAllByCustomerId(String identityNumber);
}
