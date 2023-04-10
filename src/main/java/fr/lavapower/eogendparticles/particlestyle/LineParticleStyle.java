package fr.lavapower.eogendparticles.particlestyle;

import dev.esophose.playerparticles.particles.PParticle;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import fr.lavapower.eogendparticles.EogendParticles;
import fr.lavapower.eogendparticles.configuration.section.LineSection;
import fr.lavapower.eogendparticles.configuration.section.PilarSection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class LineParticleStyle implements ParticleStyle
{
    private final EogendParticles plugin;
    private int step = 0;

    public LineParticleStyle(EogendParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<PParticle> getParticles(ParticlePair particlePair, Location location)
    {
        List<PParticle> particles = new ArrayList<>();
        LineSection lineConfig = plugin.getConfiguration().style().line();

        double current_height_step = lineConfig.height() * (step / (double)lineConfig.maxStep());
        double y = location.getY() - lineConfig.height() / (double)2 + current_height_step;
        particles.add(PParticle.point(new Location(location.getWorld(), location.getX(), y, location.getZ())));
        return particles;
    }

    @Override
    public void updateTimers()
    {
        step = (step + 1) % plugin.getConfiguration().style().line().maxStep();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getInternalName() {
        return "line";
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
