/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.valuable;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;

class ComposedDefaultKeyValue<S, K, V> implements DefaultKeyValue<S, K, V>
{
    private final V def;
    private final KeyValue<S, K, V> value;
    
    ComposedDefaultKeyValue(V def, KeyValue<S, K, V> value)
    {
        this.def = Objects.requireNonNull(def, "def");
        this.value = Objects.requireNonNull(value, "value");
    }
    
    @SuppressWarnings("NullableProblems")
    @Override
    public V getDefaultValue()
    {
        return def;
    }
    
    @SuppressWarnings("NullableProblems")
    @Override
    public K key()
    {
        return value.key();
    }
    
    @Override
    public boolean isSet(S storage)
    {
        return value.isSet(storage);
    }
    
    @Override
    public Optional<V> get(S storage)
    {
        return value.get(storage);
    }
    
    @Override
    public void set(S storage, @NullOr V value)
    {
        this.value.set(storage, value);
    }
}
