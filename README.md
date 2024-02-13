Užduotis:

Sukurti backend (REST-API) aplikaciją skirtą bendravimui (chat). Bendravimui turi būti naudojamas tik bendras bendravimo kanalas (public room) visiems vartotojams. 

Funkciniai reikalavimai:

Palaikyti dviejų tipų vartotojus: user, admin Vartotojas user gali:
1.	Gauti žinutes (kas, kada, ką parašė). Žinutes surūšiuoti nuo naujausios iki seniausios.
2.	Parašyti naują žinutę
 
Vartotojas admin gali:
1.	Užregistruoti naują user.
•	Jei user’is jau egzistuoja turėtų grįžti klaida.
2.	Pašalinti user.
•	Pašalinus user visos jo žinutės turėtu pavirsti anonymous user’io paskelbtomis žinutėmis.
3.	Ištraukti statistiką:
•	vartotojas
•	žinučių kiekis
•	pirmosios žinutės laikas
•	paskutinės žinutės laikas
•	vidutinis žinutės ilgis
•	paskutiniosios žinutės tekstas

Pagrindiniai techniniai reikalavimai:

1.	Panaudoti GIT version control. Pateikti nuorodą į viešą Git’o repo arba .zip su prisegtu .git katalogu.
2.	Panaudoti Java 11+
3.	Panaudoti Gradle (https://gradle.org/)
4.	Panaudoti Spring boot
5.	Panaudoti springfox Swagger2 arba springdoc OpenApi3
6.	Panaudoti h2 duomenų bazę (https://www.h2database.com)
7.	Su ORM naudoti tik native užklausas. (Plain SQL)
8.	Sukurti keletą automatinių testų.

Sudėtingesni techniniai reikalavimai (pagal galimybes):

1.	Panaudoti Liquibase (https://www.liquibase.org/)
2.	Panaudoti JOOQ kaip ORM (https://www.jooq.org/)
3.	Autentifikuoti vartotojus panaudojant JWT
4.	Sukurti automatinius testus panaudojant Spock biblioteką (https://spockframework.org/spock/docs/2.3/index.html)
