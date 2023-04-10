package fr.lavapower.eogendparticles.particlestyle;

import dev.esophose.playerparticles.particles.PParticle;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import dev.esophose.playerparticles.util.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class RingParticleStyle implements ParticleStyle
{
    private int step = 0;
    private final int maxStep = 32;

    @Override
    public List<PParticle> getParticles(ParticlePair particlePair, Location location)
    {
        List<PParticle> particles = new ArrayList<>();

        double ring1 = Math.PI / (this.maxStep / 2D) * this.step;
        double ring2 = Math.PI / ((double)this.maxStep / 2.0) * (((double)this.step + (double)this.maxStep / 2.0) % (double)this.maxStep);

        particles.add(PParticle.point(location.clone().add(MathL.cos(ring1), MathL.sin(ring1), MathL.sin(ring1))));
        particles.add(PParticle.point(location.clone().add(MathL.cos(ring2), MathL.sin(ring2), MathL.sin(ring2))));

        return particles;
    }

    @Override
    public void updateTimers()
    {
        step = (step + 1) % maxStep;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getInternalName() {
        return "ring";
    }

    @Override
    public Material getGuiIconMaterial() {
        return Material.COAL_BLOCK;
    }

    @Override
    public boolean canBeFixed() {
        return true;
    }

    @Override
    public boolean canToggleWithMovement() {
        return true;
    }

    @Override
    public double getFixedEffectOffset() {
        return 0;
    }

}