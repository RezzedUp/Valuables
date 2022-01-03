/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
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
 * A value associated with a particular key.
 *
 * @param <S>   storage type
 * @param <K>   key type
 * @param <V>   value type
 */
public interface KeyValue<S, K, V> extends KeyHolder<K>, Value<S, V>
{
    /**
     * Creates a new {@code KeyValue} by delegating to the provided arguments.
     *
     * @param key       the key
     * @param getter    the value getter
     * @param setter    the value setter
     * @param <S>       storage type
     * @param <K>       key type
     * @param <V>       value type
     *
     * @return a new instance composed of the arguments
     */
    static <S, K, V> KeyValue<S, K, V> of(K key, KeyGetter<S, K, V> getter, KeySetter<S, K, V> setter)
    {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
       
        return new KeyValue<>()
        {
            @Override
            public final K key() { return key; }
            
            @Override
            public final Optional<V> get(S storage) { return getter.get(storage, key); }
            
            @Override
            public final void set(S storage, @NullOr V updated) { setter.set(storage, key, updated); }
        };
    }
}
