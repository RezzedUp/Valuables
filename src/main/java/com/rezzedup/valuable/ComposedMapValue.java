package com.rezzedup.valuable;

import java.util.Map;

class ComposedMapValue<K, V> extends ComposedKeyValue<Map<K, V>, K, V> implements MapValue<K, V>
{
    ComposedMapValue(K key)
    {
        super(key, Map::containsKey, KeyValueGetter.maybe(Map::get), Map::put);
    }
}
