package com.br.sicredi.factory;

import java.io.File;
import java.io.PrintWriter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileFactory {

  public void createValidFileCSV(String path) {
    createFileCSV(path, getValidBody());
  }

  public void createInvalidFileCSV(String path) {
    createFileCSV(path, getInvalidBody());
  }

  @SneakyThrows
  private static void createFileCSV(String path, String body) {
    File csvOutputFile = new File(path);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      pw.println(body);
    }
  }

  private String getValidBody() {
    return "agencia;conta;saldo;status\n"
        + "0101;12225-6;100,00;A\n"
        + "0101;12226-8;3200,50;A\n"
        + "3202;40011-1;-35,12;I\n"
        + "3202;54001-2;0,00;P\n"
        + "3202;00321-2;34500,00;B";
  }

  private String getInvalidBody() {
    return "agencia;conta;saldo;status;debito\n"
        + "0101;12225-6;100,00;A;500,00\n"
        + "0101;12226-8;3200,50;A;00,00\n"
        + "3202;40011-1;-35,12;I;00,00\n"
        + "3202;54001-2;0,00;P;75,00\n"
        + "3202;00321-2;34500,00;B;1250,00";
  }

}
