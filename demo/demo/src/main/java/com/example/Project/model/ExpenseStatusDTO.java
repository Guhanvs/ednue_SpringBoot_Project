package com.example.Project.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ExpenseStatusDTO {
        private String status;
        private String message;
    }

