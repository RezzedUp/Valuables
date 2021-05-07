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

/**
 * Conversion layer between a value and its underlying storage,
 * which internally represents the value in a different output type.
 *
 * @param <S>   storage type
 * @param <O>   output type
 * @param <V>   value type
 */
public interface DelegatedAdapter<S, O, V> extends Adapter<O, V>, Getter<S, V>, Setter<S, V>
{
    /**
     * Creates a new value adapter composed of the provided
     * direct getter, setter, and an existing compatible adapter.
     *
     * @param getter    direct getter
     * @param setter    direct setter
     * @param adapter   existing adapter
     *
     * @param <S>   storage type
     * @param <O>   output type
     * @param <V>   value type
     *
     * @return  a new value adapter composed of the provided elements
     *
     * @throws NullPointerException     if any argument is {@code null}
     */
    static <S, O, V> DelegatedAdapter<S, O, V> delegates(Getter<S, O> getter, Setter<S, O> setter, Adapter<O, V> adapter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        Objects.requireNonNull(adapter, "adapter");
        
        return new DelegatedAdapter<>()
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
    
    static <S, O, V> DelegatedAdapter<S, O, V> directly(Getter<S, V> getter, Setter<S, V> setter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        
        return new DelegatedAdapter<>()
        {
            @Override
            public @NullOr V get(S storage)
            {
                return getter.get(storage);
            }
            
            @Override
            public void set(S storage, @NullOr V value)
            {
                setter.set(storage, value);
            }
            
            @Override
            public @NullOr O serialize(V deserialized) { return null; }
            
            @Override
            public @NullOr V deserialize(O serialized) { return null; }
        };
    }
    
    /**
     * Gets and converts the possibly-{@code null} value
     * directly from the provided storage.
     *
     * @param storage   the storage
     *
     * @return  the value if retrieval and conversion
     *          was successful, otherwise {@code null}
     */
    @Override
    public @NullOr V get(S storage); // redundantly 'public' for proper @NullOr placement
    
    /**
     * Converts and sets the value directly into the provided
     * storage or removes it if it's {@code null}.
     *
     * @param storage   the storage
     * @param value     the value to set
     *                  or {@code null} to remove
     */
    @Override
    void set(S storage, @NullOr V value);
}
