package com.data.dataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResultDto {

    private LocalDateTime dateTime;

    private Integer sum;

    private Integer status;

    private Long instructionId;
}
