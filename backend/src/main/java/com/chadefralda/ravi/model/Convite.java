package com.chadefralda.ravi.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Boolean confirmouPresenca;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "convite_id")
    private List<Pessoa> pessoas = new ArrayList<>();


}

