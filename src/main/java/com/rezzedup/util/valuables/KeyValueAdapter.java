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

public interface KeyValueAdapter<S, O, K, V> extends Adapter<O, V>
{
    static <S, O, K, V> KeyValueAdapter<S, O, K, V> of(Getter<S, K, O> getter, Setter<S, K, O> setter, Adapter<O, V> adapter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        Objects.requireNonNull(adapter, "adapter");
        
        return new KeyValueAdapter<>()
        {
            @Override
            public @NullOr V get(S storage, K key)
            {
                @NullOr O output = getter.get(storage, key);
                return (output == null) ? null : adapter.deserialize(output);
            }
    
            @Override
            public void set(S storage, K key, @NullOr V value)
            {
                @NullOr O input = (value == null) ? null : adapter.serialize(value);
                setter.set(storage, key, input);
            }
    
            @Override
            public @NullOr V deserialize(O output) { return adapter.deserialize(output); }
    
            @Override
            public @NullOr O serialize(V input) { return adapter.serialize(input); }
        };
    }
    
    @NullOr V get(S storage, K key);
    
    void set(S storage, K key, @NullOr V value);
    
    @FunctionalInterface
    interface Getter<S, K, O>
    {
        @NullOr O get(S storage, K key);
    }
    
    @FunctionalInterface
    interface Setter<S, K, O>
    {
        void set(S storage, K key, @NullOr O output);
    }
}
