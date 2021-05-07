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

public interface DefaultAdaptedValue<S, O, V> extends AdaptedValue<S, O, V>, DefaultValue<S, V>
{
    static <S, O, V> DefaultAdaptedValue<S, O, V> defaults(V def, AdaptedValue<S, O, V> value)
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
