package tech.hdurmaz.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;

import java.util.List;

public interface CreditCheckRepository extends JpaRepository<CreditCheckHistory,Integer> {
    List<CreditCheckHistory> findAllByCustomerId(String identityNumber);
}
