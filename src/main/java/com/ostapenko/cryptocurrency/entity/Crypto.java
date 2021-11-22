package com.ostapenko.cryptocurrency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crypto {
    @Id
    private String id;

    private String curr1;
    private String curr2;
    private double lprice;
    private Date createdAt;
}
