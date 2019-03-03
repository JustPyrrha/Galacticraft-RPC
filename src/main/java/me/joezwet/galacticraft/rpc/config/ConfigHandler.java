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

package me.joezwet.galacticraft.rpc.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.joezwet.galacticraft.rpc.util.Strings;
import me.joezwet.galacticraft.rpc.util.Utils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class ConfigHandler {

    public Config getConfig() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Config.class, new ConfigSerializer()).create();
        File file = new File(
                Utils.getMcDir(),
                Strings.CONFIG_PATH
        );
        String configS = "";
        if (!file.exists()) {
            LogManager.getLogger("Galacticraft RPC").warn("Could not find config file, generating it for you.");
            try {
                FileWriter writer = new FileWriter(file);
                String defaultConfig = "{\n" +
                        "    \"general\": {\n" +
                        "        \"icon\": \"NONE\",\n" +
                        "        \"show_planet\": true,\n" +
                        "        \"show_moon\": true,\n" +
                        "        \"show_station\": true\n" +
                        "    },\n" +
                        "    \"messages\": {\n" +
                        "        \"on_planet\": \"Exploring @PLANET@\",\n" +
                        "        \"on_moon\": \"Exploring @PLANET@\",\n" +
                        "        \"on_station\": \"Orbiting @PARENT@\",\n" +
                        "        \"singleplayer\": \"Playing Singleplayer\",\n" +
                        "        \"multiplayer\": \"Playing Multiplayer\",\n" +
                        "        \"icon_text\": \"Playing X Modpack on the Twitch Launcher.\"\n" +
                        "    }\n" +
                        "}";
                writer.write(defaultConfig);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            List<String> configL = Files.readAllLines(file.toPath());
            for (String s : configL) {
                configS += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(configS, Config.class);
    }
}
