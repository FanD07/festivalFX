# FestivalFX — Vision, Produktidee und langfristiger Feature-Katalog

> **Vision 0.1 · Brainstorming eingefroren am 29.10.2026**
> **Dokumenttyp:** Zukunftsbild, **keine Liste bereits implementierter Funktionen**. Alles steht unter Prüfung, Priorisierung und Einzel-Freigabe. Für den echten Stand siehe `STATUS.md` und `Wiki.md`.

## 1. Produktversprechen

**FestivalFX ist eine ambitionierte Minecraft-Java-Mod, mit der Nutzer innerhalb ihrer Minecraft-Welt realistische Festival-, Konzert- und Lichtshows bauen, gestalten, programmieren, steuern und reproduzierbar aufführen können.**

Das Ziel ist ein **Hybrid aus professioneller Lichttechnik-Simulation und spektakulärer, kreativer Festival-Visualisierung**. Die Mod soll für einfaches Experimentieren taugen, aber langfristig auch präzise Cue- und Timeline-Programmierung, große Fixture-Rigs, hochwertige Lichtstrahlen und komplexe Multimedia-Shows bieten.

**Leitbild:** „Ein vollwertiges Lichtshow-Studio, das innerhalb von Minecraft läuft und Minecraft als echte Bühne benutzt.“ Der Editor ist **keine 08/15-Minecraft-GUI**, sondern eine eigenständige, modern gestaltete, Desktop-artige Ingame-Anwendung. Was eine reale Show-Konsole ernst nimmt (Fixture-Parameter, Gruppen, Zeiten, Wiederholbarkeit), verbindet FestivalFX mit spielerischer Bühnenfreiheit.

### Wer soll die Mod verwenden können?
1. **Builder/Kreative:** Bühne bauen, Truss montieren, Licht schnell testen.
2. **Show-Programmierer:** Fixtures patchen, Cues/Presets erstellen, Musik synchronisieren, präzise Sequenzen gestalten.
3. **Live-Operator:** Vorbereitete Shows und improvisierte Szenen in Echtzeit spielen.
4. **Filmemacher/Creator:** Atemberaubende, reproduzierbare Festivalaufnahmen, langfristig mit hochwertigem Offline-Rendering.
5. **Multiplayer-Server:** Gemeinsam Bühne bauen, Rollen/Berechtigungen verteilen, zeitgleich dieselbe Show sehen.

### Nichtziele der ersten Version
- Kein echter Ersatz für zertifizierte Beleuchtungssteuerung; DMX/Art-Net/pyrotechnische Echtansteuerung ist **nicht** Teil des MVP.
- Keine vollwertige Fotorealismus-Engine beim ersten Release.
- Keine zwingende Abhängigkeit von einem externen Browser oder Cloudkonto.
- Kein eigenmächtiges Auslesen lokaler Musiksammlungen/Dateien.
- Kein Nachbau der proprietären FL-Studio-/Blender-/grandMA3-Software.

## 2. Die fünf gleichberechtigten Produktpfeiler

1. **Gerätephysik und Showtechnik:** detaillierte Fixtures mit realistischen Fähigkeiten, Bewegungen und gutem Look.
2. **Show Engine:** deterministische Zeitbasis, Cues, Automation, Gruppen, Live-Playback, Musik-/Timecode-Bezug.
3. **FestivalFX Studio:** hervorragender, vollständig in Minecraft laufender Editor mit wechselbarem UI-Stil.
4. **RenderCore:** skalierbare Beleuchtung vom günstigen Fallback bis zu extrem anspruchsvoller Lichtqualität.
5. **Zuverlässigkeit:** stabile Projekte, Save/Load, Multiplayer-Synchronität, gute Performance-Messung und sichere Konfiguration.

## 3. Spiel- und Nutzungserlebnis

### 3.1 Build / Setup
- Bühnen direkt im Minecraft-Worldspace bauen; normale Blöcke, Truss, Panels, Stützen, Rigging, Dekoration.
- Fixtures als Items/Blöcke platzieren, ausrichten, an Traversen montieren, optional Snap/Alignment für Reihen.
- Einzelne Geräte mit präzisen Positionen/Rotationen, Einheiten, Mount-Typ, ID, Typ/Fähigkeiten, universeller Gruppenmitgliedschaft.
- Mehrfachauswahl über Worldspace-Mausauswahl, Outliner oder Filter; kopieren/einfügen, Duplikate fortlaufend adressieren.
- Visuelle Montage- und Ausrichtungs-Gizmos sowie Preview-Positionen; die Bühne bleibt während des Editierens sichtbar.

### 3.2 Programming / Design
- Vollbild-Editor mit Fixture-Browser, Stage-Viewport, Inspector, Timeline, Automation, Cue-Stack, Presets und Show-Bibliothek.
- Live-Vorschau: jede Änderung an Dimmer, Farbe, Pan/Tilt, Zoom oder Gobo erscheint **sofort in der Welt**.
- Cues und Timeline parallel; spätere Prioritätsregeln bestimmen, welche Layer gewinnt, wenn mehrere Steuerquellen dieselben Parameter beeinflussen.
- Show-Projekt exportieren/importieren; Vorlagen/Demos bei Bedarf; keine Abhängigkeit von spezieller Weltposition ohne explizite Verknüpfung.

### 3.3 Live / Performance
- Start, Pause, Fortsetzen, Stopp, Seek, Blackout, Live Flash, BPM-Tap, Show-/Cue-Sprung, Fader.
- Ausgewählte Controls als minimales Overlay statt nur Fullscreen-Editor; Minecraft-Steuerung bleibt nach bewusster Eingabefokus-Zuweisung nutzbar.
- Live-Eingriffe und Timeline-Wiedergabe definieren Priorität und Verhalten bei Neustart/Disconnect.
- Multiplayer-Berechtigungen: Zuschauer, Builder, Programmer, Operator, Admin (Rollen nur nach konkreter Freigabe implementieren).

## 4. Detaillierter Geräte-Katalog (Zielbild)

### 4.1 Moving Heads — höchste Priorität

**Basistypen:** Beam, Spot, Wash und Hybrid. Nicht bloß andere Texturen: jeder Typ bekommt definierte Capability-Sets.

**Gemeinsame Parameter:**
- `Fixture ID`, Name, Gerätetyp, Gruppe(n), Montage-/Ground-Transform, Weltposition/Rotation.
- Pan, Tilt, mechanische Min-/Max-Werte; Home/Calibration, optional Achseninvertierung.
- Pan-/Tilt-Geschwindigkeit, Beschleunigung, Bewegungs-Easing, ggf. Bewegungsgrenzen/Soft Stops.
- Intensität/Dimmer, Shutter/Blackout, Strobe-Frequenz/-Modus, Grundfarbe, Mischung.
- Beam-Ausrichtung und -Breite; lokale Parameteränderung im Editor und zeitbasierte Steuerung.
- Geräteprofil-basierte Features (Fehlparameter nie als funktionierender Slider anzeigen).

**Beam:** schmale, harte Lichtbündel, prismatische Aufspaltung, Fokus/Beam-Shape, sehr klare Haze-Beams.

**Spot:** Gobo-Slots, Rotation, Fokus, Zoom, Iris, Frost/Prisma optional; erkennbare Projektionen auf Bühne/Objekte als ambitioniertes Ziel.

**Wash:** breite, weiche Lichtkegel, Farbverläufe, Edge-Softness; optional Pixel-Mapping.

**Hybrid:** je nach Optikprofil zwischen Beam/Spot/Wash wechseln, ohne die jeweilige Funktion physikalisch uneingeschränkt zu behaupten.

**Look & Design:** modellierte Yokes, Kopf, optische Front, Linse, bewegliche Mechanik. Gerätemodelle vor Umsetzung visualisieren und vom Nutzer abnehmen lassen; allgemeine Basis kann fiktiv sein, reale Brands nur mit Lizenz-/Namensprüfung.

### 4.2 Showlaser — sehr hohe Priorität

- Vollfarbiger RGB-Laser mit mehreren Kanälen und Präzisionssteuerung.
- Einzelstrahl; Fan/Fächer (symmetrisch, asymmetrisch), Tunnel/Kreise, Waves, Muster/Punktlisten; modulare Pattern-Library.
- Musterparameter: Scan-Speed, Winkel, Fläche, Größe, Rotation, Offset, Farbe, Intensität, punktweise Blankings (Simulation).
- Multi-Laser-Sync, Gruppen, Musik-/BPM-Trigger, Timing-Offsets.
- Lichterscheinung im Haze, harte Linien, Blendung/Bloom als Rendering-Option, Occlusion wo technisch korrekt.
- Später Nutzer-Patterns per Editor mit Validierung; realweltliche Sicherheitsregeln nicht auf echte Hardware übertragen.

### 4.3 LED-Bars / Pixel-FX

- Unterschiedliche Länge und Pixelanzahl; Einzelpixel, Gruppen, Mapping, Diffusion.
- Farbverläufe, Chaser, Pulse, Strobe, Mirror, Wave; Timeline-Automation; globale Helligkeit.
- Später flexibles Panel-/Matrix-Pixel-Mapping.

### 4.4 Strobes und Blinder

- Strobe-Frequenz, Pulsbreite, Intensität, RGB/Weiß, Dimmer, Flash/Release.
- Blinder mit mehreren Lichtquellen und sichtbarem Blend-/Bloom-Verhalten.
- Option zur Verringerung/Unterdrückung schneller Blinkeffekte für empfindliche Spieler.

### 4.5 LED-Walls

- Platzierbare, zu größeren Wänden verknüpfbare Panels; erkennbarer Pixelabstand/Panel-Raster, Helligkeit, Seitenverhältnis und Medienzuordnung.
- Eingebaute Animationen, Text/Logos, Gradients, Shader-Visuals; später Bilder und Videos aus explizit ausgewählten Dateien.
- Audio-Reaktivität als optionale Datenquelle; sichere Längen/Größen/Decoder-Limits.

### 4.6 Truss / Rigging / Bühnenzubehör

- Gerade Traversen, Winkel, Ecken, Bögen nach Priorität; einfache Halterungen, Klemmen, vertikale/seitliche Montage.
- Snap-to-Truss, Rotation und Fixtures mit Parent-Transform; stabile Weltkoordinaten nach Block-Updates.
- Optional Kettenzüge/Lifts als visuelle Showmechanik; keine realen Physik-/Sicherheitszusagen.

### 4.7 Nebel / Haze

- Haze-Maschine als Showgerät, Zustände Ausstoß/Leistung/Dichte.
- Räumliche Dichteverteilung (simpler globaler Haze zuerst; lokale Volumen später), Streuung entlang des Strahls, Lichtfarbe, Abklingen.
- Render-sparsame Alternative für Low-Quality; kein Muss für jeden Nutzer.

### 4.8 Special FX (Spielsimulation)

- CO₂-Jets, Flammenmaschinen, Spark-Effekte, Nebelstöße, Konfetti.
- Cue-/Timeline-triggerbar, Gruppen und Delays; visuelle Korrelation mit Musik-Drops.
- **Ausschließlich in der Spielwelt**, keine Steuerung realer Pyrogeräte.

## 5. Show-Engine — intern konsistentes Zeitmodell

### 5.1 Grundmodell

- **Show Project:** eindeutige ID, Titel, Formatversion, Tempo/Zeitsignatur, Timeline, Fixture-Zuordnungen, Presets, Cues, Referenzen auf optionale Medien.
- **Fixture:** eindeutige ID, Profile, Capability-Mapping, Transform, Gruppen, Zustand.
- **Parameter:** typisierter Wertebereich + Einheit, Default, dynamische Interpolationsregeln und zulässige Quellen.
- **Transport:** `STOPPED`, `PLAYING`, `PAUSED`, `SEEKING`; absolute Showzeit und optional Beat-Tick.
- **Ownership:** Server autorisiert globalen Playback-Zustand und Änderungen; lokaler Client zeigt nur an und interpoliert.

### 5.2 Cues & Presets

- Presets für Farbe, Position, Beam/Optik, Intensität, Effektkonfiguration; Presets nach Gerätetyp/Fähigkeiten.
- Cue-Liste, Fade-In/Out, Delay, Go/Back, Zeittrigger; Speicherung und Wiederverwendbarkeit.
- Regeln für Stacking/Priorität mehrerer Cues, sodass Ausgabe deterministisch bleibt.

### 5.3 Timeline und Automation (FL-Studio-Inspiration)

- Tracks pro Fixture/Gruppe/Parameter; Clips; Drag & Drop, Duplizieren, Loop, Snap-to-Grid und Snap-to-Beat.
- Keyframes, Easing-Kurven, Parameter-Lanes, Curve Editor, Multi-Selection, Undo/Redo.
- Waveform-Ansicht auf Wunsch, Marker/Abschnitte (Intro, Build-up, Drop, Break, Outro), Zoom/Scroll, Timecode.
- Unterschiedliche Zeitbasen: Sekunden, Takte/Beats; sauberer BPM-Wechsel optional später.
- Wiedergabe muss bei gleichem Zustand und Zeitwert reproduzierbar sein – unabhängig von Render-FPS.

### 5.4 Generative Effektbibliothek

- **Circle:** Pan-/Tilt-Kreis mit Radius, Richtung, Phase, Tempo.
- **Fan:** symmetrische/asymmetrische Fächeröffnung nach Fixture-Reihenfolge.
- **Wave:** Phasenverschiebung innerhalb einer Gruppe.
- **Chase:** Lauflicht über geordnete Fixtures / Pixel.
- **Symmetry:** links/rechts spiegeln; Gruppenpaarung erforderlich.
- **Random:** reproduzierbare Zufalls-Sequenz durch expliziten Seed.
- **Pulse:** Dimmer/Farbe nach BPM/Trigger.
- **Position Target:** gemeinsamen Weltpunkt anwählen, Pan/Tilt aus Koordinaten lösen, mechanische Limits beachten.
- Später Node-Graph/Expression Engine mit sicherer, deterministischer Auswertung.

### 5.5 Audio / Musik (optional, später)

- Vom Nutzer ausgewählte lokale Audiodateien; keine automatisierte Suche/Downloads.
- Waveform, Timecode, BPM-Analyse, Tap Tempo, Beat Marker; Beat-Erkennung ist Hilfestellung, nicht immer exakt.
- Später MIDI- und OSC-Steuerung sowie Art-Net/DMX-Bridge als gesondert geprüftes Integrationsprojekt.

## 6. FestivalFX Studio — professionelle Ingame-Software

### 6.1 Ein Editor, **drei UI-Stilprofile** in den Mod-Einstellungen

**Wichtigste gemeinsam getroffene Entscheidung:** `FL Studio Inspired`, `Blender Inspired`, `grandMA3 Inspired` sind **optische und bedienungsbezogene Auswahlprofile** für *denselben Editor*. Der Nutzer wechselt sie im Mod-Menü unter beispielsweise `FestivalFX → Editor → Interface Style` und speichert seine Auswahl dauerhaft. **Es sind nicht drei getrennte Programme oder Datenmodelle.**

| Stilprofil | Visuelle Sprache | Standardlayout | Typische Interaction Patterns |
|---|---|---|---|
| **FL Studio Inspired** | Farbig, musikalisch, DAW-artig | Breite Timeline / Spuren im Zentrum | Clips, Automation, Patterns, Transport, Effektbrowser, BPM-Raster |
| **Blender Inspired** | Dicht, technisch, 3D-fokussiert | großer Stage-Viewport, Outliner, Inspector | 3D-Gizmos, G/R/S-ähnliche Interaktionen nach Konfliktprüfung, Objektfilter, Properties |
| **grandMA3 Inspired** | Konsolenartig, kontrollorientiert | Fixture-/Group-Grid, Fader, Cue-Stack, Presets | Programmer, Presets, Executors, Fixture-Selektionsmatrix, Playback-Fader |

Alle benötigten Tools bleiben in jedem Stil verfügbar; Profile gewichten Anzeige und Shortcuts anders. Keine proprietäre Gestaltung 1:1 übernehmen.

### 6.2 Editor-Funktionsumfang

- Professionelle Vollbild-GUI (kein Item-Inventar-Haupteditor) mit optionalen Live-Overlay-Panels.
- Dockbare, verschiebbare, skalierbare Panels; Workspace/Layout-Speicher; Custom Layout langfristig.
- Menubar, Toolbar, Projektbrowser, Outliner/Fixture Browser, Objekt-Inspector, Patch-View, 3D-Viewport, Timeline, Curve Editor, Show/Cue-Library, Live-Executor.
- 3D-Bühnenvorschau aus echter Minecraft-Welt; Auswahl über Raycast und Hierarchie; Selection-Gizmos.
- Shortcuts, Kontextmenüs, Drag & Drop, Copy/Paste, Undo/Redo, Multiselect, Autosave, Tooltips, Suche/Filter.
- UI-Scale/DPI/High-DPI, WQHD/4K, kleine Auflösungen ohne überlaufende Dialoge, Maus-/Tastaturfokus.
- Lokalisierung: Deutsch/Englisch angestrebt, andere Sprachen später.
- Designs werden **vor Implementierung** vom Nutzer als Design-/Funktionsdirektor genehmigt, inklusive Icons/Buttons/Layouts.
- Optional später: mehrere native Fenster/Monitore; eine separate native App ist **nicht** Ziel für MVP.

### 6.3 Mod-Menü / Settings

**Kategorien:** General, Editor, Appearance, Controls, Graphics, Advanced Rendering, Audio, Multiplayer/Permissions, Debug (je nach Entwicklungsstand).

**Editor-Settings:** UI-Profil, Theme/Akzent, Skalierung, Animationen, Kompaktmodus, Toolbar-Placement, Shortcut-Profil, Layout-Reset, Overlay-Toggle, Autosave.

**Graphics-Settings:** Qualitäts-Preset, Renderer, Beam Samples, Haze Quality, Bloom, Shadows, Gobo Quality, Reflections, Quality Warnings; nur tatsächlich implementierte Optionen anzeigen.

**Verhalten:** Settings bleiben nach Neustart erhalten; ungültige Werte zurückweisen, Warnungen für Performance/Kompatibilität; keine heimlichen Änderungen aktiver Welt-/Serverdaten durch rein visuelle Settings.

## 7. Graphics — skalierbare Qualität + OWN RENDERPIPELINE

### 7.1 Zwei voneinander getrennte Schalter

**Grafik-Preset:** `Low`, `Medium`, `High`, `Ultra`, **`Max Quality`**, optional `Cinematic` in einem separaten Offline-Workflow.

**Render-Methode/Backend:**
1. **Native/Fallback:** Minecraft rendert Welt; FestivalFX zeichnet vereinfachte Effekte darüber. Gute Kompatibilität und Performance.
2. **FestivalFX RenderCore:** Eigene Lighting-/Post-/Volumetric-Passes sowie passende Daten- und Buffer-Integration. Die Welt bleibt größtenteils von Minecraft gerendert. *Das ist die realistische langfristige Hauptoption.*
3. **OWN RENDERPIPELINE — Experimental Full Scene:** Experimentelle Möglichkeit, größere Teile der Welt unabhängig selbst zu rendern. **Eigenständiges Forschungsprojekt**, nicht nur „Ultra an“ und kein MVP-Versprechen.

Ein Spieler könnte `RenderCore + Max Quality` wählen. Nicht jede Kombination ist auf jeder GPU/jedem Minecraft-Backend möglich; UI muss Grenzen transparent ausweisen.

### 7.2 Visuelle Qualitätsziele

- Überzeugende Beam-Geometrie und Lichtstreuung über Blickwinkel/Distanz.
- Physikalisch plausible, aber kontrollierbare volumetrische Scattering-Näherungen und Haze-Medium.
- Sichtbare weiche/härtere Ränder, Farbtemperatur/RGB, Beam-Breite, Zoom, Focus, Iris, Prism/Gobos.
- Licht auf Boden/Wänden/Objekten inklusive beweglicher Projektion; ggf. Shadow Maps und Occlusion.
- Bloom, HDR-/Tonemapping-Konzept, Anti-Aliasing, Temporal Accumulation, Denoising.
- Material-Highlights, feuchte/reflective Bühnenböden, optionale Reflexionen und spätere PBR-Vernetzung.
- Rauch-/Nebelverteilung mit ggf. lokaler Dichte, aber transparenter Approximation statt uneinlösbarem Realismusversprechen.
- Animierte Fixtures mit mechanischer Beschleunigung/Bremsung und kontinuierlicher Interpolation.

### 7.3 Engineering-Realität

„Eigene Pipeline“ bedeutet **nicht automatisch** Hardware-Raytracing, korrekte Schatten, Raymarched Haze oder fotorealistische Minecraft-Blöcke. Jeder Baustein muss gesondert erforscht, implementiert, gemessen und freigegeben werden. Spezielle technische Risiken sind: Render-Backend OpenGL/Vulkan, Shaderpack-Kollisionen, Z-Buffer/Depth-Zugriff, Transparenz-Komposition, Frame-Budget und VRAM.

**Max Quality darf FPS kosten**, darf aber nicht die gesamte Installation destabilisieren. Ein robuster Fallback und Wiederherstellung der Grafikeinstellungen nach Absturz sind langfristig wichtig.

### 7.4 Performance-Strategie

- Fixture-Culling, Occlusion/Frustum-Entscheidungen, Instancing/Batching, dynamische Buffer-Reuse.
- Adaptives Sampling als Option in High/Ultra, bei Max Quality frei einstellbar; temporal reprojection nur wenn Bildqualität geprüft.
- Separat skalierbare Beam-/Haze-Auflösung, Samples, Shadow-Resolution, Lichtanzahl, Renderdistanz.
- Interne Profiler-Metriken: Fixture Count, aktive Beams, GPU-/CPU-Kosten, VRAM, Draw Calls (sofern messbar), Netzwerktraffic.
- Reproduzierbare Benchmark-Bühnen: 1 / 20 / 100 / 300 Fixtures **als Testfälle**, keine Garantie, dass jede Konfiguration diese Mengen flüssig bewältigt.
- Zielhardware nicht auf bestimmten PC festnageln; Default für breite Hardware, Max Quality bewusst extrem.

### 7.5 Cinematic / Offline Rendering (später)

- Frame-genaue deterministische Auswertung der Show zu beliebigem Timecode.
- High-Sample/High-Resolution-Rendering ohne Echtzeit-FPS-Zwang; Export von Einzelbildern, später Video über ausdrücklich gewählten Encoder.
- 30/60 FPS fertiges Video selbst bei langsamem Frame-Rendering **nur nach Entwicklung eines wirklichen Offline-Exporters**.
- Kamerawege, Depth of Field, Motion Blur, Render-Queue, transparente Hintergründe/Compositing-Passes als Zukunftsideen.

## 8. Technische Daten & Multiplayer

- Zielplattform: Minecraft **Java Edition**, zunächst **26.3** als zum Planungsdatum letzte stabile Version; Fabric als erster Modloader, nicht dauerhaft erzwungen.
- Derzeitige Referenz (Fabric, 15.09.2026): Loom 1.17 / Gradle 9.6 für 26.3 genannt. JDK-/API-Genauversion vor Initialisierung neu verifizieren und festpinnen.
- Java für Core und Client; Build mit Gradle und reproduzierbarer Versionsverwaltung.
- Dedicated Server enthält keine Client-Renderer/UI-Abhängigkeit.
- Gemeinsame Tick-/Showzeit (Serverautorität); Clients interpolieren Animationen zwischen Sync-Paketen; nach Join/Reconnect aktueller Snapshot.
- Projekt-Dateiformate versioniert und migrierbar; begrenzte Datenmengen, Backup/Restore, Validierung beim Laden.
- Clients mit unterschiedlichen Grafikpresets sehen denselben Show-Zustand, aber in unterschiedlicher Qualität.
- Mod-Server-Rechte für Bearbeiten und Live-Playback, optional Rollen und Log der Trigger.

## 9. Zukunfts-Integrationen

- **Fixture Studio:** Benutzerdefinierte Geräteprofile, eigene 3D-Geometrie/Objektmodelle, Capability-Mapping, Kanaldefinitionen, Import mit Validierung.
- **DMX/Art-Net/MIDI/OSC:** Standardprotokolle als optionale Adapter; externe Ports nie von allein öffnen. Echte Hardware-Steuerung wäre gesondert zu sichern und nicht mit Spiel-SFX zu vermischen.
- **Browser-Controller:** Optionales UI auf externem Bildschirm; Kernfunktion bleibt Ingame. Authentifizierung/Bind-Adresse/Zugriffsmodell vor Umsetzung klären.
- **Node-Editor:** Eigene Bewegungs-/Licht-Patches via grafischer Nodes, sichere Auswertung.
- **Community-Show-Projekte:** Vorlagen, Import/Export; Online-Hosting/Marketplace derzeit nicht beschlossen.
- **Fixture-Presets/Library:** Katalog für verschiedene Lampentypen und Anwender-Templates.
- **Multi-Monitor:** Separat zu prüfende Zukunftsidee.

## 10. Priorisierung und Feature-Bewertung

| Bereich | Bedeutung | Frühestes Ziel | Risiko |
|---|---|---|---|
| Basic Moving Head + sichtbar guter Beam | Kernidentität | erster vertikaler Slice | hoch (Rendering) |
| Solider professioneller UI-Prototyp | Hauptmerkmal | technische Spikes | mittel-hoch |
| Fixture-Auswahl/Groups/Save/Load | Benutzbarkeit | Alpha | mittel |
| Laser- und LED-Basissystem | Festivalwirkung | nach Core | mittel-hoch |
| Timeline/Cues/Sync | echtes Studio | nach Basis | hoch |
| Drei wählbare UI-Profile | wichtiges Designfeature | nach gemeinsamem UI | mittel |
| Max Quality + RenderCore | Optik-Differenzierung | auf Basis des MVP | sehr hoch |
| Full Scene OWN RENDERPIPELINE | experimentelle Maximalvision | Forschung | extrem |
| Offline-Cinematic | Trailer/Creator | Forschung | sehr hoch |
| Echte externe Steuerprotokolle | professionelle Integration | später | hoch inkl. Security |

## 11. Offene Produktentscheidungen — NICHT stillschweigend entscheiden

1. Finaler Modname, Logo, Farbpalette, Schriftbild und UI-Sprache(n).
2. Fabric-only oder spätere Multi-Loader-Architektur.
3. Mindest-GPU und Support für OpenGL/Vulkan/Shaderpacks; welche Kombinationen offiziell getestet werden.
4. Exakte Fixture-3D-Modelle, reale vs. fiktive Gerätetypen, Textur-/Animationsstil.
5. UI-Bibliothek (ImGui, eigene Widgets, eingebetteter Browser oder Hybrid) nach Proof of Concept.
6. Definition der ersten Show-Projektdatei, Export-/Importmechanik und Sicherung der Inhalte.
7. Musikhandling für Multiplayer (synchronisierte Wiedergabe, Rechte, Format, Streaming ist nicht beschlossen).
8. Sicherheits- und Berechtigungsmodell beim externen Steuerzugriff.
9. Ob Screenshots/Videos/Objektmodelle von Drittanbietern in die Mod dürfen — Lizenzfrage.
10. Umfang und Obergrenzen des ersten öffentlichen Alpha-Releases.

## 12. Beispiel-User-Journey als langfristiger Zieltest

> Spieler baut eine 40 Meter breite Minecraft-Festivalbühne mit Truss und LED-Wall; montiert 32 Beam-Heads, 16 Wash-Heads und 8 Lasersysteme. Im Mod-Menü stellt er `FL Studio Inspired` und `RenderCore + Max Quality` ein. F8 öffnet FestivalFX Studio. Er patcht Geräte, bildet Gruppen, lädt *bewusst* eine lokale Audiodatei, setzt BPM/Marker und programmiert eine 60-Sekunden-Show. Die Show speichert sich, läuft auf einem Multiplayer-Server synchron und kann später mit anderen Grafikpresets wiedergegeben werden. Für den Trailer exportiert er die Sequenz irgendwann im separat implementierten Cinematic Mode.

**Das ist ein Zielbild, kein implementiertes Beispiel.**

## 13. Leitlinien für künftige Ideen

- Jede neue Idee erhält vor Umsetzung eine Feature-ID, Nutzen, Abhängigkeiten, Alternativen, Mockup bei UI/3D und Abnahmekriterien.
- `Vision.md` speichert **alle Ideen**; `Timeline.md` priorisiert nur sinnvolle Umsetzungsschritte; `DECISIONS.md` archiviert bestätigte Richtungsentscheidungen; `STATUS.md` unterscheidet Wunsch von Realität.
- Niemals eine Idee still löschen: Verschobenes/Verworfenes mit Begründung und Datum sichtbar dokumentieren (nach Nutzerfreigabe).
- Alle Design- und Funktionsentscheidungen liegen beim Nutzer, nicht beim Code-Agenten.

## 14. Stand und Herkunft technischer Grundannahmen

- Minecraft Java 26.3 Changelog (Mojang, 15.09.2026): https://feedback.minecraft.net/hc/en-us/articles/48913133328013-Minecraft-Java-Edition-26-3
- Fabric 26.3 Blog, 15.09.2026: https://fabricmc.net/2026/09/15/263.html
- Fabric Rendering: https://docs.fabricmc.net/develop/rendering/basic-concepts
- Claude Code Kontextdatei: https://support.claude.com/en/articles/14553240-give-claude-context-claude-md-and-better-prompts

Die Links belegen nur Grundlagen; Feature- und Zeitpläne sind unsere eigenen Projektentwürfe.
