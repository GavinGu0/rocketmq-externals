/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.rocketmq.connect.runtime.converter;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ByteMapConverterTest {

    @Test
    public void testByteMapConverter() {
        Map<ByteBuffer, ByteBuffer> data = new HashMap<>();
        data.put(ByteBuffer.wrap("test_key".getBytes()), ByteBuffer.wrap("test_value".getBytes()));
        ByteMapConverter bmc = new ByteMapConverter();
        byte[] o2b = bmc.objectToByte(data);
        Map<ByteBuffer, ByteBuffer> b2o = bmc.byteToObject(o2b);
        assertThat(b2o.size()).isEqualTo(1);
        assertThat(b2o.keySet().size()).isEqualTo(1);
        assertThat(b2o.values().size()).isEqualTo(1);
        for (ByteBuffer key : b2o.keySet()) {
            assertThat(new String(key.array())).isEqualTo("test_key");
            assertThat(new String(b2o.get(key).array())).isEqualTo("test_value");
        }
    }
}