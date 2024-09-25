CREATE TABLE "Users_Ruoli"(
    "id" BIGINT NOT NULL,
    "id_USER (FK)" UUID NOT NULL,
    "id_ROLE (FK)" UUID NOT NULL
);
ALTER TABLE
    "Users_Ruoli" ADD PRIMARY KEY("id");
CREATE TABLE "Roles"(
    "id" UUID NOT NULL,
    "nome_role" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "Roles" ADD PRIMARY KEY("id");
CREATE TABLE "Clienti_Indirizzi"(
    "id" UUID NOT NULL,
    "id_CLIENTE (FK)" UUID NOT NULL,
    "id_INDIRIZZO (FK)" UUID NOT NULL
);
ALTER TABLE
    "Clienti_Indirizzi" ADD PRIMARY KEY("id");
CREATE TABLE "Comuni"(
    "id" UUID NOT NULL,
    "progressivo_provincia" VARCHAR(255) NOT NULL,
    "progressivo_comune" VARCHAR(255) NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "id_PROVINCIA (FK)" UUID NOT NULL
);
ALTER TABLE
    "Comuni" ADD PRIMARY KEY("id");
CREATE TABLE "Province"(
    "id" UUID NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "sigla" VARCHAR(255) NOT NULL,
    "regione" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "Province" ADD PRIMARY KEY("id");
CREATE TABLE "Clienti"(
    "id" UUID NOT NULL,
    "nome_societa" VARCHAR(255) NOT NULL,
    "partitaIva" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "data_inserimento" DATE NOT NULL,
    "data_ultimo_contatto" DATE NOT NULL,
    "fatturato_annuale" DOUBLE PRECISION NOT NULL,
    "pec" VARCHAR(255) NOT NULL,
    "telefono" VARCHAR(255) NOT NULL,
    "email_contatto" VARCHAR(255) NOT NULL,
    "nome_contatto" VARCHAR(255) NOT NULL,
    "cognome_contatto" VARCHAR(255) NOT NULL,
    "telefono_contatto" VARCHAR(255) NOT NULL,
    "logo_aziendale" VARCHAR(255) NOT NULL,
    "tipo_cliente" VARCHAR(255) CHECK
        ("tipo_cliente" IN('')) NOT NULL
);
ALTER TABLE
    "Clienti" ADD PRIMARY KEY("id");
CREATE TABLE "Indirizzi"(
    "id" UUID NOT NULL,
    "via" VARCHAR(255) NOT NULL,
    "civico" BIGINT NOT NULL,
    "localita" VARCHAR(255) NOT NULL,
    "cap" VARCHAR(255) NOT NULL,
    "id_COMUNE (FK)" UUID NOT NULL
);
ALTER TABLE
    "Indirizzi" ADD PRIMARY KEY("id");
CREATE TABLE "Users"(
    "id" UUID NOT NULL,
    "username" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "cognome" VARCHAR(255) NOT NULL,
    "avatar_url" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "Users" ADD PRIMARY KEY("id");
CREATE TABLE "Fatture"(
    "id" UUID NOT NULL,
    "data" DATE NOT NULL,
    "importo" DOUBLE PRECISION NOT NULL,
    "numero_fattura" BIGINT NOT NULL,
    "id_STATO (FK)" UUID NOT NULL,
    "id_CLIENTE (FK)" UUID NOT NULL
);
ALTER TABLE
    "Fatture" ADD PRIMARY KEY("id");
CREATE TABLE "Stati_fatture"(
    "id" UUID NOT NULL,
    "nome_stato" BIGINT NOT NULL
);
ALTER TABLE
    "Stati_fatture" ADD PRIMARY KEY("id");
ALTER TABLE
    "Clienti_Indirizzi" ADD CONSTRAINT "clienti_indirizzi_id_indirizzo (fk)_foreign" FOREIGN KEY("id_INDIRIZZO (FK)") REFERENCES "Indirizzi"("id");
ALTER TABLE
    "Fatture" ADD CONSTRAINT "fatture_id_cliente (fk)_foreign" FOREIGN KEY("id_CLIENTE (FK)") REFERENCES "Clienti"("id");
ALTER TABLE
    "Indirizzi" ADD CONSTRAINT "indirizzi_id_comune (fk)_foreign" FOREIGN KEY("id_COMUNE (FK)") REFERENCES "Comuni"("id");
ALTER TABLE
    "Users_Ruoli" ADD CONSTRAINT "users_ruoli_id_user (fk)_foreign" FOREIGN KEY("id_USER (FK)") REFERENCES "Users"("id");
ALTER TABLE
    "Comuni" ADD CONSTRAINT "comuni_id_provincia (fk)_foreign" FOREIGN KEY("id_PROVINCIA (FK)") REFERENCES "Province"("id");
ALTER TABLE
    "Users_Ruoli" ADD CONSTRAINT "users_ruoli_id_role (fk)_foreign" FOREIGN KEY("id_ROLE (FK)") REFERENCES "Roles"("id");
ALTER TABLE
    "Clienti_Indirizzi" ADD CONSTRAINT "clienti_indirizzi_id_cliente (fk)_foreign" FOREIGN KEY("id_CLIENTE (FK)") REFERENCES "Clienti"("id");
ALTER TABLE
    "Fatture" ADD CONSTRAINT "fatture_id_stato (fk)_foreign" FOREIGN KEY("id_STATO (FK)") REFERENCES "Stati_fatture"("id");