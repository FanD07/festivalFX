# FestivalFX — Technische Zielmatrix (FFX-001)

> **Stand:** 29.10.2026 · **Ticket:** FFX-001 · **Freigabe:** A-FFX-001 (Umsetzung dieser Dokumentation) · **Status:** `REVIEW`
> Diese Datei hält **recherchierte Versionsfakten** fest. Sie ist **keine bestätigte Entscheidung**. Bestätigungen stehen ausschließlich in `DECISIONS.md` und nur nach ausdrücklicher Nutzerfreigabe.

## 0. Leseschlüssel

- `verifiziert`: direkt aus einer Primärquelle (offizielle API/Maven/Manifest) am Abrufdatum gelesen.
- `teilweise`: Primärquelle vorhanden, aber Angabe unvollständig oder inzwischen überholt.
- `offen`: nicht aus Primärquelle bestätigt; Annahme oder Sekundärquelle.
- **Empfehlung** ist ein Vorschlag von Claude; die Entscheidung trifft der Nutzer.
- Versionen veralten schnell. Vor FFX-002 (Repo-Initialisierung) erneut abrufen und dann festpinnen.

## 1. Plattform und Toolchain

| Baustein | Stand am 29.10.2026 | Status | Quelle | Empfehlung |
|---|---|---|---|---|
| Minecraft Java Edition | **26.3** stabil, Release 15.09.2026; 26.4-snapshot-6 parallel in Entwicklung | verifiziert | Mojang Version Manifest; Fabric Meta (game) | 26.3 festpinnen; Port auf 26.4 als eigenes Ticket |
| Java-Laufzeit für 26.3 | **Java 25** (`javaVersion.majorVersion = 25`, Komponente `java-runtime-epsilon`) | verifiziert | Mojang-Versions-JSON für 26.3 | JDK 25 (z. B. Eclipse Temurin) für Build und Entwicklung |
| Fabric Loader | **0.20.1** stabil (Fabric-Blog nannte zum Release noch 0.19.5) | verifiziert | Fabric Meta (loader) | 0.20.1 |
| Fabric API | **0.165.0+26.3** (veröffentlicht 23.10.2026) | verifiziert | Modrinth API, Projekt `fabric-api` | beim Setup aktuellste `+26.3`-Version pinnen |
| Fabric Loom | Blog zum 26.3-Release: **1.17**; aktuelles Loom-Release im Maven: **1.19.3** | teilweise | Fabric-Blog 26.3; Fabric Maven `fabric-loom` | exakte Version in FFX-002 aus dem offiziellen Fabric-Template für 26.3 übernehmen |
| Gradle | Blog zum 26.3-Release: **9.6.0**; aktuell: **9.8.2** | teilweise | Fabric-Blog 26.3; Gradle Versions-API | Wrapper-Version aus dem Fabric-Template übernehmen, nicht frei wählen |
| Mappings / Namen | 26.x nutzt Namen aus dem **unobfuskierten** Spiel; konkreter Mapping-Satz im Blog nicht benannt | teilweise | Fabric-Blog 26.3 | Mojang-Namen ohne zusätzlichen Mapping-Layer; in FFX-002 am Template prüfen |
| Modloader-Strategie | Fabric vorhanden; Iris/Sodium existieren auch für NeoForge | verifiziert (Verfügbarkeit) | Modrinth | vorerst **nur Fabric**, Core loader-unabhängig halten |

## 2. Eingabe, Rendering, Grafik-Backend

| Baustein | Stand | Status | Quelle | Konsequenz / Empfehlung |
|---|---|---|---|---|
| Fenster/Eingabe | 26.3 wechselt von **GLFW auf SDL**; Maus-/Tasten-Konstanten geändert, `InputConstants` statt Hardcodes verwenden | verifiziert | Fabric-Blog 26.3 | relevant für Editor und Hotkeys |
| Texteingabe | Eigene Textfelder müssen bei Fokuswechsel `TextInputManager` bzw. `Minecraft#onTextInputFocusChange` aufrufen, sonst funktioniert die Zeicheneingabe unter SDL nicht | verifiziert | Fabric-Blog 26.3 | **hohes Risiko** für jede UI-Bibliothek; Pflichtprüfpunkt in FFX-007 |
| Rendering-Änderungen 26.3 | Technische Änderungen laut Blog „primär Rendering“, ohne Detailangaben | teilweise | Fabric-Blog 26.3 | Render-Hooks in FFX-005 gegen tatsächliche API prüfen |
| Vulkan-Backend | Nur Sekundärquellen: eingebauter Vulkan-Renderer ab 26.2; Mojang-Changelog konnte nicht abgerufen werden (HTTP 403) | **offen** | Modpack-/Mod-Seiten (Sekundärquelle) | zunächst **OpenGL** als einziges Test-Backend; Vulkan-Status im Spiel selbst prüfen (FFX-003/005) |
| Blaze3D | Pakete `com.mojang.blaze3d.*` weiterhin vorhanden | teilweise | Fabric-Blog 26.3 (Importpfad) | Blaze3D-Abstraktion statt roher GL-Aufrufe (R-003) |

## 3. Begleit-Mods und UI-Kandidaten

| Baustein | Stand für 26.3 | Status | Quelle | Empfehlung |
|---|---|---|---|---|
| Mod Menu | **21.0.0** (23.09.2026) | verifiziert | Modrinth API | optionale Abhängigkeit für den Settings-Einstieg (FFX-004) |
| Sodium | **mc26.3-0.9.3-beta.1** (15.10.2026), Beta | verifiziert | Modrinth API | erst in der Kompatibilitätsmatrix (FFX-058) |
| Iris | **1.11.7+26.3** (30.09.2026) | verifiziert | Modrinth API | erst in der Kompatibilitätsmatrix (FFX-058) |
| Dear ImGui (C++) | v1.93.0 (14.10.2026) | verifiziert | GitHub `ocornut/imgui` | nur Referenz |
| imgui-java (Binding) | v1.92.7.1 (27.07.2026); Kompatibilität mit SDL-Eingabe und Vulkan **unbekannt** | teilweise | GitHub `SpaiR/imgui-java` | **keine Entscheidung**; Spike FFX-007 |
| owo-lib | keine Version für 26.3 gefunden | offen | Modrinth API | nicht als Kandidat einplanen, bis verfügbar |

Lizenzen, Wartungszustand und Sicherheitsrisiken jeder Abhängigkeit werden **vor** einer Aufnahme geprüft (R-004). Diese Tabelle ist keine Freigabe für irgendeine Abhängigkeit.

## 4. Lokale Entwicklungsumgebung (Ist-Zustand, nur gelesen)

| Punkt | Befund am 29.10.2026 | Konsequenz |
|---|---|---|
| Betriebssystem | Windows 11 | Build- und Testbefehle für Windows dokumentieren |
| JDK | nur **JDK 21** (Temurin 21.0.6) installiert, `JAVA_HOME` nicht gesetzt | **JDK 25 fehlt**: Installation durch den Nutzer oder als eigener Freigabepunkt vor FFX-002 |
| Gradle | nicht global installiert | unkritisch, Gradle-Wrapper aus dem Template verwenden |
| Minecraft Launcher | vorhanden; installierte Versionen bis 26.1, **26.3 noch nicht** | 26.3 für Ingame-Tests (R-008A) installieren, sobald FFX-003 ansteht |
| Git | 2.46.2 | ausreichend |

## 5. Offene Entscheidungen für den Nutzer

Diese Punkte sind **nicht** entschieden. Sie werden erst nach ausdrücklicher Bestätigung in `DECISIONS.md` als `BESTÄTIGT` eingetragen.

1. **Zielversion:** 26.3 festpinnen oder auf das 26.4-Release warten.
2. **Modloader:** vorerst nur Fabric (Empfehlung) oder von Beginn an Multi-Loader.
3. **Grafik-Backend:** OpenGL zuerst, Vulkan erst nach eigener Prüfung offiziell unterstützen (Empfehlung).
4. **JDK 25:** Installation selbst durchführen oder als eigenen Arbeitspunkt freigeben.
5. **Loom/Gradle:** Pinning nach offiziellem Fabric-Template in FFX-002 (Empfehlung) oder feste Vorgabe jetzt.

## 6. Quellen (abgerufen am 29.10.2026)

- Fabric-Blog 26.3: https://fabricmc.net/2026/09/15/263.html
- Fabric Meta, Spielversionen: https://meta.fabricmc.net/v2/versions/game
- Fabric Meta, Loader: https://meta.fabricmc.net/v2/versions/loader
- Fabric Maven, Loom: https://maven.fabricmc.net/net/fabricmc/fabric-loom/maven-metadata.xml
- Mojang Version Manifest: https://piston-meta.mojang.com/mc/game/version_manifest_v2.json
- Gradle aktuelle Version: https://services.gradle.org/versions/current
- Modrinth API (fabric-api, modmenu, sodium, iris, owo-lib): https://api.modrinth.com/v2/project/fabric-api/version
- imgui-java Releases: https://github.com/SpaiR/imgui-java/releases
- Dear ImGui Releases: https://github.com/ocornut/imgui/releases
- Vulkan (Sekundärquelle): https://modrinth.com/project/1jk2JhtN
- Mojang-Changelog 26.3 (Abruf mit HTTP 403 fehlgeschlagen): https://feedback.minecraft.net/hc/en-us/articles/48913133328013-Minecraft-Java-Edition-26-3
