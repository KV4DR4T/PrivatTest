package com.data.dataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructionResponseWrapper {

    private List<InstructionResponseDto> instructionDtos;
}
