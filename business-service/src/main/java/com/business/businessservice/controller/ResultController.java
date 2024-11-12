package com.business.businessservice.controller;

import com.business.businessservice.dto.TransactionResultDto;
import com.business.businessservice.service.TransactionResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final TransactionResultService transactionResultService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transactionResultService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{payerName}")
    public ResponseEntity<List<TransactionResultDto>> getAllByPayer(@PathVariable String payerName){
        return ResponseEntity.ok(transactionResultService.getAllByPayer(payerName));
    }
}
