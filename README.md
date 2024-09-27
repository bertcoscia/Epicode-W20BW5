# EPIC ENERGY SERVICES - Backend CRM

Questo progetto è un sistema di backend per la gestione di un CRM per l'azienda di energia "EPIC ENERGY SERVICES". Il sistema è progettato per gestire i contatti con i clienti business e fornisce funzionalità di gestione tramite Web Service REST basato su Spring Boot e database PostgreSQL. <br>
[Contributors](https://github.com/bertcoscia/Epicode-W20BW5/graphs/contributors) <br>
[Repository Front-end](https://github.com/Chems05/Epicode-W2BW5-FrontEnd)
## Funzionalità Principali

### Gestione Clienti
- Gestione di un elenco dei clienti con i seguenti dati:
    - ragioneSociale
    - partitaIva
    - email
    - dataInserimento
    - dataUltimoContatto
    - fatturatoAnnuale
    - pec
    - telefono
    - emailContatto
    - nomeContatto
    - cognomeContatto
    - telefonoContatto
    - logo aziendale
- Ogni cliente può avere fino a due indirizzi: sede legale e sede operativa.
- Supporto per diversi tipi di clienti: PA, SAS, SPA, SRL.

### Gestione Indirizzi
- Gli indirizzi sono composti da:
    - via
    - civico
    - località
    - CAP
    - comune
- I comuni sono gestiti attraverso un'anagrafica centralizzata e sono caratterizzati da un nome e un riferimento alla provincia.

### Importazione Dati
- Importazione dei comuni e delle province da file CSV (`elenco-comuni-italiani.csv` e `elenco-province-italiane.csv`) per popolare il database.

### Gestione Fatture
- Associato a ogni cliente è possibile gestire un insieme di fatture caratterizzate da:
    - data
    - importo
    - numero
- Ogni fattura ha uno stato che può evolvere dinamicamente.

### Filtri e Ordinamento
- È possibile ordinare i clienti per:
    - Nome
    - Fatturato annuale
    - Data di inserimento
    - Data di ultimo contatto
    - Provincia della sede legale
- È possibile filtrare i clienti per:
    - Fatturato annuale
    - Data di inserimento
    - Data di ultimo contatto
    - Parte del nome
- Filtri disponibili per le fatture:
    - Cliente
    - Stato
    - Data
    - Anno
    - Range di importi

### Gestione Utenti
- Sistema di autenticazione e autorizzazione basato su token JWT.
- Gli utenti hanno i seguenti attributi:
    - username
    - email
    - password
    - nome
    - cognome
    - avatar
- Due ruoli disponibili:
    - `USER`: Abilitato alle sole operazioni di lettura e inserimento di nuovi clienti.
    - `ADMIN`: Abilitato a tutte le operazioni.

### Funzionalità Extra
- Invio di email ai contatti dei clienti da parte degli amministratori.
- Implementazione di un frontend semplice per dimostrare le funzionalità principali.
- Test unitari (JUnit) sugli endpoint principali.