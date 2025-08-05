package com.chadefralda.ravi.controller;


import com.chadefralda.ravi.dto.ConviteContagemDTO;
import com.chadefralda.ravi.dto.ConviteDTO;
import com.chadefralda.ravi.service.ConviteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convites")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConviteController {

    private final ConviteService conviteService;

    @PostMapping
    public ResponseEntity<ConviteDTO> criarConvite(@RequestBody @Valid ConviteDTO conviteDTO) {
        ConviteDTO salvo = conviteService.salvarConvite(conviteDTO);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ConviteDTO>> listarConvites() {
        return ResponseEntity.ok(conviteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConviteDTO> buscarPorId(@PathVariable Long id) {
        ConviteDTO convite = conviteService.buscarPorId(id);
        return convite != null ? ResponseEntity.ok(convite) : ResponseEntity.notFound().build();
    }
    @GetMapping("/contagem")
    public ResponseEntity<ConviteContagemDTO> contagemConvites() {
        return ResponseEntity.ok(conviteService.calcularContagem());
    }


}
