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
            DefaultKeyValue.of(10, KeyValue.of("ten", KeyGetter.maybe(Map::get), Map::put));
    
        ten.setAsDefault(numbersByName);
        
        assertEquals(10, numbersByName.get("ten"));
        assertEquals(10, ten.get(numbersByName).orElseThrow());
        
        DefaultAdaptedKeyValue<Map<String, Number>, String, Number, Double> fiveAndAHalf =
            DefaultAdaptedKeyValue.of(
                5.5,
                AdaptedKeyValue.of(
                    Adapter.subtype(num -> (Double) num),
                    KeyValue.of("fiveAndAHalf", KeyGetter.maybe(Map::get), Map::put)
                )
            );
        
        fiveAndAHalf.setAsDefault(numbersByName);
        assertEquals(5.5, fiveAndAHalf.get(numbersByName).orElseThrow());
        
        fiveAndAHalf.remove(numbersByName);
        assertEquals(5.5, fiveAndAHalf.getOrDefault(numbersByName));
    }
}
