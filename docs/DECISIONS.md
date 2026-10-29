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
| TA-001: Start mit Minecraft Java 26.3, Fabric | Zum Planungsdatum letzte stabile Version, Fabric vorhanden | Zielmatrix FFX-001 |
| TA-002: Java 25, Fabric Loom 1.17, Gradle 9.6 als Baseline | Plattform-/Blog-Indizien; Verifikation gegen reale aktuelle Toolchain | FFX-001 |
| TA-003: Core/Render/UI/Network/Storage getrennte Module/Pakete | Testbarkeit und wechselbare Renderer/UI-Stile | FFX-008 |
| TA-004: Native Renderer + eigener RenderCore + Experimental Full Scene | Trennung von realistischer Hauptlösung und Forschung | FFX-008/050 |
| TA-005: Dear ImGui Docking als UI-Kandidat | Desktop-Editor-Muster, aber Backend-Integration riskant | FFX-007 |
| TA-006: Serverseitige Showzeit mit Client-Interpolation | Multiplayer-Synchronisierung unabhängig von FPS | FFX-008/037 |

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
