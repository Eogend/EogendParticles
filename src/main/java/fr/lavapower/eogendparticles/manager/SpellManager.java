package fr.lavapower.eogendparticles.manager;

import dev.esophose.playerparticles.particles.ParticleEffect;
import dev.esophose.playerparticles.particles.data.OrdinaryColor;
import dev.esophose.playerparticles.styles.ParticleStyle;
import fr.lavapower.eogendparticles.data.ParticleWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpellManager
{
    private final Map<String, ParticleWrapper<?>> spells = new HashMap<>();
    public SpellManager() {
        // IOLENA SPELLS
        spells.put("Iolena - 50%", new ParticleWrapper<>("AiglouChan", "ring", ParticleEffect.DUST, new OrdinaryColor(255,215,0)));
        spells.put("Iolena - 100%", new ParticleWrapper<>("AiglouChan", "rings", ParticleEffect.DUST, new OrdinaryColor(255,215,0)));
    }

    public ParticleWrapper<?> getParticle(String spell) {
        return spells.get(spell);
    }

    public Set<String> getNames() {
        return spells.keySet();
    }
}
