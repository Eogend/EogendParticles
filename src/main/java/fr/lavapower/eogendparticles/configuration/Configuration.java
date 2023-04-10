package fr.lavapower.eogendparticles.configuration;

import fr.lavapower.eogendparticles.configuration.section.LineSection;
import fr.lavapower.eogendparticles.configuration.section.PilarSection;
import fr.lavapower.eogendparticles.configuration.section.StyleSection;
import fr.lavapower.eogendparticles.configuration.section.TinyLineSection;
import org.bukkit.configuration.file.FileConfiguration;

public record Configuration(StyleSection style)
{
    public static Configuration fromFile(FileConfiguration config) {
        return new Configuration(
                new StyleSection(
                        new PilarSection(
                                config.getInt("style.pilar.maxStep"),
                                config.getInt("style.pilar.radius"),
                                config.getInt("style.pilar.height"),
                                config.getInt("style.pilar.amount")
                        ),
                        new LineSection(
                                config.getInt("style.line.maxStep"),
                                config.getInt("style.line.height")
                        ),
                        new TinyLineSection(
                                config.getInt("style.tinyLine.maxStep"),
                                config.getInt("style.tinyLine.height")
                        )
                )
        );
    }
}
