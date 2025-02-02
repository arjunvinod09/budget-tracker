package com.ust.budget.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "budget")
public class Budget {

    @Id
    private String _id;

    @Field("no")
    private long no;

    @Field("description")
    private String description;

    @Field("type")
    private Type type;

    @Field("category")
    private Category category;

    @Field("amount")
    private Double amount;

    @Field("createdDate")
    private LocalDateTime createdDate;

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
//    @BeforeSave
//    public void onCreate(){
//        this.createdDate = LocalDate.now();
//        this.createdTime = LocalTime.now();
//    }
}