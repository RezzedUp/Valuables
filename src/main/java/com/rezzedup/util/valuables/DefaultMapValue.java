/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import com.rezzedup.util.valuables.composition.ComposedDefaultMapValue;

import java.util.Map;

public interface DefaultMapValue<K, V> extends DefaultKeyValue<Map<K, V>, K, V>
{
    static <K, V> DefaultMapValue<K, V> at(K key, V def)
    {
        return new ComposedDefaultMapValue<>(def, MapValue.at(key));
    }
}
