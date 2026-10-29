# FestivalFX — Nutzer-Wiki

> **Mod-Status:** Noch keine freigegebenen, implementierten Nutzerfeatures dokumentiert.
> **Stand:** 29.10.2026 · **Wichtig:** Dieses Dokument beschreibt ausschließlich Funktionen, die tatsächlich implementiert, geprüft und freigegeben wurden. Die geplanten Ideen stehen in `Vision.md`.

## Neueste Änderungen

**Noch keine Features veröffentlicht.**

Sobald das erste Feature (voraussichtlich der Basic Moving Head) nach Umsetzung und Review angenommen ist, erstellt Claude hier **ohne gesonderten Erinnerungs-Prompt einen Entwurf**. Der Entwurf wird dem Nutzer vor der Änderung vorgelegt. Die neue Funktion erscheint dann prominent an dieser Stelle.

**Format für künftige Einträge (Muster, kein echter Release-Eintrag):**

<!--
- **NEU · YYYY-MM-DD · Alpha 0.1 · Commit abc123:** [Moving Head](#moving-head) – Platzierung, Ausrichtung, Bedienung.
- **GEÄNDERT · YYYY-MM-DD · Alpha 0.1.1:** [Bedienung](#bedienung) – neue Tastenbelegung.
- **BEHOBEN · YYYY-MM-DD · Alpha 0.1.2:** [Bekannte Probleme](#bekannte-probleme) – Fix für Speichern/Laden.
-->

**Regel:** Die 10 aktuellsten relevanten Änderungen oben, in absteigender zeitlicher Reihenfolge. Ältere Änderungen sind im `CHANGELOG.md` nachlesbar. Jeder Eintrag nennt Status (`NEU`, `GEÄNDERT`, `BEHOBEN`), Datum, genaue Version/Commit, kurze Wirkung und einen Abschnittslink. Wird ein Feature später umgebaut, alte Wiki-Anleitung *aktualisieren* statt falsche Anleitungen stehen lassen.

## Inhalt

Eine Inhaltsübersicht wird eingefügt, sobald das erste Feature freigegeben wurde. Bis dahin keine Schein-Anleitungen oder erfundenen Buttons.

## Installation

*Wird erst dokumentiert, sobald eine installierbare, getestete Mod-Version existiert.* Dann: unterstützte Minecraft-Version, Loader, JDK, Abhängigkeiten, Installationsschritte, Konfigurationsdateien und bekannte Kompatibilitätsprobleme nach Testbeleg ergänzen.

## Erste Schritte

*Wird automatisch als **Dokumentationsentwurf** angelegt, sobald das erste spielbare Feature akzeptiert wurde.*

## Geräte & Steuerung

*Noch keine abgenommenen Geräte.* Künftige Geräteseiten müssen enthalten: Gerätetyp/Version, erhalten/platzieren, montieren, Parameter, Grenzen, Steuerung, Speichern/Laden, Multiplayer-Auswirkungen, typische Fehler und Hinweise zu den verfügbaren Grafikoptionen.

## FestivalFX Studio

*Noch kein abgenommener Editor.* Später dokumentieren: Öffnen/Schließen, UI-Profil im Mod-Menü ändern, Timeline, Fixtures, Inspector, Patching, Cues, Live-Overlay, Shortcuts.

## Grafik & Render-Einstellungen

*Noch keine abgenommenen Render-Optionen.* Niemals `Max Quality`, `RenderCore`, `Own RenderPipeline`, Raytracing oder Cinematic als verfügbare Funktionen ausgeben, bevor sie tatsächlich implementiert und getestet sind.

## Multiplayer & Shows

*Noch keine abgenommenen Show- oder Netzwerkfunktionen.*

## Bekannte Probleme

*Kein getesteter Release vorhanden.* Später pro Eintrag: betroffene Version, Reproduktionsschritte, Workaround, Status und Link zu bestätigtem Fix.

## Wiki-Pflegevertrag für Claude

1. Nach jedem **freigegeben implementierten** Feature automatisch benötigte Wiki-Änderungen **als Entwurf** schreiben (inklusive neuer `Neueste Änderungen`-Zeile). Der Nutzer muss nicht extra an die Wiki denken.
2. Vor dem tatsächlichen Editieren und Committen den Entwurf zeigen und **gesonderte Freigabe** einholen. Keine eigenmächtigen Docs-Commits.
3. Nur Verhalten aus Code + verifizierten Tests dokumentieren. Offene Einschränkungen, erforderliche Mods und nicht getestete Backends ausdrücklich nennen.
4. Screenshots nur verwenden, wenn sie von der tatsächlichen Mod stammen oder ausdrücklich als Mockup gekennzeichnet wurden; keine fremden Bilder unautorisiert übernehmen.
5. Veraltete Anleitungen im selben Review ändern; `CHANGELOG.md` / `STATUS.md` synchron halten.
6. Nie eine nie existierende Version, ein nicht gemessenes FPS-Ziel oder eine nur geplante Steuerung als Tatsache darstellen.
7. Doku-Vorlage für ein späteres Feature:

<!--
### Feature: [Funktionsname]
- **Eingeführt:** [Version] / [Commit] / [Datum]
- **Status:** implementiert und getestet
- **Benötigt:** [Minecraft/Loader/sonstiges]
- **Was kann es?** [2–4 Sätze]
- **So verwendest du es:** [nachweislich richtige Schritte]
- **Einstellungen/Parameter:** [Tabelle mit Namen, Wertebereich, Standard]
- **Shortcuts:** [Tabelle nur existierender Hotkeys]
- **Multiplayer:** [ja/nein/eingeschränkt]
- **Grafik:** [Low/High/RenderCore-Support nach Tests]
- **Bekannte Einschränkungen:** [konkret]
- **Belege:** [Testfall-ID, Screenshot/GIF falls genehmigt]
-->
