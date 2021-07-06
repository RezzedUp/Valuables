/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.util.Optional;

/**
 * Deserializes an object.
 *
 * @param <S>   serialized type
 * @param <D>   deserialized type
 */
@FunctionalInterface
public interface Deserializer<S, D>
{
    /**
     * Deserializes the provided object.
     * If the object cannot be properly deserialized,
     * then the result will be empty.
     *
     * <p><b>Note:</b> this should never throw any
     * runtime exceptions or return null. An empty
     * optional should always be returned if
     * deserialization fails.</p>
     *
     * @param serialized    the object to deserialize
     *
     * @return  the deserialized result if successful,
     *          otherwise empty
     */
    Optional<D> deserialize(S serialized);
}
