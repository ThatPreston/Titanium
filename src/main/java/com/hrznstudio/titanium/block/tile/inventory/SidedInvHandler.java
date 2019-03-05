/*
 * This file is part of Titanium
 * Copyright (C) 2019, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 *
 * This means no, you cannot steal this code. This is licensed for sole use by Horizon Studio and its subsidiaries, you MUST be granted specific written permission by Horizon Studio to use this code, thinking you have permission IS NOT PERMISSION!
 */

package com.hrznstudio.titanium.block.tile.inventory;

import com.hrznstudio.titanium.block.tile.sideness.IFacingHandler;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

import java.awt.*;
import java.util.HashMap;

public class SidedInvHandler extends PosInvHandler implements IFacingHandler {

    private int color;
    private HashMap<EnumFacing, FaceMode> facingModes;

    public SidedInvHandler(String name, int xPos, int yPos, int size) {
        super(name, xPos, yPos, size);
        this.color = EnumDyeColor.WHITE.func_196060_f();
        this.facingModes = new HashMap<>();
        for (EnumFacing value : EnumFacing.values()) {
            this.facingModes.put(value, FaceMode.ENABLED);
        }
    }

    @Override
    public HashMap<EnumFacing, FaceMode> getFacingModes() {
        return facingModes;
    }

    @Override
    public int getColor() {
        return new Color(color).getRGB();
    }

    public SidedInvHandler setColor(EnumDyeColor color) {
        this.color = color.func_196060_f();
        return this;
    }

    public SidedInvHandler setColor(int color) {
        this.color = color;
        return this;
    }

    @Override
    public Rectangle getRectangle() {
        int renderingOffset = 1;
        return new Rectangle(this.getXPos() - renderingOffset - 3, this.getYPos() - renderingOffset - 3, 18 * this.getXSize() + renderingOffset * 2 + 3, 18 * this.getYSize() + renderingOffset * 2 + 3);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = super.serializeNBT();
        NBTTagCompound compound = new NBTTagCompound();
        for (EnumFacing facing : facingModes.keySet()) {
            compound.setString(facing.getName(), facingModes.get(facing).name());
        }
        nbt.setTag("FacingModes", compound);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        if (nbt.hasKey("FacingModes")) {
            NBTTagCompound compound = nbt.getCompound("FacingModes");
            for (String face : compound.keySet()) {
                facingModes.put(EnumFacing.byName(face), FaceMode.valueOf(compound.getString(face)));
            }
        }
    }

}