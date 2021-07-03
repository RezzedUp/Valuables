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
 * Serializes an object.
 *
 * @param <D>   deserialized type
 * @param <S>   serialized type
 */
@FunctionalInterface
public interface Serializer<D, S>
{
    /**
     * Serializes the provided object.
     * If the object cannot be properly serialized,
     * then the result will be empty.
     *
     * @param deserialized  the object to serialize
     *
     * @return  the serialized result if successful,
     *          otherwise empty
     *
     * @implSpec    implementers should not throw any
     *              runtime exceptions or return null
     *              - always return an empty optional
     *              if serialization fails
     */
    Optional<S> serialize(D deserialized);
}
