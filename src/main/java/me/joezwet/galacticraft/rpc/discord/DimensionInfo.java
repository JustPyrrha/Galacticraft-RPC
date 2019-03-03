/*
 * Copyright (c) 2019 Joe van der Zwet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.joezwet.galacticraft.rpc.discord;

import org.apache.logging.log4j.LogManager;

import java.util.HashMap;

public class DimensionInfo {

    private HashMap<Integer, Dimension> dimensionMap;

    public DimensionInfo() {
        dimensionMap = new HashMap<>();
    }

    public void registerDimensionInfo(int dimensionId, Dimension dimension) {
        if(dimensionMap.containsKey(dimensionId)) {
            LogManager.getLogger("Galacticraft RPC").error("Attempted to register dimension with an id that is already registered.");
            return;
        }
        dimensionMap.put(dimensionId, dimension);
    }

    public void clearDimensions() {
        dimensionMap.clear();
    }

    public Dimension getDimensionInfo(int dimensionId) {
        if(!dimensionMap.containsKey(dimensionId)) {
            return null;
        }
        return dimensionMap.get(dimensionId);
    }

}
