# Beauty Salon Application
Aplicația este realizată atât pentru clienți, cât și pentru angajații unui salon de înfrumusețare. Aceasta permite o vizualizare mai rapidă
și ușoară a departamentelor și facilitățiilor pe care salonul le pune la dispoziția cliențiilor. Pe lângă vizualizarea ofertelor, aceștia 
își pot face o rezervare la ora și data dorită la serviciul de care au nevoie. În caz că au dubii despre rezervare, clienții
pot vizualiza un istoric al acestora. Angajații pot edita prețurile anumitor facilități, pot șterge rezervări și pot vizualiza apelurile pe care
le au de îndeplinit.

## Tehnologii utilizate 
* Java 18
* GUI: JavaFX
* Buildtool: Meaven
* Database: Nitrite Java

## Precondiții 
Pentru a putea rula aplicația cu succes, aveți nevoie de Java 18 și restul tehnologiilor enumerate în paragraful de mai sus.
Pentru a verifica versiune de Java, introduceți "java --version" în Command Window, iar pentru a verifica dacă Meaven-ul a fost instalat
cu succes, introduceți "mvn --version" în Command Window.

## Clonare
Pentru a clona repository-ul, puteți folosi comanda:
* "git clone https://github.com/andreeascortan28/BeautySalonApplication.git"

## Rulare
Pentru a rula aplicația, puteți folosi comanda "mvn javafx:run".
Pentru a rula aplicatia sub forma de jar, trebuie sa mutati fisierul "run.bat" in "target", sa va plasati in acest folder, folosind comanda "cd target", iar apoi sa rulati jar-ul folosind comanda "./run.bat", scrisa in terminal.
In "run.bat" trebuie schimbat path-ul de la "javafx-sdk" (unde este instalat pe propriul computer).


