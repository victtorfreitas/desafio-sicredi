package com.br.sicredi.factory;

import com.br.sicredi.adapter.dto.Conta;
import com.br.sicredi.adapter.dto.StatusEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContaFactory {

  public Conta getValid() {
    return Conta.builder()
        .agencia("0001")
        .numero("11115-6")
        .status(StatusEnum.A)
        .saldo(1452.75)
        .build();
  }

  public Conta getIvalidAgencia() {
    return Conta.builder()
        .agencia("00001")
        .numero("11115-6")
        .status(StatusEnum.A)
        .saldo(1452.75)
        .build();
  }

  public Conta getIvalidAgenciaNull() {
    return Conta.builder()
        .numero("11115-6")
        .status(StatusEnum.A)
        .saldo(1452.75)
        .build();
  }

  public Conta getIvalidNumero() {
    return Conta.builder()
        .agencia("0001")
        .numero("11115-60")
        .status(StatusEnum.A)
        .saldo(1452.75)
        .build();
  }

  public Conta getIvalidNumeroNull() {
    return Conta.builder()
        .agencia("0001")
        .status(StatusEnum.A)
        .saldo(1452.75)
        .build();
  }

  public Conta getIvalidStatusNull() {
    return Conta.builder()
        .agencia("0001")
        .numero("11115-6")
        .saldo(1452.75)
        .build();
  }

}
