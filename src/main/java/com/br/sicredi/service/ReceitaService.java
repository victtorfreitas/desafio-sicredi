package com.br.sicredi.service;

import com.br.sicredi.adapter.dto.Conta;
import com.br.sicredi.adapter.dto.StatusEnum;
import com.br.sicredi.exception.ReceitaException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReceitaService {

  @SneakyThrows
  public void updateConta(Conta conta) {
    boolean isUpdate = updateConta(conta.getAgencia(),
        conta.getNumero(),
        conta.getSaldo(),
        conta.getStatus() != null ? conta.getStatus().name() : "");
    conta.setUpdate(isUpdate);
  }

  private boolean updateConta(String agencia, String conta, double saldo, String status)
      throws InterruptedException, ReceitaException {

    if (isContaInvalid(agencia, conta, status)) {
      return false;
    }

    sincronizarMock();
    return true;
  }

  public void sincronizarMock() throws InterruptedException, ReceitaException {
    long wait = Math.round(Math.random() * 4000) + 1000;
    Thread.sleep(wait);

    long randomError = Math.round(Math.random() * 1000);
    if (randomError == 500) {
      log.error("Error ao comunicar com a receita");
      throw new ReceitaException();
    }
  }

  private boolean isContaInvalid(String agencia, String conta, String status) {
    if (agencia == null || agencia.length() != 4) {
      return true;
    } else if (conta == null || getOnlyNumero(conta).length() != 6) {
      return true;
    } else {
      return status == null || StatusEnum.notContains(status);
    }
  }

  private String getOnlyNumero(String numero) {
    return numero.replace("-", "");
  }
}
