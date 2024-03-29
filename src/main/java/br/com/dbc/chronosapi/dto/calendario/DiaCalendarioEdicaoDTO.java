package br.com.dbc.chronosapi.dto.calendario;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiaCalendarioEdicaoDTO {
    private LocalDate dia;
    private Integer idEtapa;
    private String etapa;
    private Integer idProcesso;
    private String processo;
    private String cor;
    private List<String> areas;
    private String feriado;
}
