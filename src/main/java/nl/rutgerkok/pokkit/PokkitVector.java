package nl.rutgerkok.pokkit;

import org.bukkit.util.Vector;

import cn.nukkit.math.Vector3;

public class PokkitVector {
	public static Vector toBukkit(Vector3 nukkitVector) {
		Vector bukkitVector = new Vector();
		
		bukkitVector.setX(nukkitVector.getX());
		bukkitVector.setY(nukkitVector.getY());
		bukkitVector.setZ(nukkitVector.getZ());
		
		return bukkitVector;
	}
	
	public static Vector3 toNukkit(Vector bukkitVector) {
		Vector3 nukkitVector = new Vector3();
		
		nukkitVector.setComponents(bukkitVector.getX(), bukkitVector.getY(), bukkitVector.getZ());
		
		return nukkitVector;
	}
}
