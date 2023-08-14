package com.exer;

import com.exer.labseq.Labseq;
import com.exer.labseq.dto.LabseqDTO;

import io.quarkus.test.junit.QuarkusTest;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class LabseqResourceTest{
    @Inject
    Labseq labseq;

    @Test
    void testValidIndexResponse() {
        int test_index = 10;
        LabseqDTO result = given()
                .when().get(Paths.BASE_URL + Paths.SEQ_URL, test_index)
                .then().statusCode(200)
                .extract().body().as(LabseqDTO.class);
        String real_value = labseq.getLabseqIn(test_index).toString();
        Assertions.assertEquals(real_value, result.getResult());
    }

    @Test
    void testInvalidIndexResponse() {
        int test_index = -10;
        given()
                .when().get(Paths.BASE_URL + Paths.SEQ_URL, test_index)
                .then().statusCode(400);
    }
}
