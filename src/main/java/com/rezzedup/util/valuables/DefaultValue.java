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

public interface DefaultValue<S, V> extends DefaultGetter<S, V>, DefaultSetter<S, V>,  Value<S, V>
{
    static <S, V> DefaultValue<S, V> of(V def, Value<S, V> value)
    {
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(value, "value");
        
        return new DefaultValue<>()
        {
            @Override
            public final V getDefaultValue() { return def; }
            
            @Override
            public final Optional<V> get(S storage) { return value.get(storage); }
            
            @Override
            public final void set(S storage, @NullOr V updated) { value.set(storage, updated); }
        };
    }
    
    @Override
    default void setAsDefault(S storage)
    {
        set(storage, getDefaultValue());
    }
    
    @Override
    default void setAsDefaultIfUnset(S storage)
    {
        if (get(storage).isEmpty()) { set(storage, getDefaultValue()); }
    }
}
