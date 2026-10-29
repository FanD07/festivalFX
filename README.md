# FestivalFX – Projekt-Dokumentationspaket

**Planungsstand 29.10.2026 · Minecraft Java Edition · geplante Basis 26.3/Fabric.**

FestivalFX soll eine umfangreiche Minecraft-Mod für Festival- und Konzert-Lichtshows werden: Moving Heads, Laser, Bühne/Truss, LED-Technik, Cue-/Timeline-Programmierung, umfangreicher Ingame-Editor mit wählbaren Stilprofilen und später eine eigene High-End-Rendering-Pipeline.

> **Wichtiger Hinweis:** Dieses Paket enthält **Dokumentation und einen Arbeitsprozess, keinen Mod-Code**. Die Produktvision ist kein Implementierungsstatus. Es wurde weder GitHub bearbeitet noch ein Feature gebaut.

## Dokumenten-Navigation

### Die fünf gewünschten Kerndateien

| Datei | Aufgabe |
|---|---|
| **`Timeline.md`** | Technisch priorisierte Meilensteine/Abhängigkeiten vom Proof of Concept bis RenderCore/Cinematic. |
| **`CLAUDE.md`** | Automatisch gelesene Claude-Code-Anweisung mit „Mach weiter“ und zwingenden Freigabestufen. |
| **`Wiki.md`** | Ehrliche Nutzer-Dokumentation; erste Feature-Anleitung **erst nach Implementierung und Freigabe**; neueste Änderungen oben. |
| **`rules.md`** | Verbindliche Engineering-, Git-, UX-, Datenschutz-, Test- und Dokumentationsregeln. |
| **`Vision.md`** | Die gesamten Brainstorming-Ideen mit detailliertem Zielbild; keine Idee geht nur wegen fehlender Priorität verloren. |

**Warum `CLAUDE.md` statt `Claude.md`?** Claude Code sucht automatisch nach `CLAUDE.md` (genau diese Schreibweise). Auf Windows wären die zwei Schreibweisen in vielen Ordnern zudem derselbe Dateiname. Daher bewusst **nur die kanonische Datei `CLAUDE.md`**.

### Zusätzliche empfohlene Dateien

| Datei | Nutzen |
|---|---|
| **`STATUS.md`** | Tatsächlich erledigte Arbeiten, aktuelle Freigaben, nächste Ticket-ID: schützt den „Mach weiter“-Workflow vor Kontextverlust. |
| **`ARCHITECTURE.md`** | Saubere Grenzen zwischen Show Engine, UI, Rendering, Persistence und Multiplayer. |
| **`DECISIONS.md`** | Nachvollziehbare vom Nutzer bestätigte Design-/Technikentscheidungen; offene Vorschläge getrennt. |
| **`TESTPLAN.md`** | Konkrete Testfälle und Abnahmebelege; verhindert „funktioniert irgendwie“-Releases. |
| **`CHANGELOG.md`** | Versionen und echte ausgelieferte Änderungen; getrennt von der Wiki. |
| **`DESIGN_SYSTEM.md`** | Grundlagen für die drei UI-Stilprofile und visuelle Freigaben. |
| **`FEATURE_TEMPLATE.md`** | Ein standardisiertes Freigabeformular je Feature/Design/Commit. |
| **`SETUP.md`** | Einmalige Einrichtung und sicheres Vorgehen mit Claude Code. |
| **`.claude/settings.json`** | Optionaler, bewusst eingeschränkter Projektstart im Plan-Modus. |

## Empfohlene Projektstruktur

```text
festivalfx/                       # Repository-Root
├── README.md
├── CLAUDE.md                      # AUTOMATISCH durch Claude Code gelesen
├── rules.md
├── Vision.md
├── Timeline.md
├── Wiki.md
├── STATUS.md
├── ARCHITECTURE.md
├── DECISIONS.md
├── DESIGN_SYSTEM.md
├── TESTPLAN.md
├── CHANGELOG.md
├── FEATURE_TEMPLATE.md
├── SETUP.md
├── .claude/
│   └── settings.json             # plan als sicherer CLI-Startdefault (optional)
└── [Quellcode/Baukonfiguration erst nach Freigabe]
```

## Das gewünschte Nutzer-/Claude-Arbeitsmodell

1. Nutzer schreibt **„Mach weiter“**.
2. Claude liest `CLAUDE.md`, `STATUS.md`, `Timeline.md`, `Vision.md`, Entscheidungen und aktuellen Repo-Zustand.
3. Claude erklärt den **nächsten sinnvollen konkreten Einzelschritt**, denkt selbst über Architektur/UX/Alternativen nach und macht einen **Design-/Funktionsvorschlag**.
4. Nutzer **genehmigt genau diesen Schritt oder ändert ihn**.
5. Erst danach darf Claude den genehmigten Scope ändern. Bei unerwarteten Zusatzarbeiten erneut anhalten.
6. Tests nur nach ihrer eigenen Freigabe; dann Review und Diff zeigen. Wiki-/Changelog-/Status-Updates werden proaktiv **entworfen**, aber vor dem Schreiben ebenfalls freigegeben.
7. **Commit, Push, PR, Merge und Release werden jeweils ausdrücklich und getrennt freigegeben.**
8. Nächstes Feature nicht automatisch beginnen.

## Was bereits geklärt ist

- Produktziel: Hybrid Lichttechnik + Show-Spektakel.
- Grafik-Presets inklusive anspruchsvollem **Max Quality**.
- Ein getrennt auswählbarer eigener Renderer als Hauptidee; vollständige Welt-Render-Pipeline nur experimentelles Fernziel.
- Ein professioneller Ingame-Editor mit **Stilauswahl über Mod-Einstellungen**: FL Studio / Blender / grandMA3 inspiriert, aber ein gemeinsamer Feature-Core.
- Nutzer ist Design-/Funktionsdirektor; Claude ist beratender Coder und benötigt explizite Einzelgenehmigungen.

## Was noch offen ist

Technische Basis und APIs endgültig abnehmen, Mod-UI-Prototyp, Renderer-POC, exakte Gerätenamen/-modelle, Repository-Initialisierung, konkrete Build-/Testumgebung und sämtliche Release-Termine. Nichts davon stillschweigend als entschieden behandeln.

## Start

1. Alle Dokumente in das **Root** deines FestivalFX-Repos kopieren; vorhandene Dateien vorher vergleichen und nicht blind überschreiben.
2. `SETUP.md` lesen und Claude Code möglichst im Plan-Modus starten.
3. Claude im Projektordner öffnen.
4. Sagen: **„Mach weiter – Vorschläge und Freigabefrage zuerst, nichts schreiben.“**
5. Erwartete erste Antwort: Vorschlag `FFX-001` (Ziel-/Toolchain-Matrix) und Frage nach expliziter Freigabe.

**Quellen für Versionen/Tooling:** Minecraft https://feedback.minecraft.net/hc/en-us/articles/48913133328013-Minecraft-Java-Edition-26-3 · Fabric https://fabricmc.net/2026/09/15/263.html · Claude `CLAUDE.md` https://support.claude.com/en/articles/14553240-give-claude-context-claude-md-and-better-prompts
