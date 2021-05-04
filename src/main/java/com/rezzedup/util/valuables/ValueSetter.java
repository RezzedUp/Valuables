/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
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
public interface ValueSetter<S, V>
{
    /**
     * Sets the value within the provided storage to the
     * input value or removes it if it's {@code null}.
     *
     * @param storage   the storage
     * @param value     the value to set
     *                  or {@code null} to remove
     */
    void set(S storage, @NullOr V value);
    
    /**
     * Removes the value from the provided storage.
     * This is equivalent to calling
     * {@link #set(Object, Object) set(storage, value)}
     * with a {@code null} value.
     *
     * @param storage   the storage
     */
    default void remove(S storage)
    {
        set(storage, null);
    }
}
