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

public interface ValueAdapter<S, O, V> extends Adapter<O, V>
{
    static <S, O, V> ValueAdapter<S, O, V> of(Getter<S, O> getter, Setter<S, O> setter, Adapter<O, V> adapter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        Objects.requireNonNull(adapter, "adapter");
        
        return new ValueAdapter<>()
        {
            @Override
            public @NullOr V get(S storage)
            {
                @NullOr O output = getter.get(storage);
                return (output == null) ? null : adapter.deserialize(output);
            }
    
            @Override
            public void set(S storage, @NullOr V value)
            {
                @NullOr O input = (value == null) ? null : adapter.serialize(value);
                setter.set(storage, input);
            }
    
            @Override
            public @NullOr O serialize(V deserialized) { return adapter.serialize(deserialized); }
    
            @Override
            public @NullOr V deserialize(O serialized) { return adapter.deserialize(serialized); }
        };
    }
    
    @NullOr V get(S storage);
    
    void set(S storage, @NullOr V value);
    
    @FunctionalInterface
    interface Getter<S, O>
    {
        @NullOr O get(S storage);
    }
    
    interface Setter<S, O>
    {
        void set(S storage, @NullOr O output);
    }
}
