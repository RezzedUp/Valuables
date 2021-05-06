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
    /**
     * Gets the value in its original type directly from
     * the provided storage.
     *
     * @param storage   the storage
     *
     * @return  the original value or {@code null}
     */
    @NullOr V get(S storage);
}
