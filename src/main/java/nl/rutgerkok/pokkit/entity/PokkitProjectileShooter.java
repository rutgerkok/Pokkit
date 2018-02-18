package nl.rutgerkok.pokkit.entity;

import cn.nukkit.entity.Entity;
import nl.rutgerkok.pokkit.Pokkit;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class PokkitProjectileShooter implements ProjectileSource {

    public static  ProjectileSource toBukkit(cn.nukkit.entity.Entity nukkit) {
        if (nukkit == null) {
            return null;
        }

        return new PokkitProjectileShooter(nukkit);
    }

    private final Entity nukkit;

    PokkitProjectileShooter(Entity nukkit) {
        this.nukkit = nukkit;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        throw Pokkit.unsupported();
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        throw Pokkit.unsupported();
    }
}
