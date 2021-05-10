/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.util.Optional;

final class Adapters
{
    private Adapters() { throw new UnsupportedOperationException(); }
    
    static final Adapter<?, ?> IDENTITY = new Adapter<>()
    {
        @Override
        public Optional<Object> deserialize(Object serialized) { return Optional.of(serialized); }
    
        @Override
        public Optional<Object> serialize(Object deserialized) { return Optional.of(deserialized); }
    };
}
