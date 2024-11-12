package com.data.dataservice.service;

import com.data.dataservice.dto.InstructionDto;
import com.data.dataservice.dto.InstructionResponseDto;

import java.util.List;


public interface InstructionService {

    void createInstruction(InstructionDto instructionDto);

    InstructionResponseDto updateInstruction(Long instructionId, InstructionDto instructionDto);

    void deleteInstruction(Long instructionId);

    InstructionDto getInstructionById(Long instructionId);

    InstructionDto getInstructionByTin(String instructionTin);

    InstructionDto getInstructionByOkpo(String instructionOkpo);

    List<InstructionResponseDto> getAllByDateTime();

    List<InstructionDto> geAllByPayer(String name);

    List<InstructionDto> getInstructionByReceiver(String name);
}
