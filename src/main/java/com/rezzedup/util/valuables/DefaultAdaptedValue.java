/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.util.Objects;

public interface DefaultAdaptedValue<S, O, V> extends AdaptedValue<S, O, V>, DefaultValue<S, V>
{
    static <S, O, V> DefaultAdaptedValue<S, O, V> of(V def, AdaptedValue<S, O, V> value)
    {
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(value, "value");
        
        return new DefaultAdaptedValue<>()
        {
            @Override
            public V getDefaultValue() { return def; }
            
            @Override
            public ValueAdapter<S, O, V> adapter() { return value.adapter(); }
        };
    }
}
