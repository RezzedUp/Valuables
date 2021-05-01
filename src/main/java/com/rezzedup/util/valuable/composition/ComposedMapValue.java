package com.rezzedup.util.valuable.composition;

import com.rezzedup.util.valuable.KeyValueGetter;
import com.rezzedup.util.valuable.MapValue;

import java.util.Map;

public class ComposedMapValue<K, V> extends ComposedKeyValue<Map<K, V>, K, V> implements MapValue<K, V>
{
    public ComposedMapValue(K key)
    {
        super(key, Map::containsKey, KeyValueGetter.maybe(Map::get), Map::put);
    }
}
