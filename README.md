# Minecraft-Server-Lobby

2017/2018 Minecraft lobby plugin for a network hub server. The code handles player entry flow, lobby protections, server selection, cosmetics loading, and persistent lobby-facing stats.

## What It Does

- Runs hub/lobby behavior for a Minecraft network entry server.
- Provides commands for maintenance mode, testing, wall stats, Unix time checks, and cosmetic loading.
- Handles lobby events such as chest interaction, packet cancellation, voting, weather control, player lifecycle, and world protection.
- Includes server selector and database helpers for MySQL/SQLite-backed lobby features.
- Contains map rendering and stat display support for player-facing hub UI.

## Repository Layout

- `src/info/techwizmatt/ServerSelector/commands/` - lobby and maintenance commands.
- `src/info/techwizmatt/ServerSelector/events/` - lobby event listeners and protections.
- `src/info/techwizmatt/ServerSelector/Servers/` - server lookup and selector helpers.
- `src/info/techwizmatt/ServerSelector/storage/` - MySQL, SQLite, and database abstractions.
- `src/info/techwizmatt/ServerSelector/stats/` - lobby stat display code.

## Tech Stack

- Java
- Bukkit/Spigot-style plugin APIs
- MySQL and SQLite helpers

## Status

Archived lobby project from the 2017/2018 network era. It is useful as a reference for older Minecraft hub and server-selector systems.
