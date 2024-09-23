package com.example.back;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Patrimoine {
    private String possesseur;
    private DateTimeFormatter derniereModification;
}
