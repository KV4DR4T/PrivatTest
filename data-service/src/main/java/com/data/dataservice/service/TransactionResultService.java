package com.data.dataservice.service;

import com.data.dataservice.dto.TransactionResultDto;
import com.data.dataservice.dto.TransactionResultListDto;
import com.data.dataservice.dto.TransactionResultResponseDto;

import java.util.List;


public interface TransactionResultService {
    void createTransactionResult(TransactionResultDto transactionResultDto);

    TransactionResultResponseDto updateResult(TransactionResultDto transactionResultDto, Long resultId);

    TransactionResultResponseDto getResultById(Long resultId);

    List<TransactionResultResponseDto> getResultByInstructionId(Long instructionId);

    void delete(Long resultId);

    void createAll(TransactionResultListDto transactionResultListDto);

    List<TransactionResultDto> getAllByPayerName(String name);
}
