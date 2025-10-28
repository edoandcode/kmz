# KMZ Project

This repository contains the backend and design files for the **Digitalization and Valorization Platform for the Local Agricultural Supply Chain**.

---

## Project Specification

**Progetto: Piattaforma di Digitalizzazione e Valorizzazione della Filiera Agricola Locale**

Il progetto mira a creare una piattaforma che permetta la gestione, valorizzazione e tracciabilità dei prodotti agricoli di un territorio comunale.
La piattaforma consentirà di caricare, visualizzare e condividere informazioni legate alla filiera agricola, includendo dati relativi alla produzione, trasformazione e vendita di prodotti locali.
Sarà pensata come uno strumento per promuovere il territorio, permettendo a chiunque di comprendere la provenienza e la qualità dei prodotti tipici.
Inoltre, la piattaforma faciliterà l’organizzazione di eventi locali, come fiere e visite guidate alle aziende.

La piattaforma permette la tracciabilità di ogni prodotto attraverso i diversi attori della filiera, visualizzando l’intero ciclo produttivo.
Il caricamento di informazioni e contenuti sarà riferito a specifici punti della filiera, come aziende agricole e mercati locali.
I contenuti potranno includere, ad esempio, dati su certificazioni, metodi di coltivazione e pratiche di produzione.
La piattaforma dovrà inoltre fornire la possibilità di visualizzare tutti i punti della filiera su una mappa interattiva.

Attori coinvolti

- Produttore – carica informazioni sui propri prodotti (metodi di coltivazione, certificazioni, ecc.) e li vende nel marketplace.

- Trasformatore – inserisce dati sui processi di trasformazione e sulle certificazioni di qualità, collegando le fasi della produzione ai produttori locali; può vendere nel marketplace.

- Distributore di Tipicità – pubblica i prodotti in vendita e può creare pacchetti che combinano più articoli in un’unica offerta, promuovendo esperienze gastronomiche territoriali.

- Curatore – verifica e approva i contenuti caricati dagli altri attori prima della pubblicazione.

- Animatore della Filiera – organizza eventi, fiere e visite alle aziende agricole, promuovendo il coinvolgimento della comunità.

- Acquirente – consulta le informazioni sui prodotti e può acquistare dal marketplace o prenotare eventi.

- Utente Generico – accede ai contenuti informativi per conoscere la provenienza e la qualità dei prodotti locali.

- Gestore della Piattaforma – gestisce gli aspetti amministrativi, le autorizzazioni e gli accrediti dei vari attori.

- Sistemi Social destinatari di contenuti – consentono la condivisione dei contenuti creati dagli utenti.

- Sistema OSM (OpenStreetMap) – fornisce le mappe e la visualizzazione geografica dei punti della filiera.

**Vincoli e Dettagli Tecnici**

Il progetto deve essere sviluppato in Java e successivamente portato su Spring Boot.

Lo strato di presentazione può essere sviluppato con strumenti a scelta dello studente (CLI o API REST).

Devono essere utilizzati almeno due design pattern (diversi dal Singleton).


---

## Project Structure
- `/code`       : Contains the backend code (Spring Boot REST API) and frontend code (TODO)
- `/design/vp`  : Contains Visual Paradigm modeling files