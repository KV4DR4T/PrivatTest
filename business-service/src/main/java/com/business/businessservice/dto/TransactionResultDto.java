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
public class TransactionResultDto implements Serializable {

    private LocalDateTime dateTime;

    private Integer sum;

    private Integer status;

    private Long instructionId;
}
