package com.br.sicredi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.br.sicredi.adapter.SincronizacaoReceita;
import com.br.sicredi.factory.FileFactory;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SicrediApplicationTest {

  public static final String PATH_CONTA_TEST = "contas-test.csv";

  @Mock
  SincronizacaoReceita sincronizacaoReceita;

  @AfterEach
  void tearDown() {
    new File(SincronizacaoReceita.CONTAS_RESPONSE_CSV).delete();
    new File(PATH_CONTA_TEST).delete();
  }

  @BeforeAll
  static void beforeAll() {
    FileFactory.createValidFileCSV(PATH_CONTA_TEST);
  }

  @Test
  void canExecuteManWithArgs() {
    assertDoesNotThrow(() -> SicrediApplication.main(new String[]{PATH_CONTA_TEST}));
  }

  @Test
  void canNotExecuteManWithoutArgs() {
    String[] args = {};
    assertThrows(FileNotFoundException.class, () -> SicrediApplication.main(args));
  }

  @Test
  void canNotExecuteManWithArgsWithPathInvalid() {
    String[] args = {"arquivo-nao-existente.csv"};
    assertThrows(FileNotFoundException.class, () -> SicrediApplication.main(args));
  }
}
