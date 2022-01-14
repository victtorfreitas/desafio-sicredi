package com.br.sicredi.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.br.sicredi.exception.FileFormatInvalidException;
import com.br.sicredi.factory.FileFactory;
import java.io.File;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SincronizacaoReceitaTest {

  public static final String PATH_CONTA_TEST = "contas-test.csv";
  public static final String PATH_CONTA_TEST_EXCEPTION = "contas-test-runtime-exception.csv";

  @Autowired
  SincronizacaoReceita sincronizacaoReceita;

  @BeforeAll
  static void beforeAll() {
    FileFactory.createValidFileCSV(PATH_CONTA_TEST);
  }

  @AfterEach
  void tearDown() {
    new File(SincronizacaoReceita.CONTAS_RESPONSE_CSV).delete();
  }

  @AfterAll
  static void afterAll() {
    new File(PATH_CONTA_TEST).delete();
    new File(PATH_CONTA_TEST_EXCEPTION).delete();
  }

  @Test
  void canExecute() {
    String path = sincronizacaoReceita.execute(PATH_CONTA_TEST);
    assertThat(new File(path))
        .exists();
  }

  @Test
  void canNotExecute() {
    FileFactory.createInvalidFileCSV(PATH_CONTA_TEST_EXCEPTION);
    assertThrows(FileFormatInvalidException.class,
        () -> sincronizacaoReceita.execute(PATH_CONTA_TEST_EXCEPTION));
    assertThat(new File(SincronizacaoReceita.CONTAS_RESPONSE_CSV))
        .doesNotExist();
  }

}
