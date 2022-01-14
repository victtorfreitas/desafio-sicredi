package com.br.sicredi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

import com.br.sicredi.adapter.dto.Conta;
import com.br.sicredi.factory.ContaFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReceitaServiceTest {

  @Spy
  ReceitaService receitaService;

  @SneakyThrows
  @BeforeEach
  void setUp() {
    doNothing()
        .when(receitaService).sincronizarMock();
  }

  @Test
  void canUpdateConta() {
    Conta conta = ContaFactory.getValid();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(true);
  }

  @Test
  void canNotupdateContaWithAgenciaInvalid() {
    Conta conta = ContaFactory.getIvalidAgencia();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(false);
  }

  @Test
  void canNotupdateContaWithAgenciaNull() {
    Conta conta = ContaFactory.getIvalidAgenciaNull();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(false);
  }

  @Test
  void canNotupdateContaWithNumeroInvalid() {
    Conta conta = ContaFactory.getIvalidNumero();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(false);
  }

  @Test
  void canNotupdateContaWithNumeroNull() {
    Conta conta = ContaFactory.getIvalidNumeroNull();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(false);
  }

  @Test
  void canNotupdateContaWithStatusInvalid() {
    Conta conta = ContaFactory.getIvalidNumero();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(false);
  }

  @Test
  void canNotupdateContaWithStatusNull() {
    Conta conta = ContaFactory.getIvalidStatusNull();
    assert !conta.isUpdate();
    assertDoesNotThrow(() -> receitaService.updateConta(conta));
    assertThat(conta)
        .extracting(Conta::isUpdate)
        .isEqualTo(false);
  }
}
