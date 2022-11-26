package br.com.dbc.chronosapi.entity.classes.processos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "RESPONSAVEL")
public class ResponsavelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESPONSAVEL_SEQ")
    @SequenceGenerator(name = "RESPONSAVEL_SEQ", sequenceName = "SEQ_RESPONSAVEL", allocationSize = 1)
    @Column(name = "ID_RESPONSAVEL")
    private Integer idResponsavel;

    @Column(name = "RESPONSAVEL")
    private String responsavel;

    @JsonIgnore
    @OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY)
    private Set<ProcessoEntity> processos;

}
