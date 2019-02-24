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
            LogManager.getLogger("Galacticraft RPC").error("Could not find dimension info for dimension id " + dimensionId);
            return null;
        }
        return dimensionMap.get(dimensionId);
    }

    public class Dimension {
        private final String state;
        private final String details;
        private final String largeImageKey;
        private final String largeImageText;

        public Dimension(String state, String details, String largeImageKey, String largeImageText) {
            this.state = state;
            this.details = details;
            this.largeImageKey = largeImageKey;
            this.largeImageText = largeImageText;
        }

        public String getState() {
            return state;
        }

        public String getDetails() {
            return details;
        }

        public String getLargeImageKey() {
            return largeImageKey;
        }

        public String getLargeImageText() {
            return largeImageText;
        }
    }

}
