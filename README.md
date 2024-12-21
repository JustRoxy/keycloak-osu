# keycloak-osu

Based on [keycloak-discord](https://github.com/wadahiro/keycloak-discord)  (BIG THANKS)

Keycloak Social Login extension for Osu!.

## Install

Download `keycloak-osu-<version>.jar` from [Releases page](https://github.com/wadahiro/keycloak-discord/releases).
Then deploy it into `$KEYCLOAK_HOME/providers` directory.

## Setup

### Discord

Go to [osu! settings](https://osu.ppy.sh/home/account/edit) and create OAuth application.
You can get Client ID and Client Secret from the created application.

### Keycloak

Note: You don't need to set up the theme in `master` realm from v0.3.0.

1. Add `Osu!` Identity Provider in the realm which you want to configure.
2. In the `Osu!` identity provider page, set `Client Id` and `Client Secret`.
3. `identify` scope is set by default, add your other scopes as needed 


## Source Build

Clone this repository and run `mvn package`.
You can see `keycloak-osu-<version>.jar` under `target` directory.


## Licence

[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)


## Author
- [Hiroyuki Wada](https://github.com/wadahiro) (Discord Identity Provider)
- [JustRoxy](https://github.com/JustRoxy)

