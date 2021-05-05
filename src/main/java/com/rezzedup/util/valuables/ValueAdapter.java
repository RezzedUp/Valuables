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
public interface ValueAdapter<S, O, V> extends Adapter<O, V>
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
    static <S, O, V> ValueAdapter<S, O, V> of(DirectGetter<S, O> getter, DirectSetter<S, O> setter, Adapter<O, V> adapter)
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
    
    /**
     * Gets and converts the possibly-{@code null} value
     * directly from the provided storage.
     *
     * @param storage   the storage
     *
     * @return  the value if retrieval and conversion
     *          was successful, otherwise {@code null}
     */
    @NullOr V get(S storage);
    
    /**
     * Converts and sets the value directly into the provided
     * storage or removes it if it's {@code null}.
     *
     * @param storage   the storage
     * @param value     the value to set
     *                  or {@code null} to remove
     */
    void set(S storage, @NullOr V value);
    
    //
    //  Specialized interfaces for direct getters & setters
    //  (and for proper nullness).
    //
    
    /**
     * A "direct getter" where the value is retrieved
     * in its original type directly from storage.
     *
     * @param <S>   storage type
     * @param <O>   output type
     */
    @FunctionalInterface
    interface DirectGetter<S, O>
    {
        /**
         * Gets the value in its original type directly from
         * the provided storage.
         *
         * @param storage   the storage
         *
         * @return  the original value or {@code null}
         */
        @NullOr O get(S storage);
    }
    
    /**
     * A "direct setter" where the value is set in the
     * required output type directly into storage.
     *
     * @param <S>   storage type
     * @param <O>   output type
     */
    interface DirectSetter<S, O>
    {
        /**
         * Sets the possibly-{@code null} value in its required
         * output type directly into storage.
         *
         * @param storage   the storage
         * @param output    the value or {@code null}
         *
         * @implNote    If the provided value is {@code null},
         *              it is expected to be removed from storage.
         */
        void set(S storage, @NullOr O output);
    }
}
