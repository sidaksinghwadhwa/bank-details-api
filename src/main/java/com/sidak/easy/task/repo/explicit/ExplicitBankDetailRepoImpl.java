package com.sidak.easy.task.repo.explicit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sidak.easy.task.dto.BankDetailData;
import com.sidak.easy.task.entity.BankDetail;
import com.sidak.easy.task.entity.BankDetail_;

public class ExplicitBankDetailRepoImpl implements ExplicitBankDetailRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<BankDetailData> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankDetailData> criteria = builder.createQuery(BankDetailData.class);
        Root<BankDetail> from = criteria.from(BankDetail.class);

        criteria.multiselect(from.get(BankDetail_.id).alias(BankDetailData.Fields.id),
                from.get(BankDetail_.bankName).alias(BankDetailData.Fields.bankName),
                from.get(BankDetail_.cardNumber).alias(BankDetailData.Fields.cardNumber),
                from.get(BankDetail_.expiryDate).alias(BankDetailData.Fields.expiryDate));

        return entityManager
                .createQuery(criteria.orderBy(builder.desc(from.get(BankDetail_.expiryDate))))
                .getResultList();
    }

}
