package tech.hdurmaz.credit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCheckRepository extends JpaRepository<CreditCheckHistory,Integer> {
}
