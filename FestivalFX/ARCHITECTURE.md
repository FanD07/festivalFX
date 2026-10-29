# FestivalFX — technische Architektur (Vorschlag, noch nicht freigegeben)

> **Planungsstand 0.1, 29.10.2026.** Dies ist ein technischer Architekturentwurf. **Noch kein Code vorhanden**, keine Bibliothek ausgewählt und keine Dateistruktur als Implementierung bestätigt. Architekturänderungen benötigen ADR + Nutzerfreigabe.

## 1. Architecture drivers

1. Ein Showprojekt muss unabhängig von UI-Layout, GPU-Framerate und Grafikeinstellungen gleich ablaufen.
2. Fixtures müssen erweiterbar sein, ohne ein großes Switch/Case für jede Geräteart.
3. Client und Dedicated Server dürfen keine gegenseitigen Laufzeitabhängigkeiten auf Client-Klassen haben.
4. Renderer muss Low/Fallback und eigene RenderCore-Passes unterstützen können.
5. Ein Editor unterstützt **drei auswählbare Stilprofile**, nicht drei voneinander getrennte Implementierungen.
6. Multiplayer muss den Zeitpunkt, die Autorität und Änderungsrechte eindeutig definieren.
7. Save-/Load-/Show-Dateien und Format-Migrationen dürfen nicht von Java-Objektlayouts abhängen.
8. Testbarkeit vor Hero-Features; kleine spielbare Slices.

## 2. Komponentenübersicht (logisch)

```text
                     +---------------------------+
                     | FestivalFX Studio (client)|
                     | - UI shell & layout engine|
                     | - FL/Blender/grandMA style|
                     | - Inspector, Timeline     |
                     +-------------+-------------+
                                   | UI Commands, ViewModels
                     +-------------v-------------+
                     | Show / Fixture Core       |
                     | - Registry & capabilities |
                     | - Cue, Automation, Clock  |
                     | - deterministic evaluator |
                     +------+------+-------------+
                            |      |
           +----------------+      +----------------+
           |                                        |
+----------v-----------+                +-----------v----------+
| Server / Networking  |                | Persistence / Assets |
| - roles/permissions  |                | - world/device state |
| - show time/transport|                | - show format/schema |
| - snapshot/deltas    |                | - migrations/backup  |
+----------------------+                +----------------------+
           |
     replicated state              +------------------------+
           +---------------------->| Client Render Service  |
                                   | - Native fallback      |
                                   | - FestivalFX RenderCore|
                                   | - Experimental future  |
                                   +------------------------+
```

Der reale Dependency-Graph wird bei `FFX-008` nach Prototypen bestätigt. **Nichts hiervon ist derzeit existierender Code.**

## 3. Vorgeschlagene Pakete/Module

```text
src/main/java/<package>/
  core/                  # IDs, Parameter, Profiles, Timebase, Show model
  fixtures/              # Registry, placement, mounting, transforms
  show/                  # Cue evaluation, timelines, curves, effects
  persistence/           # codecs, schema version, migrations
  networking/            # packets, permissions, snapshots
  server/                # authoritative state, tick/transport
src/client/java/<package>/
  client/render/         # renderer interfaces, passes, fallback
  client/editor/         # shell, view models, layout profiles
  client/input/          # hotkeys, focus, selection
  client/settings/       # graphics and editor preferences
src/main/resources/
  assets/<modid>/...      # models, textures, shader assets, lang
  data/<modid>/...        # optional data-driven profiles
```

Ordner und Source-Sets sind **Beispiel**, müssen mit dem konkret genehmigten Fabric-Gradle-Setup übereinstimmen.

## 4. Kerndatenmodelle (Vorschlag)

### FixtureDefinition
- `typeId`: stabile, namespaced Kennung (bspw. `festivalfx:beam_basic`).
- `capabilities`: explizite Parameter-Typen und unterstützte Wertebereiche.
- `modelId`, `renderProfileId`, `mountTypes`, `mechanicalLimits`, `defaultState`.
- Optional: DMX-Personalities/Channel-Mapping getrennt vom Gerätetyp.

### FixtureInstance
- `id`: dauerhafte Instance-ID, **nicht** Blockposition als Identität.
- `typeId` + `definitionVersion`.
- `world/dimension`, `position`, `orientation`, `mount transform`, `groupIds`.
- `parameters`: nur gültige Capabilities, invaliden Input robust behandeln.
- `owner/permission` optional später.

### ShowProject
- `schemaVersion`, `projectId`, `name`, `bpm`, `timeSignature`, `duration`.
- `tracks` mit `clips`, `keyframes`, `effects`; `cues`, `presets`, `markers`, `groups`.
- Referenzen auf Fixture IDs und optionale Medienassets; fehlende Referenzen graceful behandeln.

### ShowClock / Evaluation
- `timeSeconds` / nanos precision: Monotonic-Zeitquelle, nicht Client-FPS.
- `transportState`, `playbackEpoch`, `loopRange`, optional Beat-Grid.
- Pure Evaluation: `evaluate(project, time, fixtureSet) -> parameterSnapshot` soweit möglich.
- Zustandsbehaftete Effekte ausschließlich mit definierten Semantiken bei Seek/Rejoin.

### Command-/Undo-System
- Jedes genehmigte UI-Edit als Command mit `before/after` (oder inverse Operation), Undo/Redo, Validierung und Net-Replay-Regeln.
- Ein Undo im Editor ist nicht automatisch ein Undo globaler Server-Operationen; Konflikte/Berechtigungen definieren.

## 5. Renderer-Design

### API-Konzept

```text
IRenderer
  initialize(GraphicsCapabilities)
  resize(viewport, scale)
  renderFixtureBatch(snapshot, renderContext)
  renderVolumetrics(snapshot, worldDepth)
  composite(minecraftFrame)
  dispose()
```

Das ist ein **Illustrationsinterface**, keine verbindliche Java-Signatur. Ziel ist die Trennung von Simulation und Rendering, nicht ein API-Vertrag vor dem Spike.

**Backend 1 — Native/Fallback:** solide Beam-/Device-Darstellung über Minecraft-kompatible Rendering-Einbindung; Qualität niedriger, breite Kompatibilität.

**Backend 2 — FestivalFX RenderCore:** eigene kompatible Render-Passes, hochqualitative Volumetrik, Haze, Gobo-/Beleuchtung, Post-Processing und Quality-Optionen. Muss dem jeweils vorhandenen Minecraft-Backend gerecht werden.

**Backend 3 — Experimental Full Scene:** nur Forschungsziel: für die komplette/weitgehend komplette Welt eigene Geometrie-/Material-/Beleuchtungspipeline. Nicht auf den kritischen MVP-Pfad legen.

**Nicht vermischen:** `RendererBackend` bestimmt *wo/wie* gerendert wird. `QualityPreset` bestimmt *mit welcher Auflösung/Abtastung/Qualität*. Die Kombination muss validiert werden.

### Besondere Integrationsthemen
- Minecraft 26.2+ bietet optional Vulkan: rohe OpenGL-Calls sind keine generell kompatible Lösung. Die Blaze3D-Abstraktion und konkrete Render Hooks zuerst prüfen.
- RenderState-Extraktion/Thread-Lifecycle respektieren. GPU-Befehle nur an erlaubten Stellen, keine Game-World-Zugriffe ungeprüft auf Render-Threads.
- Transparenz, Depth und Postprocess in OpenGL/Vulkan getrennt verifizieren; Iris/Shaderpacks nur als definierte Kompatibilitätsmatrix.
- Fallback bei unzulässigen Renderer-Fähigkeiten; Fehler-/Recovery-Screen statt schwarzem Bild.

## 6. UI-Architektur

### Gemeinsame Editor-Hülle
- Menubar, WindowManager, Docking/Layout, Hotkey Router, Fokusverwaltung, Shared Selection, Undo/Redo, Tooltip-System, Suchfunktion.
- Panels: StageViewport, FixtureTree, PatchTable, Inspector, Timeline, AutomationCurve, CueList, PresetLibrary, PlaybackExecutor, PerformanceProfiler.
- Kein Panel darf alleinige Quelle für Show-Zustand sein. Panels besitzen nur lokale Darstellungs-/UI-Zustände; Commands gehen in gemeinsame Show-/Editor-Services.

### UI-Profile als Datenkonfiguration

```text
EditorStyleProfile {
  id: FL | BLENDER | GRANDMA3,
  theme, fonts, density, icons,
  initialDockLayout, defaultVisiblePanels,
  keymap, primaryInteractions
}
```

Nicht drei eigenständige UIs schreiben; dieselbe Fixture in jeder Ansicht mit identischer ID/Parametern. Beim Stilwechsel aktuellen Projektzustand erhalten, Layout speichern, Fokus sauber zurücksetzen.

### Umsetzungstechnologie noch offen

- **Dear ImGui:** Reifes Immediate-Mode-Editor-/Docking-Konzept; native Integration in Minecraft/Fabric und Vulkan muss überprüft werden, keine Vorentscheidung.
- **Custom UI:** volle Designkontrolle, hoher Aufwand für Docking, Textinput, Clipping, HiDPI, Tastaturfokus.
- **Embedded Web:** modernes CSS/JS, aber Browser-Bundle, Thread-/Render-Integration, RAM und Packaging-Risiken; vorab evaluieren.

Eine UI-Technologiewahl erst nach einem **echt in 26.3 laufenden POC**, nicht aufgrund schöner Screenshots.

## 7. Networking & Show-Playback

- Serverautorität für gemeinsame Showzeit, Projektversion, Fixture-Edit-Rechte und Show-Transport.
- Client sendet validierte Befehle, nicht unverifizierte `setz jeden beliebigen Wert`-Pakete; Server validiert Permissions, Counts, Kanalbereiche und Rate.
- Snapshot bei Join/Reload; delta updates während Edits; Show-Playback bevorzugt über Startzeit/Transport + lokal deterministisch evaluiert.
- Interpolation reduziert Paketlast; Drift-Korrektur bei längeren Shows; Pause/Seek über absolute Showzeit.
- Keine Show-Wahrheit abhängig von 60/120/144 FPS; Render-Frames werten Zeit aus.
- Private Einzelspieler-Welt kann lokales integriertes Servermodell verwenden.

## 8. Speicherung und Migration

- Format je nach Ziel `JSON`/binärer Codec/Data Components, vorher anhand Volumen, Stabilität und Debugbarkeit auswählen.
- Show-Dateien und Minecraft-Welt-Zustände getrennt; Versionen, Hash/IDs, Fehlermeldungen und sichere Migrationspfade.
- Keine absoluten externen Dateireferenzen im veröffentlichten Standardprojekt, es sei denn bewusst als lokale, optionale Referenz.
- Autosave und Recovery so entwerfen, dass fehlerhafte Migrationen nicht den einzigen Projektstand zerstören.
- Kein ungefragter Zugriff auf Betriebssystem-Verzeichnisse oder externe Dateien.

## 9. Vorgeschlagene Meilenstein-Architektur

1. P0: Build, World-Renderer-POC, UI-POC, Fixture-POC; klare Architekturentscheidung.
2. P1: nur **ein** Moving Head, ein sichtbarer Beam, Inspector, Save/Load, zwei Clients.
3. P2: Capabilities und mehrere Geräte statt N Spezialfälle.
4. P3: deterministische Show-Engine unabhängig von UI.
5. P4: drei UI-Stilprofile aus einem gemeinsamen Komponentenbestand.
6. P5: High-End-RenderCore als nachweisbar austauschbarer Renderer.
7. P6/P7: Multimedia, externe Integrationen, Full Scene/Offline-Rendering als Sonderprojekte.

## 10. ADR- und Spike-Gates

Vor endgültiger Entscheidung fragen:
- Funktioniert das auf Minecraft 26.3 mit dem tatsächlich ausgewählten Loader/Backend?
- Welche Abhängigkeiten (und Lizenzen) sind erforderlich? Werden sie regelmäßig gewartet?
- Was ist der minimal testbare Demo-Build? Wie sieht die Fallback-Strategie aus?
- Wieviel Code ist clientseitig, wieviel serverseitig, wieviel wiederverwendbar?
- Kann UI-/Renderer-Auswahl ohne Änderung des Show-Dateiformats erfolgen?
- Was passiert beim Renderfehler, Reload, Disconnect, Save/Load und Versionswechsel?
- Hat der Nutzer genau diese Technologie/Architektur **ausdrücklich freigegeben**?

## 11. Technische Quellen, vor Implementierung neu prüfen

- https://fabricmc.net/2026/09/15/263.html
- https://docs.fabricmc.net/develop/rendering/basic-concepts
- https://docs.fabricmc.net/develop/getting-started/setting-up
- https://feedback.minecraft.net/hc/en-us/articles/48913133328013-Minecraft-Java-Edition-26-3

**Diese Architektur ist ein Vorschlag und noch keine Genehmigung für technische Änderungen.**
