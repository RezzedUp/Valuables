/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AdapterTests
{
    public enum Example
    {
        A,
        B,
        C,
        D;
    }
    
    @Test
    public void testStringAdapters()
    {
        Adapter<String, Byte> byteAdapter = Adapts.string().intoByte();
        
        assertThat(byteAdapter.deserialize("127")).contains((byte) 127);
        assertThat(byteAdapter.deserialize("0xFF")).isEmpty();
        assertThat(byteAdapter.serialize((byte) 0xFF)).contains("-1");
        
        Adapter<String, Example> enumAdapter = Adapts.string().intoEnum(Example.class);
        
        assertThat(enumAdapter.deserialize("C")).containsSame(Example.C);
        assertThat(enumAdapter.deserialize("F")).isEmpty();
    }
    
    @Test
    public void testObjectAdapters()
    {
        Adapter<Object, Float> floatAdapter = Adapts.object().intoFloat();
        
        assertThat(floatAdapter.deserialize(5L)).contains(5F);
        assertThat(floatAdapter.serialize(10F)).containsInstanceOf(Float.class);
        
        Adapter<Object, Example> enumAdapter = Adapts.object().intoEnum(Example.class);
        
        assertThat(enumAdapter.serialize(Example.A))
            .containsSame(enumAdapter.deserialize(Example.A).orElseThrow());
    }
}
