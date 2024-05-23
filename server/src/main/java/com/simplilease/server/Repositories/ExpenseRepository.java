package com.simplilease.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilease.server.Entities.Expense;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    public List<Expense> findByPropertyId(long propertyId);

    public Optional<Expense> findByPropertyIdAndExpenseId(long propertyId, long expenseId);
    
    public List<Expense> findByPropertyIdAndExpenseType(long pId, String type);

    public List<Expense> findByPropertyIdAndDate(long pId, Date d);

    public List<Expense> findByPropertyIdAndDateBetween(long propertyId, Date startDate, Date endDate);

}
