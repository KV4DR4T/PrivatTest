package com.data.dataservice.repository;

import com.data.dataservice.object.entity.TransactionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionResultRepository extends JpaRepository<TransactionResult, Long> {

    @Query(value = "SELECT r FROM TransactionResult r WHERE r.instruction.id=:id")
    List<TransactionResult> findAllByInstructionId(@Param("id") Long instructionId);

    @Query(value = "SELECT r FROM TransactionResult r JOIN Instruction i ON r.instruction.id=i.id WHERE i.fullName=:name")
    List<TransactionResult> findAllByPayerName(@Param("name") String name);
}
