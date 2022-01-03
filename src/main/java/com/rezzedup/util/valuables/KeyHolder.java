/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

/**
 * Represents an association with a key.
 *
 * @param <K>   key type
 */
@FunctionalInterface
public interface KeyHolder<K>
{
    /**
     * Gets the key. The same key should always be returned.
     *
     * @return the key
     */
    K key();
}
