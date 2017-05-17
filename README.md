1. Aplikacja została napisana używając środowiska Intellij IDEA 2017.1.1.
2. W celu ułatwienia sprawdzenia zadania został stworzony plik konfiguracyjny o nazwie application.properties, który znajduje się w folderze "main/resources". Można tam zmienić takie parametry jak:

* database.connection.url=jdbc:mysql://localhost:3306/hotel
* database.connection.driver=com.mysql.jdbc.Driver
* database.user.name=root
* database.user.password=
* hibernate.show_sql=true
* hibernate.hbm2ddl.auto=create

3. Tworząc aplikację korzystałem z XAMPP oraz MySql, więc aby dostosować się do ustawień domyślnych (patrz wyżej) należy utworzyć bazę danych o nazwie hotel.

* Jeżeli pojawi się problem z "dociąganiem" zależności prosze kliknąć prawym przyciskiem na projekt, wejść w zakładkę Maven oraz kliknąć “reimport”.

Aby przejść do panelu rezerwacji należy utworzyć nowe konto albo skorzystać z:
* email: rekrutacja@pgs-soft.com
* hasło: rekrutacja
