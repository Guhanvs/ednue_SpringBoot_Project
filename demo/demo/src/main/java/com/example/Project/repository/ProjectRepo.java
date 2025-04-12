package com.example.Project.repository;
import com.example.Project.model.ExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProjectRepo {
    @Autowired
    private  JdbcTemplate jdbcTemplate;



    public void saveExpense(ExpenseDTO expense) {
        String sql = "INSERT INTO expenses (transaction_id,date, category, amount) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                expense.getTransactionID(), expense.getDate(), expense.getCategory(), expense.getAmount());
    }

    public List<ExpenseDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM expenses",
                (rs, rowNum) -> new ExpenseDTO(
                        rs.getString("transaction_id"),
                        rs.getString("date"),
                        rs.getString("category"),
                        rs.getDouble("amount")


                ));
    }

    public List<ExpenseDTO> findAllSortedByAmount() {
        return jdbcTemplate.query("SELECT * FROM expenses ORDER BY amount",
                (rs, rowNum) -> new ExpenseDTO(
                        rs.getString("transaction_id"),
                        rs.getString("date"),
                        rs.getString("category"),
                        rs.getDouble("amount")

                ));
    }

    public List<ExpenseDTO> findAllSortedByDate() {
        return jdbcTemplate.query("SELECT * FROM expenses ORDER BY date",
                (rs, rowNum) -> new ExpenseDTO(
                        rs.getString("transaction_id"),
                        rs.getString("date"),
                        rs.getString("category"),
                        rs.getDouble("amount")



                ));
    }
}