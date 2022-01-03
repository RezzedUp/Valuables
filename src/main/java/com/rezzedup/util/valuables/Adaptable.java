/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

/**
 * Represents something capable of being adapted.
 *
 * @param <S>   serialized type
 * @param <D>   deserialized type
 */
public interface Adaptable<S, D>
{
    /**
     * Gets the adapter. The same adapter should always be returned.
     *
     * @return the adapter
     */
    Adapter<S, D> adapter();
}
