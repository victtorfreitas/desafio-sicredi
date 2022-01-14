package com.br.sicredi.adapter;

import com.br.sicredi.adapter.dto.Conta;
import com.br.sicredi.adapter.dto.StatusEnum;
import com.br.sicredi.config.FileResponseProperty;
import com.br.sicredi.exception.FileFormatInvalidException;
import com.br.sicredi.service.ReceitaService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SincronizacaoReceita {

  private final FileResponseProperty property;

  private final ReceitaService receitaService;
  public static final String CONTAS_RESPONSE_CSV = "contas-response.csv";

  public String execute(String path) {
    List<Conta> contas = getContasToFile(path);
    log.info("Iniciando sincronização!");
    contas.forEach(receitaService::updateConta);
    log.info("Finalizando sincronização!");
    return generateCSVResponse(contas);
  }

  @SneakyThrows
  private String generateCSVResponse(List<Conta> contas) {
    File csvOutputFile = new File(CONTAS_RESPONSE_CSV);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      pw.println(property.getHeader());
      contas.stream()
          .map(this::convertToCSV)
          .forEach(pw::println);
    }
    return csvOutputFile.getAbsolutePath();
  }

  private String convertToCSV(Conta conta) {
    return conta.toString();
  }

  @SneakyThrows
  private List<Conta> getContasToFile(String path) {
    List<String> lineText = readFileCSV(path);
    return lineText.subList(1, lineText.size()).stream()
        .map(this::convertToConta)
        .collect(Collectors.toList());
  }

  private List<String> readFileCSV(String path) throws IOException {
    return Arrays.asList(Files.readString(Path.of(path), StandardCharsets.UTF_8)
        .split("\n"));
  }

  private Conta convertToConta(String line) {
    List<String> attributes = Arrays.asList(line.split(";"));

    validAttributes(attributes);

    String agencia = attributes.get(0);
    String numero = attributes.get(1);
    String saldo = attributes.get(2).replace(",", ".");
    String status = attributes.get(3).trim();

    return Conta.builder()
        .agencia(agencia)
        .numero(numero)
        .saldo(Double.parseDouble(saldo))
        .status(StatusEnum.valueOf(status))
        .build();
  }

  private void validAttributes(List<String> attributes) {
    if (attributes.size() != 4) {
      log.error("Arquivo com formatação inválida!");
      throw new FileFormatInvalidException();
    }
  }
}
