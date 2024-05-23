package com.simplilease.server.Services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilease.server.Entities.Expense;
import com.simplilease.server.Repositories.ExpenseRepository;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository exRepo;

    
    /**
     * Persist a new expense
     * @param ex
     * @return
     * @throws SQLException
     */
    public Expense createExpense(Expense ex) throws SQLException{

        return exRepo.save(ex);

    }


    /**
     * Fetch persisted expenses for a property
     * @param pId
     * @return
     * @throws SQLException
     */
    public List<Expense> fetchExpensesForProperty(long pId) throws SQLException {

        List<Expense> exList = exRepo.findByPropertyId(pId);
        return exList;

    }


    /**
     * Fetch persisted expense by expense ID
     * @param pId
     * @param exId
     * @return
     * @throws SQLException
     */
    public Expense fetchExpenseById(long pId, long exId) throws SQLException {
        
        Expense ex = exRepo.findByPropertyIdAndExpenseId(pId, exId).get();
        return ex;

    }

    
    /**
     * Fetch persisted expenses for property by expense type
     * @param pId
     * @param exType
     * @return
     * @throws SQLException
     */
    public List<Expense> fetchExpenseByType(long pId, String exType) throws SQLException {

        List<Expense> exList = exRepo.findByPropertyIdAndExpenseType(pId, exType);
        return exList;

    }


    /**
     * fetch persisted expenses for date
     * @param pId
     * @param d
     * @return
     * @throws SQLException
     */
    public List<Expense> fetchExpenseByDate(long pId, Date d) throws SQLException {
        
        List<Expense> exList = exRepo.findByPropertyIdAndDate(pId, d);
        return exList;

    }


    /**
     * Fetch persisted expenses between dates
     * @param pId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    public List<Expense> fetchExpensesByDateRange(
        long pId,
        Date startDate,
        Date endDate
    ) throws SQLException {

        List<Expense> exList = exRepo.findByPropertyIdAndDateBetween(pId, startDate, endDate);
        return exList;

    }


    /**
     * Update expense description
     * @param exId
     * @param desc
     * @return
     * @throws SQLException
     */
    public Expense updateExpenseDescription(
        long exId,
        String desc
    ) throws SQLException {

        // fetch the expense
        Expense ex = exRepo.findById(exId).get();
        ex.setDescription(desc);
        
        // persist and return
        return exRepo.save(ex);

    }


    public void deleteExpenseByExId(long exId) throws SQLException {
        exRepo.deleteById(exId);
    }

}
