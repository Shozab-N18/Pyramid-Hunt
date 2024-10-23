# Pyramid Hunt

**Developed by:** Shozab Anwar Siddique  
**Student ID:** K21054573

## Description

**Pyramid Hunt** is an immersive adventure game where players step into the shoes of an archaeologist exploring a mysterious pyramid filled with valuable treasures. While navigating through the various rooms of the pyramid, players must collect valuable items to complete their quest. However, they will encounter distinct beings, such as a ghost that will attempt to ruin their progress and a mummy that demands a specific item in exchange for passage.

### Game Features
- **Exploration:** Players can move through multiple rooms within the pyramid, discovering hidden treasures and encountering unique challenges.
- **Item Collection:** Each room contains various items, some of which can be picked up while others cannot. Players must manage their inventory effectively, considering weight limits.
- **Challenges:** Overcome obstacles presented by characters like the ghost and mummy, each requiring different strategies to navigate.
- **Winning the Game:** Players win by collecting all the valuable items scattered throughout the pyramid.

## Implementation Highlights

### Core Features
- **Room Navigation:** 
  - Implemented through the `createObjects` method in the Player class.
  - Players can seamlessly walk through locations.
  
- **Item Management:**
  - Created an `Item` class with properties for name, description, and weight.
  - Items can have very high weights to prevent them from being picked up.
  - Implemented a weight system to limit what the player can carry.

- **Inventory System:**
  - Developed an `Inventory` class to store collected items.
  - Implemented a pickup method to manage item collection and ensure weight limits are respected.

- **Game Win Condition:**
  - A `winGame` method checks if the player has collected all required items and ends the game when complete.

- **Back Command:**
  - Used stacks to implement a "back" feature, allowing players to return to the previous room.

### Additional Commands
- The game includes a variety of commands for interaction:
  - **"go"**, **"quit"**, **"help"**, **"look"**, **"back"**, **"take"**, **"drop"**, **"inventory"**, **"inspect"**, **"drink"**, **"talk"**, **"give"**.

- Each command is associated with specific methods to enhance gameplay:
  - **look** - View the current room's items and characters.
  - **take** - Collect items while respecting weight limits.
  - **drop** - Remove items from the inventory and place them in the room.
  - **inspect** - Check descriptions of items in inventory or the room.
  - **drink** - Consume items to enhance abilities.
  - **talk** - Interact with characters in the game.
  - **give** - Complete quests by giving items to characters.

### Challenge Features
- **Character Implementation:**
  - Added characters that interact with the player.
  - Developed a character that moves randomly within the pyramid every 30 seconds.
  
- **Three-Word Command Recognition:**
  - Extended command parsing to recognize complex commands such as "give item character."

- **Magic Transporter Room:**
  - Introduced a room that teleports the player to random locations upon entry.

## Game Walkthrough
1. Start outside the pyramid.
2. Move west into the Hall of Statues.
3. Go down into the Artefacts Room.
4. Go south into the Middle of Nowhere.
5. Move west into the Mummy’s Room and talk to the mummy.
6. Head east back to the Middle of Nowhere, then south into the Artillery Room.
7. Collect items (bag, trap, tracker) from various rooms.
8. Continue to the Sanctuary and collect water.
9. Go down the passageway, drink water to increase carrying capacity, and collect the coffin.
10. Navigate through the Sewer to the Treasure Room, collect the treasure.
11. Use the "back" command to return to previous locations to collect additional items (torch, vase, statue).

## Code Quality Considerations
- **Coupling:** Minimized references between classes for better modularity (e.g., Item class only references Player class now).
- **Cohesion:** Classes like Player are defined for specific tasks, improving clarity and functionality.
- **Responsibility-Driven Design:** Each class handles its responsibilities appropriately, e.g., room string generation is managed within the Room class.
- **Maintainability:** Refactored the inventory logic into a separate class for better readability and ease of updates.

## Source Code
- A copy of the source code for all classes can be found in the repository.

---

Feel free to explore the pyramid and embark on your adventure in **Pyramid Hunt**!
