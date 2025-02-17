# Cinnamon Stiches Shop 🧵🛒

**Cinnamon Stiches Shop** è una web application e-commerce sviluppata in **Java** utilizzando **Eclipse** come ambiente di sviluppo. Il progetto è stato deployato su **Apache Tomcat 10** ed è basato su un database relazionale che deve essere inizializzato prima dell'uso.

---

## 📌 Tecnologie Utilizzate

- **Linguaggio:** Java (JDK 17+)
- **IDE:** Eclipse
- **Framework Web:** Servlets & JSP
- **Database:** MySQL
- **Server di Deployment:** Apache Tomcat 10.1.2
- **Frontend:** HTML, CSS, JavaScript

---

## 🚀 Installazione e Setup

### 1️⃣ Prerequisiti

Assicurati di avere installati i seguenti software:

- **Java JDK 17+** [Scarica Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Eclipse IDE for Java EE Developers** [Scarica Eclipse](https://www.eclipse.org/downloads/)
- **Apache Tomcat 10** [Scarica Tomcat](https://tomcat.apache.org/download-10.cgi)
- **MySQL Server** [Scarica MySQL](https://dev.mysql.com/downloads/mysql/)

---

### 2️⃣ Clonazione del Repository

```bash
git clone https://github.com/emanueleivn/CinnamonStichesShop.git
cd CinnamonStichesShop
```

---

### 3️⃣ Configurazione del Database

Il database deve essere popolato prima di eseguire l'applicazione. Per farlo, segui questi passaggi:

1. **Avvia il server MySQL.**
2. **Esegui lo script di popolamento del database** disponibile al seguente percorso:  
   📂 [`database/popolamento.sql`](https://github.com/emanueleivn/CinnamonStichesShop/blob/main/CinnamonStichesShop/database/popolamento.sql)
3. **Configura il file di connessione al database** nel progetto.

---

### 4️⃣ Importazione del Progetto in Eclipse

1. **Apri Eclipse** e vai su `File -> Import -> Existing Maven Projects`  
2. **Seleziona la cartella del progetto** e conferma l'importazione.  
3. **Configura il server Tomcat** in Eclipse:
   - Vai su `Window -> Preferences -> Server -> Runtime Environments`
   - Aggiungi `Apache Tomcat 10`
   - Configura il percorso di Tomcat  
4. **Esegui il progetto su Tomcat** (`Run As -> Run on Server`)

---

## 📌 Deployment su Tomcat

Per deployare il progetto su **Apache Tomcat 10**, segui questi passi:

1. **Genera il file `.war`**  
   - In Eclipse, clicca con il tasto destro sul progetto -> `Export -> WAR file`  
   - Seleziona la cartella di destinazione e premi `Finish`
   
2. **Copia il file `.war` nella cartella `webapps` di Tomcat**
   ```bash
   cp CinnamonStichesShop.war /path/to/tomcat/webapps/
   ```
3. **Avvia Tomcat**
   ```bash
   cd /path/to/tomcat/bin
   ./catalina.sh run
   ```

4. **Apri il browser e accedi all'applicazione**  
   ```
   http://localhost:8080/CinnamonStichesShop
   ```

---

## 📄 Struttura del Progetto

📂 **CinnamonStichesShop**  
 ├── 📂 `src/main/java/com/cinnamonstiches/` (Codice sorgente)  
 ├── 📂 `src/main/webapp` (Frontend JSP, CSS, JS)  
 ├── 📂 `database` (Script di creazione e popolamento del database)  
 ├── 📂 `WEB-INF` (Configurazione e file `web.xml`)    
 ├── 📜 `README.md` (Documentazione)  

---

## ⚡ Contributi

Se vuoi contribuire al progetto:

1. **Forka il repository**  
2. **Crea una nuova branch** (`git checkout -b feature/nuova-funzionalità`)  
3. **Committa le modifiche** (`git commit -m "Aggiunta nuova funzionalità"`)  
4. **Pusha la branch** (`git push origin feature/nuova-funzionalità`)  
5. **Apri una Pull Request**

---

## 📧 Contatti

Per qualsiasi domanda o supporto, puoi contattarmi tramite:  

✉️ Email: `emiiovane@gmail.com`  
🐙 GitHub: [@emanueleivn](https://github.com/emanueleivn)  

---

## 📜 Licenza

Questo progetto è distribuito sotto licenza **MIT**. Consulta il file [`LICENSE`](LICENSE) per maggiori dettagli.  

---

🔹 **Cinnamon Stiches Shop – Il tuo e-commerce di fiducia per articoli di cucito e ricamo!** 🎀✨
