/*
 * Copyright 2011 Toni Menzel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.tinybundles.core;

import java.io.InputStream;

import org.ops4j.pax.tinybundles.core.intern.AsyncRawBuilder;
import org.ops4j.pax.tinybundles.core.intern.BndBuilder;
import org.ops4j.pax.tinybundles.core.intern.TinyBundleImpl;
import org.ops4j.store.Store;
import org.ops4j.store.StoreFactory;

/**
 * Statically usable Tinybundles API.
 * Compatible with Pax Swissbox Tinybundles 1.x releases.
 */
public class TinyBundles {

    private static Store<InputStream> m_store = StoreFactory.defaultStore();

    /**
     * Start with a fresh bundle with this factory method.
     * You can then chain methodcalls thanks to the humane nature of {@link TinyBundle} interface.
     *
     * @param strategy
     *
     * @return a new instance of a  {@link TinyBundle}. This is almost always the startingpoint of any interaction with {@link TinyBundle}.
     */
    public static TinyBundle bundle( BuildableBundle strategy )
    {
        return new TinyBundleImpl( strategy,getStore() );
    }

    private static Store<InputStream> getStore()
    {
        return m_store;
    }

    public static BuildableBundle withBnd()
    {
        return new BndBuilder( with() );
    }

    public static BuildableBundle with()
    {
       // return new SynchronousRawBuilder();

        return new AsyncRawBuilder();
    }
}
