package com.data.dataservice.controller;

import com.data.dataservice.dto.InstructionDto;
import com.data.dataservice.dto.InstructionResponseDto;
import com.data.dataservice.dto.InstructionResponseWrapper;
import com.data.dataservice.dto.InstructionWrapper;
import com.data.dataservice.service.InstructionService;
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
@RequestMapping("/instructions")
@RequiredArgsConstructor
public class InstructionsController {

    private final InstructionService instructionService;

    @PostMapping
    public ResponseEntity<Void> createInstruction(@RequestBody InstructionDto instructionDto){
        instructionService.createInstruction(instructionDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{instructionId}")
    public ResponseEntity<InstructionResponseDto> updateInstruction(@RequestBody InstructionDto instructionDto, @PathVariable Long instructionId){
        return ResponseEntity.ok(instructionService.updateInstruction(instructionId, instructionDto));
    }

    @DeleteMapping("/{instructionId}")
    public ResponseEntity<Void> deleteInstruction(@PathVariable Long instructionId){
        instructionService.deleteInstruction(instructionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{instructionId}")
    public ResponseEntity<InstructionDto> getInstructionById(@PathVariable Long instructionId){
        return ResponseEntity.ok(instructionService.getInstructionById(instructionId));
    }

    @GetMapping("/payer/{name}")
    public ResponseEntity<InstructionWrapper> getInstructionByPayer(@PathVariable String name){
        return ResponseEntity.ok(instructionService.geAllByPayer(name));
    }

    @GetMapping("/receiver/{name}")
    public ResponseEntity<InstructionWrapper> getInstructionByReceiver(@PathVariable String name){
        return ResponseEntity.ok(instructionService.getInstructionByReceiver(name));
    }

    @GetMapping("/{instructionTin}")
    public ResponseEntity<InstructionDto> getInstructionByTin(@PathVariable String instructionTin){
        return ResponseEntity.ok(instructionService.getInstructionByTin(instructionTin));
    }

    @GetMapping("/{instructionOkpo}")
    public ResponseEntity<InstructionDto> getInstructionByOkpo(@PathVariable String instructionOkpo){
        return ResponseEntity.ok(instructionService.getInstructionByOkpo(instructionOkpo));
    }

    @GetMapping("/time")
    public ResponseEntity<InstructionResponseWrapper> getAllByPaymentTime(){
        return ResponseEntity.ok(instructionService.getAllByDateTime());
    }
}
