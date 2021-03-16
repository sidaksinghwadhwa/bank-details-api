package com.sidak.easy.task.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.sidak.easy.task.entity.BankDetail;
import com.sidak.easy.task.repo.explicit.ExplicitBankDetailRepo;

@Repository
public interface BankDetailRepo
        extends JpaRepositoryImplementation<BankDetail, Long>, ExplicitBankDetailRepo {
}