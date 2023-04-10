package fr.lavapower.eogendparticles.particlestyle;

import dev.esophose.playerparticles.particles.PParticle;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import fr.lavapower.eogendparticles.EogendParticles;
import fr.lavapower.eogendparticles.configuration.section.PilarSection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class PilarParticleStyle implements ParticleStyle
{
    private final EogendParticles plugin;
    private int step = 0;

    public PilarParticleStyle(EogendParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<PParticle> getParticles(ParticlePair particlePair, Location location)
    {
        List<PParticle> particles = new ArrayList<>();

        PilarSection pilarConfig = plugin.getConfiguration().style().pilar();

        double current_height_step = pilarConfig.height() * (step / (double)pilarConfig.maxStep());
        double step = (2*Math.PI) / pilarConfig.amount();
        for(double t = step; t <= 2*Math.PI; t+=step) {
            double x = pilarConfig.radius() * Math.cos(t) + location.getX();
            double z = pilarConfig.radius() * Math.sin(t) + location.getZ();
            double y = location.getY() - pilarConfig.height() / (double)2 + current_height_step;
            particles.add(PParticle.point(new Location(location.getWorld(), x, y, z)));
        }

        return particles;
    }

    @Override
    public void updateTimers()
    {
        step = (step + 1) % plugin.getConfiguration().style().pilar().maxStep();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getInternalName() {
        return "pilar";
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
