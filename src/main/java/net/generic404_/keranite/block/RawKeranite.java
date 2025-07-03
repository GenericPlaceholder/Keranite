package net.generic404_.keranite.block;

import net.generic404_.keranite.block.entity.RawKeraniteBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RawKeranite extends BlockWithEntity implements BlockEntityProvider {
    public static final IntProperty HEAT = IntProperty.of("heat", 0,120);
    public static final BooleanProperty SMELTED = BooleanProperty.of("smelted");

    public RawKeranite(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(HEAT, 0).with(SMELTED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HEAT);
        builder.add(SMELTED);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RawKeraniteBlockEntity(pos,state);
    }

//    @Override
//    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//        return checkType(type, CustomBlockEntities.RAW_KERANITE_BLOCK_ENTITY_TYPE,
//                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1,pos,state1)));
//    }
}
