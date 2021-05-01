package com.rezzedup.util.valuable;

import com.rezzedup.util.valuable.composition.ComposedMapValue;

import java.util.Map;

public interface MapValue<K, V> extends KeyValue<Map<K, V>, K, V>
{
    static <K, V> MapValue<K, V> at(K key)
    {
        return new ComposedMapValue<>(key);
    }
}
