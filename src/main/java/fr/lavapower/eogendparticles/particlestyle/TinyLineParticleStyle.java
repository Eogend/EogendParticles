package fr.lavapower.eogendparticles.particlestyle;

import dev.esophose.playerparticles.particles.PParticle;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import fr.lavapower.eogendparticles.EogendParticles;
import fr.lavapower.eogendparticles.configuration.section.LineSection;
import fr.lavapower.eogendparticles.configuration.section.TinyLineSection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class TinyLineParticleStyle implements ParticleStyle
{
    private final EogendParticles plugin;
    private int step = 0;

    public TinyLineParticleStyle(EogendParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<PParticle> getParticles(ParticlePair particlePair, Location location)
    {
        List<PParticle> particles = new ArrayList<>();
        TinyLineSection tinyLineConfig = plugin.getConfiguration().style().tinyLine();

        double current_height_step = tinyLineConfig.height() * (step / (double)tinyLineConfig.maxStep());
        double y = location.getY() - tinyLineConfig.height() / (double)2 + current_height_step;
        particles.add(PParticle.point(new Location(location.getWorld(), location.getX(), y, location.getZ())));
        return particles;
    }

    @Override
    public void updateTimers()
    {
        step = (step + 1) % plugin.getConfiguration().style().tinyLine().maxStep();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getInternalName() {
        return "tinyline";
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

