# FestivalFX — tatsächlicher Projektstatus

> **Quelle der Wahrheit für „Mach weiter“.** Stand 29.10.2026. Es existiert aus diesem Dokumentenpaket heraus **noch kein Code und kein getesteter Mod-Build**.

## Aktueller Snapshot

| Feld | Wert |
|---|---|
| Projektphase | Vorprojekt / technische Planung |
| Letzter verifizierter Meilenstein | Keiner — nur Brainstorming und Dokumentation |
| Bereits implementierte Features | 0 (in diesem Projektpaket) |
| Letztes bestätigtes Feature | keines |
| Aktives Arbeitsticket | **FFX-003 — Mod-Minimalstart Client/Server** (`REVIEW`, Tests bestanden und abgenommen; Commit/Push ausstehend) |
| Nächstes geplantes Ticket | FFX-004 bis FFX-007 (Spikes), erst nach Abnahme FFX-003 einzeln vorschlagen |
| Nächste benötigte Aktion | Commit- und Push-Freigabe für FFX-003 (C-/D-FFX-003) |
| Freigaben für Code/Assets/Design/Build/Test/Git | A-FFX-003, Test FFX-003 und B-FFX-003 erteilt; keine Commit- oder Push-Freigabe |
| Branch / Repository | Branch `feature/ffx-003-minimal-start` (von `feature/ffx-002-repo-init` @ `f2f360f`); FFX-003-Dateien nicht committed, nicht gepusht. Auf `origin`: `feature/ffx-001-tech-matrix` @ `dda6fd8`, `feature/ffx-002-repo-init` @ `f2f360f`; `main` unverändert @ `5085d0a` |
| Zielversion | Minecraft Java 26.3 / nur Fabric / Java 25 / OpenGL zuerst — **bestätigt** (ADR-001); Paket, Lizenz, Loom 1.19.3 — **bestätigt** (ADR-002) |
| Letzte Aktualisierung | 2026-10-29 (FFX-003 Mod-Minimalstart) |

## Status-Legende

- `TODO`: nicht begonnen, keine Freigabe.
- `PROPOSED`: Vorschlag liegt vor; Nutzerantwort offen.
- `APPROVED`: konkrete Umsetzung genehmigt; Freigabe-ID und Grenzen bekannt.
- `IN_PROGRESS`: Arbeit an genehmigtem Scope.
- `REVIEW`: Implementierung und Nachweise zur Abnahme bereit; keine automatische Veröffentlichung.
- `BLOCKED`: Abhängigkeit/Entscheidung/Test fehlt.
- `DONE`: abgeschlossen, in Tests und vom Nutzer akzeptiert; Docs und ggf. Git-Vorgang separat freigegeben.

## Ticket-Board

| Ticket | Beschreibung | Status | Abhängigkeit | Letzte Freigabe | Beleg |
|---|---|---|---|---|---|
| FFX-001 | Versions-/Build-/UI-/Backend-Matrix | DONE | – | A-FFX-001-D (2026-10-29, Chat) | `docs/TECH_MATRIX.md`, ADR-001 |
| FFX-002 | Repository initialisieren/auditieren | DONE | 001 | D-FFX-002 (2026-10-29, Chat) | Commit `f2f360f` auf `origin/feature/ffx-002-repo-init`, ADR-002, Build-Test bestanden |
| FFX-003 | Mod-Minimalstart Client/Server | REVIEW | 002 | B-FFX-003 (2026-10-29, Chat) | Entrypoints `FestivalFX`/`FestivalFXClient`; T1–T3 bestanden inkl. Ingame-Test |
| FFX-004 | Mod-Settings Design | TODO | 003 | – | – |
| FFX-005 | Beam Rendering Spike | TODO | 003 | – | – |
| FFX-006 | Animierter Fixture Spike | TODO | 003 | – | – |
| FFX-007 | Vollbild-Editor UI Spike | TODO | 003 | – | – |
| FFX-008 | Architekturentscheidung | TODO | 004–007 | – | – |
| FFX-010–017 | Erster fertiger Moving-Head-Vertikalschnitt | TODO | 008 | – | – |
| FFX-020–027 | Gerätesystem und Stage-Build | TODO | P1 | – | – |
| FFX-030–038 | Show Engine / Cue / Timeline / Sync | TODO | P1–P2 | – | – |
| FFX-040–047 | Drei umschaltbare UI-Stilprofile | TODO | UI/Show-Basis | – | – |
| FFX-050–059 | RenderCore / Max Quality | TODO | Renderer-Spike | – | – |
| FFX-060–066 | Multimedia und erweiterte Effekte | TODO | Show/Render | – | – |
| FFX-X01–X07 | Forschung / Experimental Features | TODO | spätere Entscheidung | – | – |

Die zusammengefassten Bereiche sind **keine** pauschale Freigabe. Sobald ein Bereich ansteht, pro einzelner ID aus `Timeline.md` auftrennen.

## Offene Freigabeanträge

| Antrag | Datum | Scope | Status | Fundstelle |
|---|---|---|---|---|
| A-FFX-001 | 2026-10-29 | Branch `feature/ffx-001-tech-matrix`, neue `TECH_MATRIX.md`, TA-001/TA-002 in `DECISIONS.md`, `STATUS.md`; kein Commit | GENEHMIGT | Chat-Session 2026-10-29 |
| B-FFX-001 | 2026-10-29 | Abnahme des FFX-001-Diffs (nur Doku) | GENEHMIGT | Chat 2026-10-29 |
| C-FFX-001 | 2026-10-29 | Lokaler Commit der drei FFX-001-Dateien | GENEHMIGT, Commit `5bd0e11` | Chat 2026-10-29 |
| D-FFX-001 | 2026-10-29 | Push `5bd0e11` → `origin/feature/ffx-001-tech-matrix` | GENEHMIGT, ausgeführt | Chat 2026-10-29 |
| A-FFX-001-D | 2026-10-29 | ADR-001 in `DECISIONS.md`, Abschnitt 5 in `TECH_MATRIX.md`, `STATUS.md`; kein Commit | GENEHMIGT | Chat 2026-10-29 |
| C-FFX-001-D | 2026-10-29 | Lokaler Commit der ADR-001-Änderungen | GENEHMIGT, Commit `dda6fd8` | Chat 2026-10-29 |
| D-FFX-001-D | 2026-10-29 | Push `dda6fd8` → `origin/feature/ffx-001-tech-matrix` | GENEHMIGT, ausgeführt | Chat 2026-10-29 |
| A-FFX-002 | 2026-10-29 | Branch `feature/ffx-002-repo-init`, Gradle-/Wrapper-/Lizenz-/`fabric.mod.json`-Dateien, ADR-002, `STATUS.md`; kein Build, kein Commit | GENEHMIGT | Chat 2026-10-29 |
| Test FFX-002 | 2026-10-29 | `gradlew.bat build` mit JDK 25 | GENEHMIGT, **bestanden**: `BUILD SUCCESSFUL` (Windows 11, Temurin 25.0.5, Gradle 9.8.2, Loom 1.19.3); `festivalfx-0.0.1-dev.jar` mit `fabric.mod.json` (Version `0.0.1-dev`) und `LICENSE_festivalfx`; `git status` unverändert. Kein Java-Code kompiliert, Ingame-Test entfällt (FFX-003) | Chat 2026-10-29 |
| B-FFX-002 | 2026-10-29 | Abnahme FFX-002 (Projektdateien, ADR-002, Testergebnis) | GENEHMIGT | Chat 2026-10-29 |
| C-FFX-002 | 2026-10-29 | Lokaler Commit der 13 FFX-002-Dateien, `gradlew` ausführbar | GENEHMIGT, Commit `f2f360f` | Chat 2026-10-29 |
| D-FFX-002 | 2026-10-29 | Push `f2f360f` → `origin/feature/ffx-002-repo-init` | GENEHMIGT, ausgeführt | Chat 2026-10-29 |
| A-FFX-003 | 2026-10-29 | Branch `feature/ffx-003-minimal-start`, `FestivalFX.java`, `FestivalFXClient.java`, Entrypoints in `fabric.mod.json`, `STATUS.md`; keine Tests, kein Commit | GENEHMIGT | Chat 2026-10-29 |
| Test FFX-003 | 2026-10-29 | T1 `build`, T2 `runServer`, T3 `runClient` mit Ingame-Bedienung | GENEHMIGT, **bestanden** (Windows 11, RTX 4080 SUPER, Temurin 25.0.5, MC 26.3, Loader 0.20.1, Fabric API 0.165.0+26.3). T1: beide Klassen kompiliert. T2: Dedicated Server auf 127.0.0.1:25565, Common-Logzeile ja, Client-Logzeile nein, sauber per `stop` beendet. T3: Titelmenü „Modded“, Testwelt „FFX-003 Test“ (Kreativ, Superflach) angelegt, F3 zeigt OpenGL 3.3.0 NVIDIA 617.42, gespeichert, erneut betreten, beendet; beide Logzeilen, keine FestivalFX-Fehler. EULA auf ausdrückliche Nutzeranweisung gesetzt. Nicht getestet: Multiplayer, Vulkan | Chat 2026-10-29 |
| B-FFX-003 | 2026-10-29 | Abnahme FFX-003 (Entrypoints, Testergebnis) | GENEHMIGT | Chat 2026-10-29 |

Immer mit konkreter Antrag-ID, Zeitbezug, vorgesehenem Scope, Status (`OFFEN/GENEHMIGT/ABGELEHNT/ERSETZT`) und Fundstelle der Bestätigung protokollieren.

## Gefällte Produktentscheidungen

- **Bestätigt:** Hybrid aus Lichttechnik-Simulation und spektakulärer Festivalshow.
- **Bestätigt als Produktziel:** Skalierbare Grafik mit extrem hochwertigem `Max Quality`.
- **Bestätigt als Konzept:** Eigene Rendering-Pipeline als gesonderte Render-Option/Forschungsziel; technische Details offen.
- **Bestätigt:** Ein Ingame-Editor mit drei im Mod-Menü wählbaren Inspirationsstilen: FL/Blender/grandMA3.
- **Bestätigt:** Claude ist ausführender Entwickler nur nach separater Einzelfreigabe; Design/Funktion entscheidet der Nutzer.

## Änderungsprotokoll dieser Datei

| Datum | Änderung | Quelle |
|---|---|---|
| 2026-10-29 | Initialzustand, alle Tickets offen | Dokumentationsentwurf aus Brainstorming |
| 2026-10-29 | FFX-001 auf `REVIEW`, Zielmatrix angelegt, Branch-Stand eingetragen | A-FFX-001 |
| 2026-10-29 | Entscheidungen als ADR-001 bestätigt, FFX-001 `DONE`, FFX-002 als nächstes Ticket | A-FFX-001-D |
| 2026-10-29 | FFX-002 auf `REVIEW`, Projektdateien angelegt, ADR-002 eingetragen | A-FFX-002 |
| 2026-10-29 | Build-Test FFX-002 bestanden, Abnahme eingetragen | B-FFX-002 |
| 2026-10-29 | FFX-002 `DONE` nach Push; FFX-003 auf `REVIEW`, Entrypoints angelegt | A-FFX-003 |
| 2026-10-29 | Tests FFX-003 bestanden (inkl. Ingame), Abnahme eingetragen | B-FFX-003 |

## Checkliste nach jedem einzelnen Feature

1. Teststatus mit Beleg und Nutzerabnahme.
2. Aktualisierter Ticketstatus und offene Folge-IDs.
3. Übersicht der aktiven Git-Änderungen, bestätigter Commits/Pushes.
4. Eventuelle Entscheidungen nach `DECISIONS.md`.
5. `Wiki.md`- und `CHANGELOG.md`-Entwürfe als eigene Freigabepunkte; keine fiktiven Releases.
6. **Nächstes Ticket nur vorschlagen; nicht automatisch beginnen.**
