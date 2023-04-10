package fr.lavapower.eogendparticles.command;

import dev.esophose.playerparticles.api.PlayerParticlesAPI;
import dev.esophose.playerparticles.particles.data.ColorTransition;
import dev.esophose.playerparticles.particles.data.NoteColor;
import dev.esophose.playerparticles.particles.data.OrdinaryColor;
import dev.esophose.playerparticles.particles.data.Vibration;
import dev.esophose.playerparticles.styles.ParticleStyle;
import fr.lavapower.eogendparticles.EogendParticles;
import fr.lavapower.eogendparticles.data.ParticleWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SpellAs implements CommandExecutor, TabCompleter
{
    private final EogendParticles plugin;
    public SpellAs(EogendParticles plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length >= 2) {
            Player player = Bukkit.getPlayer(args[0]);
            if(player != null) {
                ParticleWrapper<?> particleWrapper = plugin.getSpellManager().getParticle(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                ParticleStyle style = ParticleStyle.fromInternalName(particleWrapper.style());
                if(particleWrapper.data() == null)
                    PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, particleWrapper.effect(), style);
                else if(particleWrapper.data() instanceof ColorTransition colorTransition)
                    PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, particleWrapper.effect(), style, colorTransition);
                else if(particleWrapper.data() instanceof NoteColor noteColor)
                    PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, particleWrapper.effect(), style, noteColor);
                else if(particleWrapper.data() instanceof OrdinaryColor ordinaryColor)
                    PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, particleWrapper.effect(), style, ordinaryColor);
                else if(particleWrapper.data() instanceof Vibration vibration)
                    PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, particleWrapper.effect(), style, vibration);
                else if(particleWrapper.data() instanceof Material material)
                    PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, particleWrapper.effect(), style, material);
                sender.sendMessage("Particles lancées.");
            }
            else
                sender.sendMessage("Ce joueur n'existe pas.");
        }
        else
            sender.sendMessage("Usage : /spellas <player> <spell>");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length >= 2)
        {
            return plugin.getSpellManager().getNames().stream()
                    .filter(x -> args[1].isEmpty() || x.toLowerCase().startsWith(String.join(" ", Arrays.copyOfRange(args, 1, args.length)).toLowerCase()))
                    .filter(x -> plugin.getSpellManager().getParticle(x).owner().equals(sender.getName()) || sender.isOp())
                    .map(x -> String.join(" ", Arrays.copyOfRange(x.split(" "), args.length - 2, x.split(" ").length)))
                    .toList();
        }
        return null;
    }
}
