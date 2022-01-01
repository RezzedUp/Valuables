/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;
import java.util.Optional;

/**
 * A value represented by a getter and a setter.
 *
 * @param <S>   storage type
 * @param <V>   value type
 */
public interface Value<S, V> extends Getter<S, V>, Setter<S, V>
{
	/**
	 * Creates a new {@code Value} by delegating
	 * to the provided arguments.
	 *
	 * @param getter    the value getter
	 * @param setter    the value setter
	 * @param <S>       storage type
	 * @param <V>       value type
	 *
	 * @return  a new instance composed of the arguments
	 */
	static <S, V> Value<S, V> of(Getter<S, V> getter, Setter<S, V> setter)
	{
		Objects.requireNonNull(getter, "getter");
		Objects.requireNonNull(setter, "setter");
		
		return new Value<>()
		{
			@Override
			public final Optional<V> get(S storage) { return getter.get(storage); }
			
			@Override
			public final void set(S storage, @NullOr V updated) { setter.set(storage, updated); }
		};
	}
}
