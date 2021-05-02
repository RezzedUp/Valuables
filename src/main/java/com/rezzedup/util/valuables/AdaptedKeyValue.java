/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Optional;

public interface AdaptedKeyValue<S, O, K, V> extends Adaptable<KeyValueAdapter<S, O, K, V>>, KeyValue<S, K, V>
{
    @Override
    default Optional<V> get(S storage)
    {
        return Optional.ofNullable(adapter().get(storage, key()));
    }
    
    @Override
    default void set(S storage, @NullOr V value)
    {
        adapter().set(storage, key(), value);
    }
}