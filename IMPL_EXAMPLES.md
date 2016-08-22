# Examples on how to implement methods and events

## Implementing methods
[Sometimes it's easy](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/PokkitServer.java#L149-152), as Nukkit has the same method as Spigot.

```java
    @Override
    public int broadcastMessage(String message) {
        return nukkit.broadcastMessage(message);
    }
```

[Sometimes it's a bit more work](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/PokkitServer.java#L664-667):

```java
    @Override
    public void savePlayers() {
        nukkit.getOnlinePlayers().values().forEach(cn.nukkit.Player::save);
    }
```

[Sometimes we need some ugly code](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/PokkitServer.java#L675-681) that might break in the future, so it's a good idea to add a little check. You can use the `Pokkit.assertion(boolean, String)` method for this:

```java
    @Override
    public void setDefaultGameMode(GameMode gameMode) {
        // See nukkit.getDefaultGamemode() for setting name
        nukkit.setPropertyInt("gamemode", PokkitGameMode.toNukkit(gameMode));
        Pokkit.assertion(getDefaultGameMode() == gameMode, "Failed to set game mode");
    }
```

Sometimes you want to implement one method in a class, but are not interested in implementing the other methods in the class. In this case, don't let the code fail silently! This will only cause situations that are very hard to debug. So don't write `return null; // TODO`. Instead, use `throw Pokkit.unsupported()`. This method creates a nice error message.

[For example](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/PokkitServer.java#L738-742):

```java
    @Override
    public boolean useExactLoginLocation() {
        throw Pokkit.unsupported();
    }
```

Sometimes Nukkit simply doesn't support a feature. Using `throw Pokkit.unsupported()` is always an option. However, there are more options.

In the case of chat, [I decided to send the message anyway](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/PokkitServer.java#L130), and simply leave out everything that Pocket Edition doesn't understand.

```java
    @Override
    public void broadcast(BaseComponent component) {
        broadcastMessage(component.toLegacyText());
    }
```

[In the case of `server.createWorld`](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/PokkitServer.java#L208-230), an error is thrown when Nukkit can't handle the given settings. Otherwise, the world is created normally.

```java
 @Override
    public World createWorld(WorldCreator creator) {
        World alreadyLoaded = this.getWorld(creator.name());
        if (alreadyLoaded != null) {
            return alreadyLoaded;
        }

        if (creator.environment() != Environment.NORMAL) {
            throw new IllegalArgumentException("No Nether or End support yet");
        }

        // ... more checks here

        if (!nukkit.generateLevel(creator.name(), creator.seed(), PokkitWorldType.toNukkit(creator.type()), options)) {
            throw new RuntimeException("Failed to create world " + creator.name());
        }
        World world = this.getWorld(creator.name());
        Pokkit.assertion(world != null, "World was still null");
        return world;
    }
```

## Implementing events
Events are implemented in a `PokkitService`. Take a look at [this package](https://github.com/rutgerkok/Pokkit/tree/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/pluginservice). `PokkitService`s are [registered](https://github.com/rutgerkok/Pokkit/blob/0f5365012a11ef18a4ccc97f766fec8ff0168773/src/main/java/nl/rutgerkok/pokkit/Pokkit.java#L68-70) in the `Pokkit` class.
