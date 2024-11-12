package com.business.businessservice.service.impl;

import com.business.businessservice.api.InstructionApi;
import com.business.businessservice.dto.InstructionDto;
import com.business.businessservice.dto.InstructionResponseDto;
import com.business.businessservice.exceptions.ValidationException;
import com.business.businessservice.service.InstructionService;
import com.business.businessservice.service.TransactionResultService;
import com.business.businessservice.util.RetrofitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class InstructionServiceImpl implements InstructionService {


    private InstructionApi instructionApi;
    private final TransactionResultService transactionResultService;

    public InstructionServiceImpl(TransactionResultService transactionResultService) {
        this.transactionResultService = transactionResultService;
        this.instructionApi = RetrofitUtil.retrofit("http://localhost:8081/").create(InstructionApi.class);
    }


    @Override
    public void create(InstructionDto instructionDto) {
        try {
            validateDto(instructionDto);
            instructionApi.createInstruction(instructionDto).execute();
        } catch (ValidationException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstructionResponseDto update(InstructionDto instructionDto, Long instructionId) {
        try {
            validateDto(instructionDto);
            Call<InstructionResponseDto> call = instructionApi.updateInstruction(instructionDto, instructionId);
            Response<InstructionResponseDto> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long instructionId) {
        try {
            instructionApi.deleteInstruction(instructionId).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstructionDto getById(Long instructionId) {
        try {
            Call<InstructionDto> call = instructionApi.getInstructionById(instructionId);
            Response<InstructionDto> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstructionDto getByTin(String instructionTin) {
        try {
            if(instructionTin != null && instructionTin.matches("\\d{10}")) {
                Call<InstructionDto> call = instructionApi.getInstructionByTin(instructionTin);
                Response<InstructionDto> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    return null;
                }
            }else {
                throw new ValidationException("TIN must be 10 digits");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstructionDto getByOkpo(String instructionOkpo) {
        try {
            Call<InstructionDto> call = instructionApi.getInstructionByOkpo(instructionOkpo);
            Response<InstructionDto> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<InstructionDto> geAllByPayer(String name) {
        try {
            Call<List<InstructionDto>> call = instructionApi.getInstructionByPayer(name);
            Response<List<InstructionDto>> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<InstructionDto> getInstructionByReceiver(String name) {
        try {
            Call<List<InstructionDto>> call = instructionApi.getInstructionByReceiver(name);
            Response<List<InstructionDto>> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Scheduled(cron = "0 * * * * *")
    private void createResult(){
        try {
            Call<List<InstructionResponseDto>> call = instructionApi.getAllByPaymentTime();
            Response<List<InstructionResponseDto>> response = call.execute();
            if(response.isSuccessful()) {
                transactionResultService.createTransactionResult(response.body());
            }
            log.info("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void validateDto(InstructionDto instructionDto) {
        if (instructionDto.getFullName() == null || instructionDto.getFullName().isBlank()) {
            throw new ValidationException("Full name can not be empty");
        }

        if (instructionDto.getTin() == null || !instructionDto.getTin().matches("\\d{10}")) {
            throw new ValidationException("TIN must be 10 digits");
        }

        if (instructionDto.getCardNumber() == null || !instructionDto.getCardNumber().matches("\\d{16}")) {
            throw new ValidationException("Card number must be 16 digits");
        }

        if (instructionDto.getIban() == null || !instructionDto.getIban().matches("^[A-Z]{2}\\d{22}$")) {
            throw new ValidationException("IBAN must be in the correct format");
        }

        if (instructionDto.getBankCode() == null || !instructionDto.getBankCode().matches("\\d{6}")) {
            throw new ValidationException("Bank code must be 6 digits");
        }

        if (instructionDto.getOkpo() == null || !instructionDto.getOkpo().matches("\\d{8}")) {
            throw new ValidationException("OKPO must be 8 digits");
        }

        if (instructionDto.getReceiverFullName() == null || instructionDto.getReceiverFullName().isBlank()) {
            throw new ValidationException("Receiver's full name cannot be empty");
        }

        if (instructionDto.getMillisPeriod() == null || instructionDto.getMillisPeriod() <= 0) {
            throw new ValidationException("Period must be present");
        }

        if (instructionDto.getSum() == null || instructionDto.getSum() <= 0) {
            throw new ValidationException("Sum must be a positive number");
        }
    }
}
