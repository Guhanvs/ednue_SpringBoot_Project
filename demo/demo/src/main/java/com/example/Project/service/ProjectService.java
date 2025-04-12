package com.example.Project.service;

import com.example.Project.model.ExpenseDTO;
import com.example.Project.model.ExpenseStatusDTO;
import com.example.Project.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

        @Autowired
        private ProjectRepo repository;

        @Autowired
        private ExpenseStatusDTO expenseStatusDTO;

    public ProjectService(ProjectRepo repository) {
        this.repository = repository;
    }

    public ExpenseStatusDTO addExpense(String date, String category, double amount) {
            ExpenseDTO expense = new ExpenseDTO(UUID.randomUUID().toString(),date, category,amount);
            repository.saveExpense(expense);
            expenseStatusDTO.setMessage("Expense has been saved");
            expenseStatusDTO.setStatus("Success");
            return expenseStatusDTO;
        }
    public List<ExpenseDTO> findall() {
        return repository.findAll();
    }

        public List<ExpenseDTO> getExpensesSortedByAmount() {
            return repository.findAllSortedByAmount();
        }

        public List<ExpenseDTO> getExpensesSortedByDate() {
            return repository.findAllSortedByDate();
        }

        public void exportToFile() {
            List<ExpenseDTO> expenses = repository.findAll();
            try (FileWriter writer = new FileWriter("expenses.txt")) {
                for (ExpenseDTO e : expenses) {
                    writer.write(e.toString() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException("Error exporting to file", e);
            }
        }

        @Scheduled(fixedRate = 5000)
        public void autoSaveToFile() {
            exportToFile();
        }
    }


