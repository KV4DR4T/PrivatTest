package com.business.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResultResponseDto implements Serializable {

    private Long id;

    private LocalDateTime dateTime;

    private Integer sum;

    private String status;

    private Long instructionId;
}
