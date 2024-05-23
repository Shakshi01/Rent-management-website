package com.simplilease.server.Controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilease.server.Entities.Expense;
import com.simplilease.server.Services.ExpenseService;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/server/expenseController")
public class ExpenseController {
    
    @Autowired
    private ExpenseService exService;


    @PostMapping(
        path = "/createExpense",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createExpense(@RequestBody Expense ex) throws SQLException {

        // init response
        //ResponseEntity<?> response;

        Expense newEx = exService.createExpense(ex);
        return new ResponseEntity<Expense>(newEx, HttpStatus.OK);

    }


    /**
     * Fetch persisted expenses for property
     * @param pId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchExpensesByProperty",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Expense>> fetchExpensesByProperty(@RequestParam long pId) throws SQLException {

        List<Expense> exList = exService.fetchExpensesForProperty(pId);
        return new ResponseEntity<List<Expense>>(exList, HttpStatus.OK);

    }


    /**
     * Fetch persisted expenses for property by type
     * @param pId
     * @param type
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchExpensesByType",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Expense>> fetchExpensesByType(
        @RequestParam long pId,
        @RequestParam String type
    ) throws SQLException {

        List<Expense> exList = exService.fetchExpenseByType(pId, type);
        return new ResponseEntity<List<Expense>>(exList, HttpStatus.OK);

    }


    /**
     * Fetch persisted expenses for date
     * @param pId
     * @param date
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchExpensesForDate",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Expense>> fetchExpensesForDate(
        @RequestParam long pId,
        @RequestParam String date
    ) throws SQLException {

        // convert string to java.sql.date
        Date sqlDate = Date.valueOf(date);

        List<Expense> exList = exService.fetchExpenseByDate(pId, sqlDate);
        return new ResponseEntity<List<Expense>>(exList, HttpStatus.OK);
    }


    /**
     * Fetch persisted expenses for property between dates
     * @param pId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchExpensesForDateRange",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Expense>> fetchExpensesForDateRange(
        @RequestParam long pId,
        @RequestParam String startDate,
        @RequestParam String endDate
    ) throws SQLException {
     
        // Convert string to java.sql.date
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);

        List<Expense> exList = exService.fetchExpensesByDateRange(pId, sqlStartDate, sqlEndDate);
        return new ResponseEntity<List<Expense>>(exList, HttpStatus.OK);
        
    }


    /**
     * Update persisted expense's description
     * @param pId
     * @param expenseId
     * @param desc
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updateExpenseDescription",
        consumes = MediaType.TEXT_PLAIN_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateExpenseDescription(
        @RequestParam long pId,
        @RequestParam long expenseId,
        @RequestBody String desc
    ) throws SQLException {

        Expense ex = exService.fetchExpenseById(pId, expenseId);
        ex.setDescription(desc);
        return new ResponseEntity<Expense>(ex, HttpStatus.OK);

    }


    /**
     * Delete expense by expense Id
     * @param exId
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/deleteExpenseByExId")
    public ResponseEntity<HttpStatus> deleteExpenseByExId(@RequestParam long exId) throws SQLException {

        exService.deleteExpenseByExId(exId);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
