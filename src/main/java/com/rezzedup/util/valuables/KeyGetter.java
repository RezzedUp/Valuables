package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

@FunctionalInterface
public interface KeyGetter<S, K, O>
{
    @NullOr O get(S storage, K key);
}
