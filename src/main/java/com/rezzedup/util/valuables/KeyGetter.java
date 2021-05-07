package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

@FunctionalInterface
public interface KeyGetter<S, K, O>
{
    static <S, K, V> MaybeKeyGetter<S, K, V> maybe(KeyGetter<S, K, V> getter)
    {
        return MaybeKeyGetter.gets(getter);
    }
    
    @NullOr O get(S storage, K key);
}
