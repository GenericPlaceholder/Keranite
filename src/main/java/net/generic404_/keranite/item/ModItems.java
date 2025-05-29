package net.generic404_.keranite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.item.usable.CallbackToken;
import net.generic404_.keranite.item.usable.MinorAnchor;
import net.generic404_.keranite.item.weapons.Broadsword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item KERANITE_INGOT = registerItem("keranite_ingot", new Item(new FabricItemSettings().fireproof()));
	public static final Item RAW_KERANITE = registerItem("raw_keranite", new Item(new FabricItemSettings().fireproof()));
	public static final Item MINOR_KERANITE_ANCHOR = registerItem("minor_keranite_anchor", new MinorAnchor(new Item.Settings().fireproof().maxCount(8)));
	public static final Item KERANITE_BROADSWORD = registerItem("keranite_broadsword", new Broadsword(new Item.Settings().fireproof()));
	public static final Item CALLBACK_TOKEN = registerItem("callback_token", new CallbackToken(new Item.Settings().maxCount(16).fireproof()));

	private static void addItemsToTabIng(FabricItemGroupEntries entries){
		entries.add(KERANITE_INGOT);
		entries.add(RAW_KERANITE);
	}
	private static void addItemsToTabCom(FabricItemGroupEntries entries){
		entries.add(MINOR_KERANITE_ANCHOR);
		entries.add(KERANITE_BROADSWORD);
		entries.add(CALLBACK_TOKEN);
	}

	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(Keranite.MOD_ID, name), item);
	}

	public static void registerModItems(){
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToTabIng);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToTabCom);
	}
}