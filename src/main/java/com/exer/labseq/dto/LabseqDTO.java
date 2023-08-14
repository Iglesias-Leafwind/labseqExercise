package com.exer.labseq.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabseqDTO {
    private String Result;

    public static LabseqDTO of(BigInteger n){
        return new LabseqDTO(n.toString());
    }
}
