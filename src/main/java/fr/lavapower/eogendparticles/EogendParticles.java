package fr.lavapower.eogendparticles;

import dev.esophose.playerparticles.event.ParticleStyleRegistrationEvent;
import fr.lavapower.eogendparticles.command.Spell;
import fr.lavapower.eogendparticles.command.SpellAs;
import fr.lavapower.eogendparticles.configuration.Configuration;
import fr.lavapower.eogendparticles.configuration.section.TinyLineSection;
import fr.lavapower.eogendparticles.manager.SpellManager;
import fr.lavapower.eogendparticles.particlestyle.LineParticleStyle;
import fr.lavapower.eogendparticles.particlestyle.PilarParticleStyle;
import fr.lavapower.eogendparticles.particlestyle.RingParticleStyle;
import fr.lavapower.eogendparticles.particlestyle.TinyLineParticleStyle;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EogendParticles extends JavaPlugin implements Listener
{
    private Configuration configuration;
    private SpellManager spellManager;

    @Override
    public void onEnable() {
        // CREATE CONFIG
        saveDefaultConfig();
        configuration = Configuration.fromFile(getConfig());

        // REGISTER EVENT
        Bukkit.getPluginManager().registerEvents(this, this);

        // REGISTER COMMAND
        registerCommand("spell", new Spell(this));
        registerCommand("spellas", new SpellAs(this));

        // CREATE MANAGER
        spellManager = new SpellManager();
    }

    @EventHandler
    public void onParticleStyleRegistration(ParticleStyleRegistrationEvent event) {
        event.registerStyle(new PilarParticleStyle(this));
        event.registerStyle(new LineParticleStyle(this));
        event.registerStyle(new TinyLineParticleStyle(this));
        event.registerStyle(new RingParticleStyle());
    }

    public Configuration getConfiguration() {
        return configuration;
    }
    public SpellManager getSpellManager() {
        return spellManager;
    }

    private <T extends CommandExecutor & TabCompleter> void registerCommand(String name, T command)
    {
        PluginCommand pluginCommand = getCommand(name);
        pluginCommand.setExecutor(command);
        pluginCommand.setTabCompleter(command);
    }
}
