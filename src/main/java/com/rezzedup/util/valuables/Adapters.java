/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

final class Adapters
{
    private Adapters() { throw new UnsupportedOperationException(); }
    
    static final Adapter<?, ?> IDENTITY = new Adapter<>()
    {
        @Override
        public Object deserialize(Object output) { return output; }
    
        @Override
        public Object serialize(Object input) { return input; }
    };
}
