package com.prinved.agroApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "semence")
public class SemenceModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSemence;

    @Column(nullable = false)
    private String label;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semence", cascade = CascadeType.ALL)
    private List<VarieteModel> variete;

}
