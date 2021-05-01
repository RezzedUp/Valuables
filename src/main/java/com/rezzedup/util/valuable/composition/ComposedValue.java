/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable.composition;

import com.rezzedup.util.valuable.ValueGetter;
import com.rezzedup.util.valuable.ValueQuery;
import com.rezzedup.util.valuable.ValueSetter;
import com.rezzedup.util.valuable.Value;
import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;

public class ComposedValue<S, V> implements Value<S, V>
{
    private final ValueQuery<S> query;
    private final ValueGetter<S, V> getter;
    private final ValueSetter<S, V> setter;
    
    public ComposedValue(ValueQuery<S> query, ValueGetter<S, V> getter, ValueSetter<S, V> setter)
    {
        this.query = Objects.requireNonNull(query, "query");
        this.getter = Objects.requireNonNull(getter, "getter");
        this.setter = Objects.requireNonNull(setter, "setter");
    }
    
    @Override
    public final boolean isSet(S storage)
    {
        return query.isSet(storage);
    }
    
    @Override
    public final Optional<V> get(S storage)
    {
        return getter.get(storage);
    }
    
    @Override
    public final void set(S storage, @NullOr V value)
    {
        setter.set(storage, value);
    }
}
