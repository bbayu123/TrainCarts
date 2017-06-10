package com.bergerkiller.bukkit.tc.rails.logic;

import com.bergerkiller.bukkit.common.bases.IntVector3;
import com.bergerkiller.bukkit.common.entity.type.CommonMinecart;
import com.bergerkiller.bukkit.common.utils.FaceUtil;
import com.bergerkiller.bukkit.tc.controller.MinecartMember;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

/**
 * Handles the rail logic of a minecart sliding on the ground
 */
public class RailLogicGround extends RailLogic {
    public static final RailLogicGround INSTANCE = new RailLogicGround();

    private RailLogicGround() {
        super(BlockFace.SELF);
    }

    @Override
    public BlockFace getMovementDirection(MinecartMember<?> member, BlockFace endDirection) {
        return FaceUtil.getDirection(member.getEntity().getVelocity());
    }

    @Override
    public boolean hasVerticalMovement() {
        return true;
    }

    @Override
    public Vector getFixedPosition(CommonMinecart<?> entity, double x, double y, double z, IntVector3 railPos) {
        return new Vector(x, y, z);
    }

    @Override
    public void onPreMove(MinecartMember<?> member) {
        // Apply ground friction
        if (!member.isMovementControlled()) {
            member.getEntity().vel.multiply(member.getEntity().getDerailedVelocityMod());
        }
    }
}
