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
@Table(name = "variete")
public class VarieteModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVariete;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Double prix;

    @Column(nullable = false)
    private int quantite;

    @OneToMany(mappedBy = "variete", fetch = FetchType.LAZY)
    private List<Acheter> acheter;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "producteurId", referencedColumnName = "idProducteur")
    private ProducteurModel producteur;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "semenceId", referencedColumnName = "idSemence")
    private SemenceModel semence;

    public VarieteModel(Long idVariete, String label, Double prix, int quantite) {
        this.idVariete = idVariete;
        this.label = label;
        this.prix = prix;
        this.quantite = quantite;
    }
}
