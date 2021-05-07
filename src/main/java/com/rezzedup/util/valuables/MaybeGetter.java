/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.util.Objects;
import java.util.Optional;

/**
 * Gets a possible value from storage.
 *
 * @param <S>   storage type
 * @param <V>   value type
 */
@FunctionalInterface
public interface MaybeGetter<S, V>
{
    /**
     * Converts a direct getter into a possible value getter.
     *
     * @param getter    direct getter
     * @param <S>       storage type
     * @param <V>       value type
     *
     * @return  the direct getter wrapped by an optional
     */
    static <S, V> MaybeGetter<S, V> gets(Getter<S, V> getter)
    {
        Objects.requireNonNull(getter, "getter");
        return storage -> Optional.ofNullable(getter.get(storage));
    }
    
    /**
     * Gets the possible value from storage.
     *
     * @param storage   storage where the value may exist
     *
     * @return  the value if it was successfully
     *          retrieved, otherwise empty
     */
    Optional<V> get(S storage);
}
