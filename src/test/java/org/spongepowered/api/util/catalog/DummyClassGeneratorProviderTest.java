/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.util.catalog;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.spongepowered.api.util.generator.dummy.DummyClassGeneratorProvider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DummyClassGeneratorProviderTest {

    private DummyClassGeneratorProvider createProvider() {
        return new DummyClassGeneratorProvider("org.spongepowered.test.dummy");
    }

    @Test
    public void testCreate_Simple()
            throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        assertThrows(UnsupportedOperationException.class, () -> {
            DummyClassGeneratorProvider provider = this.createProvider();
            SimpleInterface result = provider.create(SimpleInterface.class,
                    UnsupportedOperationException.class).getConstructor(String.class).newInstance("BLAH");

            result.foo();
        });
    }

    @Test
    public void testCreate_SubInterface() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        assertThrows(UnsupportedOperationException.class, () -> {
            DummyClassGeneratorProvider provider = this.createProvider();
            SubInterface result = provider.create(SubInterface.class,
                    UnsupportedOperationException.class).getConstructor(String.class).newInstance("BLAH");

            result.blah();
        });
    }

    @Test
    public void testCreate_SubInterfaceSubMethod()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        assertThrows(UnsupportedOperationException.class, () -> {
            DummyClassGeneratorProvider provider = this.createProvider();
            SubInterface result = provider.create(SubInterface.class,
                    UnsupportedOperationException.class).getConstructor(String.class).newInstance("BLAH");

            result.test();
        });
    }

    public interface SimpleInterface {

        static void static_method() {
        }

        String foo() throws IOException;

        void blah();

    }

    public interface SubInterface extends SimpleInterface {

        int test();

    }

}
