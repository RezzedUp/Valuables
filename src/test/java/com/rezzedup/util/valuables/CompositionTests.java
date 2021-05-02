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
        Map<String, Integer> intsByName = new HashMap<>();
        
        DefaultKeyValue<Map<String, Integer>, String, Integer> ten =
            DefaultKeyValue.of(10, KeyValue.of(
                "ten", Map::containsKey, KeyValueGetter.maybe(Map::get), Map::put
            ));
    
        ten.setAsDefault(intsByName);
        
        DefaultMapValue<String, Integer> twelve = DefaultMapValue.of("twelve", 12);
        twelve.setAsDefault(intsByName);
        
        assertEquals(10, intsByName.get("ten"));
        assertEquals(12, intsByName.get("twelve"));
    }
}
