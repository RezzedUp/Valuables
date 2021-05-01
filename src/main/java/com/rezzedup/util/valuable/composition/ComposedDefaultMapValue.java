package com.rezzedup.util.valuable.composition;

import com.rezzedup.util.valuable.KeyValue;
import com.rezzedup.util.valuable.DefaultMapValue;

import java.util.Map;

public class ComposedDefaultMapValue<K, V> extends ComposedDefaultKeyValue<Map<K, V>, K, V> implements DefaultMapValue<K, V>
{
    public ComposedDefaultMapValue(V def, KeyValue<Map<K, V>, K, V> value)
    {
        super(def, value);
    }
}
