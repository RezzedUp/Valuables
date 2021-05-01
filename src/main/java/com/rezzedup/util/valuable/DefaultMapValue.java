package com.rezzedup.util.valuable;

import com.rezzedup.util.valuable.composition.ComposedDefaultMapValue;

import java.util.Map;

public interface DefaultMapValue<K, V> extends DefaultKeyValue<Map<K, V>, K, V>
{
    static <K, V> DefaultMapValue<K, V> at(K key, V def)
    {
        return new ComposedDefaultMapValue<>(def, MapValue.at(key));
    }
}
