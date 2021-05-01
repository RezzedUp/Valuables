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
import java.util.function.Function;

@FunctionalInterface
public interface ValueGetter<S, V>
{
    @SuppressWarnings("ConstantConditions")
    static <S, V> ValueGetter<S, V> maybe(Function<S, @NullOr V> getter)
    {
        Objects.requireNonNull(getter, "getter");
        return storage -> Optional.ofNullable(getter.apply(storage));
    }
    
    Optional<V> get(S storage);
}
