package com.sidak.easy.task.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sidak.easy.task.dto.BankDetailData;
import com.sidak.easy.task.entity.BankDetail;
import com.sidak.easy.task.repo.BankDetailRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BankDetailServiceImpl implements BankDetailService {

    @Autowired
    private BankDetailRepo bankDetailRepo;

    private static Map<String, String> uploaded;

    static {
        uploaded = new HashMap<>();
        uploaded.put("SUCCESS", "Uploaded Successfuly");

    }

    @Override
    public BankDetail persistBankDetail(BankDetailData bankDetailData) {

        BankDetail bankDetail = new BankDetail();
        bankDetailRepo.save(mergeForCreate(bankDetail, bankDetailData));
        return bankDetail;
    }

    private BankDetail mergeForCreate(BankDetail bankDetail, BankDetailData bankDetailData) {

        bankDetail.setBankName(bankDetailData.getBankName());
        bankDetail.setCardNumber(bankDetailData.getCardNumber());
        bankDetail.setExpiryDate(bankDetailData.getExpiryDate());

        return bankDetail;
    }

    @Override
    public List<BankDetailData> getAllBankDetails() {

        return bankDetailRepo.getAll();

    }

    @Override
    public Map<String, String> uploadBankDetails(MultipartFile file) {

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<BankDetailData> bankDetailsCsv = new CsvToBeanBuilder<BankDetailData>(reader)
                    .withType(BankDetailData.class).withIgnoreLeadingWhiteSpace(true).build();

            List<BankDetailData> bankDetails = bankDetailsCsv.parse();

            for (BankDetailData bankDetailData : bankDetails) {
                persistBankDetail(bankDetailData);
            }

        } catch (Exception ex) {
            log.error("Error Uploading Data");
        }

        return uploaded;
    }

}
