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

public interface KeyValue<S, K, V> extends KeyHolder<K>, Value<S, V>
{
    static <S, K, V> KeyValue<S, K, V> where(K key, MaybeKeyGetter<S, K, V> getter, KeySetter<S, K, V> setter)
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
            public final void set(S storage, @NullOr V value) { setter.set(storage, key, value); }
        };
    }
}
