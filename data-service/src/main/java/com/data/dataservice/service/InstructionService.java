package com.data.dataservice.service;

import com.data.dataservice.dto.InstructionDto;
import com.data.dataservice.dto.InstructionResponseDto;
import com.data.dataservice.dto.InstructionResponseWrapper;
import com.data.dataservice.dto.InstructionWrapper;

import java.util.List;


public interface InstructionService {

    void createInstruction(InstructionDto instructionDto);

    InstructionResponseDto updateInstruction(Long instructionId, InstructionDto instructionDto);

    void deleteInstruction(Long instructionId);

    InstructionDto getInstructionById(Long instructionId);

    InstructionDto getInstructionByTin(String instructionTin);

    InstructionDto getInstructionByOkpo(String instructionOkpo);

    InstructionResponseWrapper getAllByDateTime();

    InstructionWrapper geAllByPayer(String name);

    InstructionWrapper getInstructionByReceiver(String name);
}
