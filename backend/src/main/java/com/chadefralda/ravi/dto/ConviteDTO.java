package com.chadefralda.ravi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConviteDTO {
    private Long id;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotNull(message = "Confirmação de presença é obrigatória")
    private Boolean confirmouPresenca;

    @NotEmpty(message = "Pelo menos uma pessoa deve ser informada")
    private List<@Valid PessoaDTO> pessoas;
}
