--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.9
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-11-28 21:27:22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 207 (class 3079 OID 11639)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2133 (class 0 OID 0)
-- Dependencies: 207
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 24870)
-- Name: Albara; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Albara (
    id_Albara integer NOT NULL,
    dataAlbara date,
    tipusMoviment_id character varying(20),
    origen_id character varying(20),
    desti_id character varying(20),
    comAlbara text,
    codiAlbaraExtern character varying(20)
);


ALTER TABLE public.Albara OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 24868)
-- Name: Albara_id_Albara_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Albara_id_Albara_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Albara_id_Albara_seq OWNER TO postgres;

--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 175
-- Name: Albara_id_Albara_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Albara_id_Albara_seq OWNED BY Albara.id_Albara;


--
-- TOC entry 169 (class 1259 OID 24793)
-- Name: Alerta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Alerta (
    id_Alerta integer NOT NULL,
    dataAlerta date,
    tipusAlerta_id character varying(20),
    producto_id character varying(20),
    local_id character varying(20),
    estoc integer,
    estocMinim integer
);


ALTER TABLE public.Alerta OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 24791)
-- Name: Alerta_id_Alerta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Alerta_id_Alerta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Alerta_id_Alerta_seq OWNER TO postgres;

--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 168
-- Name: Alerta_id_Alerta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Alerta_id_Alerta_seq OWNED BY Alerta.id_Alerta;


--
-- TOC entry 204 (class 1259 OID 25575)
-- Name: Client; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Client (
    ids_Client integer NOT NULL,
    id_Client character varying(20),
    nomClient character varying(40)
);


ALTER TABLE public.Client OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 25573)
-- Name: Client_ids_Client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Client_ids_Client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Client_ids_Client_seq OWNER TO postgres;

--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 203
-- Name: Client_ids_Client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Client_ids_Client_seq OWNED BY Client.ids_Client;


--
-- TOC entry 189 (class 1259 OID 25079)
-- Name: Compra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Compra (
    id_Compra integer NOT NULL,
    dataCompra date,
    proveidor_id character varying(20)
);


ALTER TABLE public.Compra OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 25077)
-- Name: Compra_id_Compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Compra_id_Compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Compra_id_Compra_seq OWNER TO postgres;

--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 188
-- Name: Compra_id_Compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Compra_id_Compra_seq OWNED BY Compra.id_Compra;


--
-- TOC entry 193 (class 1259 OID 25135)
-- Name: EntradaDevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE EntradaDevolucio (
    id_EntradaDevolucio integer NOT NULL,
    dataEntrDevolucio date
);


ALTER TABLE public.EntradaDevolucio OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 25133)
-- Name: EntradaDevolucio_id_EntradaDevolucio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE EntradaDevolucio_id_EntradaDevolucio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.EntradaDevolucio_id_EntradaDevolucio_seq OWNER TO postgres;

--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 192
-- Name: EntradaDevolucio_id_EntradaDevolucio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE EntradaDevolucio_id_EntradaDevolucio_seq OWNED BY EntradaDevolucio.id_EntradaDevolucio;


--
-- TOC entry 167 (class 1259 OID 24776)
-- Name: Existencies; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Existencies (
    producte_id character varying(20) NOT NULL,
    local_id character varying(20) NOT NULL,
    estoc integer,
    estocMinim integer
);


ALTER TABLE public.Existencies OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 24829)
-- Name: FamiliaProducte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE FamiliaProducte (
    id_FamProducte character varying(20) NOT NULL
);


ALTER TABLE public.FamiliaProducte OWNER TO postgres;

--
-- TOC entry 162 (class 1259 OID 24670)
-- Name: Idioma; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Idioma (
    id_Idioma character varying(20) NOT NULL
);


ALTER TABLE public.Idioma OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 25092)
-- Name: LinCompra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE LinCompra (
    id_LinCompra integer NOT NULL,
    moviment_id integer,
    compra_id integer,
    preuCompra money,
    comLinCompra text,
    destiLocal_id character varying(20),
    numUnitRebudes integer
);


ALTER TABLE public.LinCompra OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 25090)
-- Name: LinCompra_id_LinCompra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LinCompra_id_LinCompra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LinCompra_id_LinCompra_seq OWNER TO postgres;

--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 190
-- Name: LinCompra_id_LinCompra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LinCompra_id_LinCompra_seq OWNED BY LinCompra.id_LinCompra;


--
-- TOC entry 195 (class 1259 OID 25143)
-- Name: LinEntradaDevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE LinEntradaDevolucio (
    id_LinEntradaDevolucio integer NOT NULL,
    moviment_id integer,
    entradaDevolucio_id integer,
    lineaAlbaraDev_id integer,
    motiuEntrDevol_id character varying(20),
    comEntrDevol text,
    client_id character varying(20),
    destiLocal_id character varying(20)
);


ALTER TABLE public.LinEntradaDevolucio OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 25141)
-- Name: LinEntradaDevolucio_id_LinEntradaDevolucio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LinEntradaDevolucio_id_LinEntradaDevolucio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LinEntradaDevolucio_id_LinEntradaDevolucio_seq OWNER TO postgres;

--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 194
-- Name: LinEntradaDevolucio_id_LinEntradaDevolucio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LinEntradaDevolucio_id_LinEntradaDevolucio_seq OWNED BY LinEntradaDevolucio.id_LinEntradaDevolucio;


--
-- TOC entry 200 (class 1259 OID 25224)
-- Name: LinSortidaDevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE LinSortidaDevolucio (
    id_LinSortDevolucio integer NOT NULL,
    moviment_id integer,
    sortidaDevolucio_id integer,
    motiuSortDevol_id character varying(20),
    comSortDevol text,
    origLocal_id character varying(20)
);


ALTER TABLE public.LinSortidaDevolucio OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 25222)
-- Name: LinSortidaDevolucio_id_LinSortDevolucio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LinSortidaDevolucio_id_LinSortDevolucio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LinSortidaDevolucio_id_LinSortDevolucio_seq OWNER TO postgres;

--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 199
-- Name: LinSortidaDevolucio_id_LinSortDevolucio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LinSortidaDevolucio_id_LinSortDevolucio_seq OWNED BY LinSortidaDevolucio.id_LinSortDevolucio;


--
-- TOC entry 182 (class 1259 OID 24950)
-- Name: LinTransferencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE LinTransferencia (
    id_LinTransferencia integer NOT NULL,
    moviment_id integer,
    transferencia_id integer NOT NULL,
    origLocal_id character varying(20),
    destiLocal_id character varying(20),
    numUnitRebudes integer
);


ALTER TABLE public.LinTransferencia OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 24948)
-- Name: LinTransferencia_id_LinTransferencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LinTransferencia_id_LinTransferencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LinTransferencia_id_LinTransferencia_seq OWNER TO postgres;

--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 181
-- Name: LinTransferencia_id_LinTransferencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LinTransferencia_id_LinTransferencia_seq OWNED BY LinTransferencia.id_LinTransferencia;


--
-- TOC entry 183 (class 1259 OID 24963)
-- Name: LinTransferencia_transferencia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LinTransferencia_transferencia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LinTransferencia_transferencia_id_seq OWNER TO postgres;

--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 183
-- Name: LinTransferencia_transferencia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LinTransferencia_transferencia_id_seq OWNED BY LinTransferencia.transferencia_id;


--
-- TOC entry 187 (class 1259 OID 25027)
-- Name: LinVenda; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE LinVenda (
    id_LinVenda integer NOT NULL,
    moviment_id integer,
    venda_id integer,
    preuVenda money,
    comLinVenda text,
    origLocal_id character varying(20)
);


ALTER TABLE public.LinVenda OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 25025)
-- Name: LinVenda_id_linVenda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LinVenda_id_linVenda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LinVenda_id_linVenda_seq OWNER TO postgres;

--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 186
-- Name: LinVenda_id_linVenda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LinVenda_id_linVenda_seq OWNED BY LinVenda.id_LinVenda;


--
-- TOC entry 178 (class 1259 OID 24891)
-- Name: LineaAlbara; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE LineaAlbara (
    id_LiniaAlbara integer NOT NULL,
    albara_id integer,
    moviment_id integer,
    unitats integer
);


ALTER TABLE public.LineaAlbara OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 24889)
-- Name: LineaAlbara_id_LiniaAlbara_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE LineaAlbara_id_LiniaAlbara_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.LineaAlbara_id_LiniaAlbara_seq OWNER TO postgres;

--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 177
-- Name: LineaAlbara_id_LiniaAlbara_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE LineaAlbara_id_LiniaAlbara_seq OWNED BY LineaAlbara.id_LiniaAlbara;


--
-- TOC entry 206 (class 1259 OID 25637)
-- Name: Local; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Local (
    ids_Local integer NOT NULL,
    id_local character varying(20),
    nomLocal character varying(40),
    cif character varying(9),
    telefon character varying(20),
    adreça character varying(40),
    codPost character varying(7),
    provincia_id character varying(20),
    pais character varying(20),
    tipusLocal character varying(20),
    dataAlta date,
    coordX integer,
    coordY integer
);


ALTER TABLE public.Local OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 25635)
-- Name: Local_ids_Local_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Local_ids_Local_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Local_ids_Local_seq OWNER TO postgres;

--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 205
-- Name: Local_ids_Local_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Local_ids_Local_seq OWNED BY Local.ids_Local;


--
-- TOC entry 196 (class 1259 OID 25179)
-- Name: MotiuDevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE MotiuDevolucio (
    id_MotiuDevolucio character varying(20) NOT NULL
);


ALTER TABLE public.MotiuDevolucio OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 24847)
-- Name: Moviment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Moviment (
    id_Moviment integer NOT NULL,
    dataOrdre date,
    producte_id character varying(20),
    numUnitatsOrdre integer,
    numUnitSortides integer,
    completatSN boolean,
    tipusMoviment_id character varying(20),
    dataPrevLliurament date
);


ALTER TABLE public.Moviment OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 24845)
-- Name: Moviment_id_Moviment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Moviment_id_Moviment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Moviment_id_Moviment_seq OWNER TO postgres;

--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 173
-- Name: Moviment_id_Moviment_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Moviment_id_Moviment_seq OWNED BY Moviment.id_Moviment;


--
-- TOC entry 163 (class 1259 OID 24690)
-- Name: Producte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Producte (
    id_Producte character varying(20) NOT NULL,
    nomProducte character varying(20),
    famProducte_id character varying(20)
);


ALTER TABLE public.Producte OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 24726)
-- Name: ProducteProveidor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ProducteProveidor (
    id_ProducteProveidor integer NOT NULL,
    producte_id character varying(20),
    proveidor_id character varying(20)
);


ALTER TABLE public.ProducteProveidor OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 24724)
-- Name: ProducteProveidor_id_ProducteProveidor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ProducteProveidor_id_ProducteProveidor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ProducteProveidor_id_ProducteProveidor_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 164
-- Name: ProducteProveidor_id_ProducteProveidor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ProducteProveidor_id_ProducteProveidor_seq OWNED BY ProducteProveidor.id_ProducteProveidor;


--
-- TOC entry 202 (class 1259 OID 25543)
-- Name: Proveidor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Proveidor (
    ids_Proveidor integer NOT NULL,
    id_Proveidor character varying(20),
    nomProveidor character varying(40)
);


ALTER TABLE public.Proveidor OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 25541)
-- Name: Proveidor_ids_Proveidor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Proveidor_ids_Proveidor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Proveidor_ids_Proveidor_seq OWNER TO postgres;

--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 201
-- Name: Proveidor_ids_Proveidor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Proveidor_ids_Proveidor_seq OWNED BY Proveidor.ids_Proveidor;


--
-- TOC entry 166 (class 1259 OID 24752)
-- Name: Provincia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Provincia (
    id_Provincia character varying(20) NOT NULL
);


ALTER TABLE public.Provincia OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 25216)
-- Name: SortidaDevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE SortidaDevolucio (
    id_SortidaDevol integer NOT NULL,
    dataSortDevolucio date
);


ALTER TABLE public.SortidaDevolucio OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 25214)
-- Name: SortidaDevolucio_id_SortidaDevol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE SortidaDevolucio_id_SortidaDevol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.SortidaDevolucio_id_SortidaDevol_seq OWNER TO postgres;

--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 197
-- Name: SortidaDevolucio_id_SortidaDevol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE SortidaDevolucio_id_SortidaDevol_seq OWNED BY SortidaDevolucio.id_SortidaDevol;


--
-- TOC entry 170 (class 1259 OID 24799)
-- Name: TipusAlerta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE TipusAlerta (
    id_TipusAlerta character varying(20) NOT NULL
);


ALTER TABLE public.TipusAlerta OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 24840)
-- Name: TipusMoviment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE TipusMoviment (
    id_TipusMoviment character varying(20) NOT NULL
);


ALTER TABLE public.TipusMoviment OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 24914)
-- Name: Transferencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Transferencia (
    id_Transferencia integer NOT NULL,
    dataTransferencia date
);


ALTER TABLE public.Transferencia OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 24912)
-- Name: Transferencia_id_Transferencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Transferencia_id_Transferencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Transferencia_id_Transferencia_seq OWNER TO postgres;

--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 179
-- Name: Transferencia_id_Transferencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Transferencia_id_Transferencia_seq OWNED BY Transferencia.id_Transferencia;


--
-- TOC entry 161 (class 1259 OID 24636)
-- Name: Usuari; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Usuari (
    id_Usuari character varying(20) NOT NULL,
    password character varying(20),
    login character varying(20),
    nif character varying(9),
    nomUsuari character varying(20),
    cognomUsuari character varying(30),
    idioma_id character varying(20),
    adreça character varying(20),
    Poblacio character varying(20),
    codPost character varying(6),
    local_id character varying(20),
    dataAlta date,
    vigentSN boolean,
    dataBaixa date,
    correuEM character varying(20),
    telefonFix character varying(20),
    telefonMobil character varying(20),
    Provincia_id character varying(20),
    Pais character varying(20),
    rol character varying(20),
    constraint chk_user_rol check (rol in ('Administrador','Operador Taller','Operador Oficina'))
);


ALTER TABLE public.Usuari OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 25013)
-- Name: Venda; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE Venda (
    id_Venda integer NOT NULL,
    dataVenda date,
    client_id character varying(20)
);


ALTER TABLE public.Venda OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 25011)
-- Name: Venda_id_Venda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE Venda_id_Venda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.Venda_id_Venda_seq OWNER TO postgres;

--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 184
-- Name: Venda_id_Venda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE Venda_id_Venda_seq OWNED BY Venda.id_Venda;


--
-- TOC entry 1895 (class 2604 OID 24873)
-- Name: id_Albara; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Albara ALTER COLUMN id_Albara SET DEFAULT nextval('Albara_id_Albara_seq'::regclass);


--
-- TOC entry 1893 (class 2604 OID 24796)
-- Name: id_Alerta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Alerta ALTER COLUMN id_Alerta SET DEFAULT nextval('Alerta_id_Alerta_seq'::regclass);


--
-- TOC entry 1909 (class 2604 OID 25578)
-- Name: ids_Client; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Client ALTER COLUMN ids_Client SET DEFAULT nextval('Client_ids_Client_seq'::regclass);


--
-- TOC entry 1902 (class 2604 OID 25082)
-- Name: id_Compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Compra ALTER COLUMN id_Compra SET DEFAULT nextval('Compra_id_Compra_seq'::regclass);


--
-- TOC entry 1904 (class 2604 OID 25138)
-- Name: id_EntradaDevolucio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY EntradaDevolucio ALTER COLUMN id_EntradaDevolucio SET DEFAULT nextval('EntradaDevolucio_id_EntradaDevolucio_seq'::regclass);


--
-- TOC entry 1903 (class 2604 OID 25095)
-- Name: id_LinCompra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinCompra ALTER COLUMN id_LinCompra SET DEFAULT nextval('LinCompra_id_LinCompra_seq'::regclass);


--
-- TOC entry 1905 (class 2604 OID 25146)
-- Name: id_LinEntradaDevolucio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio ALTER COLUMN id_LinEntradaDevolucio SET DEFAULT nextval('LinEntradaDevolucio_id_LinEntradaDevolucio_seq'::regclass);


--
-- TOC entry 1907 (class 2604 OID 25227)
-- Name: id_LinSortDevolucio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinSortidaDevolucio ALTER COLUMN id_LinSortDevolucio SET DEFAULT nextval('LinSortidaDevolucio_id_LinSortDevolucio_seq'::regclass);


--
-- TOC entry 1898 (class 2604 OID 24953)
-- Name: id_LinTransferencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinTransferencia ALTER COLUMN id_LinTransferencia SET DEFAULT nextval('LinTransferencia_id_LinTransferencia_seq'::regclass);


--
-- TOC entry 1899 (class 2604 OID 24965)
-- Name: transferencia_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinTransferencia ALTER COLUMN transferencia_id SET DEFAULT nextval('LinTransferencia_transferencia_id_seq'::regclass);


--
-- TOC entry 1901 (class 2604 OID 25030)
-- Name: id_LinVenda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinVenda ALTER COLUMN id_LinVenda SET DEFAULT nextval('LinVenda_id_linVenda_seq'::regclass);


--
-- TOC entry 1896 (class 2604 OID 24894)
-- Name: id_LiniaAlbara; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LineaAlbara ALTER COLUMN id_LiniaAlbara SET DEFAULT nextval('LineaAlbara_id_LiniaAlbara_seq'::regclass);


--
-- TOC entry 1910 (class 2604 OID 25640)
-- Name: ids_Local; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Local ALTER COLUMN ids_Local SET DEFAULT nextval('Local_ids_Local_seq'::regclass);


--
-- TOC entry 1894 (class 2604 OID 24850)
-- Name: id_Moviment; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Moviment ALTER COLUMN id_Moviment SET DEFAULT nextval('Moviment_id_Moviment_seq'::regclass);


--
-- TOC entry 1892 (class 2604 OID 24729)
-- Name: id_ProducteProveidor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ProducteProveidor ALTER COLUMN id_ProducteProveidor SET DEFAULT nextval('ProducteProveidor_id_ProducteProveidor_seq'::regclass);


--
-- TOC entry 1908 (class 2604 OID 25546)
-- Name: ids_Proveidor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Proveidor ALTER COLUMN ids_Proveidor SET DEFAULT nextval('Proveidor_ids_Proveidor_seq'::regclass);


--
-- TOC entry 1906 (class 2604 OID 25219)
-- Name: id_SortidaDevol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY SortidaDevolucio ALTER COLUMN id_SortidaDevol SET DEFAULT nextval('SortidaDevolucio_id_SortidaDevol_seq'::regclass);


--
-- TOC entry 1897 (class 2604 OID 24917)
-- Name: id_Transferencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Transferencia ALTER COLUMN id_Transferencia SET DEFAULT nextval('Transferencia_id_Transferencia_seq'::regclass);


--
-- TOC entry 1900 (class 2604 OID 25016)
-- Name: id_Venda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Venda ALTER COLUMN id_Venda SET DEFAULT nextval('Venda_id_Venda_seq'::regclass);


--
-- TOC entry 1946 (class 2606 OID 25037)
-- Name: fk_LinVenda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinVenda
    ADD CONSTRAINT fk_LinVenda PRIMARY KEY (id_LinVenda);


--
-- TOC entry 1934 (class 2606 OID 24878)
-- Name: pk_Albara; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Albara
    ADD CONSTRAINT pk_Albara PRIMARY KEY (id_Albara);


--
-- TOC entry 1924 (class 2606 OID 24798)
-- Name: pk_Alerta; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Alerta
    ADD CONSTRAINT pk_Alerta PRIMARY KEY (id_Alerta);


--
-- TOC entry 1950 (class 2606 OID 25084)
-- Name: pk_Compra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Compra
    ADD CONSTRAINT pk_Compra PRIMARY KEY (id_Compra);


--
-- TOC entry 1956 (class 2606 OID 25140)
-- Name: pk_EntradaDevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY EntradaDevolucio
    ADD CONSTRAINT pk_EntradaDevolucio PRIMARY KEY (id_EntradaDevolucio);


--
-- TOC entry 1928 (class 2606 OID 24833)
-- Name: pk_FamProducte; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY FamiliaProducte
    ADD CONSTRAINT pk_FamProducte PRIMARY KEY (id_FamProducte);


--
-- TOC entry 1914 (class 2606 OID 24674)
-- Name: pk_Idioma; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Idioma
    ADD CONSTRAINT pk_Idioma PRIMARY KEY (id_Idioma);


--
-- TOC entry 1952 (class 2606 OID 25100)
-- Name: pk_LinCompra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinCompra
    ADD CONSTRAINT pk_LinCompra PRIMARY KEY (id_LinCompra);


--
-- TOC entry 1958 (class 2606 OID 25151)
-- Name: pk_LinEntradaDevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT pk_LinEntradaDevolucio PRIMARY KEY (id_LinEntradaDevolucio);


--
-- TOC entry 1966 (class 2606 OID 25232)
-- Name: pk_LinSortDevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinSortidaDevolucio
    ADD CONSTRAINT pk_LinSortDevolucio PRIMARY KEY (id_LinSortDevolucio);


--
-- TOC entry 1940 (class 2606 OID 24955)
-- Name: pk_LinTransferencia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinTransferencia
    ADD CONSTRAINT pk_LinTransferencia PRIMARY KEY (id_LinTransferencia);


--
-- TOC entry 1936 (class 2606 OID 24896)
-- Name: pk_LiniaAlbara; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LineaAlbara
    ADD CONSTRAINT pk_LiniaAlbara PRIMARY KEY (id_LiniaAlbara);


--
-- TOC entry 1962 (class 2606 OID 25183)
-- Name: pk_MotiuDevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY MotiuDevolucio
    ADD CONSTRAINT pk_MotiuDevolucio PRIMARY KEY (id_MotiuDevolucio);


--
-- TOC entry 1932 (class 2606 OID 24852)
-- Name: pk_Moviment; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Moviment
    ADD CONSTRAINT pk_Moviment PRIMARY KEY (id_Moviment);


--
-- TOC entry 1916 (class 2606 OID 24694)
-- Name: pk_Producte; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Producte
    ADD CONSTRAINT pk_Producte PRIMARY KEY (id_Producte);


--
-- TOC entry 1918 (class 2606 OID 24731)
-- Name: pk_ProducteProveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ProducteProveidor
    ADD CONSTRAINT pk_ProducteProveidor PRIMARY KEY (id_ProducteProveidor);


--
-- TOC entry 1920 (class 2606 OID 24756)
-- Name: pk_Provincia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Provincia
    ADD CONSTRAINT pk_Provincia PRIMARY KEY (id_Provincia);


--
-- TOC entry 1964 (class 2606 OID 25221)
-- Name: pk_SortidaDevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY SortidaDevolucio
    ADD CONSTRAINT pk_SortidaDevolucio PRIMARY KEY (id_SortidaDevol);


--
-- TOC entry 1926 (class 2606 OID 24803)
-- Name: pk_TipusAlerta; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY TipusAlerta
    ADD CONSTRAINT pk_TipusAlerta PRIMARY KEY (id_TipusAlerta);


--
-- TOC entry 1930 (class 2606 OID 24844)
-- Name: pk_TipusMoviment; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY TipusMoviment
    ADD CONSTRAINT pk_TipusMoviment PRIMARY KEY (id_TipusMoviment);


--
-- TOC entry 1938 (class 2606 OID 24919)
-- Name: pk_Transferencia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Transferencia
    ADD CONSTRAINT pk_Transferencia PRIMARY KEY (id_Transferencia);


--
-- TOC entry 1912 (class 2606 OID 24640)
-- Name: pk_Usuari; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Usuari
    ADD CONSTRAINT pk_Usuari PRIMARY KEY (id_Usuari);


--
-- TOC entry 1944 (class 2606 OID 25018)
-- Name: pk_Venda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Venda
    ADD CONSTRAINT pk_Venda PRIMARY KEY (id_Venda);


--
-- TOC entry 1970 (class 2606 OID 25548)
-- Name: pk_idsProveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Proveidor
    ADD CONSTRAINT pk_idsProveidor PRIMARY KEY (ids_Proveidor);


--
-- TOC entry 1976 (class 2606 OID 25580)
-- Name: pk_ids_Client; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Client
    ADD CONSTRAINT pk_ids_Client PRIMARY KEY (ids_Client);


--
-- TOC entry 1982 (class 2606 OID 25642)
-- Name: pk_ids_Local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Local
    ADD CONSTRAINT pk_ids_Local PRIMARY KEY (ids_Local);


--
-- TOC entry 1922 (class 2606 OID 24780)
-- Name: pk_producte_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Existencies
    ADD CONSTRAINT pk_producte_local PRIMARY KEY (producte_id, local_id);


--
-- TOC entry 1948 (class 2606 OID 25054)
-- Name: un_Mov_LinVenda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinVenda
    ADD CONSTRAINT un_Mov_LinVenda UNIQUE (moviment_id);


--
-- TOC entry 1960 (class 2606 OID 25153)
-- Name: un_Moviment_EntrDevol; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT un_Moviment_EntrDevol UNIQUE (moviment_id);


--
-- TOC entry 1954 (class 2606 OID 25132)
-- Name: un_Moviment_LinCompra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinCompra
    ADD CONSTRAINT un_Moviment_LinCompra UNIQUE (moviment_id);


--
-- TOC entry 1968 (class 2606 OID 25234)
-- Name: un_Moviment_SortDevol; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinSortidaDevolucio
    ADD CONSTRAINT un_Moviment_SortDevol UNIQUE (moviment_id);


--
-- TOC entry 1942 (class 2606 OID 25076)
-- Name: un_Moviment_Transferencia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY LinTransferencia
    ADD CONSTRAINT un_Moviment_Transferencia UNIQUE (moviment_id);


--
-- TOC entry 1978 (class 2606 OID 25582)
-- Name: un_NomClient; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Client
    ADD CONSTRAINT un_NomClient UNIQUE (nomClient);


--
-- TOC entry 1984 (class 2606 OID 25646)
-- Name: un_NomLocal; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Local
    ADD CONSTRAINT un_NomLocal UNIQUE (nomLocal);


--
-- TOC entry 1972 (class 2606 OID 25552)
-- Name: un_NomProveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Proveidor
    ADD CONSTRAINT un_NomProveidor UNIQUE (nomProveidor);


--
-- TOC entry 1974 (class 2606 OID 25550)
-- Name: un_idProveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Proveidor
    ADD CONSTRAINT un_idProveidor UNIQUE (id_Proveidor);


--
-- TOC entry 1980 (class 2606 OID 25584)
-- Name: un_id_Clinet; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Client
    ADD CONSTRAINT un_id_Clinet UNIQUE (id_Client);


--
-- TOC entry 1986 (class 2606 OID 25644)
-- Name: un_id_Local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY Local
    ADD CONSTRAINT un_id_Local UNIQUE (id_local);


--
-- TOC entry 2020 (class 2606 OID 25828)
-- Name: f_SortidaDevolucio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinSortidaDevolucio
    ADD CONSTRAINT f_SortidaDevolucio FOREIGN KEY (sortidaDevolucio_id) REFERENCES SortidaDevolucio(id_SortidaDevol) ON UPDATE CASCADE;


--
-- TOC entry 2011 (class 2606 OID 25863)
-- Name: fk_Compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinCompra
    ADD CONSTRAINT fk_Compra FOREIGN KEY (compra_id) REFERENCES Compra(id_Compra) ON UPDATE CASCADE;


--
-- TOC entry 2014 (class 2606 OID 25798)
-- Name: fk_EntradaDevolucio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT fk_EntradaDevolucio FOREIGN KEY (entradaDevolucio_id) REFERENCES EntradaDevolucio(id_EntradaDevolucio) ON UPDATE CASCADE;


--
-- TOC entry 1990 (class 2606 OID 24835)
-- Name: fk_FamProducte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Producte
    ADD CONSTRAINT fk_FamProducte FOREIGN KEY (famProducte_id) REFERENCES FamiliaProducte(id_FamProducte) ON UPDATE CASCADE;


--
-- TOC entry 2015 (class 2606 OID 25803)
-- Name: fk_LineaAlbaraDev; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT fk_LineaAlbaraDev FOREIGN KEY (lineaAlbaraDev_id) REFERENCES LineaAlbara(id_LiniaAlbara) ON UPDATE CASCADE;


--
-- TOC entry 2016 (class 2606 OID 25808)
-- Name: fk_MotiuEntrDevol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT fk_MotiuEntrDevol FOREIGN KEY (motiuEntrDevol_id) REFERENCES MotiuDevolucio(id_MotiuDevolucio) ON UPDATE CASCADE;


--
-- TOC entry 2021 (class 2606 OID 25833)
-- Name: fk_MotiuSortDevol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinSortidaDevolucio
    ADD CONSTRAINT fk_MotiuSortDevol FOREIGN KEY (motiuSortDevol_id) REFERENCES MotiuDevolucio(id_MotiuDevolucio) ON UPDATE CASCADE;


--
-- TOC entry 2002 (class 2606 OID 24907)
-- Name: fk_Moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LineaAlbara
    ADD CONSTRAINT fk_Moviment FOREIGN KEY (moviment_id) REFERENCES Moviment(id_Moviment) ON UPDATE CASCADE;


--
-- TOC entry 2008 (class 2606 OID 25768)
-- Name: fk_Moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinVenda
    ADD CONSTRAINT fk_Moviment FOREIGN KEY (moviment_id) REFERENCES Moviment(id_Moviment) ON UPDATE CASCADE;


--
-- TOC entry 2003 (class 2606 OID 25778)
-- Name: fk_Moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinTransferencia
    ADD CONSTRAINT fk_Moviment FOREIGN KEY (moviment_id) REFERENCES Moviment(id_Moviment) ON UPDATE CASCADE;


--
-- TOC entry 2017 (class 2606 OID 25813)
-- Name: fk_Moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT fk_Moviment FOREIGN KEY (moviment_id) REFERENCES Moviment(id_Moviment) ON UPDATE CASCADE;


--
-- TOC entry 2022 (class 2606 OID 25838)
-- Name: fk_Moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinSortidaDevolucio
    ADD CONSTRAINT fk_Moviment FOREIGN KEY (moviment_id) REFERENCES Moviment(id_Moviment) ON UPDATE CASCADE;


--
-- TOC entry 2012 (class 2606 OID 25868)
-- Name: fk_Moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinCompra
    ADD CONSTRAINT fk_Moviment FOREIGN KEY (moviment_id) REFERENCES Moviment(id_Moviment) ON UPDATE CASCADE;


--
-- TOC entry 1992 (class 2606 OID 25658)
-- Name: fk_Producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ProducteProveidor
    ADD CONSTRAINT fk_Producte FOREIGN KEY (producte_id) REFERENCES Producte(id_Producte) ON UPDATE CASCADE;


--
-- TOC entry 1991 (class 2606 OID 25653)
-- Name: fk_Proveidor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ProducteProveidor
    ADD CONSTRAINT fk_Proveidor FOREIGN KEY (proveidor_id) REFERENCES Proveidor(id_Proveidor) ON UPDATE CASCADE;


--
-- TOC entry 2010 (class 2606 OID 25663)
-- Name: fk_Proveidor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Compra
    ADD CONSTRAINT fk_Proveidor FOREIGN KEY (proveidor_id) REFERENCES Proveidor(id_Proveidor) ON UPDATE CASCADE;


--
-- TOC entry 2024 (class 2606 OID 25648)
-- Name: fk_Provincia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Local
    ADD CONSTRAINT fk_Provincia FOREIGN KEY (provincia_id) REFERENCES Provincia(id_Provincia);


--
-- TOC entry 1987 (class 2606 OID 25738)
-- Name: fk_Provincia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Usuari
    ADD CONSTRAINT fk_Provincia FOREIGN KEY (Provincia_id) REFERENCES Provincia(id_Provincia) ON UPDATE CASCADE;


--
-- TOC entry 1995 (class 2606 OID 25848)
-- Name: fk_TipusAlerta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Alerta
    ADD CONSTRAINT fk_TipusAlerta FOREIGN KEY (tipusAlerta_id) REFERENCES TipusAlerta(id_TipusAlerta) ON UPDATE CASCADE;


--
-- TOC entry 1998 (class 2606 OID 25275)
-- Name: fk_TipusMoviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Moviment
    ADD CONSTRAINT fk_TipusMoviment FOREIGN KEY (tipusMoviment_id) REFERENCES TipusMoviment(id_TipusMoviment) ON UPDATE CASCADE;


--
-- TOC entry 2004 (class 2606 OID 25783)
-- Name: fk_Transferencia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinTransferencia
    ADD CONSTRAINT fk_Transferencia FOREIGN KEY (transferencia_id) REFERENCES Transferencia(id_Transferencia) ON UPDATE CASCADE;


--
-- TOC entry 2001 (class 2606 OID 24902)
-- Name: fk_albara; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LineaAlbara
    ADD CONSTRAINT fk_albara FOREIGN KEY (id_LiniaAlbara) REFERENCES Albara(id_Albara) ON UPDATE CASCADE;


--
-- TOC entry 2006 (class 2606 OID 25793)
-- Name: fk_idLocalDesti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinTransferencia
    ADD CONSTRAINT fk_idLocalDesti FOREIGN KEY (destiLocal_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2007 (class 2606 OID 25693)
-- Name: fk_id_Client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Venda
    ADD CONSTRAINT fk_id_Client FOREIGN KEY (client_id) REFERENCES Client(id_Client) ON UPDATE CASCADE;


--
-- TOC entry 2018 (class 2606 OID 25818)
-- Name: fk_id_Client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT fk_id_Client FOREIGN KEY (client_id) REFERENCES Client(id_Client) ON UPDATE CASCADE;


--
-- TOC entry 1994 (class 2606 OID 25703)
-- Name: fk_id_Local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Existencies
    ADD CONSTRAINT fk_id_Local FOREIGN KEY (local_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 1989 (class 2606 OID 25748)
-- Name: fk_id_Local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Usuari
    ADD CONSTRAINT fk_id_Local FOREIGN KEY (local_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2009 (class 2606 OID 25773)
-- Name: fk_id_Local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinVenda
    ADD CONSTRAINT fk_id_Local FOREIGN KEY (origLocal_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2019 (class 2606 OID 25823)
-- Name: fk_id_Local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinEntradaDevolucio
    ADD CONSTRAINT fk_id_Local FOREIGN KEY (destiLocal_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 1997 (class 2606 OID 25858)
-- Name: fk_id_Local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Alerta
    ADD CONSTRAINT fk_id_Local FOREIGN KEY (local_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2013 (class 2606 OID 25873)
-- Name: fk_id_Local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinCompra
    ADD CONSTRAINT fk_id_Local FOREIGN KEY (destiLocal_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2005 (class 2606 OID 25788)
-- Name: fk_id_LocalOrigen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinTransferencia
    ADD CONSTRAINT fk_id_LocalOrigen FOREIGN KEY (origLocal_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2023 (class 2606 OID 25843)
-- Name: fk_id_LocalOrigen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY LinSortidaDevolucio
    ADD CONSTRAINT fk_id_LocalOrigen FOREIGN KEY (origLocal_id) REFERENCES Local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 1999 (class 2606 OID 25280)
-- Name: fk_producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Moviment
    ADD CONSTRAINT fk_producte FOREIGN KEY (producte_id) REFERENCES Producte(id_Producte) ON UPDATE CASCADE;


--
-- TOC entry 1996 (class 2606 OID 25853)
-- Name: fk_producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Alerta
    ADD CONSTRAINT fk_producte FOREIGN KEY (producto_id) REFERENCES Producte(id_Producte) ON UPDATE CASCADE;


--
-- TOC entry 2000 (class 2606 OID 24884)
-- Name: fk_tipusMoviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Albara
    ADD CONSTRAINT fk_tipusMoviment FOREIGN KEY (tipusMoviment_id) REFERENCES TipusMoviment(id_TipusMoviment) ON UPDATE RESTRICT;


--
-- TOC entry 1988 (class 2606 OID 25743)
-- Name: idioma_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Usuari
    ADD CONSTRAINT idioma_fk FOREIGN KEY (idioma_id) REFERENCES Idioma(id_Idioma) ON UPDATE CASCADE;


--
-- TOC entry 1993 (class 2606 OID 25698)
-- Name: producte_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY Existencies
    ADD CONSTRAINT producte_fk FOREIGN KEY (producte_id) REFERENCES Producte(id_Producte) ON UPDATE CASCADE;

-- Creació del triggers pel controls dels identificadors
CREATE OR REPLACE FUNCTION setClientId() RETURNS TRIGGER AS $setClientId$
  DECLARE
  BEGIN
	NEW.id_Client := 'C' || NEW.ids_Client;
   RETURN NEW;
  END;
$setClientId$ LANGUAGE plpgsql;

CREATE TRIGGER setClientId BEFORE INSERT OR UPDATE 
    ON Client FOR EACH ROW 
    EXECUTE PROCEDURE setClientId();

CREATE OR REPLACE FUNCTION setLocalId() RETURNS TRIGGER AS $setLocalId$
  DECLARE
  BEGIN
	NEW.id_Local := 'L' || NEW.ids_Local;
   RETURN NEW;
  END;
$setLocalId$ LANGUAGE plpgsql;

CREATE TRIGGER setLocalId BEFORE INSERT OR UPDATE 
    ON Local FOR EACH ROW 
    EXECUTE PROCEDURE setLocalId();

CREATE OR REPLACE FUNCTION setProviderId() RETURNS TRIGGER AS $setProviderId$
  DECLARE
  BEGIN
	NEW.id_Proveidor := 'P' || NEW.ids_Proveidor;
   RETURN NEW;
  END;
$setProviderId$ LANGUAGE plpgsql;

CREATE TRIGGER setProviderId BEFORE INSERT OR UPDATE 
    ON Proveidor FOR EACH ROW 
    EXECUTE PROCEDURE setProviderId();

--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-28 21:27:23

--
-- PostgreSQL database dump complete
--

