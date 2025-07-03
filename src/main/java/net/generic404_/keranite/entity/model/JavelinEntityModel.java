package net.generic404_.keranite.entity.model;

import net.generic404_.keranite.Keranite;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.util.Identifier;

public class JavelinEntityModel extends TridentEntityModel {
    public static final Identifier TEXTURE = new Identifier(Keranite.MOD_ID, "textures/item/javelin");

    public JavelinEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild(
                "pole", ModelPartBuilder.create().uv(0, 6).cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F), ModelTransform.NONE
        );
        modelPartData2.addChild("base", ModelPartBuilder.create().uv(4, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F), ModelTransform.NONE);
        modelPartData2.addChild("left_spike", ModelPartBuilder.create().uv(4, 3).cuboid(-2.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F), ModelTransform.NONE);
        modelPartData2.addChild("middle_spike", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F), ModelTransform.NONE);
        modelPartData2.addChild("right_spike", ModelPartBuilder.create().uv(4, 3).mirrored().cuboid(1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 32, 32);
    }
}
