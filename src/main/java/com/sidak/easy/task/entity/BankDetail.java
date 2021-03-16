package com.sidak.easy.task.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
public class BankDetail {

    @Id
    @GeneratedValue
    private Integer id;

    private String bankName;

    private String cardNumber;

    private Date expiryDate;

}