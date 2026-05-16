package com.example.jounaling_app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

  @Bean
  public NewTopic cornTopic() {
    return TopicBuilder.name("corn-topic").partitions(2).build();
  }

  // @Bean
  // public KafkaProperties kafkaProperties(KafkaProperties properties) throws IOException {
  //   // Resolve classpath paths to actual file system paths
  //   String truststorePath = resolveClasspathToFile("certs/svc.pem");
  //   String certChainPath = resolveClasspathToFile("certs/service.cert");
  //   String keyPath = resolveClasspathToFile("certs/service.key");

  //   // Inject the absolute paths into the Kafka properties
  //   properties.getProperties().put("ssl.truststore.certificates", truststorePath);
  //   properties.getProperties().put("ssl.keystore.certificate.chain", certChainPath);
  //   properties.getProperties().put("ssl.keystore.key", keyPath);

  //   return properties;
  // }

  // private String resolveClasspathToFile(String resourcePath) throws IOException {
  //   ClassPathResource resource = new ClassPathResource(resourcePath);
  //   try {
  //     // Works if running locally from filesystem
  //     return resource.getFile().getAbsolutePath();
  //   } catch (IOException e) {
  //     // Fallback: copy file out if running from inside a packaged JAR file
  //     File tempFile = File.createTempFile("kafka-cert-", ".pem");
  //     tempFile.deleteOnExit();
  //     java.nio.file.Files.copy(resource.getInputStream(), tempFile.toPath(),
  //         java.nio.file.StandardCopyOption.REPLACE_EXISTING);
  //     return tempFile.getAbsolutePath();
  //   }
  // }

}
