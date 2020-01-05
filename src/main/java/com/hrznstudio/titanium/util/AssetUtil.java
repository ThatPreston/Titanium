/*
 * This file is part of Titanium
 * Copyright (C) 2020, Horizon Studio <contact@hrznstudio.com>.
 *
 * This code is licensed under GNU Lesser General Public License v3.0, the full license text can be found in LICENSE.txt
 */

package com.hrznstudio.titanium.util;

import com.hrznstudio.titanium.api.client.IAsset;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;

import java.awt.*;


public class AssetUtil {

    public static void drawAsset(Screen screen, IAsset asset, int xPos, int yPos) {
        Point offset = asset.getOffset();
        Rectangle area = asset.getArea();
        screen.getMinecraft().getTextureManager().bindTexture(asset.getResourceLocation());
        screen.blit(xPos + offset.x,
                yPos + offset.y,
                area.x,
                area.y,
                area.width,
                area.height);
    }

    public static void drawSelectingOverlay(int x, int y, int width, int height) {
        GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();
        AbstractGui.fill(x, y, width, height, -2130706433);
        GlStateManager.enableLighting();
        GlStateManager.disableDepthTest();
    }

    public static void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i = startX;
            startX = endX;
            endX = i;
        }
        AbstractGui.fill(startX, y, endX + 1, y + 1, color);
    }

    public static void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i = startY;
            startY = endY;
            endY = i;
        }
        AbstractGui.fill(x, startY + 1, x + 1, endY, color);
    }
}
