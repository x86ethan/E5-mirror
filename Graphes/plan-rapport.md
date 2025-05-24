---
title: SAE S2.02 -- Rapport graphes
subtitle: Équipe Skibidi T1
author: Jonas FACON, Antonin Marouzé, Ethan Robert, 
date: 2025
---

_Ci-dessus, remplacer NOM_EQUIPE par le nom de votre équipe, et les Prénom Nom par vos prénoms et noms._

_Effacez les indications en italique avant de rendre le rapport._

_Le fichier source Markdown de ce rapport se trouve sur Moodle._

# Version 1

## Choix pour la modélisation

_Ci-dessous, H1, H2, etc. désignent des noms d'hôtes ; V1, V2, etc. désignent des noms de visiteurs. Pour chacun et chacune d'entre iels, vous devrez donner des valeurs pour les colonnes HOBBIES, GENDER, PAIR_GENDER et BIRTH_DATE. Vous pouvez réutiliser les mêmes valeurs plusieurs fois. La présentation des données doit être **lisible** (par ex. tableau, capture d'écran de tableur avec résolution suffisante)._ 

### Forte affinité
_Donnez une paire (H1, V1) qui présente une forte affinité. Expliquez pourquoi._

| FORNAME | NAME | BIRTH_DATE | HOBBIES | GENDER | PAIR_GENDER |
|---|---|---|---|---|---|
| Patrick| H1 |28/08/2010 | culture,reading,botanic | male | male |
| Jean | V1 | 12/11/2010 | swim,culture,reading | male | male |

Ils ont une forte affinité car ils ont les mêmes centres d'intérêt, n'ont pas de grosse différence d'âge, et ils sont compatibles au niveau du genre (Homme -> Homme) (Homme -> Homme)

### Faible affinité
_Donnez une paire (H2, V2) qui présente une faible affinité. Expliquez pourquoi_

| FORNAME | NAME | BIRTH_DATE | HOBBIES | GENDER | PAIR_GENDER |
|---|---|---|---|---|---|
| Gotaga | H2 | 07/09/1993 | gaming,esport | male | male |
| Marine | V2 | 05/08/1968 | politic,swim,cooking | female | female |

D'une, ils ont un trop grand écart d'âge, ils n'ont pas de compatibilité en terme de préférences de genre, et ils ne possèdent aucun centre d'intérêt en commun.

### Arbitrage entre les critères d'affinité
_Donnez trois paires hôte-visiteur (H3, V3), (H4, V4), (H5, V5) d'affinités à peu près équivalentes. Ces paires doivent illustrer comment vous arbitrez entre les différents critères d'affinité (passe-temps, préférences de genre, différence d'âge). Donc, idéalement, les raisons d'affinité seraient différentes dans les trois paires._

| FORNAME | NAME | BIRTH_DATE | HOBBIES | GENDER | PAIR_GENDER |
|---|---|---|---|---|---|
| Luc | H3 | 02/12/2008 | science,gaming | male | female |
| Pablo | V3 | 16/11/2008 | music | male | female |
| Juliette | H4 | 22/05/2006 | sport,reading | female | male |
| Eva | V4 | 18/08/2004 | sport,reading | female | male |
| Isagi | H5 | 29/05/2007 | football,shopping | male | male |
| Bachira | V5 | 17/09/2005 | basketball,culture | male | male |



## Exemple complet
_Donnez un exemple de quatre hôtes A, B, C, D et quatre visiteurs W, X, Y, Z. Puis, donnez l'appariement qui vous considérez le meilleur entre ces hôtes et visiteurs._

| NAME | BIRTH_DATE | HOBBIES                        | GENDER | PAIR_GENDER |
|------|------------|--------------------------------|--------|-------------|
| A    | 15/06/2006 | reading, photography           | male   | male        |
| B    | 11/05/2008 | cooking, running               | male   | male        |
| C    | 20/03/2005 | drawing, dancing               | female | male        |
| D    | 22/11/2008 | photography, reading, hiking   | male   |             |
| W    | 01/09/2010 | drawing, yoga                  | male   | male        |
| X    | 03/02/2006 | dancing, writing               | female | male        |
| Y    | 14/03/2005 | reading                        | female | male        |
| Z    | 24/01/2004 | reading, cooking, running      | male   | male        |

Solution 1 :
- W ← C ✅ 
- Z ← B ✅ 
- Y ← A ✅ 
- X ← D ✅ 

## Score d'affinité

_**Lisez les conseils pour l'écriture de pseudo-code se trouvant sur Moodle**._

_Donner le pseudo-code de la fonction `score_affinité_1(hôte, visiteur)` qui retourne un nombre représentant le degré d'affinité entre un hôte et un visiteur._

```
double score_affinité_1(hôte, visiteur) 
  score = 0

  si hôte.age - visiteur.age <= 30 mois
    score = score - 5

  si hôte.age - visiteur.age <= 18 mois (Pour que l'afiniter par rapport a l'age soit plus importante)
    score = score - 5

  si hôte.gender == visiteur.pair_gender
    score = score - 5

  si visiteur.gender == hôte.pair_gender
    score = score - 5

  N = 1 + nombre_hobbies_en_commun(hôte, visiteur)
  score = score * N

  retourner score
```

## Retour sur l'exemple

_Donnez la matrice d'adjacence du graphe biparti complet entre les hôtes A, B, C, D et les visiteurs W, X, Y, Z que vous avez introduit plus haut. Les poids des arêtes sont donnés par la fonction `score_affinité_1`._

_Calculez l'appariement de poids minimal pour ce graphe. Obtenez-vous l'appariement que vous aviez identifié comme le meilleur ?_

```
Matrice du graphe :
                A        B        C        D 
       W  -10,000  -15,000  -20,000  -15,000 
       X  -15,000  -10,000  -10,000   -5,000 
       Y  -30,000   -5,000  -10,000  -20,000 
       Z  -30,000  -30,000  -15,000  -20,000 

Affectation de cout minimal -85,000000 :
(W, C, -20,000000)
(Z, B, -30,000000)
(Y, A, -30,000000)
(X, D, -5,000000)
```

# Version 2

_Ci-dessous, vous définirez des hôtes ayant des noms A1, A2, B1, B2, etc., et des visiteurs ayant des noms W1, W2, X1, X2, etc. Pour chacun et chacune d'entre iels, vous devrez donner :_

- _la valeur pour la coronne NAME parmi A1, A2, B1, ..., W1, W2, X1, ... ;_
- _des valeurs pour les colonnes HOBBIES, GENDER, PAIR_GENDER, BIRTH_DATE pour tout le monde ;_
- _des valeurs pour les colonnes HOST_HAS_ANIMAL, HOST_FOOD pour les hôtes ;_
- _des valeurs pour les colonnes GUEST_ANIMAL_ALLERGY, GUEST_FOOD_CONSTRAINT pour les visiteurs._

### Hosts

| NAME | HOBBIES               | GENDER | PAIR_GENDER | BIRTH_DATE | HOST_HAS_ANIMAL | HOST_FOOD |
|------|------------------------|--------|-------------|------------|------------------|-----------|
| A1   | Cooking, gardening     | Female | Male        | 2006-04-22 | Yes              | veggie    |
| A2   | Hiking, reading        | Male   | Female      | 2005-12-10 | No               | noNuts    |
| B1   | Cinema, board games    | Male   | Male        | 2007-07-01 | Yes              | veggie    |
| B2   | Dance, photography     | Female | Female      | 2004-03-15 | No               | noNuts    |
| C1   | Skateboarding, gaming  | Male   | Female      | 2006-05-03 | Yes              | veggie    |
| C2   | Drawing, animals       | Female | Male        | 2007-09-22 | No               | noNuts    |

### Guests

| NAME | HOBBIES               | GENDER | PAIR_GENDER | BIRTH_DATE | GUEST_ANIMAL_ALLERGY | GUEST_FOOD_CONSTRAINT |
|------|------------------------|--------|-------------|------------|------------------------|------------------------|
| W1   | Music, writing         | Female | Male        | 2005-09-30 | No                     | veggie                 |
| W2   | Swimming, painting     | Male   | Female      | 2004-11-12 | Yes                    | noNuts                 |
| X1   | Climbing, cinema       | Male   | Male        | 2008-02-27 | No                     | none                   |
| X2   | Reading, yoga          | Female | Female      | 2006-06-18 | Yes                    | veggie                 |
| Y1   | Biking, manga          | Male   | Female      | 2005-01-16 | No                     | noNuts                 |
| Y2   | Theater, piano         | Female | Male        | 2006-12-04 | Yes                    | veggie                 |
| Z1   | Photography, gaming    | Male   | Male        | 2008-03-10 | No                     | veggie                 |
| Z2   | Singing, drawing       | Female | Female      | 2007-07-25 | Yes                    | noNuts                 |

## Exemple avec appariement total

_Donnez un exemple de quatre hôtes A1, B1, C1, D1 et quatre visiteurs W1, X1, Y1, Z1 pour lesquels il existe des incompatibilités entre certains hôtes et certains visiteurs, mais il est possible de trouver un appariement qui respecte les contraintes rédhibitoires._

### Contraintes rédhibitoires :

    Un visiteur allergique aux animaux ne peut pas être placé chez un hôte ayant un animal.

    Un hôte ne doit pas proposer une alimentation incompatible avec les contraintes alimentaires du visiteur.

### Hosts

| NAME | HOBBIES         | GENDER | PAIR_GENDER | BIRTH_DATE | HOST_HAS_ANIMAL | HOST_FOOD |
|------|------------------|--------|-------------|------------|------------------|-----------|
| A1   | Gardening        | Female | Male        | 2006-03-10 | Yes              | veggie    |
| B1   | Cinema           | Male   | Female      | 2007-08-22 | No               | noNuts    |
| C1   | Gaming           | Male   | Male        | 2005-05-01 | Yes              | noNuts    |
| D1   | Drawing          | Female | Female      | 2004-12-15 | No               | veggie    |

### Guests

| NAME | HOBBIES         | GENDER | PAIR_GENDER | BIRTH_DATE | GUEST_ANIMAL_ALLERGY | GUEST_FOOD_CONSTRAINT |
|------|------------------|--------|-------------|------------|------------------------|------------------------|
| W1   | Music            | Female | Male        | 2005-07-10 | Yes                    | noNuts                 |
| X1   | Climbing         | Male   | Male        | 2006-02-19 | No                     | veggie                 |
| Y1   | Manga            | Male   | Female      | 2007-06-04 | Yes                    | noNuts                 |
| Z1   | Photography      | Female | Female      | 2008-01-30 | No                     | veggie                 |


_Donnez également l'appariement que vous considérez le meilleur pour cet exemple. Expliquez pourquoi._

|Hôte|Visiteur|Raison du choix|
|---|---|---|
|B1|W1|W1 est allérgiques aux animeaux et allérgique aux cacahuétes arachides,B1 est n'a pas d'animal et propose une alimentation sans arachides |
|D1|X1|X1 n'a pas d'allergie mais posséde un rémige vegétarien et D1 propose un régime végétarien et ne posséde pas d'animal|
|C1|Y1|Y1 est allergique aux animaux et suit un réigme aux sans arachides et C1 ne posséde pas d'animal |
|A1|Z1|	Z1 n’a pas d’allergie et suit un régime veggie et n'a pas d'allergy vers un annimal. A1 a un animal et propose veggie|

## Exemple sans appariement total

_Donnez un exemple de quatre hôtes A2, B2, C2, D2 et quatre visiteurs W2, X2, Y2, Z2 pour lesquels il n'est pas possible de former quatre paires hôte-visiteur à cause d'incompatibilités._

### Hosts

| NAME | HOBBIES         | GENDER | PAIR_GENDER | BIRTH_DATE | HOST_HAS_ANIMAL | HOST_FOOD |
|------|------------------|--------|-------------|------------|------------------|-----------|
| A2   | Painting         | Female | Male        | 2005-06-15 | Yes              |           |
| B2   | Basketball       | Male   | Female      | 2004-09-10 | No               |           |
| C2   | Cooking          | Female | Male        | 2006-03-22 | Yes              |           |
| D2   | Photography      | Male   | Female      | 2007-12-05 | No               |           |

### Guests

| NAME | HOBBIES         | GENDER | PAIR_GENDER | BIRTH_DATE | GUEST_ANIMAL_ALLERGY | GUEST_FOOD_CONSTRAINT |
|------|------------------|--------|-------------|------------|------------------------|------------------------|
| W2   | Hiking           | Male   | Female      | 2007-01-17 | Yes                    |                       |
| X2   | Reading          | Female | Male        | 2006-04-12 | No                     |                       |
| Y2   | Writing          | Male   | Male        | 2005-08-25 | Yes                    | noNuts                 |
| Z2   | Yoga             | Female | Female      | 2004-11-08 | No                     | veggie                 |

Incompatibilités :

  1. W2 est allergique aux animaux → A2 et C2 (ayant un animal) ne peuvent pas l’accueillir.

  2. Y2 a une contrainte alimentaire noNuts → A2 et C2 (proposent autre chose que noNuts) ne peuvent pas l’accueillir.

  3. Z2 suit un régime veggie → B2 (qui ne propose pas de restriction alimentaire) ne peut pas l’accueillir, mais D2 propose veggie.

_Pour cet exemple, quel est le plus grand nombre de paires qu'on peut former ?_
  * En raison des incompatibilités mentionnées ci-dessus, le plus grand nombre de paires que l’on peut former est de 3.

_Donnez l'appariement que vous considérez le meilleur. Expliquez pourquoi._
  * W2 et B2 : W2 a une allergie aux animaux et B2 n’a pas d’animal. Ils peuvent donc être appariés sans problème.

## Score d'affinité

_Donner le pseudo-code de la fonction `score_affinité_2(hôte, visiteur)` qui retourne un nombre représentant le degré d'affinité entre un hôte et un visiteur. Vous pouvez réutiliser la fonction `score_affinité_1` (l'appeler ou copier du code)._

### Comptabilité 2 :
```
Fonction computeCompatibility():
    // Prise en compte des préférences

    // Parsing des hobbies
    guestHobbies = éclater(this.guest.getCriteriaValue("HOBBIES"), ",")
    hostHobbies = éclater(this.host.getCriteriaValue("HOBBIES"), ",")

    matchingHobbies = nouvelle ListeVide

    Pour chaque hobby dans guestHobbies:
        Si arrayContient(hostHobbies, hobby) alors:
            ajouter hobby à matchingHobbies
        Fin Si
    Fin Pour

    Pour chaque hobby dans hostHobbies:
        Si arrayContient(guestHobbies, hobby) ET NON arrayListContient(matchingHobbies, hobby) alors:
            ajouter hobby à matchingHobbies
        Fin Si
    Fin Pour

    Si longueur(hostHobbies) > longueur(guestHobbies) alors:
        maxHobbiesLength = longueur(hostHobbies)
    Sinon:
        maxHobbiesLength = longueur(guestHobbies)
    Fin Si

    hobbiesCompatibility = taille(matchingHobbies) / maxHobbiesLength

    genderCompatibility = 0

    Si this.guest.getCriteriaValue("PAIR_GENDER") est égal à this.host.getCriteriaValue("GENDER") alors:
        genderCompatibility = 1double score_affinité_3(hôte, visiteur) 

    Fin Si

    ageCompatibility = 0

    Si valeurAbsolue(this.host.getAge() - this.guest.getAge()) <= 1 alors:
        ageCompatibility = 1
    Fin Si

    // Contraintes rédhibitoires

    animalAllergyCompatibility = vrai
    Si this.host.hasCriteria("HOST_HAS_ANIMAL", "true") ET this.guest.hasCriteria("GUEST_ANIMAL_ALLERGY", "true") alors:
        animalAllergyCompatibility = faux
    Fin Si

    foodAllergyCompatibility = vrai

    Si this.guest.hasCriteria("GUEST_FOOD", "vegetarian") ET NON this.host.hasCriteria("HOST_FOOD", "vegetarian") alors:
        foodAllergyCompatibility = faux
    Sinon Si this.guest.hasCriteria("GUEST_FOOD", "nonuts") ET NON this.host.hasCriteria("HOST_FOOD", "nonuts") alors:
        foodAllergyCompatibility = faux
    Fin Si

    Si animalAllergyCompatibility ET foodAllergyCompatibility ET historyCompatibility alors:
        this.compatibility = (genderCompatibility + hobbiesCompatibility + ageCompatibility) / 3.0
    Sinon:
        this.compatibility = 0.0
    Fin Si
Fin Fonction
```

## Retour sur l'exemple

_Donnez les matrices d'adjacence pour les deux exemples de la Version 2 (A1,B1,C1,D1/W1,X1,Y1,Z1 et A2,B2,C2,D2/W2,X2,Y2,Z2). Les poids des arêtes sont déterminés par la fonction `score_affinité_2`. Pensez à nommer les lignes et les colonnes._
|    | W1  | X1   | Y1   | Z1   |
| -- | --- | ---- | ---- | ---- |
| A1 | 0.0 | 0.33 | 0.0  | 0.33 |
| B1 | 1.0 | 0.0  | 0.33 | 0.0  |
| C1 | 0.0 | 0.0  | 0.0  | 0.0  |
| D1 | 0.0 | 1.0  | 0.0  | 0.67 |

|    | W2  | X2   | Y2  | Z2   |
| -- | --- | ---- | --- | ---- |
| A2 | 0.0 | 0.33 | 0.0 | 0.33 |
| B2 | 1.0 | 0.0  | 0.0 | 0.0  |
| C2 | 0.0 | 0.0  | 0.0 | 0.0  |
| D2 | 0.0 | 0.67 | 0.0 | 0.67 |


_Calculez l'appariement de poids minimal pour chacun des graphes. Obtenez-vous l'appariement que vous aviez identifié comme le meilleur ?_
```
Affectation de cout minimal 0,000000 :
(D1, W1, 0,000000)
(A1, Y1, 0,000000)
(C1, X1, 0,000000)
(B1, Z1, 0,000000)
```
```
Affectation de cout minimal 0,000000 :
(D2, W2, 0,000000)
(A2, Y2, 0,000000)
(C2, X2, 0,000000)
(B2, Z2, 0,000000)
```

## Robustesse de la modélisation (question difficile)

_Est-ce que votre fonction `score_affinité_2` garantit que les contraintes rédhibitoires seront toujours respectées, quel que soit le jeu de données ? Justifiez votre réponse._

Oui, la fonction `score_affinite_2` garantit bel et bien que les contraintes rédhibitoires seront **toujours** respectées, car leur vérification fait renter en jeu un schéma conditionnel n'autorisant pas un score d'affinité autre que 0 si une d'entre elles n'est pas respectée. 

_**Indications** : Cherchez un exemple de **grande taille** pour lequel la fonction `score_affinité_2` pourrait ne pas garantir le respect des contraintes. Dans cet exemple, vous auriez beaucoup d'adolescents compatibles sans affinité, et quelques adolescents incompatibles avec beaucoup d'affinité._
* 100 visiteurs et 100 hôtes.

* 90 visiteurs sont compatibles avec 90 hôtes (pas de contraintes rédhibitoires) mais n’ont aucune affinité (hobbies différents, genres non préférés, âge éloigné) score_affinité_2 à peux prés égale à 0.01 ou 0.0

* 10 visiteurs ont une affinité parfaite (score théorique = 1.0) avec 10 hôtes mais ils sont incompatibles pour des raisons rédhibitoires : allergies, végétarisme non respecté, historique problématique.

_Il est possible que votre fonction garantisse le respect des contraintes quel que soit l'exemple. Si vous pensez que c'est le cas, donnez des arguments pour convaincre._ 

La fonction donnant un **score de compatibilité**, compris entre 0 et 1, et non un choix binaire (vrai ou faux) pour renseigner sur la compatibilité entre deux adolescents. Ainsi, il est garanti que l'algorithme garantisses les associations selon le meilleur scénario possible à chaque fois. Les associations où une des contraintes rhédibitoires n'est pas respectée possède inévitablement un score de compatibilité de 0. Elles sont ainsi ignorées, et les étudiants ne peuvent pas être mis ensemble.




# Version 3

_Ci-dessous, H1, H2, etc. désignent des noms d'hôtes et V1, V2, etc désignent des noms de visiteurs. Pour chacun et chacune d'entre iels, vous devrez donner des valeurs pour toutes les colonnes pertinentes en fonction de leur rôle, hôte ou visiteur._
| FORENAME | NAME | COUNTRY | BIRTH_DATE | GUEST_ANIMAL_ALLERGY | HOST_HAS_ANIMAL | GUEST_FOOD_CONSTRAINT | HOST_FOOD | HOBBIES  | GENDER | PAIR_GENDER | HISTORY |
|----------|------|---------|------------|--------|----|------|------|------|--------|---|----------|
| Alice    | H1   | France  | 2006-07-15 |        | true       |                        | nonuts    | music,reading,hiking     | F      | M            | same     |
| Bruno    | H2   | France  | 2005-03-20 |        | false      |                        | vegie     | football,travel,cinema   | M      | F            | other    |
| Chloé    | H3   | France  | 2007-11-05 |        | fals       |                        | nonuts    | drawing,reading,chess    | F      | F            | same     |
| David    | H4   | France  | 2004-01-30 |        | true       |                        | vegie     | basketball,music,cinema  | M      | F            | other    |
| Emma     | V1   | France  | 2006-06-20 | true   |            | vegetarian             |           | reading,cinema,hiking    | F      | M            | same     |
| Félix    | V2   | France  | 2005-12-02 |        |            | noNuts                 |           | football,chess,music     | M      | F            | same     |
| Inès     | V3   | France  | 2006-08-18 |        |            |                        |           | drawing,reading,cinema   | F      | F            | other    |
| Jules    | V4   | France  | 2004-05-09 | true   |            | noNuts                 |           | basketball,travel,music  | M      | F            | other    |


## Équilibrage entre affinité / incompatibilité

_Donnez au moins quatre paires hôte-visiteur (H1, V1), (H2, V2), (H3, V3), (H4, V4), ... que vous considérez quasi équivalents pour l'affectation. Certaines de ces paires doivent ne pas respecter les contraintes considérées rédhibitoires dans la Version 2, d'autres doivent les respecter. Ces exemples doivent illustrer l'équilibrage que vous faites entre l'incompatibilité d'une part et l'affinité d'autre part : combien et quel type d'affinité permet de compenser combien et quel type d'incompatibilité. Les exemples seront accompagnés de commentaires expliquant vos choix._
| Pair     | Contraintes respectées ? | Affinité estimée |Commentaire  |
| -------- | ----- | ------ | ------- |
| (H1, V1) | ❌ Allergie + animal | 0.95 | Très forte affinité car même hobbies,mais V1 est allergique aux animaux et H1 a un animal|
| (H2, V2) | ✅  | 0.65 | Compatibles sur hobbies même hobbies, âge proche, préférences de genre OK, aucune contrainte violée. Affinité moyenne mais sûre.|
| (H3, V3) | ✅  | 0.90 | Très bonne affinité même hobbies, même genre (F-F), historique compatible. Pas de contrainte rédhibitoire. Une des paires avec le plus de potentiel|
| (H4, V4) | ❌ Allergie + animal| 0.85 | H4 a un animal et V4 est allergique, donc incompatibles. Pourtant, très forte affinité car même hobbies ,âge et genre compatibles.|


## Score d'affinité

_Donner le pseudo-code de la fonction `score_affinité_3(hôte, visiteur)` qui retourne un nombre représentant le degré d'affinité entre un hôte et un visiteur. Vous pouvez réutiliser les fonctions `score_affinité_1` et `score_affinité_2`._

```
Fonction computeCompatibility(h de type History):
    // Prise en compte des préférences

    // Parsing des hobbies
    guestHobbies = éclater(this.guest.getCriteriaValue("HOBBIES"), ",")
    hostHobbies = éclater(this.host.getCriteriaValue("HOBBIES"), ",")

    matchingHobbies = nouvelle ListeVide

    Pour chaque hobby dans guestHobbies:
        Si arrayContient(hostHobbies, hobby) alors:
            ajouter hobby à matchingHobbies
        Fin Si
    Fin Pour

    Pour chaque hobby dans hostHobbies:
        Si arrayContient(guestHobbies, hobby) ET NON arrayListContient(matchingHobbies, hobby) alors:
            ajouter hobby à matchingHobbies
        Fin Si
    Fin Pour

    Si longueur(hostHobbies) > longueur(guestHobbies) alors:
        maxHobbiesLength = longueur(hostHobbies)
    Sinon:
        maxHobbiesLength = longueur(guestHobbies)
    Fin Si

    hobbiesCompatibility = taille(matchingHobbies) / maxHobbiesLength

    genderCompatibility = 0

    Si this.guest.getCriteriaValue("PAIR_GENDER") est égal à this.host.getCriteriaValue("GENDER") alors:
        genderCompatibility = 1
    Fin Si

    ageCompatibility = 0

    Si valeurAbsolue(this.host.getAge() - this.guest.getAge()) <= 1 alors:
        ageCompatibility = 1
    Fin Si

    // Contraintes rédhibitoires

    animalAllergyCompatibility = vrai
    Si this.host.hasCriteria("HOST_HAS_ANIMAL", "true") ET this.guest.hasCriteria("GUEST_ANIMAL_ALLERGY", "true") alors:
        animalAllergyCompatibility = faux
    Fin Si

    foodAllergyCompatibility = vrai

    Si this.guest.hasCriteria("GUEST_FOOD", "vegetarian") ET NON this.host.hasCriteria("HOST_FOOD", "vegetarian") alors:
        foodAllergyCompatibility = faux
    Sinon Si this.guest.hasCriteria("GUEST_FOOD", "nonuts") ET NON this.host.hasCriteria("HOST_FOOD", "nonuts") alors:
        foodAllergyCompatibility = faux
    Fin Si

    // Historique

    historyCompatibility = vrai
    historyAffinity = 0
    Si this.guest.hasCriteria("HISTORY", "same") ET this.host.hasCriteria("HISTORY", "same") ET NON h.hasAlreadyBeenMatched(this.host, this.guest) alors:
        historyCompatibility = faux
    Sinon Si h.hasAlreadyBeenMatched(this.guest, this.host) ET (this.host.hasCriteria("HISTORY", "other") OU this.guest.hasCriteria("HISTORY", "other")) alors:
        historyCompatibility = faux
    Fin Si

    Si animalAllergyCompatibility ET foodAllergyCompatibility ET historyCompatibility alors:
        this.compatibility = (genderCompatibility + hobbiesCompatibility + ageCompatibility) / 3.0
    Sinon:
        this.compatibility = 0.0
    Fin Si
Fin Fonction

```

## Retour sur l'exemple

_Donnez le résultat de la fonction `score_affinité_3` pour les exemples d'équilibrage (H1, V1), (H2, V2), etc. ci-dessus. Est-ce que vous obtenez des scores proches ?_ 

_**Remarque**: Deux scores ne sont pas proches ou éloignés dans l'absolu ; cela dépend de la valeur minimale et la valeur maximale que peut prendre le score. Par exemple, les nombres 10 et 20 sont "proches" à l'échelle de l'intervalle de 0 à 1000, mais ne sont pas "proches" à l'échelle de l'intervalle 0 à 30._

    🧩 (H1, V1)

    H1 a un animal & V1 est allergique -> ❌ rédhibitoire

    Hobbies communs : reading, hiking -> 2 en commun / max(3,3) = 2/3 ≈ 0.666

    Genre : F (host) / M (PAIR_GENDER) -> ✅

    Âge : 2006 vs 2006 -> ✅

    Résultat final : malgré affinité élevée, compatibilité = 0.0 (cause allergie)

    🧩 (H2, V2)

    Aucune contrainte violée

    Hobbies : football, music en commun -> 1 commun / max(3,3) = 1/3 ≈ 0.333

    Genre : M / F -> ✅

    Âge : 2005 vs 2005 -> ✅

    Résultat : (1 + 0.333 + 1)/3 = 0.777

    🧩 (H3, V3)

    Aucune contrainte violée

    Hobbies : drawing, reading, cinema -> 3/3 = 1.0

    Genre : F / F -> ✅

    Âge : 2007 vs 2006 -> ✅

    Résultat : (1 + 1 + 1)/3 = 1.0

    🧩 (H4, V4)

    H4 a un animal & V4 est allergique -> ❌ rédhibitoire

    Hobbies communs : basketball, music, cinema -> 3/3 = 1.0

    Genre : M / F -> ✅

    Âge : 2004 vs 2004 -> ✅

    Résultat final : compatibilité = 0.0

| Pair     | Contraintes rédhibitoires | Affinité brute (avant invalidation) | Score final |
| -------- | ------------------------- | ----------------------------------- | ----------- |
| (H1, V1) | Oui (animal)              | ≈ 0.888                             | 0.0         |
| (H2, V2) | Non                       | ≈ 0.777                             | 0.777       |
| (H3, V3) | Non                       | 1.0                                 | 1.0         |
| (H4, V4) | Oui (animal)              | 1.0                                 | 0.0         |
