/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

/**
 * Sets a value in storage.
 *
 * @param <S>   storage type
 * @param <V>   value type
 */
@FunctionalInterface
public interface Setter<S, V>
{
    /**
     * Sets the value within the provided storage to the input value or removes it if it's {@code null}.
     *
     * @param storage   the storage
     * @param updated   the value to set or
     *                  {@code null} to remove
     */
    void set(S storage, @NullOr V updated);
    
    /**
     * Removes the value from the provided storage. This is equivalent to calling
     * {@link #set(Object, Object) set(storage, value)} with a {@code null} value.
     *
     * @param storage   the storage
     */
    default void remove(S storage)
    {
        set(storage, null);
    }
}
