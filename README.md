# Pokkit  [![Build Status](https://travis-ci.org/ZINGDING/Pokkit.svg?branch=master)](https://travis-ci.org/ZINGDING/Pokkit)

The Minecraft multiplayer scene is fragmented. There are many competing server implementations. A popular server is [Spigot](http://www.spigotmc.org/). Spigot is able to load plugins, which can interact with Minecraft through the Spigot Plugin API. For Minecraft Pocket Edition, the mobile version of Minecraft, a server with a plugin API similar to Spigot exists: [Nukkit](https://forums.nukkit.io/). Unfortunately, plugins written for Spigot cannot run on Nukkit.

Pokkit is a translation layer that allows plugins written for Spigot to work on Nukkit. However, it is far from perfect. Because of the size of the Spigot API, it is not possible for me to implement all of the Spigot API.

**Do not expect that Pokkit allows you to run a random Spigot plugin on Nukkit.** Pokkit is still very incomplete, so only a handful of plugins will work. A list of plugins that are confirmed to work or not to work can be found on [Google Docs](https://docs.google.com/spreadsheets/d/1afyyvkdQWyJO6bv1kzofrJxqniqlg28TKqr3YK6ouic/edit?usp=sharing). You are encouraged to edit this list yourself if you have found a Spigot plugin that works or doesn't work.

My motivation for creating Pokkit was to allow some plugins that I use to work on Nukkit. I am not trying to write a complete implementation of the Spigot API myself. This would be impossible. Keep in mind that Spigot was created over the span of five years by hundreds of different programmers.

## For Spigot plugin developrs
If you are a Spigot plugin developer looking to port your plugin, Pokkit may be very interesting to you. Instead of maintaining two code bases, or introducing large amounts of abstraction into your codebase, you can simply keep using the Spigot API.

While testing your plugin, you will most likely come accross an unimplemented method or event. You are encouraged to implement these methods and events, and to submit your changes as a pull request. This will bring us closer to a complete implementation of the Spigot API.

To get started writing code for Pokkit, I have created a [file with examples](./IMPL_EXAMPLES.md) on how I implemented the methods.

## How to use it
Install Pokkit like you would install any Nukkit plugin: place [the JAR file](https://github.com/rutgerkok/Pokkit/releases) in the `plugins` directory of your Nukkit server. Start your server, then stop it again. You'll notice that a new folder has been created inside the `plugins` directory: a folder called `Pokkit`. Open this folder. Inside the folder, there a three things:

* A folder called `bukkitPlugins`: place any plugin written for Spigot in this folder.
* A file called `permissions.yml`: this is Bukkit's [permissions.yml file](http://wiki.bukkit.org/Permissions.yml).
* A file called `scoreboard.yml`: scoreboard data is saved here.

It is important to remember that you must **install Bukkit plugins in the `plugins/Pokkit/bukkitPlugins/` directory**, and not in the `plugins/` directory.

## Compiling instructions
* Make sure you have installed [Maven](https://maven.apache.org/).
* Download a copy of the source code of Pokkit (use one of the buttons on this page).
* Then run `mvn install` in the directory containing the Pokkit source code.
* A JAR file will be placed in the newly created `target` directory.

## Bug reports
In general, I will only fix a bug for you if happens to also affect me. Pokkit is a personal project that I uploaded in the hope that it will be useful to others, but I'm not willing to spend the time to create a complete, bug-free implementation of the Spigot API. See my reasoning in the introduction.

Still, I will leave open all valid bug reports, so that anyone interested in improving Pokkit can have a look.

## Pull requests
Contributions are very much welcomed. If you have any questions, feel free to send me an e-mail (address is on my Github profile).

## License
License is MIT, except for the files that were derived from the Bukkit project. See the [license](./LICENSE.md) file for details.

## Authors
MrPowerGamerBR, ZINGDING, Rutger Kok
