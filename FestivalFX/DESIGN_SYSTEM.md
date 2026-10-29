# FestivalFX — Design System & UI-Richtlinien (Konzept)

> **Stand:** 29.10.2026 · **Status:** reiner UI-/UX-Vorschlag. Der Nutzer entscheidet über finale Designs. Diese Datei ist **keine** Freigabe, Oberflächen umzusetzen.

## 1. Produktcharakter

FestivalFX Studio wirkt wie eine professionelle, hochwertige Desktop-Produktionssoftware **in Minecraft**. Nicht wie eine Truhen-GUI. Es ist präzise, produktiv, modular, gut lesbar und auf lange Sitzungen ausgelegt. Inspirationen sind Arbeitsweisen aus FL Studio, Blender und grandMA3, keine Pixel-für-Pixel-Kopien.

## 2. Gemeinsame UI-Bausteine

- **Application Shell:** Titelleiste, Menüleiste, Projektstatus, Playback-/Renderstatus, Docking-Bereich, Benachrichtigungen.
- **Panels:** Stage Viewport, Fixture Outliner, Device/Parameter Inspector, Timeline, Automation Curve Editor, Cue Stack, Patch Grid, Asset Browser, Fixture Group Grid, Master / Faders, Preferences.
- **Controls:** Sliders mit Zahleneingabe (z. B. Winkel), Color Picker, Jog Wheels, Drag Handles, Fader, Knobs, Tabellen, Kontextmenüs, Popup-Dialoge, Resizing Handles, Suchfelder.
- **Key UX:** mehrstufiges Undo/Redo, Multi-Selection, Kontextabhängigkeit, Fokuszustand, Tastatur-Shortcuts, Eingabemodifikatoren, Autosave-Anzeige, Erklär-Tooltips.
- **Berechtigungen:** nur bearbeitbare Controls anklickbar; read-only Zuschauerstatus klar sichtbar.

## 3. Drei Stile, eine Programmlogik

### Stil FL Studio Inspired
- Priorität: Timeline, Automation und musikalisches Programmieren.
- Visuelle Sprache: kontrastreiche Clips, farbige Track-Tags, flexible Panels, kompakte Transportleiste.
- Timeline prominent, Stage Preview als zuschaltbares Dock; Clips und Parameterautomationen per Maus ziehen.
- Typische Funktion: Effektclip erzeugen, BPM setzen, Keyframes malen, Drop prüfen.
- **Keine** Übernahme echter FL-Logos oder Assetdateien.

### Stil Blender Inspired
- Priorität: 3D-Stage-Bau, Fixture-Placement, räumliche Auswahl und Properties.
- Visuelle Sprache: nüchtern, flache Panels, klarer Outliner, sorgfältige Icons, größtmöglicher 3D-Viewport.
- Stage Viewport mit Transform-Gizmos, Frustum-/Beam-Preview, Gizmo-Pivot und optionalem Grid.
- Typische Funktion: Traversenreihe markieren, Fixture-Gruppe duplizieren, 3D-Ausrichtung setzen.
- Shortcuts dürfen von Blender inspiriert sein, müssen aber mit Minecraft-Steuerung konfliktfrei sein.

### Stil grandMA3 Inspired
- Priorität: Live-Lichttechnik, Präzision, Fixtures, Gruppen, Presets und Cues.
- Visuelle Sprache: konsolenartig, parametriert, große anklickbare Executor-Flächen, Fader, Statusfarben.
- Typische Funktion: Fixture-Gruppe wählen, Farbpreset aufrufen, Executor starten, Blackout setzen.
- Begriffe/UX an realer Lichttechnik orientieren; keine proprietären grandMA3-Assets/Lizenzen voraussetzen.

### Gemeinsame Zusicherung
- Derselbe vollständige Funktionsbestand bleibt über wechselbare Panels erreichbar. Nur Default-Layout, Theme, Shortcuts, Control-Darstellung und Schnellaktionen ändern sich.
- Ein Stilwechsel verändert keine Showwerte, Fixture-IDs oder Cue-/Timeline-Daten.
- Einstellungen über `Minecraft Mod Menu → FestivalFX → Settings → Editor → Interface Style` (Pfad als Zielkonzept).
- User-Einstellungen versioniert speichern; Custom Layout optional, wenn umgesetzt.

## 4. Geplanter Einstellungsdialog

```text
FestivalFX Settings
├── General
│   ├── Language
│   ├── Project Autosave
│   └── Notifications
├── Editor
│   ├── Interface Style [FL / Blender / grandMA3]
│   ├── Layout [Style Default / Custom]
│   ├── Compact Density / Smooth Animations
│   ├── Shortcut Profile
│   └── UI Scale
├── Appearance
│   ├── Theme / Accent Color / Contrast
│   └── Panel Background / Widget Density
├── Graphics
│   ├── Renderer [Native / RenderCore / Experimental*]
│   ├── Quality [Low / Medium / High / Ultra / Max Quality]
│   ├── Beam / Haze / Shadow / Bloom Options*
│   └── Compatibility Information
├── Live
│   ├── Overlay Mode / Hotkeys*
│   └── Strobe Accessibility*
└── Advanced
    └── Diagnostics / Benchmark / Recover Settings*

* nur zeigen, wenn tatsächlich implementiert
```

**Exakte Namen/Hotkeys/Anordnung bedürfen Designfreigabe.** Die Vorschau enthält bewusst Zukunftsoptionen und darf nicht als existierende UI in der Wiki dokumentiert werden.

## 5. Standards für Interaktion

- Präzise Werte können per Tastatur eingegeben, nicht nur per Slider verändert werden.
- Bei `Pan`, `Tilt`, `Zoom`, `Focus`, `Dimmer` und Farbe Einheiten/zulässige Werte anzeigen.
- Keyboard-Fokus klar erkennbar; Escape verlässt Popup/Operation zuverlässig.
- Shortcuts je Stil frei konfigurierbar; Fokus entscheidet, ob Minecraft-Aktionen oder Editorbefehle ankommen.
- Kontextmenüs verhindern versehentliche destructive Actions (Delete, Reset); Undo/Redo für reversible Schritte.
- Lange Operationen zeigen Fortschritt, Fehler und Abbruchmöglichkeiten.
- Warnungen vor GPU-intensiven Presets und nicht kompatiblen Shader-Backends.
- Werkzeuge für große Setups: Batch-Edit, Grouping, Suche, Filter, Multi-Select, Copy/Paste.
- Kleine Displays müssen nutzbar bleiben, WQHD/4K sollen besonders gut aussehen.

## 6. Design-Freigabe-Checkliste (vor **jeder** neuen UI-Implementierung)

1. **Wer nutzt es, zu welchem Ziel?**
2. **Storyboard/Screens:** Hauptansicht, ausgewähltes Objekt, leere Ansicht, Laden, Fehler, Hover, Dialoge.
3. **Layout/Wireframe:** Größe, Docking, Scroll, Hierarchie.
4. **Controls:** klare Interaktionen, Wertebereiche, Tastatur/Maus.
5. **Look:** Farben, Typografie, Icons, Abstände, Kontrast, Animationen.
6. **Varianten:** FL/Blender/grandMA3, mindestens die betroffene Darstellung.
7. **Integration:** Input-Fokus, Game-Viewport, Client-Renderer, Performance.
8. **Akzeptanz:** explizite Entscheidung des Nutzers für konkret gezeigte Variante.

Eine technische Empfehlung von Claude ist ausdrücklich erwünscht. Eine Designfreigabe darf daraus **nicht abgeleitet** werden.
