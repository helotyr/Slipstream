package com.slipstream;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slipstream {
    public Follower follower;
    public AMPC ampc;
    public VelocityController controller;
    public MecanumKinematics kinematics;
    public SlipstreamConfig config;

    public Slipstream(Follower follower, HardwareMap hardwareMap, SlipstreamConfig config) {
        this.follower = follower;
        this.config = config;
        ampc = new AMPC(follower, config);
        controller = new VelocityController(follower, ampc, config);
        kinematics = new MecanumKinematics(hardwareMap, ampc, controller, config);
    }

    public void update() {
        follower.updatePose();

        PathChain currentChain = follower.getCurrentPathChain();
        if (currentChain != null && currentChain != ampc.getActivePath()) {
            ampc.setActivePath(currentChain);
        }

        ampc.update();
        controller.velocity();
        kinematics.drive();

        if (ampc.isPathComplete() && follower.isBusy()) {
            follower.breakFollowing();
        }
    }
}