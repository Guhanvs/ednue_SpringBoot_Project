package com.example.Project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private String transactionID;
    private String date;
    private String category;
    private double amount;

}