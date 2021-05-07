/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CompositionTests
{
    @Test
    public void testMapComposition()
    {
        Map<String, Number> numbersByName = new HashMap<>();
        
        DefaultKeyValue<Map<String, Number>, String, Number> ten =
            DefaultKeyValue.defaults(10, KeyValue.from("ten", KeyGetter.maybe(Map::get), Map::put));
    
        ten.setAsDefault(numbersByName);
        assertEquals(10, numbersByName.get("ten"));
        
        DefaultAdaptedKeyValue<Map<String, Number>, Number, String, Double> fiveAndAHalf =
            DefaultAdaptedKeyValue.defaults(
                5.5,
                AdaptedKeyValue.adapts(
                    KeyValue.from("fiveAndAHalf", KeyGetter.maybe(Map::get), Map::put),
                    Adapter.subtype(num -> (Double) num)
                )
            );
        
        fiveAndAHalf.setAsDefault(numbersByName);
        assertEquals(5.5, numbersByName.get("fiveAndAHalf"));
        
        fiveAndAHalf.remove(numbersByName);
        assertEquals(5.5, fiveAndAHalf.getOrDefault(numbersByName));
    }
}
