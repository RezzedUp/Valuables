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

public interface Value<S, V> extends MaybeGetter<S, V>, Setter<S, V>
{
    static <S, V> Value<S, V> from(MaybeGetter<S, V> getter, Setter<S, V> setter)
    {
        Objects.requireNonNull(getter, "getter");
        Objects.requireNonNull(setter, "setter");
        
        return new Value<>()
        {
            @Override
            public final Optional<V> get(S storage) { return getter.get(storage); }
            
            @Override
            public final void set(S storage, @NullOr V updated) { setter.set(storage, updated); }
        };
    }
}
