/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
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

/**
 * Gets a possible value from storage at a specified key.
 *
 * @param <S>   storage type
 * @param <K>   key type
 * @param <V>   value type
 */
@FunctionalInterface
public interface KeyGetter<S, K, V>
{
    /**
     * Converts a direct key getter into an optional key getter.
     *
     * @param getter    direct key getter
     * @param <S>       storage type
     * @param <K>       key type
     * @param <V>       value type
     *
     * @return the direct getter wrapped by an optional
     */
    @SuppressWarnings("ConstantConditions")
    static <S, K, V> KeyGetter<S, K, V> maybe(BiFunction<S, K, @NullOr V> getter)
    {
        Objects.requireNonNull(getter, "getter");
        return (storage, key) -> Optional.ofNullable(getter.apply(storage, key));
    }
    
    /**
     * Gets a possible value from storage at the specified key.
     *
     * @param storage   storage that may contain the value
     * @param key       the key
     *
     * @return the value if it was successfully retrieved, otherwise empty
     */
    Optional<V> get(S storage, K key);
}
