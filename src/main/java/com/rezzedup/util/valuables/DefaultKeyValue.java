/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import com.rezzedup.util.valuables.composition.ComposedDefaultKeyValue;

public interface DefaultKeyValue<S, K, V> extends DefaultValue<S, V>, KeyValue<S, K, V>
{
    static <S, K, V> DefaultKeyValue<S, K, V> compose(V def, KeyValue<S, K, V> value)
    {
        return new ComposedDefaultKeyValue<>(def, value);
    }
}
