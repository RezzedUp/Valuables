/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuable>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuable;

import com.rezzedup.util.valuable.composition.ComposedValue;

public interface Value<S, V> extends ValueGetter<S, V>, ValueQuery<S>, ValueSetter<S, V>
{
    static <S, V> Value<S, V> compose(ValueQuery<S> query, ValueGetter<S, V> getter, ValueSetter<S, V> setter)
    {
        return new ComposedValue<>(query, getter, setter);
    }
}
