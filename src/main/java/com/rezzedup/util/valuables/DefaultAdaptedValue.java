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
 * A value that's associated with a default fallback and must be adapted to and from storage.
 *
 * @param <S>   storage type
 * @param <O>   output type
 * @param <V>   value type
 */
public interface DefaultAdaptedValue<S, O, V> extends AdaptedValue<S, O, V>, DefaultValue<S, V>
{
    /**
     * Creates a new {@code DefaultAdaptedValue} by delegating to the provided arguments. Getting the value
     * will always deserialize from storage, and setting the value will always serialize into storage.
     *
     * @param value     an existing key value implementation that gets and sets the value from
     *                  storage in the original 'output' type
     * @param <S>       storage type
     * @param <O>       output type
     * @param <V>       value type
     *
     * @return a new instance composed of the arguments
     */
    static <S, O, V> DefaultAdaptedValue<S, O, V> of(V def, AdaptedValue<S, O, V> value)
    {
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(value, "value");
        
        return new DefaultAdaptedValue<>()
        {
            @Override
            public Adapter<O, V> adapter() { return value.adapter(); }
    
            @Override
            public V getDefaultValue() { return def; }
    
            @Override
            public Optional<V> get(S storage) { return value.get(storage); }
    
            @Override
            public void set(S storage, @NullOr V updated) { value.set(storage, updated); }
        };
    }
}
