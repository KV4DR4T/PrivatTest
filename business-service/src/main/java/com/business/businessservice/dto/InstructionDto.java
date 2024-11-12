package com.business.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructionDto implements Serializable {

    private String fullName;

    private String tin;

    private String cardNumber;

    private String iban;

    private String bankCode;

    private String okpo;

    private String receiverFullName;

    private Long millisPeriod;

    private Integer sum;
}
