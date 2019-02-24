package me.joezwet.galacticraft.rpc.discord;

import org.apache.logging.log4j.LogManager;

import java.util.HashMap;

public class DimensionInfo {

    private HashMap<Integer, Dimension> dimensionMap;

    public DimensionInfo() {
        dimensionMap = new HashMap<>();
    }

    public void registerDimnesionInfo(int dimensionId, Dimension dimension) {
        if(dimensionMap.containsKey(dimensionId)) {
            LogManager.getLogger("Galacticraft RPC").error("Attempted to register dimension with an id that is already registered.");
            return;
        }
        dimensionMap.put(dimensionId, dimension);
    }

    public Dimension getDimensionInfo(int dimensionId) {
        if(!dimensionMap.containsKey(dimensionId)) {
            return null;
        }
        return dimensionMap.get(dimensionId);
    }

}
