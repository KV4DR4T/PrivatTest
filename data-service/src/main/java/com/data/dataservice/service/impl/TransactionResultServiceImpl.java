package com.data.dataservice.service.impl;

import com.data.dataservice.dto.TransactionResultDto;
import com.data.dataservice.dto.TransactionResultListDto;
import com.data.dataservice.dto.TransactionResultResponseDto;
import com.data.dataservice.exceptions.ResultNotFoundException;
import com.data.dataservice.object.entity.Instruction;
import com.data.dataservice.object.entity.TransactionResult;
import com.data.dataservice.repository.InstructionRepository;
import com.data.dataservice.repository.TransactionResultRepository;
import com.data.dataservice.service.TransactionResultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionResultServiceImpl implements TransactionResultService {

    private final TransactionResultRepository transactionResultRepository;
    private final InstructionRepository instructionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createTransactionResult(TransactionResultDto transactionResultDto) {
        transactionResultRepository.save(modelMapper.map(transactionResultDto, TransactionResult.class));
    }

    @Override
    public TransactionResultResponseDto updateResult(TransactionResultDto transactionResultDto, Long resultId) {
        if (transactionResultRepository.existsById(resultId)) {
            TransactionResult transactionResult = modelMapper.map(transactionResultDto, TransactionResult.class);
            transactionResult.setId(resultId);
            return modelMapper.map(transactionResultRepository.save(transactionResult), TransactionResultResponseDto.class);
        } else {
            throw new ResultNotFoundException("Transaction result with id: " + resultId + " not found");
        }
    }

    @Override
    public void delete(Long resultId) {
        if (transactionResultRepository.existsById(resultId)) {
            transactionResultRepository.deleteById(resultId);
        } else {
            throw new ResultNotFoundException("Transaction result with id: " + resultId + " not found");
        }
    }

    @Override
    public void createAll(TransactionResultListDto transactionResultListDto) {
        List<TransactionResult> transactionResults = new LinkedList<>();

        for (TransactionResultResponseDto transactionResultResponseDto : transactionResultListDto.getTransactionResults()) {
            TransactionResult transactionResult = modelMapper.map(transactionResultResponseDto, TransactionResult.class);
            Instruction instruction = transactionResult.getInstruction();
            long minutesUntilNext = instruction.getMillisPeriod() / 60000;
            instruction.setDateTimeOfNextTransaction(transactionResult.getDateTime().plusMinutes(minutesUntilNext));
            instructionRepository.save(instruction);

            transactionResults.add(modelMapper.map(transactionResultResponseDto, TransactionResult.class));
        }


        transactionResultRepository.saveAll(transactionResults);
    }

    @Override
    public List<TransactionResultDto> getAllByPayerName(String name) {
        List<TransactionResult> transactionResults = transactionResultRepository.findAllByPayerName(name);
        List<TransactionResultDto> resultDtos = new LinkedList<>();

        for (TransactionResult transactionResult: transactionResults){
            resultDtos.add(modelMapper.map(transactionResult, TransactionResultDto.class));
        }

        return resultDtos;
    }

    @Override
    public TransactionResultResponseDto getResultById(Long resultId) {
        TransactionResult transactionResult = transactionResultRepository.findById(resultId).orElseThrow(() -> new ResultNotFoundException("TransactionResult with id: " + resultId + " not found"));
        return modelMapper.map(transactionResult, TransactionResultResponseDto.class);
    }

    @Override
    public List<TransactionResultResponseDto> getResultByInstructionId(Long instructionId) {
        List<TransactionResult> transactionResultList = transactionResultRepository.findAllByInstructionId(instructionId);
        if (transactionResultList.isEmpty()) {
            throw new ResultNotFoundException("No transaction results been found with instruction id: " + instructionId);
        }

        List<TransactionResultResponseDto> responseDtos = new LinkedList<>();

        for (TransactionResult tr : transactionResultList) {
            responseDtos.add(modelMapper.map(tr, TransactionResultResponseDto.class));
        }

        return responseDtos;
    }
}
