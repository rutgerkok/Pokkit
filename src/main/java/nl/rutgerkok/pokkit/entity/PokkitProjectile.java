package nl.rutgerkok.pokkit.entity;

import nl.rutgerkok.pokkit.Pokkit;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;

public class PokkitProjectile extends PokkitEntity implements Projectile {

    public static Projectile toBukkit(cn.nukkit.entity.projectile.EntityProjectile nukkit) {
        if (nukkit == null) {
            return null;
        }

        return new PokkitProjectile(nukkit);
    }

    private final cn.nukkit.entity.projectile.EntityProjectile nukkit;

    PokkitProjectile(cn.nukkit.entity.projectile.EntityProjectile nukkit) {
        super(nukkit);
        this.nukkit = nukkit;
    }

    /**
     * Retrieve the shooter of this projectile.
     *
     * @return the {@link ProjectileSource} that shot this projectile
     */
    public ProjectileSource getShooter(){
        return PokkitProjectileShooter.toBukkit(this.nukkit.shootingEntity);
    };

    /**
     * Set the shooter of this projectile.
     *
     * @param source the {@link ProjectileSource} that shot this projectile
     */
    public void setShooter(ProjectileSource source) {
        throw Pokkit.unsupported();
    }

    /**
     * Determine if this projectile should bounce or not when it hits.
     * <p>
     * If a small fireball does not bounce it will set the target on fire.
     *
     * @return true if it should bounce.
     */
    public boolean doesBounce() {
        throw Pokkit.unsupported();
    }

    /**
     * Set whether or not this projectile should bounce or not when it hits
     * something.
     *
     * @param doesBounce whether or not it should bounce.
     */
    public void setBounce(boolean doesBounce) {
        throw Pokkit.unsupported();
    }
}


