/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.composition;

import com.rezzedup.util.valuables.KeyValue;
import com.rezzedup.util.valuables.DefaultMapValue;

import java.util.Map;

public class ComposedDefaultMapValue<K, V> extends ComposedDefaultKeyValue<Map<K, V>, K, V> implements DefaultMapValue<K, V>
{
    public ComposedDefaultMapValue(V def, KeyValue<Map<K, V>, K, V> value)
    {
        super(def, value);
    }
}
