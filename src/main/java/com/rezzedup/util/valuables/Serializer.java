/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
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
     * If the object cannot be properly serialized, then the result will be empty.
     *
     * <p><b>Note:</b> this should never throw any runtime exceptions or return null. An empty
     * optional should always be returned if serialization fails.</p>
     *
     * @param deserialized  the object to serialize
     *
     * @return the serialized result if successful, otherwise empty
     */
    Optional<S> serialize(D deserialized);
}
