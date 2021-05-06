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

public interface DelegatedKeyAdapter<S, O, K, V> extends Adapter<O, V>
{
    static <S, O, K, V> DelegatedKeyAdapter<S, O, K, V> adapted(KeyGetter<S, K, O> getter, KeySetter<S, K, O> setter, Adapter<O, V> adapter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        Objects.requireNonNull(adapter, "adapter");
        
        return new DelegatedKeyAdapter<>()
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
            public @NullOr V deserialize(O serialized) { return adapter.deserialize(serialized); }
    
            @Override
            public @NullOr O serialize(V deserialized) { return adapter.serialize(deserialized); }
        };
    }
    
    static <S, O, K, V> DelegatedKeyAdapter<S, O, K, V> direct(KeyGetter<S, K, V> getter, KeySetter<S, K, V> setter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        
        return new DelegatedKeyAdapter<>()
        {
            @Override
            public @NullOr V get(S storage, K key) { return getter.get(storage, key); }
            
            @Override
            public void set(S storage, K key, @NullOr V value) { setter.set(storage, key, value); }
            
            @Override
            public @NullOr V deserialize(Object serialized) { return null; }
            
            @Override
            public @NullOr O serialize(V deserialized) { return null; }
        };
    }
    
    @NullOr V get(S storage, K key);
    
    void set(S storage, K key, @NullOr V value);
    
}
