package com.br.sicredi.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

  private String agencia;
  private String numero;
  private double saldo;
  private StatusEnum status;

  @Setter
  boolean isUpdate;

  @Override
  public String toString() {
    return agencia + ";" + numero + ";" + getSaldoFormated() + ";" + status + ";" + isUpdate;
  }

  public String getSaldoFormated() {
    return String.valueOf(saldo).replace(".", ",");
  }

  public String getOnlyNumero() {
    return numero.replace("-", "");
  }
}
