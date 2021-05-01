package com.rezzedup.valuable;

import java.util.Map;

class ComposedDefaultMapValue<K, V> extends ComposedDefaultKeyValue<Map<K, V>, K, V> implements DefaultMapValue<K, V>
{
    ComposedDefaultMapValue(V def, KeyValue<Map<K, V>, K, V> value)
    {
        super(def, value);
    }
}
