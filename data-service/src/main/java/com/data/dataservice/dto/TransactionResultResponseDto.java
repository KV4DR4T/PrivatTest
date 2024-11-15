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
public class TransactionResultResponseDto {

    private Long id;

    private LocalDateTime dateTime;

    private Integer sum;

    private String status;

    private Long instructionId;
}
