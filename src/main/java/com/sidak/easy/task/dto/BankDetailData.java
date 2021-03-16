package com.sidak.easy.task.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.sidak.easy.task.entity.BankDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor
@ToString
public class BankDetailData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @CsvBindByName
    @NotBlank
    private String bankName;

    @CsvBindByName
    @NotBlank
    @CreditCardNumber
    private String cardNumber;

    @CsvDate(value = "MM-yyyy", writeChronologyEqualsReadChronology = true)
    @CsvBindByName
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy", timezone = "Asia/Kolkata")
    private Date expiryDate;

    public static BankDetailData toData(BankDetail bankDetail) {

        BankDetailData bankDetailData = new BankDetailData();
        bankDetailData.setId(bankDetail.getId());
        bankDetailData.setBankName(bankDetail.getBankName());
        bankDetailData.setCardNumber(bankDetail.getCardNumber());
        bankDetailData.setExpiryDate(bankDetail.getExpiryDate());

        return bankDetailData;
    }

    public BankDetailData(Integer id, String bankName, String cardNumber, Date expiryDate) {

        this.id = id;
        this.bankName = bankName;
        this.cardNumber = cardNumber.substring(0, 4) + "************";
        this.expiryDate = expiryDate;
    }
}
