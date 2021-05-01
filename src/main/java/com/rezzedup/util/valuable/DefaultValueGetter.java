/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable;

public interface DefaultValueGetter<S, V> extends Defaultable<V>, ValueGetter<S, V>
{
    default V getOrDefault(S storage)
    {
        return get(storage).orElseGet(this::getDefaultValue);
    }
}
