# FestivalFX — sicherer Start mit Claude Code

> Ziel: Claude soll eigenständig mitdenken, **aber nicht eigenmächtig ändern**.

## A. Projektordner einrichten

1. FestivalFX-Repository auswählen bzw. bei Bedarf nach separater Freigabe neu anlegen.
2. Diese Markdown-Dateien in den Repo-Root kopieren; vorhandene `README.md`, `CLAUDE.md` oder andere Dateien **nicht ungeprüft überschreiben**.
3. Prüfen, ob `CLAUDE.md` wirklich großgeschrieben im Root liegt. Offizielle Claude-Code-Konvention: https://support.claude.com/en/articles/14553240-give-claude-context-claude-md-and-better-prompts
4. Bei einem bestehenden Repository die Dokumente mit vorhandenen Branch-/Build-/Projektregeln abgleichen, bevor sie verbindlich übernommen werden.

## B. Plan-Modus als sichere Grundeinstellung

Im Paket ist optional `.claude/settings.json` enthalten:

```json
{
  "permissions": {
    "defaultMode": "plan"
  }
}
```

`plan` ist für die ersten Analysen sinnvoll: Claude liest und plant, statt Dateien zu bearbeiten. **Wichtig:** Verfügbare Permission-Modi und Prioritäten sind abhängig von Claude-Code-Version und Oberfläche; Desktop/VS-Code können eigene Startmodus-Regeln haben. Daher den **sichtbaren aktiven Modus prüfen**, nicht blind auf eine JSON-Datei verlassen.

Für CLI alternativ explizit starten:

```bash
claude --permission-mode plan
```

Vor einer genehmigten Umsetzung bei Bedarf bewusst in einen kontrollierten manuellen Modus wechseln. **Niemals `--dangerously-skip-permissions` / Bypass für diesen Workflow verwenden.** Auch `auto` oder Auto-Accept ist keine inhaltliche Nutzerfreigabe.

Weder `CLAUDE.md` noch die JSON-Einstellung bieten absolute technische Sperren gegen jedes mögliche Verhalten. Echte GitHub-Zugriffsrechte, geschützte Branches und menschliches Review sind zusätzliche sinnvolle Sicherheitsstufen.

## C. Erster Prompt an Claude

> Lies `CLAUDE.md`, `rules.md`, `STATUS.md`, `Timeline.md`, `Vision.md`, `ARCHITECTURE.md`, `DECISIONS.md`, `DESIGN_SYSTEM.md` und `TESTPLAN.md`. Prüfe den Zustand des Repositories **nur lesend**. Zeig mir den nächsten technischen Schritt als konkrete Feature-Vorlage mit Alternativen, Design- und Funktionsvorschau sowie Risiken. Ich genehmige jeden Implementierungsschritt, alle Designänderungen, Tests, Commits, Pushes und Dokumentenänderungen separat. Bis zu meiner ausdrücklichen Freigabe darfst du nichts ändern. Beginne mit dem Vorschlag `FFX-001`.

Später reicht gewöhnlich:

> **Mach weiter.**

Die Einordnung „Mach weiter = nur nächster Vorschlag“ steht verbindlich in `CLAUDE.md`.

## D. Freigaben im Alltag

- Beispiel: `Ich genehmige A-FFX-011 in genau der vorgelegten Fassung, ohne weitere Features.`
- Beispiel: `Die angegebenen Testbefehle für FFX-011 sind genehmigt.`
- Beispiel: `Die vorgelegten Wiki-/Changelog-Updates für FFX-011 genehmigt.`
- Beispiel: `Ich genehmige Commit C-FFX-011 mit den gezeigten Dateien und der angegebenen Message.`
- Beispiel: `Ich genehmige Push D-FFX-011 von Commit abc123 nach origin/feature/ffx-011-moving-head.`

Wenn Claude den Umfang erweitert oder Dateien ändern will, die nicht im bestätigten Scope stehen: neue Freigabe. **Keine impliziten Dauerfreigaben.**

## E. Zusätzliche GitHub-Empfehlungen

- `main` nach Möglichkeit als geschützten Branch konfigurieren (Review-/Status-Checks nach deinem Bedarf).
- Feature-Branches anstelle direkter Änderungen auf `main`.
- Repo-Zugang mit minimal erforderlichen Berechtigungen; keine Secret-Tokens im Chat/Repo.
- Optional Pull-Requests statt Direkt-Push auf `main`; Merges nach eigener Nutzerfreigabe.
- Lokale Arbeitskopien vor Migrationen/Dateiformatänderungen sichern.

## F. Quellen

- Claude Kontextdatei: https://support.claude.com/en/articles/14553240-give-claude-context-claude-md-and-better-prompts
- Claude Code FAQ / Permission Modes: https://support.claude.com/en/articles/14554922-claude-code-user-faq
- Fabric 26.3: https://fabricmc.net/2026/09/15/263.html
