package com.example.lamparainteligente.api.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LampDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int amount;
}
