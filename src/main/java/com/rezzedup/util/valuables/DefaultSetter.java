/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

/**
 * Sets a value in storage with options for default values.
 *
 * @param <S>   storage type
 * @param <V>   value type
 */
public interface DefaultSetter<S, V> extends Setter<S, V>
{
	/**
	 * Sets the value in storage to the default value.
	 *
	 * @param storage   the storage
	 */
	void setAsDefault(S storage);
	
	/**
	 * Sets the value in storage to the default value
	 * if there isn't already an existing value yet.
	 *
	 * @param storage   the storage
	 */
	void setAsDefaultIfUnset(S storage);
}
