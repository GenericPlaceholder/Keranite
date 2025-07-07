package net.generic404_.keranite.block;

import net.generic404_.keranite.block.entity.CustomBlockEntities;
import net.generic404_.keranite.block.entity.RawKeraniteBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RawKeraniteBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final IntProperty HEAT = IntProperty.of("heat", 0,120);
    public static final BooleanProperty SMELTED = BooleanProperty.of("smelted");

    public RawKeraniteBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(HEAT, 0).with(SMELTED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HEAT);
        builder.add(SMELTED);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RawKeraniteBlockEntity(pos,state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, CustomBlockEntities.RAW_KERANITE_BLOCK_ENTITY,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1,pos,state1)));
    }
}
