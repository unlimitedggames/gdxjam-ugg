package com.gdxjam.desktop;

import java.io.File;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;


public class GamePacker {

    public static void main(String[] args) throws Exception {
        String inputDir = "";
        String outputDir = "";

        String path = "F:/Users/Cuellar/workspace/Projects/GDXJam/core/gfx";
        String path2 = "F:/Users/Cuellar/workspace/Projects/GDXJam/android/assets/data";

        TexturePacker.Settings setts = new TexturePacker.Settings();
        setts.filterMag = TextureFilter.Linear;
        setts.filterMin = TextureFilter.Linear;
        setts.bleed = true;
        setts.duplicatePadding = true;
        setts.edgePadding = true;
        setts.useIndexes = false;
        setts.paddingX = 2;
        setts.paddingY = 2;

        inputDir = path + "/0.16";
        outputDir = path2 + "/";
        TexturePacker.process(setts, inputDir, outputDir, "gui.atlas");
        inputDir = path + "/0.416";
        outputDir = path2 + "/0.416";
        TexturePacker.process(setts, inputDir, outputDir, "gui.atlas");
        inputDir = path + "/0.66";
        outputDir = path2 + "/0.66";
        TexturePacker.process(setts, inputDir, outputDir, "gui.atlas");
        inputDir = path + "/1";
        outputDir = path2 + "/1";
        setts.maxWidth = 2048;
        TexturePacker.process(setts, inputDir, outputDir, "gui.atlas");

        path = "F:/Users/Cuellar/workspace/Projects/GDXJam/core/gfx/gameAtlas";

        setts = new TexturePacker.Settings();
        setts.filterMag = TextureFilter.Linear;
        setts.filterMin = TextureFilter.Linear;
        setts.bleed = true;
        setts.duplicatePadding = true;
        setts.edgePadding = true;
        setts.useIndexes = false;
        setts.paddingX = 2;
        setts.paddingY = 2;

        inputDir = path + "/0.16";
        outputDir = path2 + "/";
        TexturePacker.process(setts, inputDir, outputDir, "game.atlas");
        inputDir = path + "/0.416";
        outputDir = path2 + "/0.416";
        TexturePacker.process(setts, inputDir, outputDir, "game.atlas");
        inputDir = path + "/0.66";
        outputDir = path2 + "/0.66";
        TexturePacker.process(setts, inputDir, outputDir, "game.atlas");
        inputDir = path + "/1";
        outputDir = path2 + "/1";
        setts.maxWidth = 2048;
        TexturePacker.process(setts, inputDir, outputDir, "game.atlas");
    }

}
