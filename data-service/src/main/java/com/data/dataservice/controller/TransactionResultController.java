package com.data.dataservice.controller;

import com.data.dataservice.dto.TransactionResultDto;
import com.data.dataservice.dto.TransactionResultListDto;
import com.data.dataservice.dto.TransactionResultResponseDto;
import com.data.dataservice.service.TransactionResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class TransactionResultController {

    private final TransactionResultService transactionResultService;

    @PostMapping
    public ResponseEntity<Void> createResult(@RequestBody TransactionResultDto transactionResultDto){
        transactionResultService.createTransactionResult(transactionResultDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{resultId}")
    public ResponseEntity<TransactionResultResponseDto> updateResult(@RequestBody TransactionResultDto transactionResultDto, @PathVariable Long resultId){
        return  ResponseEntity.ok(transactionResultService.updateResult(transactionResultDto, resultId));
    }

    @DeleteMapping("/{resultId}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long resultId){
        transactionResultService.delete(resultId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<TransactionResultResponseDto> getResultById(@PathVariable Long resultId){
        return  ResponseEntity.ok(transactionResultService.getResultById(resultId));
    }

    @GetMapping("/{instructionId}")
    public ResponseEntity<List<TransactionResultResponseDto>> getResultByInstructionId(@PathVariable Long instructionId){
        return  ResponseEntity.ok(transactionResultService.getResultByInstructionId(instructionId));
    }

    @GetMapping("/payer/{name}")
    public ResponseEntity<List<TransactionResultDto>> getAllByPayerName(@PathVariable String name){
        return  ResponseEntity.ok(transactionResultService.getAllByPayerName(name));
    }

    @PostMapping("/all")
    public ResponseEntity<Void> createAll(@RequestBody TransactionResultListDto transactionResultListDto){
        transactionResultService.createAll(transactionResultListDto);
        return ResponseEntity.ok().build();
    }

}
