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
 * A value that must be adapted to and from storage.
 *
 * @param <S>   storage type
 * @param <O>   output type
 * @param <V>   value type
 */
public interface AdaptedValue<S, O, V> extends Adaptable<O, V>, Value<S, V>
{
    /**
     * Creates a new {@code AdaptedValue} by delegating to the provided arguments. Getting the value will
     * always deserialize from storage, and setting the value will always serialize into storage.
     *
     * @param adapter   the adapter
     * @param value     an existing value implementation that gets and sets the value from
     *                  storage in the original 'output' type
     * @param <S>       storage type
     * @param <O>       output type
     * @param <V>       value type
     *
     * @return a new instance composed of the arguments
     */
    static <S, O, V> AdaptedValue<S, O, V> of(Adapter<O, V> adapter, Value<S, O> value)
    {
        Objects.requireNonNull(adapter, "adapter");
        Objects.requireNonNull(value, "value");
        
        return new AdaptedValue<>()
        {
            @Override
            public Adapter<O, V> adapter() { return adapter; }
    
            @Override
            public Optional<V> get(S storage)
            {
                return value.get(storage).flatMap(adapter::deserialize);
            }
    
            @Override
            public void set(S storage, @NullOr V updated)
            {
                @NullOr O output = (updated == null) ? null : adapter.serialize(updated).orElse(null);
                value.set(storage, output);
            }
        };
    }
}
