package com.business.businessservice.service.impl;

import com.business.businessservice.api.ResultApi;
import com.business.businessservice.dto.InstructionResponseDto;
import com.business.businessservice.dto.TransactionResultDto;
import com.business.businessservice.dto.TransactionResultResponseWrapperDto;
import com.business.businessservice.dto.TransactionResultResponseDto;
import com.business.businessservice.dto.TransactionResultWrapper;
import com.business.businessservice.enums.Status;
import com.business.businessservice.exceptions.ValidationException;
import com.business.businessservice.service.TransactionResultService;
import com.business.businessservice.util.RetrofitUtil;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class TransactionResultServiceImpl implements TransactionResultService {
    private ResultApi resultApi;

    public TransactionResultServiceImpl() {
        this.resultApi = RetrofitUtil.retrofit("http://localhost:8081/").create(ResultApi.class);
    }

    @Override
    public void createTransactionResult(List<InstructionResponseDto> body) {
        TransactionResultResponseWrapperDto transactionResultResponseWrapperDto = new TransactionResultResponseWrapperDto();

        ArrayList<TransactionResultResponseDto> transactionResultResponseDtos = new ArrayList<>();

        for (InstructionResponseDto instructionResponseDto: body){
            TransactionResultResponseDto transactionResultResponseDto = new TransactionResultResponseDto();
            transactionResultResponseDto.setDateTime(LocalDateTime.now());
            transactionResultResponseDto.setSum(instructionResponseDto.getSum());
            transactionResultResponseDto.setStatus(Status.ACTIVE.name());
            transactionResultResponseDto.setInstructionId(instructionResponseDto.getId());

            transactionResultResponseDtos.add(transactionResultResponseDto);
        }
        transactionResultResponseWrapperDto.setTransactionResults(transactionResultResponseDtos);

        try {
            Call<Void> call = resultApi.createAll(transactionResultResponseWrapperDto);
            call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            resultApi.delete(id).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TransactionResultDto> getAllByPayer(String payerName) {
        if(payerName != null && !payerName.isBlank()){
            Call<TransactionResultWrapper> call = resultApi.getAllByPayer(payerName);
            try {
                Response<TransactionResultWrapper> response = call.execute();
                if(response.isSuccessful()){
                    return response.body().getTransactionResultDtos();
                }else {
                    return null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new ValidationException("Payers's full name cannot be empty");
        }
    }
}
