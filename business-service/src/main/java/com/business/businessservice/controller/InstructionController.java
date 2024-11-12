package com.business.businessservice.controller;

import com.business.businessservice.dto.InstructionDto;
import com.business.businessservice.dto.InstructionResponseDto;
import com.business.businessservice.service.InstructionService;
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
public class InstructionController {

    private final InstructionService instructionService;

    @PostMapping
    public ResponseEntity<Void> createInstruction(@RequestBody InstructionDto instructionDto){
        instructionService.create(instructionDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{instructionId}")
    public ResponseEntity<InstructionResponseDto> updateInstruction(@RequestBody InstructionDto instructionDto, @PathVariable Long instructionId){
        return ResponseEntity.ok(instructionService.update(instructionDto, instructionId));
    }

    @DeleteMapping("/{instructionId}")
    public ResponseEntity<Void> deleteInstruction(@PathVariable Long instructionId){
        instructionService.delete(instructionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{instructionId}")
    public ResponseEntity<InstructionDto> getInstructionById(@PathVariable Long instructionId){
        return ResponseEntity.ok(instructionService.getById(instructionId));
    }

    @GetMapping("/{instructionTin}")
    public ResponseEntity<InstructionDto> getInstructionByTin(@PathVariable String instructionTin){
        return ResponseEntity.ok(instructionService.getByTin(instructionTin));
    }

    @GetMapping("/{instructionOkpo}")
    public ResponseEntity<InstructionDto> getInstructionByOkpo(@PathVariable String instructionOkpo){
        return ResponseEntity.ok(instructionService.getByOkpo(instructionOkpo));
    }

    @GetMapping("/payer/{name}")
    public ResponseEntity<List<InstructionDto>> getInstructionByPayer(@PathVariable String name){
        return ResponseEntity.ok(instructionService.geAllByPayer(name));
    }

    @GetMapping("/receiver/{name}")
    public ResponseEntity<List<InstructionDto>> getInstructionByReceiver(@PathVariable String name){
        return ResponseEntity.ok(instructionService.getInstructionByReceiver(name));
    }
}
