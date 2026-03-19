package com.tracker.expensetracker;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;
    private String title;
    private Double amount;
    private String category;
    private Date date;

    public Expense() {
        this.date = new Date();
    }

    public Expense(String title, Double amount, String category, Date date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date != null ? date : new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
