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

public interface AdaptedKeyValue<S, O, K, V> extends Adaptable<O, V>, KeyValue<S, K, V>
{
    static <S, O, K, V> AdaptedKeyValue<S, O, K, V> adapts(KeyValue<S, K, O> value, Adapter<O, V> adapter)
    {
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(adapter, "adapter");
        
        return new AdaptedKeyValue<>()
        {
            @Override
            public Adapter<O, V> adapter() { return adapter; }
            
            @Override
            public K key() { return value.key(); }
            
            @Override
            public Optional<V> get(S storage)
            {
                return value.get(storage).map(adapter::deserialize);
            }
    
            @Override
            public void set(S storage, @NullOr V updated)
            {
                @NullOr O output = (updated == null) ? null : adapter.serialize(updated);
                value.set(storage, output);
            }
        };
    }
}
