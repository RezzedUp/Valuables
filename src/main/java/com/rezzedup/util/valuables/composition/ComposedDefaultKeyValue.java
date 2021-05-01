/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.composition;

import com.rezzedup.util.valuables.DefaultKeyValue;
import com.rezzedup.util.valuables.KeyValue;
import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;

public class ComposedDefaultKeyValue<S, K, V> implements DefaultKeyValue<S, K, V>
{
    private final V def;
    private final KeyValue<S, K, V> value;
    
    public ComposedDefaultKeyValue(V def, KeyValue<S, K, V> value)
    {
        this.def = Objects.requireNonNull(def, "def");
        this.value = Objects.requireNonNull(value, "value");
    }
    
    @SuppressWarnings("NullableProblems")
    @Override
    public final V getDefaultValue()
    {
        return def;
    }
    
    @SuppressWarnings("NullableProblems")
    @Override
    public final K key()
    {
        return value.key();
    }
    
    @Override
    public final boolean isSet(S storage)
    {
        return value.isSet(storage);
    }
    
    @Override
    public final Optional<V> get(S storage)
    {
        return value.get(storage);
    }
    
    @Override
    public final void set(S storage, @NullOr V value)
    {
        this.value.set(storage, value);
    }
}
