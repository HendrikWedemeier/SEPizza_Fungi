# Beispielprojekt mit Java, SpringBoot und Gradle

Stefan Sarstedt, stefan.sarstedt(at)haw-hamburg.de

## A. Einrichtung des JDK und Ausführen der Tests

1. Installiere auf Deinem Rechner (Anleitung siehe Vorlesung):
    - Java JDK (**nicht das Java Runtime Environment (JRE)**! Mindestens das **JDK 21**.
    - JetBrains IntelliJ Ultimate (neueste Version)

2. Öffne ein Terminal-Fenster.
    - Unter Windows: bevorzugt die Powershell (ps), oder cmd (command).
    - Falls du noch nicht sicher im Umgang mit einem Terminal bist (Verzeichnisse wechseln, etc.), schaue dir ein
      Tutorial wie
      z.B. [dieses für Windows](https://www.makeuseof.com/tag/a-beginners-guide-to-the-windows-command-line/)
      oder [dieses für Linux](https://ubuntu.com/tutorials/command-line-for-beginners#1-overview)
      oder [diese für macOS](https://www.makeuseof.com/tag/mac-terminal-commands-cheat-sheet/) an.

3. Klone das Projekt:
    ```bash
    git clone <git-Link des Projekts>
    ```

4. Prüfe mittels `javac -version` (vergiss das "c" nicht!), ob Du das korrekte JDK verwendest! Falls nicht, achte auf
   die korrekte Einrichtung des JDK (Punkt 1).

5. Führe die Tests im Terminal aus mittels
     ```bash
     ./gradlew clean build (unter Linux/macOS und Windows Powershell (PS) - bei Bedarf dort zuvor "chmod +x ./gradlew" ausführen, um die Ausführungsberechtigung zu setzen)
     ```
   bzw.
     ```bash
     gradlew.bat clean build (unter Windows mit der cmd(command)-Shell)
     ```
   Gradlew/Gradle ist ein Tool zur Projektautomatisierung (ähnlich `maven`, `make`, `npm`, `msbuild`, ...) und übersetzt
   das Projekt, führt die Tests aus und erzeugt eine Jar-Datei aus den Quellen. Informationen zu gradle findest
   Du [hier](https://gradle.org). Wesentlich ist die Konfigurationsdatei `build.gradle`, in der die
   Projektabhängigkeiten zu externen Bibliotheken und Tasks definiert werden. Durch das Java-Plugin stehen Tasks zur
   Übersetzung, Starten der Applikation, etc. zur Verfügung. Du kannst alles verfügbaren Tasks mittels
   `./gradlew (gradlew.bat) tasks` auflisten.

   Es sollte `Build Successful` erscheinen (falls nein, prüfe noch einmal Punkt 4). Die erste Ausführung des
   Gradle-Wrappers `gradlew` dauert etwas länger, da zunächst die Gradle-Distribution und dann die abhängigen
   Java-Bibliotheken geladen werden (später kommen sie aus dem lokalen Cache).  
   <br />
   Falls ein Fehler wie `Execution failed for task ':bootJarMainClassName'... Could not resolve ...` auftritt, blockiert
   wahrscheinlich deine Firewall das Herunterladen der Depedencies. Problem und Lösung
   siehe [hier](https://stackoverflow.com/questions/25243342/gradle-build-is-failing-could-not-resolve-all-dependencies-for-configuration):

6. Führe das Projekt im Terminal aus mittels
    ```bash
    ./gradlew bootRun
    ```
   bzw.
    ```bash
    gradlew.bat bootRun
    ```
   Du kannst auch direkt die jar-Datei starten (es kommt auf dasselbe heraus):
     ```bash
    java -jar ./build/libs/<jar-Datei des Projekts> (bzw. Backslashes unter Windows nutzen)
    ```
   Bei der Ausführung wird noch nicht viel zu sehen sein (und der gradle-Task bleibt stehen - du kannst ihn mittels
   Ctrl-C abbrechen). Näheres zu der Funktionalität der Applikation gibt es in der Vorlesung.

## B. Einrichtung einer IDE

1. Installiere lokal auf Deinem Rechner (achte auf die aktuellen Versionen!):
    - (empfohlen) Jetbrains IntelliJ IDEA **Ultimate**(!), aktuelle(!) Version: https://www.jetbrains.com/idea/ (du
      kannst dies mit Deiner `haw-hamburg.de`-Adresse kostenlos nutzen)
        - Evtl. hattest du schon einmal ein JetBrains-Studierenden-Abo. Falls es abgelaufen ist, kannst du es in deinem
          Profil auf der JetBrains-Seite verlängern.
    - (alternativ) Eclipse mit Lombok
      Plugin: https://projectlombok.org ([Anleitung hier](https://projectlombok.org/setup/intellij)).

2. Starte IntelliJ, **aber öffne das Projekt noch nicht**!

3. Aktiviere in IntelliJ bei `IntelliJ IDEA->Settings...` unter `Editor->Code Style` auf dem Tab `Formatter` die Option
   `Turn formatter on/off with markers in code comments`. Falls diese Option nicht gesetzt ist, führt dies in den
   REST-assured-Testfällen zu unschönen Code-Reformatierungen, die das Lesen dieser Testfälle erschweren.

4. Öffne nun Dein geklontes Projekt in IntelliJ.
    - Öffne unbedingt den Projekt-Hauptordner, in dem die build.gradle, src-Ordner etc. liegen - nicht einen
      übergeordneten Ordner! Sonst erkennt IntelliJ das Projekt nicht.
    - Es dauert etwas beim ersten Laden.

5. Aktiviere bei `IntelliJ IDEA->Settings...` unter `Build,Execution,Deployment->Compiler->Annotation Processors` das
   Annotation Processing (`Enable annotation processing`). Hierdurch erkennt IntelliJ die erzeugten Lombok-Artefakte
   korrekt und erzeugt keine Warnungen/Fehler mehr im Editor aufgrund (fälschlich) "fehlender" Getter/Setter. Evtl. ist
   das in deiner IntelliJ Ultimate bereits voreingestellt.

6. Setze bei `IntelliJ IDEA->Settings...` unter `Build,Execution,Deployment->Build Tools->Gradle` die Optionen
   `Build an run using` und `Run tests using` auf `Gradle`. Setze die `Gradle JVM` auf `Project SDK`.

7. Setze unter `File->Project Structure...` das JDK (Option `Project Settings->Project`) auf deine JDK-Version (
   entsprechend dein heruntergeladenes JDK - evtl. musst du das JDK erst hinzufügen: Option `Platform Settings->SDKs`).

8. Öffne ein Terminal in IntelliJ (`View->Tool Windows->Terminal` im Menü). Führe die Tests wie unter A.6 beschrieben
   hier im Terminal aus.
    - Unter Windows: Schaue, welches Default-Terminal (cmd oder Powershell PS) IntelliJ hier benutzt. Du kannst es auch
      bei Bedarf unter
      `IntelliJ IDEA->Settings...->Tools->Terminal->Shell Path` ändern.

9. Du kannst auch die Tests durch die IDE ausführen lassen. Gehe dazu mit der rechten Maustaste auf Deine Tests in Deinem Projekt und
   wähle `Run Tests in <Projektname>`. Falls dies nicht funktioniert, ist wahrscheinlich das falsche SDK für Dein
   IntelliJ-Projekt eingestellt (siehe Punkt B.7).
