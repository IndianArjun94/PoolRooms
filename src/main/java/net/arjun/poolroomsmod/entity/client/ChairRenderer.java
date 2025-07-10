package net.arjun.poolroomsmod.entity.client;

import net.arjun.poolroomsmod.entity.custom.ChairEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;

public class ChairRenderer extends EntityRenderer<ChairEntity, EntityRenderState> {
    public ChairRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public boolean shouldRender(ChairEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
