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
│   │   └── Client.java     (le client)
│   ├── serverPackage/
│   │   └── Server.java     (le serveur)
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

## Comment lancer le projet

1. Ouvrir le projet `TP1-DAR2026` dans IntelliJ IDEA.
2. Lancer la classe `serverPackage.Server`. Le message `server waiting for a client to connect` apparaît.
3. Lancer la classe `clientPackage.Client`.
4. Le client affiche `im connected` et le serveur affiche `client connected`.

---

## Conclusion

Dans ce TP, j'ai créé ma première connexion entre un client et un serveur avec des sockets TCP en Java. J'ai appris à :

- utiliser `ServerSocket` pour ouvrir un port côté serveur ;
- utiliser `accept()` pour attendre un client ;
- utiliser `Socket` côté client pour se connecter à un serveur (avec `localhost` ou une adresse IP) ;
- fermer les sockets avec `close()` ;
- travailler avec Git dans un projet IntelliJ.
