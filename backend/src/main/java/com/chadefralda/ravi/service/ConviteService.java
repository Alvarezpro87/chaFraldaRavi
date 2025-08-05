package com.chadefralda.ravi.service;

import com.chadefralda.ravi.dto.ConviteContagemDTO;
import com.chadefralda.ravi.dto.ConviteDTO;
import com.chadefralda.ravi.dto.PessoaDTO;
import com.chadefralda.ravi.model.Convite;
import com.chadefralda.ravi.model.Pessoa;
import com.chadefralda.ravi.repository.ConviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConviteService {

    private final ConviteRepository conviteRepository;

    public ConviteDTO salvarConvite(ConviteDTO dto) {
        Convite convite = Convite.builder()
                .email(dto.getEmail())
                .confirmouPresenca(dto.getConfirmouPresenca())
                .pessoas(dto.getPessoas().stream().map(this::toPessoa).collect(Collectors.toList()))
                .build();

        Convite salvo = conviteRepository.save(convite);
        return toDTO(salvo);
    }

    public List<ConviteDTO> listarTodos() {
        return conviteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ConviteDTO buscarPorId(Long id) {
        return conviteRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    // Conversão entre Entity <-> DTO
    private ConviteDTO toDTO(Convite convite) {
        return ConviteDTO.builder()
                .id(convite.getId())
                .email(convite.getEmail())
                .confirmouPresenca(convite.getConfirmouPresenca())
                .pessoas(convite.getPessoas().stream().map(this::toDTO).collect(Collectors.toList()))
                .build();
    }

    private PessoaDTO toDTO(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .build();
    }

    private Pessoa toPessoa(PessoaDTO dto) {
        return Pessoa.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .build();
    }
    public ConviteContagemDTO calcularContagem() {
        List<Convite> convites = conviteRepository.findAll().stream()
                .filter(c -> Boolean.TRUE.equals(c.getConfirmouPresenca()))
                .toList();

        long totalConvites = convites.size();
        long totalPessoas = 0;
        long totalAdultos = 0;
        long totalCriancasAcimaDe3 = 0;
        long totalCriancasAbaixoDe3 = 0;

        LocalDate hoje = LocalDate.now();

        for (Convite convite : convites) {
            for (Pessoa pessoa : convite.getPessoas()) {
                totalPessoas++;
                int idade = Period.between(pessoa.getDataNascimento(), hoje).getYears();
                if (idade >= 18) {
                    totalAdultos++;
                } else if (idade >= 3) {
                    totalCriancasAcimaDe3++;
                } else {
                    totalCriancasAbaixoDe3++;
                }
            }
        }
        return new ConviteContagemDTO(
                totalConvites,
                totalPessoas,
                totalAdultos,
                totalCriancasAcimaDe3,
                totalCriancasAbaixoDe3
        );
    }
}
