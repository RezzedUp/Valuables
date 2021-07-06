/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;

/**
 * A value associated with a default fallback and a key.
 *
 * @param <S>   storage type
 * @param <K>   key type
 * @param <V>   value type
 */
public interface DefaultKeyValue<S, K, V> extends DefaultValue<S, V>, KeyValue<S, K, V>
{
    /**
     * Creates a new {@code DefaultKeyValue} by delegating
     * to the provided arguments.
     *
     * @param def       the default fallback value
     * @param value     an existing key value implementation
     * @param <S>       storage type
     * @param <K>       key type
     * @param <V>       value type
     *
     * @return  a new instance composed of the arguments
     */
    static <S, K, V> DefaultKeyValue<S, K, V> of(V def, KeyValue<S, K, V> value)
    {
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(value, "value");
        
        return new DefaultKeyValue<>()
        {
            @Override
            public V getDefaultValue() { return def; }
    
            @Override
            public K key() { return value.key(); }
    
            @Override
            public Optional<V> get(S storage) { return value.get(storage); }
    
            @Override
            public void set(S storage, @NullOr V updated) { value.set(storage, updated); }
        };
    }
}
