/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mz.co.jmtecnologias.multigest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 *
 * @author JM-Tecnologias
 */
public class Service {

    private static final String API_URL = "http://127.0.0.1:8000";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public Service() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Company> getCompany() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(API_URL + "/company/find-all"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP ERRO: " + response.statusCode());
        }
        String json = response.body();
        return objectMapper.readValue(json, new TypeReference<List<Company>>() {
        });
    }

    public Company createCompany(Company company) throws IOException, InterruptedException {
        String companyJson = objectMapper.writeValueAsString(company);
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(API_URL + "/company/register"))
                .header(
                        "Content-Type",
                        "application/json")
                .method("POST", HttpRequest.BodyPublishers
                        .ofString(companyJson))
                .build();
        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("HTTP ERRO: " + response.statusCode() + " - " + response.body());
        }

        return objectMapper.readValue(response.body(), Company.class);

    }

    public Company updateCompany(int id, Company company) throws IOException, InterruptedException {
        String companyJson = objectMapper.writeValueAsString(company);
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(API_URL + "/company/update/" + id))
                .header(
                        "Content-Type",
                        "application/json")
                .method("PATCH", HttpRequest.BodyPublishers
                        .ofString(companyJson))
                .build();
        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        /*
        200 OK
        201 Created
        202 Accepted
        204 No Content
         */
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException("HTTP ERRO: " + response.statusCode() + " - " + response.body());
        }

        return objectMapper.readValue(response.body(), Company.class);

    }
}
