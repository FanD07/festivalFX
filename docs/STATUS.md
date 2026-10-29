# FestivalFX — tatsächlicher Projektstatus

> **Quelle der Wahrheit für „Mach weiter“.** Stand 29.10.2026. Es existiert aus diesem Dokumentenpaket heraus **noch kein Code und kein getesteter Mod-Build**.

## Aktueller Snapshot

| Feld | Wert |
|---|---|
| Projektphase | Vorprojekt / technische Planung |
| Letzter verifizierter Meilenstein | Keiner — nur Brainstorming und Dokumentation |
| Bereits implementierte Features | 0 (in diesem Projektpaket) |
| Letztes bestätigtes Feature | keines |
| Aktives Arbeitsticket | **FFX-001 — Technische Zielmatrix** (`REVIEW`) |
| Nächstes geplantes Ticket | FFX-002 — Repository initialisieren (erst nach Abnahme FFX-001) |
| Nächste benötigte Aktion | Nutzer prüft `TECH_MATRIX.md` und entscheidet die offenen Punkte; danach Abnahme B-FFX-001 |
| Freigaben für Code/Assets/Design/Build/Test/Git | Nur A-FFX-001 (Doku-Umsetzung FFX-001); keine Code-, Build-, Test- oder Git-Publishing-Freigabe |
| Branch / Repository | Lokaler Branch `feature/ffx-001-tech-matrix` (von `main` @ `5085d0a`); nur Dokumentation, kein Code; nicht committed, nicht gepusht |
| Zielversion als Planungsvorschlag | Minecraft Java 26.3 / Fabric / Java 25; verifiziert, Bestätigung durch Nutzer offen (siehe `TECH_MATRIX.md`) |
| Letzte Aktualisierung | 2026-10-29 (FFX-001 Zielmatrix) |

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
| FFX-001 | Versions-/Build-/UI-/Backend-Matrix | REVIEW | – | A-FFX-001 (2026-10-29, Chat) | `docs/TECH_MATRIX.md` |
| FFX-002 | Repository initialisieren/auditieren | TODO | 001 | – | – |
| FFX-003 | Mod-Minimalstart Client/Server | TODO | 002 | – | – |
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

## Checkliste nach jedem einzelnen Feature

1. Teststatus mit Beleg und Nutzerabnahme.
2. Aktualisierter Ticketstatus und offene Folge-IDs.
3. Übersicht der aktiven Git-Änderungen, bestätigter Commits/Pushes.
4. Eventuelle Entscheidungen nach `DECISIONS.md`.
5. `Wiki.md`- und `CHANGELOG.md`-Entwürfe als eigene Freigabepunkte; keine fiktiven Releases.
6. **Nächstes Ticket nur vorschlagen; nicht automatisch beginnen.**
