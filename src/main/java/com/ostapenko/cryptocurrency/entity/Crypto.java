package com.ostapenko.cryptocurrency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
@Getter
@Setter
public class Crypto {
    @Id
    private int id;

    @Field("name")
    private String currencyName;
    private String usd;

    @Field("price_precision")
    private int pricePrecision;

    @Field("lprice")
    private double Lprice;

    @Field("hprice")
    private double Hprice;
}
