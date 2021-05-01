/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable;

import com.rezzedup.util.valuable.composition.ComposedKeyValue;

public interface KeyValue<S, K, V> extends KeyHolder<K>, Value<S, V>
{
    static <S, K, V> KeyValue<S, K, V> compose(K key, KeyValueQuery<S, K> query, KeyValueGetter<S, K, V> getter, KeyValueSetter<S, K, V> setter)
    {
        return new ComposedKeyValue<>(key, query, getter, setter);
    }
}
