FlatSpace
=========

Download a runnable version! FlatSpace Beta Wilsonis 1.6 with Christmas DLC:
https://dl.dropboxusercontent.com/u/25808307/FlatSpace%20B%201.6.zip
Just extract the ZIP and double click the Flatspace.jar!

Sources:
 - Game Idea: http://www.lewpen.com/games/flatspace/
 - StdDraw Class / API: http://introcs.cs.princeton.edu/java/15inout/

Gameplay:
Use the mouse to target floating objects, and click to move the spaceship forward in that direction.
Large boxes can take more than one shot to destroy. It's based on a random number generator.
Levels get harder the longer you survive - the rate of box spawning increases exponentially.
Special objects begin to spawn as your levels increase. Powerups can spawn at any time.
Using the Textures menu you can change how things look in the game.

Meet the Objects:
 - Standard Box: The original FlatSpace floating box. Worth 5 points.
 - Large Box: Larger version of the Standard Box. Takes a random amount of hits. Worth 50 points.
 - Stars: Only take one hit but split into 5 fragments in random directions. Worth 75 points.
 - Star Fragments: Only take one hit but move extremely fast and in erratic patterns. Worth 20 points.
 - NPC Enemy: Spawns at higher levels and shoots bullets in random directions. Worth 75 points.
 - "The Wall": Spawns every 5 levels after the first 15 levels. Each box worth 20 points.

Meet the Powerups:
 - Fire Rate: Increases your firing speed to every second tick instead of every seventh
 - Force Field: a blue circle around your ship that prevents you from getting hit while active
 - Nuke: Clears everything from the screen but you receive no points

Options Menu (F5):
 - VSync: Turns on/off the beta FPS controlling system that is built in. Eventually you won't be able to turn it off.
 - Disk Drive / CD Opening: whether the CD drive opens after you die to let you "insert a coin to continue"!
 - Textures on/off: Turns the textures menu on/off.
 - Debugging: turns on/off the F3 menu and the F2 invincibility function
 - Reset High Scores: clears the level, time, and high score to zero

Debug Controls:
 - F2: Invinciblity Mode
 - F3: Debug Menu, with info (ENT = Entities, FPS = Current frames per second (1 sec polling), VSY = VSync Delay in ms)
