package com.offerista.consumer.primeconsumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PrimeNumberConsumer {
    @Value(value = "http.host")
    private String http_host;

    @PostConstruct
    public void getNumbers() {

        WebClient webClient = WebClient.create(http_host);

        Flux<Integer> numbersFlux = webClient.get()
                .uri("/generate")
                .retrieve()
                .bodyToFlux(Integer.class);

        numbersFlux.subscribe(key -> writeToCsv(key));

    }

    public boolean isPrimeNumber(int num) {
        for (int i = 2; i <= num / 2; ++i) {
            // condition for nonprime number
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void writeToCsv(int num) {
        Map<Integer, Boolean> numbersData = new HashMap<>();

        if (isPrimeNumber(num)) {
            numbersData.put(num, true);
            System.out.println(num + " is prime number");
        } else {
            numbersData.put(num, false);
            System.out.println(num + " is NOT prime number");
        }

        try {
            createCSVFile(numbersData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCSVFile(Map<Integer, Boolean> numbersData) throws IOException {

        String[] HEADERS = {"number", "isPrime"};
        FileWriter out = new FileWriter("result.csv", true);
        numbersData.forEach((number, isPrime) -> {

            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.builder().build())) {
                printer.printRecord(number, isPrime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
