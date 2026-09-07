**LootSwap** is a lightweight, exploration-focused mod for **Minecraft 1.21.1 (NeoForge)**. It solves a problem every veteran knows too well: **players memorize where the best loot chests in every structure are, and exploration stops being exploration**.

Which of the desert temple's chests holds the good stuff, where the shipwreck's treasure chest hides, how to beeline to the bastion's treasure room — once you've memorized it all, looting degrades into a fixed routine: walk in, go straight to the jackpot chest, grab it, leave. No suspense, no reason to explore carefully, and every repeated visit feels exactly the same.

With LootSwap installed, opening a naturally generated container has a chance to **swap loot tables** with another nearby container holding different loot. **Which chest contains the jackpot is no longer an answer you can memorize** — the same structure feels unknown again on every single visit, and the joy of exploring comes back.

## How It Works

When a player right-clicks a container that still has a loot table, the mod rolls the configured chance, then searches the surrounding radius for another unopened container whose loot table is different. The two containers exchange their loot tables and random seeds; the next time they are opened, each generates loot according to its new table. Which chest is the "jackpot chest" stays unpredictable until you actually open it.

## Features

- **Anti-memorization** — the jackpot chest is no longer in a fixed spot; every structure visit becomes worth anticipating again.
- **Chance-based swapping** — opening a container with a loot table has a configurable chance (default 50%) to trigger a swap.
- **Radius search** — looks for another unopened container with a *different* loot table within a configurable cubic radius (default 10 blocks).
- **Seeds swap too** — the loot table and its generation seed are exchanged together, so containers produce loot consistent with their new tables.
- **Natural containers only** — only affects containers that still carry a loot table (never opened by a player). Already-looted chests are left alone.
- **Server-side only** — no client installation required. Works in singleplayer and on multiplayer servers.
- **Debug mode** — toggle it to print exactly which two loot tables were swapped.

## Configuration

Config file: `config/lootswap-common.toml`

| Option | Default | Range | Description |
| --- | --- | --- | --- |
| radius | 10 | 1 – 64 | Swap search radius (in blocks) |
| swapChance | 0.5 | 0.0 – 1.0 | Chance of triggering a swap (0.5 = 50%) |

## Commands

| Command | Description |
| --- | --- |
| `/lootswap debug` | Toggles debug mode. When enabled, every swap prints a message like `[LootSwap] Swapped DIFFERENT loot: minecraft:chests/... <-> minecraft:chests/...` |
