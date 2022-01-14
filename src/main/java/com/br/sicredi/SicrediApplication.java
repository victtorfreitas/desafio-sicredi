package com.br.sicredi;

import com.br.sicredi.adapter.SincronizacaoReceita;
import java.io.File;
import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class SicrediApplication {

  public static void main(String[] args) throws FileNotFoundException {
    ApplicationContext context = SpringApplication.run(SicrediApplication.class, args);
    SincronizacaoReceita sincronizacaoReceita = context.getBean(SincronizacaoReceita.class);
    if (args.length > 0 && new File(getPath(args)).exists()) {
      String path = getPath(args);
      String pathGeneratedFile = sincronizacaoReceita.execute(path);
      log.info("Arquivo gerado com sucesso!");
      log.info("O Caminho do arquivo gerado é: " + pathGeneratedFile);
    } else {
      log.warn("Arquivo não localizado");
      throw new FileNotFoundException();
    }
  }

  private static String getPath(String[] args) {
    return args[0];
  }

}
