package com.paygoal.lamparainteligente.api.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LampDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int amount;
}
