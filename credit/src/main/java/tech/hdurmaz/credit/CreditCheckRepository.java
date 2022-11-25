package tech.hdurmaz.credit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCheckRepository extends JpaRepository<CreditCheckHistory, Integer> {

  List<CreditCheckHistory> findAllByCustomerId(String identityNumber);
}
