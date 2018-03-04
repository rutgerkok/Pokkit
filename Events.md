# Adding Event Listeners

Not all Bukkit events are supported. Here is how to add one, using the example of the PlayerItemConsume event.

The `PlayerItemConsumeEvent` is in `cn/nukkit/event/player/PlayerItemConsumeEvent` in the nukkit server.

We'll put the item handler code in `nl/rutgerkok/pokkit/pluginservice/PlayerItemConsumeEvents` in the Pokkit codebase.

Create a new class called, and extend `EventTranslator`:

```java
public class PlayerItemConsumeEvents extends EventTranslator {
    
    
}

```

Now add an event handler called `onPlayerItemConsume`, with the `@EventHandler` annotation:

```java
public class PlayerItemConsumeEvents extends EventTranslator {
    
    @EventHandler(ignoreCancelled = false)
    public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) {
        
    }
}
```

First thing is to add the Bukkit handler exit code:

```java.
@EventHandler(ignoreCancelled = false)
 public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) 
    if (canIgnore(PlayerItemConsumeEvent.getHandlerList())) {
            return;
        }
    }

```

Now convert the Nukkit objects to Bukkit ones:

```java.
    @EventHandler(ignoreCancelled = false)
    public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) {
        if (canIgnore(PlayerItemConsumeEvent.getHandlerList())) {
            return;
        }

        Player bukkitPlayer = PokkitPlayer.toBukkit(event.getPlayer());
        ItemStack item = PokkitItemStack.toBukkitCopy(event.getItem());
    }
    
```

Construct the Bukkit event:

```java.
    @EventHandler(ignoreCancelled = false)
    public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) {
        if (canIgnore(PlayerItemConsumeEvent.getHandlerList())) {
            return;
        }

        Player bukkitPlayer = PokkitPlayer.toBukkit(event.getPlayer());
        ItemStack item = PokkitItemStack.toBukkitCopy(event.getItem());
        PlayerItemConsumeEvent bukkitEvent = new PlayerItemConsumeEvent(bukkitPlayer, item);
    }
    
```

And finally call the event:

```java.
    @EventHandler(ignoreCancelled = false)
    public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) {
        if (canIgnore(PlayerItemConsumeEvent.getHandlerList())) {
            return;
        }

        Player bukkitPlayer = PokkitPlayer.toBukkit(event.getPlayer());
        ItemStack item = PokkitItemStack.toBukkitCopy(event.getItem());
        PlayerItemConsumeEvent bukkitEvent = new PlayerItemConsumeEvent(bukkitPlayer, item);
        callCancellable(event, bukkitEvent);
    }
    
```

Here is the entire class, including imports:

```java
package nl.rutgerkok.pokkit.pluginservice;

import cn.nukkit.event.EventHandler;
import nl.rutgerkok.pokkit.item.PokkitItemStack;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemConsumeEvents extends EventTranslator {

    @EventHandler(ignoreCancelled = false)
    public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) {
        if (canIgnore(PlayerItemConsumeEvent.getHandlerList())) {
            return;
        }

        Player bukkitPlayer = PokkitPlayer.toBukkit(event.getPlayer());
        ItemStack item = PokkitItemStack.toBukkitCopy(event.getItem());
        PlayerItemConsumeEvent bukkitEvent = new PlayerItemConsumeEvent(bukkitPlayer, item);
        callCancellable(event, bukkitEvent);
    }
}

```


To have your event handler load, you need to add it to the `services` in `Pokkit.java`:

```java.
private final List<PokkitService> services = ImmutableList.of(new MainScoreboardService(), new PermissionsYml(),
        new PluginService(), new PlayerBlockEvents(), new PlayerConnectEvents(), new PlayerChatEvents(),
        new PlayerInteractEvents(), new EntityEvents(), new InventoryEvents(), new PlayerItemConsumeEvents());

public Pokkit() {
    // Created using reflection
}
```