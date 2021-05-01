package com.rezzedup.valuable;

import java.util.Map;

public interface DefaultMapValue<K, V> extends DefaultKeyValue<Map<K, V>, K, V>
{
    static <K, V> DefaultMapValue<K, V> at(K key, V def)
    {
        return new ComposedDefaultMapValue<>(def, MapValue.at(key));
    }
}
