package org.projeto;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.projeto.infra.repository.impl.EventRepositoryImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EventoIntegrationTest {
    private final EntityManager entityManager;

    public EventoIntegrationTest(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Test
    void whenGetAllEvents_thenShouldReturnPageSizeEventQty() {
        given()
                .contentType(ContentType.JSON)
                .param("page", 0)
                .param("size", 1)
                .when().get("/events")
                .then()
                .statusCode(200)
                .log().body()
                .body("size()", is(1))
                .body("[0].eventoId", is(2))
                .body("[0].nome", is("Evento 2"))
                .body("[0].dataFinal", is("2024-11-26"));
    }

    @Test
    void whenGetAllEventsWithInvalidSize_thenShouldReturnBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .param("page", 0)
                .param("size", -1)
                .when().get("/events")
                .then()
                .statusCode(400);
    }

    @Test
    void whenGetEventById_thenShouldReturnEvent() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/events/1")
                .then()
                .statusCode(200)
                .log().all()
                .body("eventoId", is(1))
                .body("nome", is("Evento 1"))
                .body("dataFinal", is("2024-10-30"));
    }

    @Test
    void whenGetEventByInvalidId_thenShouldReturnNotFound() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/events/999")
                .then()
                .statusCode(500)
                .body(containsString("Evento não encontrado"));
    }
}