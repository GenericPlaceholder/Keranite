package net.generic404_.keranite.block.entity;

import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.block.RawKeraniteBlock;
import net.generic404_.keranite.util.NearbyUtil;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;

public class RawKeraniteBlockEntity extends BlockEntity {
    private static final Direction[] directions = Direction.values();

    public RawKeraniteBlockEntity(BlockPos pos, BlockState state) {
        super(CustomBlockEntities.RAW_KERANITE_BLOCK_ENTITY, pos, state);
    }

    int heat = 0;
    boolean smelted = false;

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Heat", this.heat);
        nbt.putBoolean("Smelted", this.smelted);
    }

//    protected void update(World world, BlockPos pos) {
//        if (this.spongeFluids(world, pos)) {
//            world.setBlockState(pos, Blocks.WET_SPONGE.getDefaultState(), Block.NOTIFY_LISTENERS);
//            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(Blocks.WATER.getDefaultState()));
//        }
//    }

    public boolean spongeFluids(World world, BlockPos pos, Fluid... fluid){
        // Is it ethical to just use the sponge code here?
        return BlockPos.iterateRecursively(pos, 9, 65, (currentPos, queuer) -> {
                for (Direction direction : directions) {
                    queuer.accept(currentPos.offset(direction));
                }
            }, currentPos -> {
                if (currentPos.equals(pos)) {
                    return true;
                } else {
                    BlockState blockState = world.getBlockState(currentPos);
                    FluidState fluidState = world.getFluidState(currentPos);
                    boolean temp = false;
                    for(Fluid fluid1 : fluid) {
                        if(fluidState.isOf(fluid1)) {
                            temp = true;
                            break;
                        }
                    }
                    if (!temp) {
                        return false;
                    } else if (blockState.getBlock() instanceof FluidDrainable fluidDrainable && !fluidDrainable.tryDrainFluid(world, currentPos, blockState).isEmpty()) {
                        return true;
                    } else {
                        if (blockState.getBlock() instanceof FluidBlock) {
                            world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                        } else {
                            if (!blockState.isOf(Blocks.KELP) && !blockState.isOf(Blocks.KELP_PLANT) && !blockState.isOf(Blocks.SEAGRASS) && !blockState.isOf(Blocks.TALL_SEAGRASS)) {
                                return false;
                            }
                            world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                        }

                        return true;
                    }
                }
            }) > 1;
        }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld sworld) {
            BlockState[] surroundings = {
                    sworld.getBlockState(pos.add(0,0,1)),
                    sworld.getBlockState(pos.add(0,0,-1)),
                    sworld.getBlockState(pos.add(1,0,0)),
                    sworld.getBlockState(pos.add(-1,0,0)),
                    sworld.getBlockState(pos.add(0,1,0)),
                    sworld.getBlockState(pos.add(0,-1,0))
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
                    sworld.setBlockState(touchingPositions[counter0], Blocks.AIR.getDefaultState(), 3);
                } else
                if(blockState.isIn(BlockTags.ICE)||blockState.isIn(BlockTags.SNOW)){
                    surroundingTemp -= 20;
                    sworld.setBlockState(touchingPositions[counter0], Blocks.AIR.getDefaultState(), 3);
                } else
                if(blockState.isOf(Blocks.WATER)){
                    surroundingTemp -= 2;
                    spongeFluids(sworld, pos, Fluids.WATER, Fluids.FLOWING_WATER);
                } else
                if(blockState.isIn(BlockTags.FIRE)||blockState.isIn(BlockTags.STRIDER_WARM_BLOCKS)){
                    surroundingTemp += 10;
                    sworld.setBlockState(touchingPositions[counter0], Blocks.AIR.getDefaultState(), 3);
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
                sworld.setBlockState(pos, ModBlocks.KERANITE_BLOCK.getDefaultState());
                sworld.removeBlockEntity(pos);
            }
            writeNbt(new NbtCompound());
            if (!(heat==0&&smelted)) {
                sworld.setBlockState(pos, state.with(RawKeraniteBlock.HEAT, heat).with(RawKeraniteBlock.SMELTED, smelted));
            }

            if(surroundingTemp!=0){
                ArrayList<Entity> NearbyEntities = NearbyUtil.getByWorld(world,50,pos);
                for(Entity entity : NearbyEntities){
                    if(entity instanceof PlayerEntity player){
                        player.sendMessage(Text.of("Heat: " + heat + ", smelted: " + smelted));
                    }
                }
            }

            markDirty(sworld,pos,state);
        }
    }
}
