package com.data.dataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructionResponseDto {
    private Long id;
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
