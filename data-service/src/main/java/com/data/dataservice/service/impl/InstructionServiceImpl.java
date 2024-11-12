package com.data.dataservice.service.impl;

import com.data.dataservice.dto.InstructionDto;
import com.data.dataservice.dto.InstructionResponseDto;
import com.data.dataservice.dto.InstructionResponseWrapper;
import com.data.dataservice.dto.InstructionWrapper;
import com.data.dataservice.exceptions.InstructionNotFoundException;
import com.data.dataservice.object.entity.Instruction;
import com.data.dataservice.repository.InstructionRepository;
import com.data.dataservice.service.InstructionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InstructionServiceImpl implements InstructionService {

    private final InstructionRepository instructionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createInstruction(InstructionDto instructionDto) {
        instructionRepository.save(modelMapper.map(instructionDto, Instruction.class));
    }

    @Override
    public InstructionResponseDto updateInstruction(Long instructionId, InstructionDto instructionDto) {
        if(instructionRepository.existsById(instructionId)) {
            Instruction instruction = modelMapper.map(instructionDto, Instruction.class);
            instruction.setId(instructionId);
            return modelMapper.map(instructionRepository.save(instruction), InstructionResponseDto.class);
        }else {
            throw new InstructionNotFoundException("Instruction with id: " + instructionId + " not found");
        }
    }

    @Override
    public void deleteInstruction(Long instructionId) {
        instructionRepository.deleteById(instructionId);
    }

    @Override
    public InstructionDto getInstructionById(Long instructionId) {
        Instruction instruction = instructionRepository.findById(instructionId).orElseThrow(() -> new InstructionNotFoundException("Instruction with id: " + instructionId + " not found"));
        return modelMapper.map(instruction, InstructionDto.class);
    }

    @Override
    public InstructionDto getInstructionByTin(String instructionTin) {
        Instruction instruction = instructionRepository.findByTin(instructionTin).orElseThrow(() -> new InstructionNotFoundException("Instruction with TIN: " + instructionTin + " not found"));
        return modelMapper.map(instruction, InstructionDto.class);
    }

    @Override
    public InstructionDto getInstructionByOkpo(String instructionOkpo) {
        Instruction instruction = instructionRepository.findByOkpo(instructionOkpo).orElseThrow(() -> new InstructionNotFoundException("Instruction with OKPO: " + instructionOkpo + " not found"));
        return modelMapper.map(instruction, InstructionDto.class);
    }

    @Override
    public InstructionResponseWrapper getAllByDateTime() {
        List<Instruction> instructions = instructionRepository.getAllByPaymentTime(LocalDateTime.now());
        List<InstructionResponseDto> responseDtos = new LinkedList<>();

        for(Instruction instruction: instructions){
            responseDtos.add(modelMapper.map(instruction, InstructionResponseDto.class));
        }
        return new InstructionResponseWrapper(responseDtos);
    }

    @Override
    public InstructionWrapper geAllByPayer(String name) {
        List<Instruction> instructions = instructionRepository.findAllByFullName(name);
        List<InstructionDto> responseDtos = new LinkedList<>();

        for(Instruction instruction: instructions){
            responseDtos.add(modelMapper.map(instruction, InstructionDto.class));
        }
        return new InstructionWrapper(responseDtos);
    }

    @Override
    public InstructionWrapper getInstructionByReceiver(String name) {
        List<Instruction> instructions = instructionRepository.findAllByReceiverFullName(name);
        List<InstructionDto> responseDtos = new LinkedList<>();

        for(Instruction instruction: instructions){
            responseDtos.add(modelMapper.map(instruction, InstructionDto.class));
        }
        return new InstructionWrapper(responseDtos);
    }

}
