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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ConfigSerializer implements JsonSerializer<Config> {

    @Override
    public JsonElement serialize(Config src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject general = new JsonObject();
        general.addProperty("show_planet", src.getGeneral().shouldShowPlanet());
        general.addProperty("show_moon", src.getGeneral().shouldShowMoon());
        general.addProperty("show_station", src.getGeneral().shouldShowStation());
        general.addProperty("icon", src.getGeneral().getIcon());

        JsonObject messages = new JsonObject();
        messages.addProperty("on_planet", src.getMessages().getOnPlanet());
        messages.addProperty("on_moon", src.getMessages().getOnMoon());
        messages.addProperty("on_station", src.getMessages().getOnStation());
        messages.addProperty("singleplayer", src.getMessages().getSingleplayer());
        messages.addProperty("multiplayer", src.getMessages().getMultiplayer());
        messages.addProperty("icon_text", src.getMessages().getIconText());

        JsonObject result = new JsonObject();
        result.add("general", general);
        result.add("messages", messages);

        return result;
    }
}
