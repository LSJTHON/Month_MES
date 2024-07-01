package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.InOutBound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InOutBoundRepository extends JpaRepository<InOutBound, Long> {

    List<InOutBound> findByTransactionType(InOutBound.TransactionType transactionType);
}
