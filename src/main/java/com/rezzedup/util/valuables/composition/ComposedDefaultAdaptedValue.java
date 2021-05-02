/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.composition;

import com.rezzedup.util.valuables.AdaptedValue;
import com.rezzedup.util.valuables.DefaultAdaptedValue;
import com.rezzedup.util.valuables.ValueAdapter;

import java.util.Objects;

public class ComposedDefaultAdaptedValue<S, O, V> implements DefaultAdaptedValue<S, O, V>
{
    private final V def;
    private final AdaptedValue<S, O, V> value;
    
    public ComposedDefaultAdaptedValue(V def, AdaptedValue<S, O, V> value)
    {
        this.def = Objects.requireNonNull(def, "def");
        this.value = Objects.requireNonNull(value, "value");
    }
    
    @Override
    public V getDefaultValue()
    {
        return def;
    }
    
    @Override
    public ValueAdapter<S, O, V> adapter()
    {
        return value.adapter();
    }
    
    @Override
    public boolean isSet(S storage)
    {
        return value.isSet(storage);
    }
}
