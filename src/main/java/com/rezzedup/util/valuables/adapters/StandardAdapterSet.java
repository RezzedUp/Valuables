package com.rezzedup.util.valuables.adapters;

import com.rezzedup.util.valuables.Adapter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

/**
 * A collection of standard adapters.
 *
 * @param <S>   serialized type
 */
public interface StandardAdapterSet<S>
{
    Adapter<S, String> strings();
    
    Adapter<S, Boolean> booleans();
    
    Adapter<S, Character> characters();
    
    Adapter<S, Byte> bytes();
    
    Adapter<S, Short> shorts();
    
    Adapter<S, Integer> integers();
    
    Adapter<S, Long> longs();
    
    Adapter<S, Float> floats();
    
    Adapter<S, Double> doubles();
    
    Adapter<S, BigInteger> bigIntegers();
    
    Adapter<S, BigDecimal> bigDecimals();
    
    Adapter<S, UUID> uuids();
}
