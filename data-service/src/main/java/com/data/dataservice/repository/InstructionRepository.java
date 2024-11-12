package com.data.dataservice.repository;


import com.data.dataservice.object.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {

    Optional<Instruction> findByTin(String tin);

    Optional<Instruction> findByOkpo(String instructionOkpo);

    @Query("SELECT i FROM Instruction i WHERE i.dateTimeOfNextTransaction < :time")
    List<Instruction> getAllByPaymentTime(@Param("time") LocalDateTime specifiedTime);

    List<Instruction> findAllByFullName(String name);

    List<Instruction> findAllByReceiverFullName(String name);
}
