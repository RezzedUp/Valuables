/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

/**
 * Represents a default value.
 *
 * @param <V>   value type
 */
@FunctionalInterface
public interface Defaultable<V>
{
    /**
     * Gets the default value.
     * The same value should always be returned.
     *
     * @return  the default value
     */
    V getDefaultValue();
}
