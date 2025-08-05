package com.chadefralda.ravi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConviteContagemDTO {
    private long totalConvites;
    private long totalPessoas;
    private long totalAdultos;
    private long totalCriancasAcimaDe3;
    private long totalCriancasAbaixoDe3;
}
