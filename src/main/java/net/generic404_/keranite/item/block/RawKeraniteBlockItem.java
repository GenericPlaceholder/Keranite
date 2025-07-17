package net.generic404_.keranite.item.block;

import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RawKeraniteBlockItem extends BlockItem {
    public RawKeraniteBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (stack.isOf(ModBlocks.ODD_ENDSTONE.asItem())) {
            tooltip.add(Text.translatable(Util.createTranslationKey("block", new Identifier(Keranite.MOD_ID, "odd_endstone.requires"))).formatted(Formatting.GRAY, Formatting.ITALIC));
            tooltip.add(Text.translatable(Util.createTranslationKey("block", new Identifier(Keranite.MOD_ID, "odd_endstone.suggests"))).formatted(Formatting.GRAY, Formatting.ITALIC));
            tooltip.add(Text.translatable(Util.createTranslationKey("block", new Identifier(Keranite.MOD_ID, "odd_endstone.disclaimer"))).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}
