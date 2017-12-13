package info.luxman.prometheusdemo;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class PrometheusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}
}
