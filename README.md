jee-hazi-alap
=============

Ez a project a BME JavaEE tárgyának házifeladatához készült, hogy egy stabil alapot biztosítson.

Szükséges szoftverek
--------------------

- Java 7 (ellenőrzés: java -version)
- Maven 3, path-ba beállítva, hogy mvn paranccsal indítható legyen (ellenőrzés: mvn -version)
- Internetkapcsolat

Használat
---------

### Fordítás

A jee könyvtárból:

    mvn clean install

### Futtatás

A jee könyvtárból:

    mvn embedded-glassfish:run

Ezután ezen az URL-en érhető el:

    http://localhost:8080/jee-web

A kettő össze is vonható:

    mvn clean install && mvn embedded-glassfish:run

Felépítés
---------

A project 3 projectből, valamint egy összefogó modulból áll, mindegyiknek van saját pom.xml fájlja. A jee-web a webes rész, a jee-ejb az üzleti logikai rész, a jee-ear pedig az Enterprise Project. A webes project dependál az EJB-sre, az EAR-os pedig a másik kettőre.

Működés szerint a fordítás létrehozza a war, a jar, ezekből az ear fájlokat. A futtatás pedig elindít egy embedded Glassfish szervert, majd deployolja rá az ear fájlt. Az adatbázis az alkalmazásszerverben lévő in-memory Derby.

Tesztműködés
------------

A mintaalkalmazás egy egyszerű oldal, ahol egy egyszerű formmal felvehetőek az adatbázisba entitások kitölthető adattal, valamint listázva vannak a felvett elemek.

A mintaalkalmazásban fel van véve pár bemutató funkcionalitás:

### Adatbázis feltöltése tesztadatokkal (EJB project)

A hu.bme.StartupListener Singleton EJB az alkalmazás inicializációjakor létrehozza a teszteadatokat. Ez azért szükséges, mert mivel az adatbázis in-memory, újraindítások között elvesznek.

### Entitás (EJB project)

A hu.bme.entities.TestEntity egy felvett entitás.

### Stateless Session Bean (EJB project)

A hu.bme.TestSessionBean egy felvett session bean, amely adatbázist is használ.

### Managed bean (Web project)

A hu.bme.TestManagedBean egy felvett managed bean, request scope-s.

### Facelet oldal (Web project)

A testpage.xhtml a mintaoldal.
