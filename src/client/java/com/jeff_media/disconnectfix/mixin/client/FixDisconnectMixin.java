package com.jeff_media.disconnectfix.mixin.client;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Scoreboard.class)
public class FixDisconnectMixin {

    @Final
    @Shadow
    private Object2ObjectMap<String, Team> teamsByScoreHolder;

    @Inject(method = "removeScoreHolderFromTeam", at = @At("HEAD"), cancellable = true)
    private void removeScoreHolderFromTeam(String string, Team string2, CallbackInfo ci) {
        teamsByScoreHolder.remove(string);
        string2.getPlayerList().remove(string);
        ci.cancel();
    }
}

