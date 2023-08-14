package com.exer.labseq;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Labseq {

    //start cache with predefined values
    private final List<BigInteger> cache = new ArrayList<>(){{
        add(BigInteger.ZERO);
        add(BigInteger.ONE);
        add(BigInteger.ZERO);
        add(BigInteger.ONE);
    }};

    //method that will get the n'th position labseq value from cache or compute it
    public BigInteger getLabseqIn(int n){
        if(hasCache(n)){
            return cache.get(n);
        }
        return computeLabseqPathOf(n);
    }

    //method that verifies if the n'th value is cached
    private boolean hasCache(int n){
        //cache size is always 4 when asking for n= 0 1 2 3 this should return true but when its 4 it returns false.
        //cache size is 6 then n= 0 1 2 3 4 5 returns true and 6 and above returns false.
        //cache.size()-1 now follows the max array index value and cause n follows the array index if we ask for a numb that isn't on array it will return false
        return cache.size()-1 >= n;
    }
    //method that will compute until n'th labseq value and cache every single value until the n'th one
    private BigInteger computeLabseqPathOf(int n){
        BigInteger returning_value = null;
        for(int idx = cache.size(); idx <= n; idx++){
            returning_value = calculateLabseqOf(idx);
            cache.add(returning_value);
        }
        return returning_value;
    }

    //method that will calculate the labseq value using the formula: L(n) = L(n-4) + L(n-3)
    private BigInteger calculateLabseqOf(int n){
        return cache.get(n-4).add(cache.get((n-3)));
    }

}
