package com.gregtechceu.gtceu.data.data;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials.Color;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.tag.TagPrefix;
import com.gregtechceu.gtceu.api.tag.TagUtil;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class TagLoader {

    public static void init(RegistrateTagsProvider<Item> provider) {
        create(provider, lens, Color.White, GTItems.MATERIAL_ITEMS.get(lens, Glass).getId());
        create(provider, "pistons", rl("piston"), rl("sticky_piston"));
    }

    private static void create(RegistrateTagsProvider<Item> provider, String tagName, ResourceLocation... rls) {
        create(provider, TagUtil.createItemTag(tagName));
    }

    private static void create(RegistrateTagsProvider<Item> provider, TagPrefix prefix, Material material, ResourceLocation... rls) {
        create(provider, ChemicalHelper.getTag(prefix, material), rls);
    }

    private static void create(RegistrateTagsProvider<Item> provider, TagKey<Item> tagKey, ResourceLocation... rls) {
        TagBuilder builder = provider.getOrCreateRawBuilder(tagKey);
        for (ResourceLocation rl : rls) {
            builder.addElement(rl);
        }
        builder.build();
    }

    private static ResourceLocation rl(String name) {
        return new ResourceLocation(name);
    }
}