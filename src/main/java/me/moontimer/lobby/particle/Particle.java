package me.moontimer.lobby.particle;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Particle {

    ParticleEnum particleEnum;

    public Particle() {

    }

    HashMap<Player, ParticleEnum> particleHashMap = new HashMap<Player, ParticleEnum>();


    public void addParticle(Player player, ParticleEnum particle) {
        particleHashMap.put(player, particle);
    }

    public ParticleEnum getParticleEnum() {
        return particleEnum;
    }
}
