Saé 2.01, Organisation de séjours linguistiques
===

### Équipe Skibidi T1 :
>- Antonin Marouzé <antonin.marouze.etu@univ-lille.fr>
>- Ethan Robert <ethan.robert.etu@univ-lille.fr>
>- Jonas Facon <jonas.facon.etu@univ-lille.fr>

# Mockup Figma

Vous pouvez retrouver notre mockup [ici](https://www.figma.com/design/Xwd3agHcfRAceG18WzvhdK/Paper-Wireframe-Kit--Community-?node-id=7574-2017&t=5QnpfoNC5vI4tsCR-1) ou sur : https://www.figma.com/design/Xwd3agHcfRAceG18WzvhdK/Paper-Wireframe-Kit--Community-?node-id=7574-2017&t=5QnpfoNC5vI4tsCR-1

# Conception 

Le projet est décomposé en quatre grands répertoires, chacun ayant son but et ses spécificités : 
 - **App** : Contient tout le code, les tests, les librairies et les assets du projet. Il s'agit du coeur de la partie POO, et d'une bonne part de celle d'IHM.
 - **Doc** : Contient tout ce qui est nécessaire à la génération du rapport.
 - **Graphes** : Contient tout ce qui touche à la partie Graphes
 - **renduIHM** : Contient les maquettes et le reste de la conception de l'interface utilisateur du projet. 

## Composition du code

Le dossier **App** est le plus complexe. Hormis une architecture de projet java assez classique (dossiers `src`, `test`, `lib`), il contient deux packages : 
 - **`model`** : Contient tout le code de la partie POO, le modèle abstrait, tout ce qui touche en somme de près ou de loin au *back-end* du projet.
 - **`ui`** : Contient le code de la partie IHM, avec l'inteface et ses bindings au package `model` (*front-end* du projet).

# Contribution

Pour cette saé en général et sur la partie IHM tous les membre du groupe on participé on developement du projet 

