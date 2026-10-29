# FestivalFX — Rules & Engineering Policy

> **Normativ:** Bei Konflikten zwischen diesem Dokument und unverbindlichen Plänen gilt die strengere Freigabeanforderung. Der Nutzer entscheidet über Design/Funktion. Diese Datei beschreibt überprüfbare Standards, nicht neue Produktzusagen.

## R-001 – Genehmigung vor jeder Änderung
- Reines Lesen/Analysieren ist gestattet. Jede neue Schreibaktion, jede neue Funktion, Designänderung, Asset-Erstellung, Testausführung, Buildausführung, Dependency-/Konfigurationsänderung, Dateioperation, Commit, Push, PR und Merge muss vorab inhaltlich und dem Scope nach genehmigt sein.
- Ein genehmigter Plan berechtigt **nicht** zu ungefragten Erweiterungen, Refactorings, Git-Aktionen oder weiteren Arbeitspaketen.
- Bei Unklarheit oder Fehler: nicht selbstständig „reparieren“; erst Ursache und konkrete Änderung zur Freigabe vorlegen. Ausgenommen ist rein lesende Diagnose.
- Jeder Freigabeantrag hat eine ID (z. B. FFX-011), Ziel, Datei-/Subsystemgrenzen, UI-Vorschau, testbare Kriterien und Risikoangabe.
- Claude soll Optionen kritisch bewerten und konkrete Empfehlungen aussprechen; die finale Entscheidung trifft ausschließlich der Nutzer.

## R-002 – Git und Datenintegrität
- Niemals ohne explizite Einzelgenehmigung: `git add`, `commit`, `push`, `merge`, `rebase`, `reset`, `clean`, Tag/Release, Branch-Löschung, Änderungen an GitHub Issues/PRs. Insbesondere `git push --force` nicht vorschlagen, außer eine vom Nutzer begründete Sonderentscheidung erfordert das.
- Änderungen bevorzugt in Feature-Branches `feature/ffx-###-kurzname`; Branch-Anlage ebenfalls genehmigungspflichtig. Kein direktes Schreiben auf `main` ohne Einzelfreigabe.
- Vor jedem Commit: `git diff --stat`, lesbarer Diff-Überblick, Dateiliste, Testbericht und Dokumentationsänderungen präsentieren. Vor Push: Remote, Zielbranch und Commit-Hash nennen.
- Nutzerdateien, Saves, Show-Projekte und Git-Historie nie überschreiben/löschen ohne vorherige Zustimmung + Backup-/Rollback-Plan.
- Keine Secrets, Tokens, Account-Daten oder absolute private Pfade in Repository-Dateien protokollieren.

## R-003 – Architekturgrenzen
- **Core:** deterministische, nach Möglichkeit vom Spielclient unabhängig testbare Showlogik.
- **Fixture Model:** stabile ID, Typ, Transform, Kanal-/Fähigkeitsprofil; Datenmodell unabhängig von UI und konkretem Renderer.
- **RenderCore:** rein clientseitig, modulare Backends / Passes / Quality; keine Simulation dauerhaft an FPS koppeln.
- **Editor:** gemeinsamer Zustand/Commands/Undo-Stack für alle UI-Stilprofile; kein duplizierter Feature-Code pro Stil.
- **Networking:** Server als Autorität für Zeit/Projektzustände/Berechtigungen, Clients interpolieren und rendern. Die Wahl der Qualität ist lokal und verändert nicht die Show.
- **Persistence:** versionierte Formate mit Validierung/Migration/Backups; keine unkontrollierte Java-Objektserialisierung.
- **Integrationen:** Audio, MIDI, OSC, Art-Net, Browser-Controller hinter optionalen Schnittstellen und standardmäßig deaktiviert.
- **APIs:** Minecraft-Rendering-Backend nicht voraussetzen; OpenGL-/Vulkan-Unterstützung prüfen, Blaze3D statt ungebundener GL-Direktaufrufe bevorzugen.

## R-004 – Codequalität
- Java-Codestyle einheitlich; sprechende Namen; keine extremen God-Classes. Bevorzugt kleine, testbare Komponenten und nachvollziehbare Pakete.
- Keine unbestätigten Drittanbieter-Abhängigkeiten; Lizenz, Wartbarkeit, Versionssupport und Sicherheitsrisiken vorab prüfen.
- Keine harten Zahlen für Pan/Tilt, Lichtstärke oder DMX ohne Gerätedaten/Profilmodell; Einheiten explizit (`degrees`, `seconds`, `milliseconds`, `channels`, `kelvin` falls sinnvoll).
- Render- und Netzwerkzustände zwischen Threads nicht ungeschützt teilen; Snapshot-/Command-/Message-Ansatz.
- Bei jedem Feature: Positive/negative Fälle, Randfälle, Save/Load, Multiplayer-Auswirkungen und Grafikpreset-Fallback prüfen, sofern betroffen.
- Keine pauschalen Behauptungen über physikalische Korrektheit; vereinfachte Näherung benennen.

## R-005 – UX und Design
- Ingame-Editor in der Anmutung einer professionellen Desktop-Anwendung. Keine primäre Bedienung über normale Truhen-/Inventar-GUIs.
- Drei **wählbare UI-Profile** in den Mod-Einstellungen: `FL Studio Inspired`, `Blender Inspired`, `grandMA3 Inspired`; alle nutzen dieselbe Funktionsbasis und haben konsistente Daten.
- Vor jedem visuellen Feature Entwurf mit Layout, Controls, Farben, Zuständen, Tastenkürzeln, Fokus-/Hover-Verhalten und Mindestauflösung zeigen; ohne Nutzerfreigabe keine finalen UI-Entscheidungen.
- UI-Skalierung, Tastatur-/Maus-Fokus, barrierearme Kontraste, Tooltips, Eingabevalidierung, Undo/Redo, robuste Fehleranzeigen beachten.
- Keine UI-Elemente, die eine Funktion suggerieren, die nicht existiert. Nicht implementierte Optionen als `Experimental`/`Unavailable` markieren oder weglassen.
- Design nur von Drittsoftware **inspirieren** lassen, keine 1:1 Kopien geschützter Logos, Texturen oder Grafiken.

## R-006 – Grafik/Leistung
- Grafik-**Preset** und **Renderer-Backend** getrennt modellieren. `Max Quality` darf sehr teuer sein, muss aber deaktivierbar/konfigurierbar sein.
- Zieloptik: gute Beams/Haze/Gobos/Reflexionen/Schatten; jedes davon ist ein separat zu beweisendes Feature, keine automatische Eigenschaft des Begriffs „eigene Pipeline“.
- Rendering gegen FPS-Einbrüche, VRAM-Probleme, Transparenz-/Shader-Konflikte und Weltwechsel testen. Für Grafikoptionen Fallbacks und klare Warnungen bereitstellen.
- Messungen mit dokumentierter Hardware, Auflösung, Sichtweite, Shaderzustand, Fixture-Anzahl und reproduzierbarer Szene. Keine erfundenen Benchmarks oder FPS-Versprechen.
- Max-Quality-Experiment darf nicht ungefragt zum Default werden.

## R-007 – Sicherheit, Multiplayer, Ressourcen
- Keine unbegrenzten Datenpakete/Fixture-Anzahlen aus untrusted Inputs; Bounds, IDs, Serverberechtigungen, Ratenbegrenzung, Eingabeprüfung.
- Externe Steuerschnittstellen nicht ungefragt öffnen, keine Remote-Befehle außerhalb der Mod ohne Genehmigung.
- Audio-/Videodateien nur mit expliziter Benutzeraktion einlesen und nach unterstütztem Format/Lizenz prüfen; keine automatischen Downloads.
- Helle Flash/Strobe-Effekte als Benutzeroption begrenz-/deaktivierbar machen (Photosensibilität); riskante reale Effekte bleiben reine Spielsimulation.
- Welt-/Show-Speicherstände müssen einen erkennbaren Migrations- und Recovery-Pfad haben.

## R-008 – Tests, Definition of Done
- Kein `DONE` bei reinem Kompiliererfolg. Erforderlich sind passende Unit-/Integration-/manuelle Spieltests und Freigabe der sichtbaren UX.
- Testmatrix wächst mit dem Feature: Client, dedizierter Server, Welt laden/speichern, Chunk-Reload, Multiplayer, Backend/Shaderpack, Quality-Einstellungen, ggf. Mod-Menu.
- Wenn Testumgebung fehlt: `BLOCKED`/`NOT TESTED`; keine Erfindung von Prüfergebnissen.
- Nach einem Fehler und vor einem Fix: Bericht + neue Genehmigung, falls über explizit genehmigte Testkorrektur hinaus.

### R-008A – Echte Minecraft-Testpflicht
- Vor jeder finalen Abnahme eines spielrelevanten Features **nach gesonderter Zustimmung** den aktuellen genehmigten Build im echten Minecraft-Client starten und das Feature aktiv selbst bedienen, sofern GUI-Zugriff verfügbar ist.
- Neue Geräte in einer Testwelt platzieren; Steuerung, Lichtdarstellung, Animation, Bedienbarkeit und relevante Persistenz-/Reload-Szenarien prüfen. Screenshots/Videos, Logs und genaue Reproduktionsschritte dokumentieren.
- Compile-Erfolg, Unit-Tests, Mocks, generierte Bilder oder Behauptungen ersetzen keine tatsächlichen Ingame-Interaktionen.
- Bei nicht zugänglicher GUI `NOT TESTED` oder `BLOCKED` kennzeichnen; keine fiktiven Beweise erstellen und kein spielrelevantes Feature als vollständig getestet abschließen.
- Für jeden Fix erneut **konkrete Freigabe** einholen und die betroffenen Ingame-Tests nach erneuter Zustimmung wiederholen; separate Freigaben für finale Abnahme, Commit und Push bleiben Pflicht.

## R-009 – Dokumente konsistent halten
- `Vision.md`: mögliche/gewünschte Zukunft, keine Umsetzungsgarantie.
- `Timeline.md`: Abhängigkeiten und geplante Arbeit, mit Freigabe-Gates.
- `STATUS.md`: wahrer Stand je Ticket; die Basis für `Mach weiter`.
- `DECISIONS.md`: nur Entscheidungen mit ausdrücklicher Bestätigung.
- `ARCHITECTURE.md`: Ist-Architektur getrennt von Vorschlagsarchitektur.
- `TESTPLAN.md`: überprüfbare Prüfbedingungen.
- `Wiki.md`: **nur echte, abgenommene Nutzerfunktionen**. Erster Eintrag erst nach erfolgreichem erstem Feature.
- `CHANGELOG.md`: ausgelieferte/bestätigte Änderungen; keine Ideen als Release-News.
- Für jedes implementierte Feature Dokumentationsentwurf proaktiv erstellen, separat zur Freigabe vorlegen und erst im freigegebenen Release/Commit veröffentlichen.
- Kopfbereich `Neueste Änderungen` in `Wiki.md` nach jeder freigegebenen neuen Funktion aktualisieren; maximal 10 jüngste relevante Meldungen sichtbar, ältere im Archiv/Changelog.

## R-010 – Transparenz
- Fakten, Annahmen, offene Fragen und Forschungsziele klar kennzeichnen.
- Bei Problemen offen sagen, was funktioniert und was nicht; keine Scheinsicherheit oder Pseudotests.
- Die KI führt **nicht** automatisch den gesamten nächsten Meilenstein aus. Ein klares `STOPP – Freigabe benötigt` ist erwartetes korrektes Verhalten.
- Kein „Ich habe fertig“ ohne überprüfbaren Diff und Tests.
