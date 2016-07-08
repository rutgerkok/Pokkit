# Pokkit

The Minecraft multiplayer scene is fragmented. There are many competing server implementations. A popular server is [Spigot](http://www.spigotmc.org/). For Minecraft Pocket Edition, the mobile version of Minecraft, a server with a plugin API similar to Spigot exists: [Nukkit](https://forums.nukkit.io/). Unfortunately, plugins written for Spigot cannot run directly on Nukkit.

Pokkit is an attempt at creating a translation layer, that allows Spigot plugins to work on Nukkit.

## How to use it
Install Nukkit like you would install any Nukkit plugin: place the JAR file in the `plugins` directory of your Nukkit server. Start your server, then stop it again. You'll notice that a new folder has been created inside the `plugins` directory: a folder called `Pokkit`. Open this folder. Inside the folder, there a three things:

* A folder called `bukkitPlugins`: place any plugin written for Spigot in this folder.
* A file called `permissions.yml`: this is Bukkit's [permissions.yml file](http://wiki.bukkit.org/Permissions.yml).
* A file called `scoreboard.yml`: scoreboard data is saved here.

It is important to remember that you must **install Bukkit plugins in the `plugins/Pokkit/bukkitPlugins/` directory**, and not in the `plugins/` directory.

## Compiling instructions
* Make sure you have installed Maven.
* Download a copy of the source code of [Nukkit](https://github.com/Nukkit/Nukkit).
* Download a copy of the source code of Pokkit (use one of the buttons on this page).
* Run `mvn install` in the directory containing the Nukkit source code.
* Then run `mvn install` in the directory containg the Pokkit source code.
* A JAR file will be placed in the newly created `target` directory.

## Pull requests
Contributions are always welcome. If you have any questions, feel free to send me an e-mail (address is on my Github profile).
