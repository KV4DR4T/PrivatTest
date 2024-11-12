package com.data.dataservice.object.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name="instruction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String tin;

    private String cardNumber;

    private String iban;

    private String bankCode;

    private String okpo;

    @Column(name="receiver_name")
    private String receiverFullName;

    private Long millisPeriod;

    private Integer sum;

    @Column(name = "next_time")
    private LocalDateTime dateTimeOfNextTransaction;
}
