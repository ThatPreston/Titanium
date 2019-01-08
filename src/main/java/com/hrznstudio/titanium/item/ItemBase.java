/*
 * This file is part of Titanium
 * Copyright (C) 2018, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 *
 * This means no, you cannot steal this code. This is licensed for sole use by Horizon Studio and its subsidiaries, you MUST be granted specific written permission by Horizon Studio to use this code, thinking you have permission IS NOT PERMISSION!
 */
package com.hrznstudio.titanium.item;

import com.hrznstudio.titanium.api.internal.IModelRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
//import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ItemBase extends Item implements IModelRegistrar {

    public ItemBase(String name,Builder properties) {
        super(properties);
        setRegistryName(name);
    }

    @Override
    public void registerModels() {
        //ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (hasDetails(null)) {
            addDetails(null, stack, tooltip, flagIn.isAdvanced());
        }
        for (Key key : Key.values()) {
            if (hasDetails(key)) {
                if (key.isDown()) {
                    addDetails(key, stack, tooltip, flagIn.isAdvanced());
                } else {
                    //tooltip.add(new TextComponentString("Hold " + TextFormatting.YELLOW + key.getName() + TextFormatting.GRAY + " for more information")); TODO
                }
            }
        }
    }

    public void addDetails(@Nullable Key key, ItemStack stack, List<ITextComponent> tooltip, boolean advanced) {

    }

    public boolean hasDetails(@Nullable Key key) {
        return false;
    }

    public enum Key implements IStringSerializable {
        SHIFT(GLFW.GLFW_KEY_RIGHT_SHIFT, GLFW.GLFW_KEY_LEFT_SHIFT),
        CTRL(GLFW.GLFW_KEY_RIGHT_CONTROL, GLFW.GLFW_KEY_LEFT_CONTROL),
        ALT(GLFW.GLFW_KEY_RIGHT_ALT, GLFW.GLFW_KEY_LEFT_ALT);

        final String name;
        int[] keys;

        Key(int... keys) {
            this.keys = keys;
            this.name = name();
        }

        Key(int[] keysWin, String macName, int[] keysMac) {
            if (Minecraft.IS_RUNNING_ON_MAC) {
                this.keys = keysMac;
                this.name = macName;
            } else {
                this.keys = keysWin;
                this.name = name();
            }
        }

        public boolean isDown() {
            for (int key : keys)
                if (GLFW.glfwGetKey(Minecraft.getInstance().mainWindow.getHandle(), key) == GLFW.GLFW_PRESS)
                    return true;
            return false;
        }

        @Override
        @Nonnull
        public String getName() {
            return StringUtils.capitalize(name.toLowerCase());
        }
    }
}