package com.sidak.easy.task.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sidak.easy.task.dto.BankDetailData;
import com.sidak.easy.task.service.BankDetailService;

@CrossOrigin(origins = "*")
@RestController
public class BankDetailController {

    @Autowired
    private BankDetailService bankDetailService;

    @PostMapping("/save")
    public ResponseEntity<BankDetailData> saveDetails(
            @Valid @RequestBody BankDetailData bankDetailData) {

        return ResponseEntity
                .ok(BankDetailData.toData(bankDetailService.persistBankDetail(bankDetailData)));
    }

    @GetMapping("/")
    public ResponseEntity<List<BankDetailData>> getAll() {

        return ResponseEntity.ok(bankDetailService.getAllBankDetails());
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadBankDetails(
            @RequestParam("file") MultipartFile file, Model model) {

        return ResponseEntity.ok(bankDetailService.uploadBankDetails(file));

    }

}