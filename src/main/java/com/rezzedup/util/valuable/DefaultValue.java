/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable;

import com.rezzedup.util.valuable.composition.ComposedDefaultValue;

public interface DefaultValue<S, V> extends DefaultValueGetter<S, V>, DefaultValueSetter<S, V>,  Value<S, V>
{
    static <S, V> DefaultValue<S, V> compose(V def, Value<S, V> value)
    {
        return new ComposedDefaultValue<>(def, value);
    }
    
    @Override
    default void setAsDefault(S storage)
    {
        if (!isSet(storage)) { set(storage, getDefaultValue()); }
    }
}
