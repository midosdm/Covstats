package com.covstats.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="CovData")
@Entity
public class CovData {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name="Pays")
    private String pays;

    @Column(name="Infections")
    private Long infections;

    @Column(name="Deces")
    private Long deces;

    @Column(name="Guerisons")
    private Long guerisons;

    @Column(name="TauxDeces")
    private Double tauxDeces;

    @Column(name="TauxGuerison")
    private Double tauxGuerison;

    @Column(name="TauxInfection")
    private Double tauxInfection;

    public CovData(Long id, String pays, Long infections, Long deces, Long guerisons, Double tauxDeces, Double tauxGuerison, Double tauxInfection){
        this.id = id;
        this.pays = pays;
        this.infections = infections;
        this.deces=deces;
        this.guerisons=guerisons;
        this.tauxDeces=tauxDeces;
        this.tauxGuerison = tauxGuerison;
        this.tauxInfection=tauxInfection;
    }
}
