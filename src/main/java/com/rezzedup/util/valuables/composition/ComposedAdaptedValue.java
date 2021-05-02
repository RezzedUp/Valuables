/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.composition;

import com.rezzedup.util.valuables.AdaptedValue;
import com.rezzedup.util.valuables.ValueAdapter;
import com.rezzedup.util.valuables.ValueQuery;

import java.util.Objects;

public class ComposedAdaptedValue<S, O, V> implements AdaptedValue<S, O, V>
{
    private final ValueAdapter<S, O, V> adapter;
    private final ValueQuery<S> query;
    
    public ComposedAdaptedValue(ValueAdapter<S, O, V> adapter, ValueQuery<S> query)
    {
        this.adapter = Objects.requireNonNull(adapter, "adapter");
        this.query = Objects.requireNonNull(query, "query");
    }
    
    @Override
    public ValueAdapter<S, O, V> adapter()
    {
        return adapter;
    }
    
    @Override
    public boolean isSet(S storage)
    {
        return query.isSet(storage);
    }
}
