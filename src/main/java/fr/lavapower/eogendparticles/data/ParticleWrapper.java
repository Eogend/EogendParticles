package fr.lavapower.eogendparticles.data;

import dev.esophose.playerparticles.particles.ParticleEffect;

public record ParticleWrapper<T>(String owner, String style, ParticleEffect effect, T data)
{
}
