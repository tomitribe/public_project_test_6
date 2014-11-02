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
package org.tomitribe.swizzle.stream;

import org.junit.Assert;
import org.tomitribe.util.IO;

import java.io.IOException;
import java.io.InputStream;

public class StreamAsserts {
    public static void assertFilter(final String before, final String after, Decorator decorator) throws IOException {

        final InputStream read = decorator.decorate(IO.read(before));

        final String output = IO.slurp(read);

        Assert.assertEquals(after, output);
    }
}
