package com.prinved.agroApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acheter")
public class Acheter implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date date;

    @Column
    private int quantite;

    @Column()
    private Double prixTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId")
    private ClientModel client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "varieteId")
    private VarieteModel variete;

    public Double prixTotal(int quantite, Double prix){
        return quantite * prix;
    }
}
