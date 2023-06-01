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
@Table(name = "producteur")
public class ProducteurModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProducteur;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producteur", cascade = CascadeType.ALL)
    private List<VarieteModel> variete;
}
