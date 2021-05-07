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

public interface DefaultAdaptedKeyValue<S, O, K, V> extends AdaptedKeyValue<S, O, K, V>, DefaultKeyValue<S, K, V>
{
    static <S, O, K, V> DefaultAdaptedKeyValue<S, O, K, V> defaults(V def, AdaptedKeyValue<S, O, K, V> value)
    {
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(value, "value");
        
        return new DefaultAdaptedKeyValue<>()
        {
            @Override
            public Adapter<O, V> adapter() { return value.adapter(); }
    
            @Override
            public K key() { return value.key(); }
    
            @Override
            public V getDefaultValue() { return def; }
    
            @Override
            public Optional<V> get(S storage) { return value.get(storage); }
    
            @Override
            public void set(S storage, @NullOr V updated) { value.set(storage, updated); }
        };
    }
}
