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

public class Config {

    private General general;
    private Messages messages;

    public General getGeneral() {
        return general;
    }

    public Messages getMessages() {
        return messages;
    }

    public class General {

        private String icon;
        private boolean show_planet;
        private boolean show_moon;
        private boolean show_station;

        public String getIcon() {
            return icon;
        }

        public boolean shouldShowPlanet() {
            return show_planet;
        }

        public boolean shouldShowMoon() {
            return show_moon;
        }

        public boolean shouldShowStation() {
            return show_station;
        }
    }

    public class Messages {

        private String on_planet;
        private String on_moon;
        private String on_station;
        private String singleplayer;
        private String multiplayer;
        private String icon_text;

        public String getOnPlanet() {
            return on_planet;
        }

        public String getOnMoon() {
            return on_moon;
        }

        public String getOnStation() {
            return on_station;
        }

        public String getSingleplayer() {
            return singleplayer;
        }

        public String getMultiplayer() {
            return multiplayer;
        }

        public String getIconText() {
            return icon_text;
        }
    }
}
