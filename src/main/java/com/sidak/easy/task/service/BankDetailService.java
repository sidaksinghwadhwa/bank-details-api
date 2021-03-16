package com.sidak.easy.task.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sidak.easy.task.dto.BankDetailData;
import com.sidak.easy.task.entity.BankDetail;

public interface BankDetailService {

    @Transactional
    BankDetail persistBankDetail(BankDetailData bankDetailData);

    @Transactional(readOnly = true)
    List<BankDetailData> getAllBankDetails();

    Map<String, String> uploadBankDetails(MultipartFile file);
}
