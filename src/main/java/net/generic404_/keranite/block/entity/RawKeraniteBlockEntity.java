package net.generic404_.keranite.block.entity;

import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.block.RawKeranite;
import net.generic404_.keranite.util.NearbyUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class RawKeraniteBlockEntity extends BlockEntity {
    public RawKeraniteBlockEntity(BlockPos pos, BlockState state) {
        //super(CustomBlockEntities.RAW_KERANITE_BLOCK_ENTITY_TYPE, pos, state);
        super(BlockEntityType.FURNACE, pos, state);
    }

    int heat = 0;
    boolean smelted = false;

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Heat", this.heat);
        nbt.putBoolean("Smelted", this.smelted);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld) {
            BlockState[] surroundings = {
                    world.getBlockState(pos.add(0,0,1)),
                    world.getBlockState(pos.add(0,0,-1)),
                    world.getBlockState(pos.add(1,0,0)),
                    world.getBlockState(pos.add(-1,0,0)),
                    world.getBlockState(pos.add(0,1,0)),
                    world.getBlockState(pos.add(0,-1,0))
            };
            BlockPos[] touchingPositions = {
                    pos.add(0,0,1),
                    pos.add(0,0,-1),
                    pos.add(1,0,0),
                    pos.add(-1,0,0),
                    pos.add(0,1,0),
                    pos.add(0,-1,0)
            };

            int surroundingTemp = 0;
            int counter0 = 0;
            for(BlockState blockState : surroundings){
                if(blockState.isOf(Blocks.POWDER_SNOW)){
                    surroundingTemp -= 40;
                    world.removeBlock(touchingPositions[counter0], true);
                } else
                if(blockState.isIn(BlockTags.ICE)||blockState.isIn(BlockTags.SNOW)){
                    surroundingTemp -= 20;
                    world.removeBlock(touchingPositions[counter0], true);
                } else
                if(blockState.isOf(Blocks.WATER)){
                    surroundingTemp -= 2;
                    world.removeBlock(touchingPositions[counter0], true);
                } else
                if(blockState.isIn(BlockTags.FIRE)||blockState.isIn(BlockTags.STRIDER_WARM_BLOCKS)){
                    surroundingTemp += 10;
                    world.removeBlock(touchingPositions[counter0], true);
                } else
                if(blockState.isOf(Blocks.LAVA)){
                    surroundingTemp += 40;
                    world.removeBlock(touchingPositions[counter0], true);
                }
                counter0++;
            }

            if(heat+surroundingTemp>120) {
                heat = 120;
                smelted = true;
            }else if(heat+surroundingTemp<0){
                heat = 0;
            }else{
                heat += surroundingTemp;
            }
            if(heat==120&&!smelted){
                Keranite.LOGGER.info("those who smelt");
                smelted = true;
            }else if(heat==0&&smelted){
                Keranite.LOGGER.info("those who ModBlocks.KERANITE_BLOCK.getDefaultState()");
                world.setBlockState(pos, ModBlocks.KERANITE_BLOCK.getDefaultState());
                world.removeBlockEntity(pos);
            }
            writeNbt(new NbtCompound());
            if (!(heat==0&&smelted)) {
                world.setBlockState(pos, state.with(RawKeranite.HEAT, heat).with(RawKeranite.SMELTED, smelted));
            }

            if(surroundingTemp!=0){
                ArrayList<Entity> NearbyEntities = NearbyUtil.getByWorld(world,50,pos);
                for(Entity entity : NearbyEntities){
                    if(entity instanceof PlayerEntity player){
                        player.sendMessage(Text.of("Heat: " + heat + ", smelted: " + smelted));
                    }
                }
            }

            markDirty(world,pos,state);
        }
    }
}
