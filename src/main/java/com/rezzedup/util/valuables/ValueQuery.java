/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

/**
 * Checks whether or not a value exists in storage.
 *
 * @param <S>   storage type
 */
@FunctionalInterface
public interface ValueQuery<S>
{
    /**
     * Checks whether or not the value exists
     * within the provided storage.
     *
     * @param storage   the storage
     *
     * @return  {@code true} if the value exists,
     *          otherwise {@code false}
     */
    boolean isSet(S storage);
}
