/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable.composition;

import com.rezzedup.util.valuable.KeyValueGetter;
import com.rezzedup.util.valuable.KeyValueQuery;
import com.rezzedup.util.valuable.KeyValueSetter;
import com.rezzedup.util.valuable.KeyValue;
import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;

public class ComposedKeyValue<S, K, V> implements KeyValue<S, K, V>
{
    private final K key;
    private final KeyValueQuery<S, K> query;
    private final KeyValueGetter<S, K, V> getter;
    private final KeyValueSetter<S, K, V> setter;
    
    public ComposedKeyValue(K key, KeyValueQuery<S, K> query, KeyValueGetter<S, K, V> getter, KeyValueSetter<S, K, V> setter)
    {
        this.key = Objects.requireNonNull(key, "key");
        this.query = Objects.requireNonNull(query, "query");
        this.getter = Objects.requireNonNull(getter, "getter");
        this.setter = Objects.requireNonNull(setter, "setter");
    }
    
    @SuppressWarnings("NullableProblems")
    @Override
    public final K key()
    {
        return key;
    }
    
    @Override
    public final boolean isSet(S storage)
    {
        return query.isSet(storage, key);
    }
    
    @Override
    public final Optional<V> get(S storage)
    {
        return getter.get(storage, key);
    }
    
    @Override
    public final void set(S storage, @NullOr V value)
    {
        setter.set(storage, key, value);
    }
}
