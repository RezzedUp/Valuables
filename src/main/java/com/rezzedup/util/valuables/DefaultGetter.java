/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

/**
 * Gets a value from storage or a default fallback.
 *
 * @param <S>   storage type
 * @param <V>   value type
 */
public interface DefaultGetter<S, V> extends Defaultable<V>, Getter<S, V>
{
    /**
     * Gets a value from storage or the default fallback value if it doesn't exist.
     *
     * @param storage   the storage
     *
     * @return the value from storage or the default fallback
     */
    default V getOrDefault(S storage)
    {
        return get(storage).orElseGet(this::getDefaultValue);
    }
}
