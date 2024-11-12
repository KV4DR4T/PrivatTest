package com.business.businessservice.service;

import com.business.businessservice.dto.InstructionDto;
import com.business.businessservice.dto.InstructionResponseDto;

import java.util.List;

public interface InstructionService {
    void create(InstructionDto instructionDto);

    InstructionResponseDto update(InstructionDto instructionDto, Long instructionId);

    void delete(Long instructionId);

    InstructionDto getById(Long instructionId);

    InstructionDto getByTin(String instructionTin);

    InstructionDto getByOkpo(String instructionOkpo);

    List<InstructionDto> geAllByPayer(String name);

    List<InstructionDto> getInstructionByReceiver(String name);
}
