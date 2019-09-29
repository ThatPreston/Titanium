package com.hrznstudio.titanium.material;

import java.util.HashMap;

public class ResourceRegistry {

    private static HashMap<String, ResourceMaterial> MATERIALS = new HashMap<>();

    public static ResourceMaterial getOrCreate(String type) {
        return MATERIALS.computeIfAbsent(type, s -> new ResourceMaterial(type));
    }

}
