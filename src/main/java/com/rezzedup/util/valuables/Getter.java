/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Gets a possible value from storage.
 *
 * @param <S>   storage type
 * @param <V>   value type
 */
@FunctionalInterface
public interface Getter<S, V>
{
	/**
	 * Converts a direct getter into an optional getter.
	 *
	 * @param getter    direct getter
	 * @param <S>       storage type
	 * @param <V>       value type
	 *
	 * @return  the direct getter wrapped by an optional
	 */
	@SuppressWarnings("ConstantConditions")
	static <S, V> Getter<S, V> maybe(Function<S, @NullOr V> getter)
	{
		Objects.requireNonNull(getter, "getter");
		return storage -> Optional.ofNullable(getter.apply(storage));
	}
	
	/**
	 * Gets the possible value from storage.
	 *
	 * @param storage   storage that may contain the value
	 *
	 * @return  the value if it was successfully
	 *          retrieved, otherwise empty
	 */
	Optional<V> get(S storage);
}
