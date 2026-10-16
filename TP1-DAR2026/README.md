# TP 1 : Les sockets en mode connecté TCP

## Activité 1-1 : Première connexion Client/Serveur

| | |
|---|---|
| **Étudiant** | Mohamed Yakoubi |
| **Classe** | LSI 3 |
| **Matière** | Développement d'applications réparties |
| **Établissement** | Faculté des Sciences de Sfax |
| **Année universitaire** | 2026 – 2027 |

---

## Objectif du TP

Dans ce TP, je crée deux petits programmes Java :

- un **serveur** qui attend qu'un client se connecte ;
- un **client** qui se connecte au serveur.

Les deux programmes communiquent avec des **sockets en mode connecté (TCP)**.

### Quelques mots importants

| Mot | Explication simple |
|---|---|
| **Socket** | Un point de connexion entre deux programmes sur le réseau. |
| **TCP (mode connecté)** | Le client et le serveur créent d'abord une connexion. Ensuite, ils peuvent communiquer. |
| **Port** | Un numéro (ici `1234`) qui dit à quel programme on veut parler sur l'ordinateur. |
| **Adresse IP** | L'adresse d'un ordinateur sur le réseau. |
| **localhost** | Le nom de mon propre ordinateur (adresse `127.0.0.1`). |

---

## Outils utilisés

| Outil | Version |
|---|---|
| Système | Windows 10 |
| Git | 2.45.2 |
| Java (JDK) | OpenJDK 25 (Temurin) |
| IDE | IntelliJ IDEA 2026.2.3 |

---

## Structure du projet

```text
TP1-DAR2026
├── screenshots/            (les captures d'écran de ce README)
├── src/
│   ├── clientPackage/
│   │   ├── Client.java     (activité 1-1 : le client)
│   │   ├── Client2.java    (activité 1-2 : client de la calculatrice)
│   │   └── Client3.java    (activité 1-3 : client avec menu et adresse IP saisie)
│   ├── serverPackage/
│   │   ├── Server.java     (activité 1-1 : le serveur)
│   │   ├── Server2.java    (activité 1-2 : serveur de la calculatrice)
│   │   └── Server3.java    (activité 1-3 : serveur avec 4 opérations)
│   └── Main.java           (code d'exemple créé par IntelliJ, je ne l'utilise pas)
├── .gitignore
└── TP1-DAR2026.iml
```

---

## Étape 1 et Étape 2 : Vérifier Git et Java

Je lance un terminal (Command Prompt) et je tape deux commandes :

```text
git --version
java --version
```

![Vérification de Git et de Java](screenshots/step%201%20%2B%202.PNG)

**Description :**

- `git --version` affiche **git version 2.45.2.windows.1**. Git est bien installé.
- `java --version` affiche **openjdk 25 (Temurin-25+36 LTS)**. Java est bien installé, et la version 25 est plus grande que 1.8 (version demandée).
- Les deux commandes marchent dans le terminal. Donc je n'ai pas besoin de changer la variable d'environnement `PATH`.

Dans mon espace de travail, j'ai créé le dossier **`MohamedYakoubi_LSI3_DevAppReparties_TP1`**. Mon projet est dans ce dossier.

---

## Étape 3 : Création du serveur

### Créer le projet

Je lance IntelliJ IDEA et je crée un nouveau projet Java avec les réglages par défaut :

- **Name** : `TP1-DAR2026`
- **Location** : dans le dossier `MohamedYakoubi_LSI3_DevAppReparties_TP1`
- **Create Git repository** : coché (pour avoir un dépôt Git)
- **Build system** : IntelliJ
- **JDK** : 25

![Création du projet TP1-DAR2026](screenshots/project%20creation.PNG)

**Description :** C'est la fenêtre « New Project » d'IntelliJ. La case « Create Git repository » est cochée, comme demandé dans le TP.

### Écrire le code du serveur

Dans le dossier `src`, je crée le package **`serverPackage`**. Dans ce package, je crée la classe **`Server`** avec la méthode `main()`.

![Code du serveur](screenshots/server%20code.PNG)

**Description :** À gauche, on voit l'arborescence du projet (`src` > `serverPackage` > `Server`). À droite, on voit le code de la classe `Server`.

Voici le code :

```java
package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("server waiting for a client to connect");
        Socket socket = socketServeur.accept();
        System.out.println("client connected");

        socket.close();
        socketServeur.close();
    }
}
```

**Ce que j'ai complété (les TODO) :**

| Étape du TP | Code | Explication |
|---|---|---|
| La première étape | `new ServerSocket(1234)` | Le serveur ouvre le **port 1234**. Il est prêt à recevoir un client. |
| La deuxième étape | `socketServeur.accept()` | Le serveur **attend** un client. Le programme reste bloqué sur cette ligne jusqu'à l'arrivée d'un client. |
| La dernière étape | `socket.close()` et `socketServeur.close()` | Je **ferme** le socket du client, puis le socket du serveur. |

Autres points :

- `System.out.println(...)` affiche un message dans la console (c'est le `S.o.p` du TP).
- J'ai ajouté `throws IOException` à la méthode `main`, car les opérations réseau peuvent créer une erreur.
- J'ai ajouté les `import` nécessaires pour `ServerSocket`, `Socket` et `IOException`.

### Lancer le serveur

Je lance la classe `Server`.

![Lancement du serveur](screenshots/server%20run.PNG)

**Interprétation du résultat :**

- La première ligne (très longue) est la commande que IntelliJ utilise pour lancer Java. Je peux l'ignorer.
- Le message **`server waiting for a client to connect`** veut dire que le serveur a bien démarré. Il a ouvert le port 1234 sans erreur et il attend un client sur la ligne `accept()`.
- Le programme **ne se termine pas**. Le bouton rouge « Stop » est encore actif, donc le serveur tourne toujours. Il va rester en attente jusqu'à ce qu'un client se connecte.

---

## Étape 4 : Création du client

### Écrire le code du client

Dans le dossier `src`, je crée le package **`clientPackage`**. Dans ce package, je crée la classe **`Client`** avec la méthode `main()`.

![Code du client avec localhost](screenshots/client%20code.PNG)

**Description :** On voit la classe `Client`. Dans cette première version, le client utilise le nom `localhost`.

Voici le code :

```java
package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("im connected");
        socket.close();
    }
}
```

**Ce que j'ai complété (les TODO) :**

| Étape du TP | Code | Explication |
|---|---|---|
| La première étape | `new Socket("localhost", 1234)` | Le client se **connecte** au serveur. Il donne l'adresse du serveur (`localhost`) et le port (`1234`, le même que celui du serveur). |
| La dernière étape | `socket.close()` | Le client **ferme** son socket. |

### Lancer le client

Je lance d'abord le serveur, puis je lance la classe `Client`.

![Lancement du client](screenshots/client%20run.PNG)

**Interprétation du résultat :**

- Le message **`im connected`** veut dire que la connexion avec le serveur a **réussi**.
- Le message **`Process finished with exit code 0`** veut dire que le programme s'est terminé **sans erreur** (le code 0 veut dire « tout va bien »).
- Côté serveur, la ligne `accept()` reçoit le client. Le serveur affiche alors **`client connected`**, ferme ses sockets, puis il s'arrête.

> **Remarque :** si je lance le client sans lancer le serveur avant, j'ai une erreur `java.net.ConnectException: Connection refused`. Il faut donc toujours lancer **le serveur en premier**.

### Remplacer `localhost` par une adresse IP

**a) Trouver l'adresse IP de mon PC**

Je tape la commande `ipconfig` dans le terminal.

![Adresse IP avec la commande ipconfig](screenshots/local%20ip%20extraction%20using%20ipconfig%20command.PNG)

**Description :** `ipconfig` affiche les informations réseau de mon PC. J'ai entouré en rouge l'adresse **IPv4 : `192.168.192.1`**. Elle vient de la carte réseau `vEthernet` de mon PC.

**b) Changer le code du client**

Dans la classe `Client`, je remplace `"localhost"` par l'adresse IP :

```java
Socket socket = new Socket("192.168.192.1", 1234);
```

![Code du client avec l'adresse IP](screenshots/client%20with%20ip.PNG)

**Description :** Sur la ligne 8, le client utilise maintenant **`192.168.192.1`** à la place de `localhost`.

**Explication :** Le client et le serveur sont sur le même PC. L'adresse `192.168.192.1` est une adresse de mon PC, donc le client trouve le serveur et le résultat est le même qu'avec `localhost`. Si le serveur est sur un autre PC, il faut mettre l'adresse IP de cet autre PC (les deux PC doivent être dans le même réseau).

---

## Activité 1-2 : Service de calculatrice

Dans cette activité, le client n'a pas de calculatrice. Il demande donc au serveur de faire un calcul : la valeur `x` envoyée par le client est **multipliée par 5 côté serveur**.

### Étape 1 : Préparation de l'espace de travail

Je ne modifie pas `Client` et `Server` de l'activité 1-1. Je crée deux nouvelles classes dans les mêmes packages :

- **`serverPackage.Server2`** : le serveur de la calculatrice ;
- **`clientPackage.Client2`** : le client de la calculatrice.

Comme ça, le travail de l'activité 1-1 reste intact. Les captures d'écran de cette activité sont ajoutées dans ce README.

### Étape 2 : Création du service de multiplication

**Ce que j'ai complété (les sous-étapes du TP) :**

| Étape du TP | Côté | Code | Explication |
|---|---|---|---|
| a) Taper un entier `x` | Client | `scanner.nextInt()` | Le client lit l'entier écrit au clavier. |
| b) Envoyer `x` au serveur | Client | `dos.writeInt(x)` | Le client envoie l'entier avec un `DataOutputStream`. |
| c) Recevoir `x` | Serveur | `dis.readInt()` | Le serveur était bloqué sur cette ligne, en attente du nombre. |
| d) Calculer le produit | Serveur | `x * 5` | Le calcul est fait côté serveur. |
| e) Envoyer le résultat | Serveur | `dos.writeInt(result)` | Le serveur renvoie le résultat au client. |
| f) Recevoir le résultat | Client | `dis.readInt()` | Le client lit le résultat puis l'affiche. |

Voici le cœur de la boucle de chaque programme (sans les `import` ni la fermeture des sockets) :

```java
// Client2 : envoyer x, recevoir et afficher le résultat
dos.writeInt(x);
int result = dis.readInt();
System.out.println("Server result (x * 5): " + result);
```

```java
// Server2 : recevoir x, calculer, renvoyer
int x = dis.readInt();
int result = x * 5;
dos.writeInt(result);
```

### Lancer le serveur, puis le client

Je lance d'abord `Server2`.

![Lancement de Server2](screenshots/server2%20run.PNG)

**Description :** Le serveur affiche **`Server waiting for a client to connect...`**. Il est bloqué sur `accept()` et attend un client (le bouton « Stop » est encore actif).

Je lance ensuite `Client2`.

![Lancement de Client2](screenshots/client2%20run.PNG)

**Description :** Le client affiche **`Connected to server`**, puis il me demande de taper un entier avec le message `Enter an integer (0 to stop):`.

![Server2 : client connecté](screenshots/server2%20client%20connected.PNG)

**Description :** Du côté du serveur, `accept()` a reçu le client : le message **`Client connected`** apparaît. Le serveur attend maintenant le premier nombre.

### Tester avec des valeurs

Je tape `1` dans le client.

![Client2 envoie un entier](screenshots/client2%20sends%20integer.PNG)

![Server2 reçoit l'entier et calcule](screenshots/server2%20receives%20integer%20and%20calculates.PNG)

**Interprétation du résultat :**

- Le client envoie `1`. Le serveur affiche **`Received: 1 | Sending result: 5`** : il a bien reçu `1`, calculé `1 * 5` et renvoyé `5`.
- Le client affiche **`Server result (x * 5): 5`**. Le résultat calculé par le serveur est bien arrivé chez le client.
- Le client redemande ensuite un nouvel entier : la boucle continue.

| Valeur de `x` | Résultat affiché | Interprétation |
|---|---|---|
| positive, par exemple `1` | `5` | Cas normal : `1 * 5 = 5` (voir les captures ci-dessus). |
| négative, par exemple `-3` | `-15` | Le serveur reçoit `-3` et renvoie `-15`. Le signe est conservé grâce à `DataInputStream` / `DataOutputStream` (voir plus bas). |
| `0` | aucun calcul | `0` est le signal d'arrêt de la communication (voir plus bas). Sans ce signal, `0 * 5` donnerait `0`. |

### Gérer les entiers négatifs : `DataInputStream` et `DataOutputStream`

Avec les classes simples `OutputStream.write(int)` et `InputStream.read()`, **un seul octet** est envoyé (une valeur entre 0 et 255). Un nombre négatif, ou un nombre plus grand que 255, serait donc mal transmis.

Avec **`DataOutputStream.writeInt(x)`**, le client envoie l'entier complet sur **4 octets**. Avec **`DataInputStream.readInt()`**, le serveur relit exactement ces 4 octets. L'entier reçu est donc identique à l'entier envoyé, même s'il est négatif.

### Plusieurs entiers, puis arrêt avec 0

Le client et le serveur utilisent une boucle `while (true)` :

- tant que l'utilisateur tape un entier **différent de 0**, le client l'envoie, reçoit `x * 5` et l'affiche ;
- quand l'utilisateur tape **`0`**, le client envoie `0` au serveur, puis il sort de la boucle ;
- le serveur reçoit `0`, comprend que c'est la fin et sort aussi de sa boucle.

Chaque côté ferme ensuite ses flux et ses sockets.

![Client2 : arrêt de la communication](screenshots/client2%20stopping%20communication.PNG)

**Description :** Je tape `0`. Le client affiche **`Sending 0 to stop communication...`**, puis **`Connection closed.`** et enfin `Process finished with exit code 0` (le programme s'est terminé sans erreur).

![Server2 : arrêt de la communication](screenshots/server2%20stopped%20communciation.PNG)

**Description :** Le serveur affiche **`Received 0. Stopping communication...`**, puis **`Connection closed.`** et `Process finished with exit code 0`. Les sockets sont donc bien fermés **des deux côtés**, et les deux programmes se terminent proprement.

---

## Activité 1-3 : Amélioration du service de calculatrice

Dans cette activité, j'améliore l'application de l'activité 1-2 en deux étapes :

1. le client et le serveur peuvent être sur **deux machines distinctes** ;
2. la calculatrice propose **quatre opérations** au lieu d'une seule.

Comme avant, je crée de nouvelles classes : **`Server3`** et **`Client3`**.

### Étape 1 : Connexion Client/Serveur sur deux machines distinctes

Pour gérer l'adresse IP et le port, j'utilise les classes **`java.net.InetAddress`** et **`java.net.InetSocketAddress`**.

| Côté | Code | Explication |
|---|---|---|
| Serveur | `InetAddress.getLocalHost()` | Le serveur récupère l'adresse IP de sa propre machine. |
| Serveur | `new InetSocketAddress(ip, 1234)` | Il associe cette adresse IP au port choisi (`1234`). |
| Serveur | `serverSocket.bind(socketAddress)` | Le `ServerSocket` écoute sur cette adresse IP et ce port. |
| Client | `InetAddress.getByName(serverIP)` | Le client transforme l'adresse IP tapée au clavier en objet `InetAddress`. |
| Client | `socket.connect(new InetSocketAddress(address, 1234))` | Le client se connecte à l'adresse IP du serveur, à la place de `localhost`. |

Voici les lignes importantes :

```java
// Server3 : écouter sur l'adresse IP réelle de la machine
InetAddress hostAddress = InetAddress.getLocalHost();
ServerSocket serverSocket = new ServerSocket();
serverSocket.bind(new InetSocketAddress(hostAddress.getHostAddress(), PORT));
```

```java
// Client3 : se connecter à l'adresse IP du serveur
InetAddress address = InetAddress.getByName(serverIP);
Socket socket = new Socket();
socket.connect(new InetSocketAddress(address, PORT));
```

L'adresse IP du serveur n'est plus écrite dans le code : le client la **demande au clavier**. Je n'ai donc plus besoin de modifier le code quand l'adresse IP change.

### Lancer le serveur et le client

![Server3 en attente](screenshots/server3%20running%20waiting%20for%20client.PNG)

**Description :** Le serveur affiche **`Server listening on 172.30.48.1:1234`**. Il écoute sur l'adresse IP de la machine (et non plus sur toutes les adresses) et sur le port `1234`. Puis il affiche `Waiting for a client to connect...`.

![Client3 demande l'adresse IP](screenshots/client3%20running%20requesting%20server%20IP.PNG)

**Description :** Le client ne se connecte pas tout de suite. Il me demande d'abord l'adresse IP du serveur : `Enter server IP address:`.

![Client3 : saisie de l'IP et connexion](screenshots/client3%20entering%20server%20IP%20and%20connecting.PNG)

**Description :** Je tape l'adresse IP affichée par le serveur (`172.30.48.1`). Le client affiche **`Connected to server at 172.30.48.1:1234`**, puis le **menu de la calculatrice**.

![Server3 : connexion du client](screenshots/server3%20receieved%20client%20connection%20via%20IP.PNG)

**Interprétation du résultat :** Le serveur affiche **`Client connected from: 172.30.48.1`**. La connexion a donc réussi **en passant par une adresse IP**, et non par `localhost`.

> **Remarque :** pour mes captures, le client et le serveur tournent sur le **même PC** (le serveur voit le client arriver de sa propre adresse, `172.30.48.1`). Pour utiliser **deux PC**, le serveur doit écouter sur l'adresse IP de la carte réseau du réseau local, visible avec la commande `ipconfig` (comme dans l'activité 1-1). Sur mon PC, `getLocalHost()` a trouvé l'adresse de la carte virtuelle `vEthernet (WSL)`, et non celle de ma carte Wi-Fi. Le client doit ensuite taper l'adresse IP du PC serveur, et les deux PC doivent être dans le même réseau.

### Étape 2 : Service de calculatrice amélioré

Le client affiche un **menu**. Il envoie au serveur le **choix de l'opération** puis la **valeur `x`**. Le serveur fait le calcul selon l'opération et renvoie le résultat.

| Choix | Opération | Calcul côté serveur | Exemple avec `x = 10` |
|---|---|---|---|
| `1` | Addition | `x + 5` | `15.0` |
| `2` | Soustraction | `x - 5` | `5.0` |
| `3` | Multiplication | `x * 5` | `50.0` |
| `4` | Division | `x / 5` | `2.0` |
| `0` | Quitter | aucun calcul | fin de la communication |

Voici le cœur du programme :

```java
// Client3 : envoyer le choix et x, puis recevoir le résultat
dos.writeInt(choice);
dos.writeInt(x);
double result = dis.readDouble();
```

```java
// Server3 : calculer selon le choix
switch (choice) {
    case 1: result = x + 5; break;
    case 2: result = x - 5; break;
    case 3: result = x * 5; break;
    case 4: result = (double) x / 5; break;
}
dos.writeDouble(result);
```

**Explication :**

- Le client envoie **deux entiers** dans cet ordre : le choix, puis `x`. Le serveur les lit dans le même ordre avec deux `readInt()`.
- Le résultat est envoyé avec **`writeDouble`** / **`readDouble`** (un nombre décimal), car une division peut donner un nombre à virgule. Par exemple, `7 / 5 = 1.4`. Si le résultat était un `int`, on perdrait la partie décimale.
- Quand le choix est `0`, le serveur sort de sa boucle sans attendre de valeur `x`.

### Tester le menu

Je choisis l'opération `1` (addition) avec `x = 3`.

![Client3 : calcul demandé et résultat reçu](screenshots/client3%20requested%20calculation%20and%20received%20result.PNG)

![Server3 : calcul et résultat renvoyé](screenshots/server3%20recieved%20request%20and%20claulcated%20and%20returned%20result.PNG)

**Interprétation du résultat :**

- Le client affiche **`Result: 8.0`**. Le résultat est `8.0` et non `8`, car il est de type `double`.
- Le serveur affiche **`Operation: Addition | 3 + 5 = 8.0`**. C'est bien lui qui a fait le calcul, selon l'opération choisie par le client.
- Le menu reste affiché après le résultat : le client peut choisir une autre opération, ou `0` pour quitter. Dans ce cas, les deux programmes ferment leurs sockets, comme dans l'activité 1-2.

---

## Comment lancer le projet

1. Ouvrir le projet `TP1-DAR2026` dans IntelliJ IDEA.
2. **Activité 1-1 :** lancer `serverPackage.Server`, puis `clientPackage.Client`. Le client affiche `im connected` et le serveur affiche `client connected`.
3. **Activité 1-2 :** lancer `serverPackage.Server2`, puis `clientPackage.Client2`. Taper des entiers (le client affiche `x * 5`), puis `0` pour arrêter.
4. **Activité 1-3 :** lancer `serverPackage.Server3` et noter l'adresse IP affichée. Lancer `clientPackage.Client3`, taper cette adresse IP, puis choisir les opérations dans le menu (`0` pour quitter).

> **Attention :** les trois serveurs utilisent le même port `1234`. Il faut **arrêter un serveur avant d'en lancer un autre**, sinon Java affiche l'erreur `BindException: Address already in use`. Il faut aussi toujours lancer **le serveur avant le client**.

---

## Conclusion

Dans ce TP, j'ai créé ma première connexion entre un client et un serveur avec des sockets TCP en Java, puis j'ai ajouté un service de calculatrice. J'ai appris à :

- utiliser `ServerSocket` pour ouvrir un port côté serveur ;
- utiliser `accept()` pour attendre un client ;
- utiliser `Socket` côté client pour se connecter à un serveur (avec `localhost` ou une adresse IP) ;
- échanger des données avec `DataInputStream` et `DataOutputStream` (`writeInt`, `readInt`, `writeDouble`, `readDouble`), y compris des nombres négatifs ;
- faire un traitement **côté serveur** et renvoyer le résultat au client ;
- arrêter proprement une communication et fermer les sockets des deux côtés ;
- utiliser `InetAddress` et `InetSocketAddress` pour gérer une adresse IP et un port ;
- travailler avec Git dans un projet IntelliJ.
