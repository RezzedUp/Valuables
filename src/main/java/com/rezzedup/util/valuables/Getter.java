package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

/**
 * Gets a possibly-{@code null} value directly from storage.
 *
 * @param <S> storage type
 * @param <V> value type
 */
@FunctionalInterface
public
interface Getter<S, V>
{
    static <S, V> MaybeGetter<S, V> maybe(Getter<S, V> getter)
    {
        return MaybeGetter.gets(getter);
    }
    
    /**
     * Gets the value in its original type directly from
     * the provided storage.
     *
     * @param storage   the storage
     *
     * @return  the original value or {@code null}
     */
    public @NullOr V get(S storage); // redundantly 'public' for proper @NullOr placement
}
