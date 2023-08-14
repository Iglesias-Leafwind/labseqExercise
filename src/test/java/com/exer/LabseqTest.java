package com.exer;

import com.exer.labseq.Labseq;

import io.quarkus.test.junit.QuarkusTest;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LabseqTest {

    @Inject
    Labseq labseq;

    private final List<BigInteger> initialValues = new ArrayList<>(){{
        add(BigInteger.ZERO);
        add(BigInteger.ONE);
        add(BigInteger.ZERO);
        add(BigInteger.ONE);
    }};

    //labseq 555 real big number value
    private final BigInteger bigNumber = new BigInteger("473342137479804513548841454380076892139883005696");

    @Test
    void testInitialCacheIdx(){

        List<BigInteger> testArray = new ArrayList<>();
        for(int test_idx = 0; initialValues.size() > test_idx; test_idx++) {
            testArray.add(labseq.getLabseqIn(test_idx));
        }
        Assertions.assertEquals(initialValues,testArray);
    }

    @Test
    void testSmallUncachedValue(){
        int test_idx = 5;
        BigInteger test_value = labseq.getLabseqIn(test_idx);
        BigInteger real_value = initialValues.get(test_idx-4).add(initialValues.get(test_idx-3));

        Assertions.assertEquals(real_value,test_value);
    }

    @Test
    void testHighUncachedValue(){
        int test_idx = 555;
        BigInteger test_value = labseq.getLabseqIn(test_idx);
        Assertions.assertEquals(bigNumber,test_value);
    }

    @Test
    void testSmallCachedValue(){
        int test_idx = 5;
        labseq.getLabseqIn(test_idx);
        BigInteger test_value = labseq.getLabseqIn(test_idx);
        BigInteger real_value = initialValues.get(test_idx-4).add(initialValues.get(test_idx-3));

        Assertions.assertEquals(real_value,test_value);
    }

    @Test
    void testHighCachedValue(){
        int test_idx = 555;
        labseq.getLabseqIn(test_idx);
        BigInteger test_value = labseq.getLabseqIn(test_idx);
        Assertions.assertEquals(bigNumber,test_value);
    }

}