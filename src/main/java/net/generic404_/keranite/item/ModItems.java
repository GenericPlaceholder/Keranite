package net.generic404_.keranite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.item.usable.CallbackToken;
import net.generic404_.keranite.item.usable.DisposableCallbackToken;
import net.generic404_.keranite.item.usable.MinorAnchor;
import net.generic404_.keranite.item.weapons.Broadsword;
import net.generic404_.keranite.item.weapons.Javelin;
import net.generic404_.keranite.item.weapons.Rapier;
import net.generic404_.keranite.item.weapons.Verybroadsword;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item KERANITE_INGOT = registerItem("keranite_ingot", new Item(new FabricItemSettings().fireproof()));
	public static final Item RAW_KERANITE = registerItem("raw_keranite", new Item(new FabricItemSettings().fireproof()));

	public static final Item MINOR_KERANITE_ANCHOR = registerItem("minor_keranite_anchor", new MinorAnchor(new Item.Settings().fireproof().maxCount(8)));
	public static final Item CALLBACK_TOKEN = registerItem("callback_token", new CallbackToken(new Item.Settings().maxCount(1).fireproof()));
	public static final Item DISPOSABLE_CALLBACK_TOKEN = registerItem("disposable_callback_token", new DisposableCallbackToken(new Item.Settings().maxCount(16)));

	public static final Item BROADSWORD = registerItem("broadsword", new Broadsword(new Item.Settings().fireproof()));
	public static final Item RAPIER = registerItem("rapier", new Rapier(new Item.Settings().fireproof()));
	public static final Item VERYBROADSWORD = registerItem("verybroadsword", new Verybroadsword(new Item.Settings()));
	public static final Item JAVELIN = registerItem("javelin",new Javelin(new Item.Settings().fireproof()));

	public static final Item LIGHT_HELMET = registerItem("light_helmet", new ArmorItem(KeraniteArmorMaterials.LIGHT, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item LIGHT_CHESTPLATE = registerItem("light_chestplate", new KeraniteArmorItem(KeraniteArmorMaterials.LIGHT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item LIGHT_LEGGINGS = registerItem("light_leggings", new ArmorItem(KeraniteArmorMaterials.LIGHT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item LIGHT_BOOTS = registerItem("light_boots", new ArmorItem(KeraniteArmorMaterials.LIGHT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

	public static final Item HEAVY_HELMET = registerItem("heavy_helmet", new KeraniteArmorItem(KeraniteArmorMaterials.HEAVY, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item HEAVY_CHESTPLATE = registerItem("heavy_chestplate", new KeraniteArmorItem(KeraniteArmorMaterials.HEAVY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item HEAVY_LEGGINGS = registerItem("heavy_leggings", new KeraniteArmorItem(KeraniteArmorMaterials.HEAVY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item HEAVY_BOOTS = registerItem("heavy_boots", new KeraniteArmorItem(KeraniteArmorMaterials.HEAVY, ArmorItem.Type.BOOTS, new FabricItemSettings()));

	public static final Item THIGHHIGHS_PINK = registerItem("thighhighs_pink", new ArmorItem(KeraniteArmorMaterials.SOCK_PINK, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item THIGHHIGHS_BLACK = registerItem("thighhighs_black", new ArmorItem(KeraniteArmorMaterials.SOCK_BLACK, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item THIGHHIGHS_BLUE = registerItem("thighhighs_blue", new ArmorItem(KeraniteArmorMaterials.SOCK_BLUE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item THIGHHIGHS_TRANS = registerItem("thighhighs_trans", new ArmorItem(KeraniteArmorMaterials.SOCK_TRANS, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item THIGHHIGHS_LESB = registerItem("thighhighs_lesb", new ArmorItem(KeraniteArmorMaterials.SOCK_LESB, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item THIGHHIGHS_GAY = registerItem("thighhighs_gay", new ArmorItem(KeraniteArmorMaterials.SOCK_GAY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));

	private static void addItemsToTabIng(FabricItemGroupEntries entries){
		entries.add(KERANITE_INGOT);
		entries.add(RAW_KERANITE);
	}
	private static void addItemsToTabCom(FabricItemGroupEntries entries){
		entries.add(CALLBACK_TOKEN);
		entries.add(DISPOSABLE_CALLBACK_TOKEN);
		entries.add(BROADSWORD);
		entries.add(RAPIER);
	}

	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(Keranite.MOD_ID, name), item);
	}

	public static void registerModItems(){
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToTabIng);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToTabCom);
	}
}