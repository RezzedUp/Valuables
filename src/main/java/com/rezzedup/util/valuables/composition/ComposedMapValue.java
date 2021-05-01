/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.composition;

import com.rezzedup.util.valuables.KeyValueGetter;
import com.rezzedup.util.valuables.MapValue;

import java.util.Map;

public class ComposedMapValue<K, V> extends ComposedKeyValue<Map<K, V>, K, V> implements MapValue<K, V>
{
    public ComposedMapValue(K key)
    {
        super(key, Map::containsKey, KeyValueGetter.maybe(Map::get), Map::put);
    }
}
