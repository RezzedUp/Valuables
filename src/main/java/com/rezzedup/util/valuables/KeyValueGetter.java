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
import java.util.function.BiFunction;

@FunctionalInterface
public interface KeyValueGetter<S, K, V>
{
    @SuppressWarnings("ConstantConditions")
    static <S, K, V> KeyValueGetter<S, K, V> maybe(BiFunction<S, K, @NullOr V> getter)
    {
        Objects.requireNonNull(getter, "getter");
        return (storage, key) -> Optional.ofNullable(getter.apply(storage, key));
    }
    
    Optional<V> get(S storage, K key);
}
