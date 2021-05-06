/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

@FunctionalInterface
public interface Deserializer<S, D>
{
    @SuppressWarnings("unchecked")
    static <S, D> Deserializer<S, D> cast()
    {
        return serialized -> (D) serialized;
    }
    
    @NullOr D deserialize(S serialized);
}
