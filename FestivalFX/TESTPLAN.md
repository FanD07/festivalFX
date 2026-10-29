# FestivalFX — Testplan und Qualitätsnachweise

> **Stand:** 29.10.2026. Noch keine Tests durchgeführt. **Testausführung, die Dateien verändert/Programme startet, ist freigabepflichtig.** Dieser Plan dient als Vorschlag für die Abnahmekriterien jeder späteren Arbeitseinheit.

## 1. Testphilosophie

- Eine sichtbar hübsche Demo reicht nicht als `DONE`.
- Jedes Ticket braucht reproduzierbare positive, negative und Edge-Case-Prüfungen.
- Automatisierte Tests, reale Minecraft-Tests, Rendering-Bildvergleiche und Multiplayer-Szenarien ergänzen einander.
- Kein Testresultat erfinden. Jede Prüfung enthält Datum, Ticket-ID, Build/Commit, Hardware/Version, Ergebnis und ggf. Screenshot/Log.
- Kleine vertikale Features mit vollständigem Testpfad bevorzugen.

## 2. Teststufen

| Stufe | Zweck | Beispiel |
|---|---|---|
| Unit | Pure Java-Logik, ohne Minecraft/GPU | Pan/Tilt-Limits, Keyframe-Interpolation, Fixture-ID, Show-Clock |
| Component | Subsystem-Verhalten mit Stubs | Geräteprofil mit Inspector, Cue-Resolution, Version-Migration |
| Integration | Minecraft/Fabric, Client/Server | Block platzieren und laden, Packet-Sync, Mod-Menu-Settings |
| Manual Gameplay | Benutzererlebnis, Grafik, UI | Fixture mit Maus bewegen; Editor bedienen; Speichern und Welt neu betreten |
| Visual/Golden Scene | deterministische Grafikvergleiche | 1/20/100 Beams, Gobos mit Schatten, Haze-Dichte |
| Perf/Stress | Leistungsbedarf und Skalierung | Fixtures-Count, CPU/GPU-Zeit, VRAM, Servertraffic |
| Regression | bestehende Funktionen nach Änderungen | Save/Load + Synchronität + Hotkeys + Fallback |

### Verbindlicher Minecraft-Ingame-Abnahmetest

**Gate:** Jedes direkt spielbare Feature muss im **real gestarteten Minecraft-Client** durch tatsächliche Bedienung überprüft werden, bevor Claude es als fertig zur finalen Funktionsabnahme vorlegt. Dazu gehören (sofern jeweils anwendbar): Gerät platzieren, alle neuen Controls auslösen, sichtbare Effekte/Animationen beobachten, Grenzwerte prüfen, Welt speichern und erneut laden, Verhalten nach Chunk-Reload überprüfen und dokumentierte Regressionen erneut ausführen. Für sichtbare Features sind Screenshots oder ein Video zusammen mit dem Testprotokoll vorzulegen. Dieses Gate ist getrennt von Unit- und Build-Tests.

**Freigabegate:** Minecraft starten, Welten anlegen, Daten verändern, Screenshots speichern und Testskripte ausführen jeweils nur innerhalb der **vorher einzeln genehmigten** Testaktionen. Fehlerbehebungen brauchen eine konkrete weitere Genehmigung und anschließende Wiederholung der relevanten Tests.

**Wenn GUI-Automation nicht verfügbar ist:** Nicht so tun, als sei Minecraft getestet worden. Status `NOT TESTED` oder `BLOCKED` vergeben, fehlende Voraussetzungen benennen und einen Schritt-für-Schritt-Test für einen Menschen bzw. eine geeignete GUI-Testumgebung bereitstellen. Für ein spielrelevantes Feature kein vollständiges Ingame-PASS aus rein automatisierten Tests ableiten.

**Pflichtnachweise:** Testdatum, Minecraft-/Fabric-/Mod-Version, Hardware/Grafikmodus, genehmigter Testumfang, tatsächliche Interaktionen, erwartetes/erhaltenes Ergebnis, Screenshots/Videos bzw. Logs, bekannte Fehler, Regressionen und Wiederholung nach Fix.

## 3. Allgemeine Testmatrix (pro betroffenem Feature)

- Plattform: Minecraft Java 26.3 (oder bestätigte Zielversion), Fabric Loader/API-Version dokumentieren.
- Java/JDK, Gradle/Loom Buildtool-Stand festhalten.
- Singleplayer mit integrierter Serverinstanz; Dedicated Server ohne Client-Only-Klassen.
- Multiplayer mit mindestens zwei Clients: einer ändert Fixture/Show, beide sehen erwartete Werte.
- Welt neu laden; Chunk entladen/neu laden; Dimension wechseln, falls relevant.
- Editor auf 1080p/WQHD/4K; UI-Scale; deutsch/englisch soweit vorhanden.
- Grafik: Native/Fallback auf zugelassener Hardware; eigener RenderCore erst nach Verfügbarkeit; Qualitätsoptionen `Low`/`Max Quality` erst nach Implementierung.
- Rendering: OpenGL/Vulkan nach tatsächlich unterstützter Konfiguration und bewusst erlaubten Shaderpacks. Nicht unterstützte Matrixkombinationen klar dokumentieren.
- Speicherformat: korruptes/fehlendes Profil, unbekannte Version, ungültige Parameter, Duplikat-ID.
- Tastatur/Maus: Input-Fokus, Escape, Scroll, Drag, Undo/Redo, App-Wechsel.

## 4. Erste messbare Akzeptanztests

### AT-001 Basic Moving Head
**GIVEN** eine neue Testwelt mit installiertem FestivalFX-Prototyp, **WHEN** ein Moving Head platziert und rotiert wird, **THEN** aktualisieren sich Modell und Lichtquelle korrekt, ohne Renderfehler.
- Pan/Tilt-Min/Max und Richtungsumkehr überprüfen; keine mathematischen Sprünge beim Wrap-around.
- Dimmer 0 vollständig aus, 1 (oder definierter Maxwert) plausibel; RGB ändert Strahlfarbe.
- Werte nach Welt-/Chunk-Reload erhalten.
- Ein zweiter Client sieht denselben Parameterstand; Backend der beiden Clients darf verschieden sein.
- Große/ungültige Werte robust behandeln.
- Benutzer bestätigt visuelles Fixture-Design, Steuerung und Basis-Rendering.

### AT-002 Renderer Spike / Beam
- Beam folgt Pan/Tilt und Camera Movement; wirkt nicht wie statische Bildschirmtextur.
- Sichtbarkeit bei unterschiedlichen Weltpositionen/Kameraentfernungen und bei Nebel an/aus testen.
- Kein unbeabsichtigtes Leuchten durch Wände (oder klar dokumentierter POC-Limitationsstatus).
- Vergleich Low/High, sobald beide existieren; Render-Capabilities sauber erkannt.
- Render-State nach Weltwechsel/GUI/Fehler korrekt wiederhergestellt.

### AT-003 UI Spike
- UI öffnet/schließt ohne Minecraft-Inventar-Look; Bedienelemente reagieren auf Maus und Tastatur.
- Verschieben, Docking oder beweisbarer funktionaler Ersatz; Game-Input und UI-Input korrekt getrennt.
- Drei Konzeptprofile in freigegebenen Layouts visualisierbar, später ohne Verlust des Projektzustands umschaltbar.
- Kein Render-/Input-Crash bei Auflösungswechsel, UI-Scale, Escape und Alt-Tab.
- Entwürfe vor finaler Textur-/Theme-Implementierung vom Nutzer freigegeben.

### AT-004 Show Engine
- Cue-Playback reproduziert identische Parameter zu identischer Showzeit.
- Pause/Resume/Seek/Loop ohne Zeit-Drift und ohne akkumulierte Bewegungsfehler.
- Keyframe-Interpolation unabhängig von FPS; deterministische Random-Effekte bei gleichem Seed.
- Wiederaufnahme eines Zuschauers nach Disconnect: plausibler Zustand innerhalb definierter Toleranz (zu spezifizieren).

### AT-005 Grafische High-End-Ziele
- Golden Scene mit definierter Kamera, Uhrzeit, Haze, Fixturezahl, Shader/Backend.
- Durchsicht von Volumetrik, Occlusion, Gobo/Shadow, HDR/Bloom, Reflexionen nur falls implementiert.
- FPS/GPU-Zeit bei 1/20/100/300 Geräten **messen** und als Hardware-abhängige Ergebnisse dokumentieren; keine pauschalen Mindest-FPS vor Messung.
- Absturz-/VRAM-Verhalten sowie Deaktivierung des High-End-Modus prüfen.

## 5. Testbericht-Vorlage

```md
## Testreport FFX-XXX / YYYY-MM-DD
- Autor / Ticket: ...
- Freigabe zur Testausführung: [Bestätigung / ID]
- Build/Commit/Branch: ...
- Minecraft/Loader/Fabric API/JDK/Backend: ...
- Hardware/Monitorauflösung/Shaderzustand: ...
- Szenario/Testdaten: ...
- Testfälle: [ID, Schritte, erwartet, beobachtet]
- Ergebnis: PASS / FAIL / BLOCKED / NOT TESTED
- Bugs: ...
- Belege: [Log/Screenshot/Video/Dateipfad]
- Betroffene Regressionen: ...
- Freigabe zur Abnahme: OFFEN / ERTEILT / ABGELEHNT
```

## 6. Release-Gates

1. Bestätigter Scope und abgenommene sichtbare Funktion/Design.
2. Build validiert, wichtige Tests dokumentiert; Abweichungen offengelegt.
3. `Wiki.md`-Eintrag + `Neueste Änderungen` aktuell; `CHANGELOG.md`, `STATUS.md` angepasst, jeweils separat zur Freigabe.
4. Änderungen als Diff gezeigt, Commit/Push/Release explizit freigegeben.
5. Fehler-/Wiederherstellungsmaßnahmen bei Migrationen und Settings bekannt.

## 7. Dokumentation unerledigter Tests

- `NOT TESTED`: Umgebung/Hardware/Backend fehlt, nicht als PASS zählen.
- `BLOCKED`: reproduzierbares Hindernis oder fehlende Freigabe; Ursache dokumentieren.
- `FAILED`: beobachteter Defekt; Fix darf nur im jeweils genehmigten Scope umgesetzt werden.
