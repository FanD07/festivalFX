# FestivalFX — Entscheidungslog (ADR / Design Direction)

> Hier stehen nur **explizit bestätigte** Entscheidungen. Offene Hypothesen sind als `VORSCHLAG` markiert. Technische Architekturentscheidungen erhalten nach Freigabe eigene ADR-Nummern.

## Bestätigte Produktentscheidungen

| ID | Datum | Entscheidung | Status / Konsequenz |
|---|---|---|---|
| PD-001 | 2026-10-29 | FestivalFX kombiniert realistische Lichttechnik-Simulation und spektakuläre Festivalshows. | **BESTÄTIGT**; Featuredesign muss beide Zielgruppen berücksichtigen. |
| PD-002 | 2026-10-29 | Grafikqualität ist frei einstellbar; `Max Quality` soll optisch besonders anspruchsvoll sein, auch mit hoher GPU-Last. | **BESTÄTIGT**; Performance-Fallback weiterhin nötig. |
| PD-003 | 2026-10-29 | Eine **eigene Render-Pipeline** wird als getrennte Option erwogen; zusätzliche Full-Scene-Pipeline ist experimentelles Zukunftsziel. | **KONZEPT BESTÄTIGT**, genaue Technik **OFFEN**. |
| PD-004 | 2026-10-29 | Ingame-Editor wirkt wie eine professionelle Desktop-Software, nicht wie normales Minecraft-Inventar. | **BESTÄTIGT**; UI-Prototyp früh priorisieren. |
| PD-005 | 2026-10-29 | Im Mod-Menü wird der **Stil eines Editors** gewählt: `FL Studio Inspired`, `Blender Inspired` oder `grandMA3 Inspired`. Nicht drei separate Programme. | **BESTÄTIGT**; alle Stile nutzen denselben Core. |
| PD-006 | 2026-10-29 | Claude agiert als eigenständig denkender technischer Berater/Programmierer, führt aber **jede konkrete Änderung nur nach gesonderter Zustimmung** aus. | **BESTÄTIGT**; Plan-, Review-, Commit- und Push-Gates. |
| PD-007 | 2026-10-29 | `Wiki.md` wird für neue echte Features proaktiv fortgeführt, zuletzt hinzugefügte Funktionen oben hervorgehoben; tatsächliche Wiki-Änderungen müssen vorher abgenommen werden. | **BESTÄTIGT**; keine Fake-Wiki-Features. |

## Noch nicht bestätigte technische Vorschläge

| Vorschlag | Begründung | Entscheidung benötigt |
|---|---|---|
| TA-001: Start mit Minecraft Java 26.3, Fabric | 26.3 am 29.10.2026 verifiziert letzte stabile Version (26.4 nur Snapshots); Fabric Loader 0.20.1 und Fabric API 0.165.0+26.3 verfügbar; Belege in `TECH_MATRIX.md` | **Entschieden → ADR-001** (BESTÄTIGT, 2026-10-29) |
| TA-002: Java 25 als Baseline; Loom/Gradle nach offiziellem Fabric-Template für 26.3 pinnen | Java 25 laut Mojang-Versions-JSON verifiziert; Blog-Angabe Loom 1.17 / Gradle 9.6.0 inzwischen überholt (Loom 1.19.3, Gradle 9.8.2 aktuell); Belege in `TECH_MATRIX.md` | **Entschieden → ADR-001** (BESTÄTIGT, 2026-10-29); exaktes Pinning in FFX-002 |
| TA-003: Core/Render/UI/Network/Storage getrennte Module/Pakete | Testbarkeit und wechselbare Renderer/UI-Stile | FFX-008 |
| TA-004: Native Renderer + eigener RenderCore + Experimental Full Scene | Trennung von realistischer Hauptlösung und Forschung | FFX-008/050 |
| TA-005: Dear ImGui Docking als UI-Kandidat | Desktop-Editor-Muster, aber Backend-Integration riskant | FFX-007 |
| TA-006: Serverseitige Showzeit mit Client-Interpolation | Multiplayer-Synchronisierung unabhängig von FPS | FFX-008/037 |

## Bestätigte technische Entscheidungen (ADR)

### ADR-001 – Technische Zielplattform
- Datum: 2026-10-29
- Status: BESTÄTIGT
- Ticket und Freigabe: FFX-001 / A-FFX-001-D (Nutzerauswahl im Chat am 2026-10-29)
- Kontext: Vor der Repository-Initialisierung (FFX-002) müssen Spielversion, Loader, JDK, Build-Tooling und das erste Grafik-Backend feststehen. Versionsfakten und Quellen: `TECH_MATRIX.md`.
- Alternativen:
  - Zielversion: auf das 26.4-Release warten (keine stabile Basis, Termin unbekannt).
  - Loader: Multi-Loader (Fabric + NeoForge) von Beginn an (mehr Setup, doppelte Tests, jede Abhängigkeit für beide nötig).
  - Backend: OpenGL und Vulkan gleichzeitig (doppelter Testaufwand, Vulkan-Status in 26.3 unbestätigt).
  - Loom/Gradle: jetzt feste Versionen vorgeben (Risiko, dass sie nicht zum offiziellen Template passen).
- Entscheidung:
  1. Zielversion **Minecraft Java 26.3**, festgepinnt.
  2. **Vorerst nur Fabric**; der Core bleibt loader-unabhängig.
  3. **OpenGL** als erstes offiziell getestetes Backend; Vulkan erst nach Prüfung im Spiel (FFX-003/005) und eigener Entscheidung.
  4. **Java 25** als Baseline; der Nutzer installiert JDK 25 selbst, Claude prüft nur lesend.
  5. **Loom- und Gradle-Version** werden in FFX-002 aus dem offiziellen Fabric-Template für 26.3 übernommen und vor dem Pinnen zur Freigabe vorgelegt.
- Konsequenzen / Risiken: FFX-002 setzt ein installiertes JDK 25 voraus. Vulkan-Nutzer sind bis zur Folgeentscheidung nicht offiziell unterstützt. Ein Wechsel auf 26.4 erfordert einen Port.
- Rückweg / Migration: Port auf 26.4 bzw. Aufnahme von NeoForge oder Vulkan jeweils als eigenes Ticket mit eigener Freigabe.

### ADR-002 – Projektidentität und Build-Pinning
- Datum: 2026-10-29
- Status: BESTÄTIGT
- Ticket und Freigabe: FFX-002 / A-FFX-002 (Nutzerauswahl im Chat am 2026-10-29)
- Kontext: Für die Repository-Initialisierung müssen Mod-ID, Maven-Gruppe, Java-Paket, Lizenz, Loom-Version und CI festgelegt werden. Grundlage ist das offizielle Fabric-Template, Branch `26.3` von `FabricMC/fabric-example-mod` (CC0).
- Alternativen:
  - Paket: `dev.festivalfx` (nur sauber mit eigener Domain).
  - Lizenz: MIT oder LGPL-3.0.
  - Loom: `1.19-SNAPSHOT` wie im Template (nicht reproduzierbar).
  - CI: GitHub-Actions-Workflow aus dem Template sofort übernehmen.
- Entscheidung:
  1. Mod-ID `festivalfx`, Maven-Gruppe und Java-Basispaket **`io.github.fand07.festivalfx`**.
  2. Lizenz **All Rights Reserved**; späteres Lockern bleibt möglich.
  3. **Fabric Loom 1.19.3** (Release) statt `1.19-SNAPSHOT`; Gradle-Wrapper 9.8.2 mit geprüfter Distributions-Prüfsumme.
  4. **Kein CI** in FFX-002; GitHub Actions später als eigenes Ticket.
- Konsequenzen / Risiken: Falls Loom 1.19.3 mit 26.3 nicht baut, wird gestoppt und neu entschieden (Ausweichoption `1.19-SNAPSHOT`). Fremde dürfen den Code nicht weiterverwenden, solange die Lizenz nicht geändert wird.
- Rückweg / Migration: Lizenzwechsel per Austausch von `LICENSE` und `fabric.mod.json`; Paketumbenennung, solange noch kein veröffentlichter Build existiert, ohne Migrationsaufwand für Spieler.

## ADR-Schema für neue technische Entscheidungen

```md
### ADR-00X – [Titel]
- Datum: YYYY-MM-DD
- Status: VORSCHLAG / BESTÄTIGT / ERSETZT / ABGELEHNT
- Ticket und Freigabe: FFX-XXX / A-FFX-XXX (genaue Nutzerbestätigung)
- Kontext: [Problem, technische Voraussetzungen]
- Alternativen: [A, B, C inkl. Nachteile]
- Entscheidung: [nur nach ausdrücklicher Bestätigung ausfüllen]
- Konsequenzen / Risiken: [Auswirkungen auf Build, UI, Renderer, Formate]
- Rückweg / Migration: [Plan]
```

**Wichtig:** Niemals `VORSCHLAG` durch `BESTÄTIGT` ersetzen, nur weil Claude eine Variante technisch besser findet.
