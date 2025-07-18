package net.generic404_.keranite.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup KERANITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Keranite.MOD_ID, "keranite_ingot"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.keranite"))
                    .icon(()->new ItemStack(ModItems.KERANITE_INGOT)).entries((displayContext, entries) -> {
                        //this is where you put items into the group
                        entries.add(ModItems.RAW_KERANITE);
                        entries.add(ModItems.KERANITE_INGOT);
                        entries.add(ModBlocks.KERANITE_BLOCK);
                        entries.add(ModBlocks.ODD_ENDSTONE);

                        entries.add(ModItems.CALLBACK_TOKEN);
                        entries.add(ModItems.DISPOSABLE_CALLBACK_TOKEN);

                        entries.add(ModItems.BROADSWORD);
                        entries.add(ModItems.RAPIER);
                        //entries.add(ModItems.JAVELIN);

                        entries.add(ModItems.LIGHT_HELMET);
                        entries.add(ModItems.LIGHT_CHESTPLATE);
                        entries.add(ModItems.LIGHT_LEGGINGS);
                        entries.add(ModItems.LIGHT_BOOTS);

                        entries.add(ModItems.HEAVY_HELMET);
                        entries.add(ModItems.HEAVY_CHESTPLATE);
                        entries.add(ModItems.HEAVY_LEGGINGS);
                        entries.add(ModItems.HEAVY_BOOTS);

                        entries.add(ModItems.THIGHHIGHS_PINK);
                        entries.add(ModItems.THIGHHIGHS_BLACK);
                        entries.add(ModItems.THIGHHIGHS_BLUE);
                        entries.add(ModItems.THIGHHIGHS_TRANS);
                        entries.add(ModItems.THIGHHIGHS_LESB);
                        entries.add(ModItems.THIGHHIGHS_GAY);
                    }).build());

    public static void registerItemGroups(){

    }
}
