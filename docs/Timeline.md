# FestivalFX — technische Timeline / Roadmap

> **Version:** Plan 0.1 · **Stand:** 29.10.2026 · **Status:** 100 % Vorschlag, noch nichts implementiert.
> Reihenfolge nach **technischen Abhängigkeiten und Risiko**, nicht nach visueller Attraktivität. **Keine Termine versprochen.** Jeder Punkt wird zunächst vorgeschlagen und separat freigegeben.

## 0. Leseschlüssel

- `TODO` offen; `PROPOSED` Claude hat einen konkreten Plan vorgelegt; `APPROVED` Nutzer hat **genau diesen** Scope freigegeben; `IN_PROGRESS` derzeit in genehmigter Umsetzung; `REVIEW` zur Abnahme; `DONE` vollständig getestet, dokumentiert und ggf. veröffentlicht; `BLOCKED` Hindernis.
- Statuspflege ausschließlich in `STATUS.md`; in dieser Datei stehen die planmäßigen Abhängigkeiten und Abnahmekriterien.
- Jede Zeile ist ein potentiell **eigenständiger Freigabegegenstand**. Größere Zeilen vor Umsetzung in kleine Tickets herunterbrechen. Abhängigkeit = muss vorher technisch geklärt / nachgewiesen sein, nicht zwangsläufig veröffentlicht.
- **Release-Gates:** Gate A (Design/Plan), B (Implementierung/Tests/Docs-Review), C (Commit), D (Push/PR), E (Merge/Release). Alle Genehmigungen gesondert.

## Phase P0 — Projektdefinition & technische Machbarkeit (höchste Priorität)

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-001 | Technische Zielmatrix festlegen | Minecraft 26.3 / Fabric, JDK, Loom, Gradle, Loader, Fabric API, UI-Library und Grafik-Backends geprüft; Entscheidung dokumentiert | keine |
| FFX-002 | Repo-Audit oder Repository initialisieren | Build-/Run-Projekt mit nachvollziehbarer Ordnerstruktur, lizenzklar; Git nur nach Freigabe | 001 |
| FFX-003 | Minimal-Client + Dedicated-Server-Start | Mod registriert sich; Client startet; Server ohne Client-Grafikcode; Logs/Beweise | 002 |
| FFX-004 | Mod-Settings-Entwurf und Konfigurationsspeicher | Versionierte Settings, Validierung, Defaults; Mod-Menu-Einstieg als Konzept | 003 |
| FFX-005 | Renderer-Spike: 1 Testbeam | Sichtbarer positionierbarer RGB-Beam, unterschiedliche Betrachtungswinkel, Messszene; kompatibler Fallback | 003 |
| FFX-006 | Animation-/Fixture-Spike | Ein provisorischer schwenk-/neigbarer 3D-Kopf, Pan/Tilt, Interpolation, Game-Time statt FPS | 003 |
| FFX-007 | Editor-Technologie-Spike | Ein natives Vollbildfenster mit drei Layout-Presets, Fokus, Maus/Tastatur, DPI; Prototyp/Bewertung von Dear ImGui vs. Custom UI | 003 |
| FFX-008 | Architekturentscheidung nach Spikes | ADR: UI-/Renderer-/Networking-Basis, Risiken, MVP-Grenzen, Prioritäten | 004–007 |

**P0-Gate:** Build/Run auf Zielversion funktioniert; `FFX-005`, `006`, `007` zeigen prinzipielle Machbarkeit; keine fundamentalen Backend-Konflikte verschwiegen.

## Phase P1 — Erster vertikaler Slice: **Ein Moving Head vom Block bis zum Editor**

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-010 | Fixture-Datenmodell / stabile IDs / Typfähigkeiten | Pure-Core-Modell, Unit-Tests, klar definierte Zustands- und Capability-Felder | 008 |
| FFX-011 | Basic Moving Head als platzierbarer Block | Eine Fixture mit 3D-Modell, Mount-/Orientierungslogik, definierter Auswahl; eigenes Design vorab freigeben | 006,010 |
| FFX-012 | Pan/Tilt/Dimmer/RGB-Steuerung | Lokal reproduzierbare Werte und Übergänge, min/max aus Profil | 011 |
| FFX-013 | Beam Renderer v1 + Fallback | Blendung/Beam mit Zoom-Parameter, Tiefentest und sauberem Render-State | 005,012 |
| FFX-014 | Persistenz v1 | Fixture-ID, Typ, Pose und Einstellungen Save/Load/Chunk-Reload | 010–013 |
| FFX-015 | Netzwerk v1 | Serverautoritative Fixture-Parameter; anderer Client sieht identischen Zustand | 014 |
| FFX-016 | Minimal Studio UI / Basic Inspector | Professionell wirkender Vollbildeditor, Fixture-Liste, Pan/Tilt/Color/Dimmer; zunächst ein Grundlayout | 007,012 |
| FFX-017 | Spieltest & Doku für Basic Moving Head | Qualitätskontrolle, nutzerbestätigtes Design, `Wiki.md` **erster Feature-Eintrag**, Changelog-/Status-Draft | 011–016 |

**Erster nutzbarer Release-Kandidat:** Ein Spieler platziert einen Moving Head, öffnet den Editor, ändert Werte, sieht den Beam, speichert Welt, lädt sie wieder und kann die Änderung im Multiplayer sehen.

## Phase P2 — Modulares Geräte- und Bühnensystem

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-020 | Geräteprofile & Fixture-Capabilities | Data-driven Capabilities und robuste Validierung; keine Duplikatlogik | P1 |
| FFX-021 | Beam/Spot/Wash/Hybrid | Unterschiedliche Optik/Fähigkeiten/Modelle, kein bloßes Recolor | 020 |
| FFX-022 | RGB-Showlaser v1 | Einzelstrahl, Fan, Tunnel, Wave als separate überprüfbare Effekte | 020,013 |
| FFX-023 | LED-Bar / Pixelgruppen | Pixel-Farbsteuerung, Chaser, Gruppenverhalten | 020 |
| FFX-024 | Strobe / Blinder | Frequenz/Dimmer/Flash, visuelle Schutzoptionen | 020 |
| FFX-025 | Truss / Mounting / Rigging v1 | Montierbare Traversen, Snap/Grid-Ausrichtung, konsistente Orientation | 011 |
| FFX-026 | Fixture-Auswahl, Gruppen, Copy/Paste | Multi-Select, Gruppen-IDs, Duplicate, Undo/Redo | 016,020 |
| FFX-027 | Basis-Patch / kanalorientierte Steuerung | Universe/Adresse, Kanalkollision prüfen, Auto-Patch, kein fertiges DMX-Versprechen | 020,026 |

**P2-Gate:** Mehrere Gerätearten auf einer Bühne mit stabiler Speicherung, nachvollziehbaren Fähigkeiten, Auswahl- und Gruppensystem.

## Phase P3 — Show Engine: deterministische Live- und Timeline-Programmierung

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-030 | Show-Time / Clock & Transport | Play/Pause/Seek/Stop; Tick-/Frame-unabhängige Zeitbasis | P1 |
| FFX-031 | Cue-System / Fade / Delay | Speichern und Auslösen von Lichtzuständen; kontrollierte Übergänge | 026,030 |
| FFX-032 | Parameter-Automation / Keyframes | Pan/Tilt/Farbe/Dimmer als Kurven; Easing/Interpolation | 030 |
| FFX-033 | Timeline Tracks / Clips / Snap | Spuren, Clips, Zoom, Verschieben, Raster, Undo/Redo | 032,016 |
| FFX-034 | Effekte v1 | Circle, Fan, Wave, Chase, Symmetry, Random mit deterministischer Seed-Logik | 032 |
| FFX-035 | Spatial Target / Fixture-Fan | Auswahl eines Weltpunktes, automatische Pan-/Tilt-Lösung und Limits | 026,012 |
| FFX-036 | BPM, Marker, Beat Grid | Tempo, Takt, Marker, Synchronisation zu Show-Zeit | 030,033 |
| FFX-037 | Multiplayer Show Sync | Startzeit, Epoch/Driftkorrektur, Snapshots und Serverrechte | 030,015 |
| FFX-038 | Show-Dateiformat und Projektmanagement | Import/Export, Schema-Version, Recovery und sichere Validierung | 031–037 |

**P3-Gate:** Mindestens eine 60-Sekunden-Beispielshow reproduzierbar auf zwei Clients abspielbar, ohne dass GPU-FPS die Zeitachse verfälschen.

## Phase P4 — Professioneller Editor und UI-Stilprofile

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-040 | UI-Komponentenbibliothek & Layout-Engine | Docking, Panel-State, Themes, DPI, Tooltips, Tastaturfokus | 007,016 |
| FFX-041 | `FL Studio Inspired` Profil | Timeline zentriert, Clip-/Automation-Bedienung, eigene Shortcuts | 033,040 |
| FFX-042 | `Blender Inspired` Profil | großer 3D-Viewport, Outliner, Properties, Transform-Gizmos | 025,026,040 |
| FFX-043 | `grandMA3 Inspired` Profil | Fixture-Gruppen, Presets, Cues, Executor/Fader-Matrix | 027,031,040 |
| FFX-044 | Stil-Auswahl in Mod-Settings | Profil wechseln und speichern; ein gemeinsamer Editor-Backend-Zustand | 041–043,004 |
| FFX-045 | Custom Layout / Panel-Docking | eigenständige Layoutspeicherung, Import/Export optional | 040,044 |
| FFX-046 | Live Overlay Mode | Kleine Pultoberfläche über laufendem Minecraft-Viewport | 040,037 |
| FFX-047 | UI-Qualitäts-/Kompatibilitätstests | WQHD/4K und kleiner, Skalierung, Fokus, Controller-Konflikte, Mehrsprachigkeit | 040–046 |

**Design-Grundsatz:** `FL Like`, `Blender Like` und `grandMA3 Like` sind **wählbare Oberflächen-Stile** im Mod-Menü, nicht drei getrennte Anwendungen. Gemeinsamer Funktionsumfang, unterschiedliche visuelle Hierarchie/Workflows.

## Phase P5 — RenderCore, Qualitätsprofile und realistische Optik

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-050 | Renderer-Interface / Backend-Auswahl | Native/Fallback vs. eigener FestivalFX RenderCore; Backend-Fähigkeiten und Warnungen | 008,013 |
| FFX-051 | Quality Presets: Low → Max Quality | Separate Qualitätsoptionen, Profile, robustes Umschalten; keine „FPS-Garantie“ | 050 |
| FFX-052 | Optimiertes Volumetric Beam Rendering | Raymarching/temporal reprojection, Haze-Medium, Culling/Batching | 050 |
| FFX-053 | Gobo/Focus/Zoom/Prism | Optikprofile, Projektionen, Rotation; sichtbare Testszenen | 021,052 |
| FFX-054 | Dynamische Beleuchtung von Weltgeometrie | Beleuchtung auf Blöcken, Wand/Boden, steuerbare Reichweite | 052 |
| FFX-055 | Shadows / Occlusion | Traverse/Objekte beschatten Beams und Projektionen | 054 |
| FFX-056 | HDR / Bloom / Tonemapping | reproduzierbare Helligkeit/Glow, keine pauschale SDR-Überbelichtung | 052 |
| FFX-057 | Material-/Reflexionsmodell (Forschung) | Bewertung SSR vs. Raytracing; messbarer Proof of Concept | 054 |
| FFX-058 | Shaderpack-/Backend-Interoperabilität | Kompatibilitätsmatrix mit Iris/Shaderpacks und OpenGL/Vulkan soweit verfügbar | 050–057 |
| FFX-059 | Benchmark & Grafik-Einstellungen | reproduzierbare Belastungsszenen, klar benannte VRAM/FPS-Kosten | 051–058 |

**P5-Gate:** Bildqualität und Kosten objektiv messbar. `Max Quality` ist ein **Preset**, `FestivalFX RenderCore` eine **Renderer-Option**; beides unabhängig kombinierbar, sofern technisch unterstützt.

## Phase P6 — Fortgeschrittene Showtechnik

| ID | Arbeitsschritt | Ergebnis / Nachweis | Voraussetzung |
|---|---|---|---|
| FFX-060 | Haze-/Nebelmaschinen | Ein/Aus, Dichte/Zonen und Zusammenspiel mit Licht | 052 |
| FFX-061 | Simulierte CO₂/Flammen/Funken/Konfetti | Showtrigger, visuelle Einstellungen, Multiplayer | 037 |
| FFX-062 | LED-Wall v1 | Pixel-/Pattern-Render, Panel-Layouts, Materialzuweisung | 023,050 |
| FFX-063 | Medienimport v1 (optional) | Sichere Bild-/Videodatei-Unterstützung, bewusst begrenzte Formate | 062 |
| FFX-064 | Musik/Audio-Waveform (optional) | Lokale Audiodatei, BPM/Marker, Lizenzhinweise | 036 |
| FFX-065 | Live-Effekte / Fader-Seiten / Preset-Macros | Sofortsteuerung, Flash-Buttons, Blackout, Prioritäten der Playback-Layer | 043,037 |
| FFX-066 | Fixture Studio / Custom Profiles | Eigene Capability-/Modell-/Kanalprofile, Import/Validierung | 020,047 |

## Phase P7 — Forschung / experimentelle Oberklasse

| ID | Forschungspunkt | Statusgrenze |
|---|---|---|
| FFX-X01 | Own RenderPipeline — **vollständiger Szenenrenderer**, statt nur Post-/Lighting-Passes | Forschungsprojekt; nicht Teil des MVP; braucht formelles Go/No-Go |
| FFX-X02 | Raytraced / volumetric path-traced Lighting | Machbarkeit und Hardware-Backend separat prüfen |
| FFX-X03 | Raytraced Reflections / PBR der gesamten Minecraft-Welt | Sehr hoher Aufwand; Weltmaterialien und dynamische Geometrie sind eigene Probleme |
| FFX-X04 | Cinematic Offline-Rendering / deterministischer Videoexport | Separater Export- und Codec-Workflow; kein Versprechen für 60 FPS ohne Implementierung |
| FFX-X05 | MIDI / OSC / Art-Net / externes Web-Pult | Netzwerk-/Sicherheits-/Lizenzfreigabe, klare Vertrauensgrenzen nötig |
| FFX-X06 | Node-basierter Effekteditor | Zusätzlicher Editor mit Graph-Validation und deterministischer Ausführung |
| FFX-X07 | Multi-Window / Multi-Monitor Desktop Integration | OS-spezifisch und möglicherweise außerhalb von Minecraft nötig |

## Vorschlag für Release-Pakete (ohne Zeitangabe)

- **PoC:** FFX-001 bis FFX-008 (Machbarkeit, keine öffentliche Mod).
- **Alpha 0.1:** FFX-010 bis FFX-017 (Moving-Head-Vertikalschnitt).
- **Alpha 0.2:** wichtige P2-Geräte/Truss-/Gruppenfunktionen nach Einzelentscheid.
- **Alpha 0.3:** P3 Show Engine & Timeline.
- **Alpha 0.4:** P4 UI-Stilprofile.
- **Beta:** P5 Grafik-/RenderCore-Qualität und P6 erweiterte Showtechnik; Reihenfolge bei Bedarf begründet ändern.
- **Experimental:** P7-Projekte separat; kein Release-Versprechen.

## Bei jeder Planänderung dokumentieren

1. Warum wurde ein Schritt eingefügt/verschoben/gestrichen?
2. Welche Abhängigkeiten ändern sich?
3. Hat der Nutzer die Änderung genehmigt?
4. Update `STATUS.md`, ggf. `DECISIONS.md` und `Vision.md` nach Freigabe.
