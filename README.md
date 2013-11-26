FlatSpace
=========

This is a rebuilt version of the game located here: http://www.lewpen.com/games/flatspace/
It is built in Java and is made using the StdDraw class made by Princeton Intro to CS.
http://introcs.cs.princeton.edu/java/15inout/

Gameplay:
Use the mouse to target floating objects, and click to move the spaceship forward in that direction.
Some boxes can take more than one shot to destroy. It's based on a random number generator.
Levels get harder the longer you survive - the rate of box spawning increases exponentially.
Special objects begin to spawn as your levels increase. Powerups can spawn at any time.

Meet the Objects:
 - Standard Box: The original FlatSpace floating box. Worth 5 points.
 - Large Box: Larger version of the Standard Box. Takes a random amount of hits. Worth 50 points.
 - Stars: Only take one hit but split into 5 fragments in random directions. Worth 75 points.
 - Star Fragments: Only take one hit but move extremely fast and in erratic patterns. Worth 20 points.
 - "NPC" (Green Ship): Spawns at higher levels and shoots bullets in random directions. Worth 75 points.

Debug Controls:
 - F2: Invinciblity Mode
 - F3: Debug Menu, with info (E = Entities, S = Bullets Fired, T = Program Clock)
