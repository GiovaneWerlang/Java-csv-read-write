package br.utfpr.edu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesteRel {
    private int idfixo;
    private String pai;
    private String relacionado;

    public TesteRel(String pai, String relacionado) {
        this.pai = pai;
        this.relacionado = relacionado;
    }
}
