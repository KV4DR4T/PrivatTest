package com.business.businessservice.service;

import com.business.businessservice.dto.InstructionResponseDto;
import com.business.businessservice.dto.TransactionResultDto;

import java.util.List;

public interface TransactionResultService {
    void createTransactionResult(List<InstructionResponseDto> body);

    void delete(Long id);
    List<TransactionResultDto> getAllByPayer(String payerName);
}
