/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable;

import pl.tlinkowski.annotation.basic.NullOr;

@FunctionalInterface
public interface KeyValueSetter<S, K, V>
{
    void set(S storage, K key, @NullOr V value);
}
