--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-12-01 14:17:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 219 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 219
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 50970)
-- Name: albara; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE albara (
    id_albara integer NOT NULL,
    dataalbara date,
    tipusmoviment_id character varying(20),
    origen_id character varying(20),
    desti_id character varying(20),
    comalbara text,
    codialbaraextern character varying(20)
);


ALTER TABLE public.albara OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 50976)
-- Name: albara_id_albara_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE albara_id_albara_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.albara_id_albara_seq OWNER TO postgres;

--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 171
-- Name: albara_id_albara_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE albara_id_albara_seq OWNED BY albara.id_albara;


--
-- TOC entry 172 (class 1259 OID 50978)
-- Name: alerta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alerta (
    id_alerta integer NOT NULL,
    dataalerta date,
    tipusalerta_id character varying(20),
    producte_id character varying(20),
    local_id character varying(20),
    estoc integer,
    estocminim integer
);


ALTER TABLE public.alerta OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 50981)
-- Name: alerta_id_alerta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alerta_id_alerta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alerta_id_alerta_seq OWNER TO postgres;

--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 173
-- Name: alerta_id_alerta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alerta_id_alerta_seq OWNED BY alerta.id_alerta;


--
-- TOC entry 174 (class 1259 OID 50983)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    ids_client integer NOT NULL,
    id_client character varying(20),
    nomclient character varying(40)
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 50986)
-- Name: client_ids_client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE client_ids_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_ids_client_seq OWNER TO postgres;

--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 175
-- Name: client_ids_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE client_ids_client_seq OWNED BY client.ids_client;


--
-- TOC entry 176 (class 1259 OID 50988)
-- Name: compra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE compra (
    id_compra integer NOT NULL,
    datacompra date,
    proveidor_id character varying(20)
);


ALTER TABLE public.compra OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 50991)
-- Name: compra_id_compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE compra_id_compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.compra_id_compra_seq OWNER TO postgres;

--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 177
-- Name: compra_id_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE compra_id_compra_seq OWNED BY compra.id_compra;


--
-- TOC entry 178 (class 1259 OID 50993)
-- Name: entradadevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE entradadevolucio (
    id_entradadevolucio integer NOT NULL,
    dataentrdevolucio date
);


ALTER TABLE public.entradadevolucio OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 50996)
-- Name: entradadevolucio_id_entradadevolucio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE entradadevolucio_id_entradadevolucio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.entradadevolucio_id_entradadevolucio_seq OWNER TO postgres;

--
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 179
-- Name: entradadevolucio_id_entradadevolucio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE entradadevolucio_id_entradadevolucio_seq OWNED BY entradadevolucio.id_entradadevolucio;


--
-- TOC entry 180 (class 1259 OID 50998)
-- Name: existencies; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE existencies (
    producte_id character varying(20) NOT NULL,
    local_id character varying(20) NOT NULL,
    estoc integer,
    estocminim integer
);


ALTER TABLE public.existencies OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 51397)
-- Name: grup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grup (
    id_grup integer NOT NULL,
    nom character varying(20)
);


ALTER TABLE public.grup OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 51400)
-- Name: grup_id_grup_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grup_id_grup_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grup_id_grup_seq OWNER TO postgres;

--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 216
-- Name: grup_id_grup_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grup_id_grup_seq OWNED BY grup.id_grup;


--
-- TOC entry 181 (class 1259 OID 51004)
-- Name: idioma; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE idioma (
    id_idioma character varying(20) NOT NULL
);


ALTER TABLE public.idioma OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 51046)
-- Name: linalbara; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE linalbara (
    id_liniaalbara integer NOT NULL,
    albara_id integer,
    moviment_id integer,
    unitats integer
);


ALTER TABLE public.linalbara OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 51007)
-- Name: lincompra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lincompra (
    id_lincompra integer NOT NULL,
    moviment_id integer,
    compra_id integer,
    preucompra money,
    comlincompra text,
    destilocal_id character varying(20),
    numunitrebudes integer
);


ALTER TABLE public.lincompra OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 51013)
-- Name: lincompra_id_lincompra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lincompra_id_lincompra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lincompra_id_lincompra_seq OWNER TO postgres;

--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 183
-- Name: lincompra_id_lincompra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lincompra_id_lincompra_seq OWNED BY lincompra.id_lincompra;


--
-- TOC entry 194 (class 1259 OID 51049)
-- Name: lineaalbara_id_liniaalbara_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lineaalbara_id_liniaalbara_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lineaalbara_id_liniaalbara_seq OWNER TO postgres;

--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 194
-- Name: lineaalbara_id_liniaalbara_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lineaalbara_id_liniaalbara_seq OWNED BY linalbara.id_liniaalbara;


--
-- TOC entry 184 (class 1259 OID 51015)
-- Name: linentradadevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE linentradadevolucio (
    id_linentradadevolucio integer NOT NULL,
    moviment_id integer,
    entradadevolucio_id integer,
    lineaalbaradev_id integer,
    motiuentrdevol_id character varying(20),
    comentrdevol text,
    client_id character varying(20),
    destilocal_id character varying(20)
);


ALTER TABLE public.linentradadevolucio OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 51021)
-- Name: linentradadevolucio_id_linentradadevolucio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE linentradadevolucio_id_linentradadevolucio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.linentradadevolucio_id_linentradadevolucio_seq OWNER TO postgres;

--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 185
-- Name: linentradadevolucio_id_linentradadevolucio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE linentradadevolucio_id_linentradadevolucio_seq OWNED BY linentradadevolucio.id_linentradadevolucio;


--
-- TOC entry 186 (class 1259 OID 51023)
-- Name: linsortidadevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE linsortidadevolucio (
    id_linsortdevolucio integer NOT NULL,
    moviment_id integer,
    sortidadevolucio_id integer,
    motiusortdevol_id character varying(20),
    comsortdevol text,
    origlocal_id character varying(20)
);


ALTER TABLE public.linsortidadevolucio OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 51029)
-- Name: linsortidadevolucio_id_linsortdevolucio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE linsortidadevolucio_id_linsortdevolucio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.linsortidadevolucio_id_linsortdevolucio_seq OWNER TO postgres;

--
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 187
-- Name: linsortidadevolucio_id_linsortdevolucio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE linsortidadevolucio_id_linsortdevolucio_seq OWNED BY linsortidadevolucio.id_linsortdevolucio;


--
-- TOC entry 188 (class 1259 OID 51031)
-- Name: lintransferencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lintransferencia (
    id_lintransferencia integer NOT NULL,
    moviment_id integer,
    transferencia_id integer NOT NULL,
    origlocal_id character varying(20),
    destilocal_id character varying(20),
    numunitrebudes integer
);


ALTER TABLE public.lintransferencia OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 51034)
-- Name: lintransferencia_id_lintransferencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lintransferencia_id_lintransferencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lintransferencia_id_lintransferencia_seq OWNER TO postgres;

--
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 189
-- Name: lintransferencia_id_lintransferencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lintransferencia_id_lintransferencia_seq OWNED BY lintransferencia.id_lintransferencia;


--
-- TOC entry 190 (class 1259 OID 51036)
-- Name: lintransferencia_transferencia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lintransferencia_transferencia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lintransferencia_transferencia_id_seq OWNER TO postgres;

--
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 190
-- Name: lintransferencia_transferencia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lintransferencia_transferencia_id_seq OWNED BY lintransferencia.transferencia_id;


--
-- TOC entry 191 (class 1259 OID 51038)
-- Name: linvenda; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE linvenda (
    id_linvenda integer NOT NULL,
    moviment_id integer,
    venda_id integer,
    preuvenda money,
    comlinvenda text,
    origlocal_id character varying(20)
);


ALTER TABLE public.linvenda OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 51044)
-- Name: linvenda_id_linvenda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE linvenda_id_linvenda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.linvenda_id_linvenda_seq OWNER TO postgres;

--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 192
-- Name: linvenda_id_linvenda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE linvenda_id_linvenda_seq OWNED BY linvenda.id_linvenda;


--
-- TOC entry 195 (class 1259 OID 51051)
-- Name: local; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE local (
    ids_local integer NOT NULL,
    id_local character varying(20),
    nomlocal character varying(40),
    cif character varying(9),
    telefon character varying(20),
    "adreça" character varying(40),
    codpost character varying(7),
    provincia_id character varying(20),
    pais character varying(20),
    tipuslocal character varying(20),
    dataalta date,
    coordx integer,
    coordy integer
);


ALTER TABLE public.local OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 51054)
-- Name: local_ids_local_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE local_ids_local_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.local_ids_local_seq OWNER TO postgres;

--
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 196
-- Name: local_ids_local_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE local_ids_local_seq OWNED BY local.ids_local;


--
-- TOC entry 197 (class 1259 OID 51056)
-- Name: motiudevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE motiudevolucio (
    id_motiudevolucio character varying(20) NOT NULL
);


ALTER TABLE public.motiudevolucio OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 51059)
-- Name: moviment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE moviment (
    id_moviment integer NOT NULL,
    dataordre date,
    producte_id character varying(20),
    numunitatsordre integer,
    numunitsortides integer,
    completatsn boolean,
    tipusmoviment_id character varying(20),
    dataprevlliurament date
);


ALTER TABLE public.moviment OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 51062)
-- Name: moviment_id_moviment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE moviment_id_moviment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.moviment_id_moviment_seq OWNER TO postgres;

--
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 199
-- Name: moviment_id_moviment_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE moviment_id_moviment_seq OWNED BY moviment.id_moviment;


--
-- TOC entry 200 (class 1259 OID 51064)
-- Name: producte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE producte (
    id_producte character varying(20) NOT NULL,
    nomproducte character varying(20),
    productegrup_id integer,
    productesubgrup_id integer
);


ALTER TABLE public.producte OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 51067)
-- Name: producteproveidor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE producteproveidor (
    id_producteproveidor integer NOT NULL,
    producte_id character varying(20),
    proveidor_id character varying(20)
);


ALTER TABLE public.producteproveidor OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 51070)
-- Name: producteproveidor_id_producteproveidor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE producteproveidor_id_producteproveidor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.producteproveidor_id_producteproveidor_seq OWNER TO postgres;

--
-- TOC entry 2253 (class 0 OID 0)
-- Dependencies: 202
-- Name: producteproveidor_id_producteproveidor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE producteproveidor_id_producteproveidor_seq OWNED BY producteproveidor.id_producteproveidor;


--
-- TOC entry 203 (class 1259 OID 51072)
-- Name: proveidor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE proveidor (
    ids_proveidor integer NOT NULL,
    id_proveidor character varying(20),
    nomproveidor character varying(40)
);


ALTER TABLE public.proveidor OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 51075)
-- Name: proveidor_ids_proveidor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proveidor_ids_proveidor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.proveidor_ids_proveidor_seq OWNER TO postgres;

--
-- TOC entry 2254 (class 0 OID 0)
-- Dependencies: 204
-- Name: proveidor_ids_proveidor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proveidor_ids_proveidor_seq OWNED BY proveidor.ids_proveidor;


--
-- TOC entry 205 (class 1259 OID 51077)
-- Name: provincia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE provincia (
    id_provincia character varying(20) NOT NULL
);


ALTER TABLE public.provincia OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 51080)
-- Name: sortidadevolucio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sortidadevolucio (
    id_sortidadevol integer NOT NULL,
    datasortdevolucio date
);


ALTER TABLE public.sortidadevolucio OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 51083)
-- Name: sortidadevolucio_id_sortidadevol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sortidadevolucio_id_sortidadevol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sortidadevolucio_id_sortidadevol_seq OWNER TO postgres;

--
-- TOC entry 2255 (class 0 OID 0)
-- Dependencies: 207
-- Name: sortidadevolucio_id_sortidadevol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sortidadevolucio_id_sortidadevol_seq OWNED BY sortidadevolucio.id_sortidadevol;


--
-- TOC entry 217 (class 1259 OID 51402)
-- Name: subgrup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE subgrup (
    id_subgrup integer NOT NULL,
    id_grup integer NOT NULL,
    nom character varying(20)
);


ALTER TABLE public.subgrup OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 51405)
-- Name: subgrup_id_subgrup_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE subgrup_id_subgrup_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subgrup_id_subgrup_seq OWNER TO postgres;

--
-- TOC entry 2256 (class 0 OID 0)
-- Dependencies: 218
-- Name: subgrup_id_subgrup_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE subgrup_id_subgrup_seq OWNED BY subgrup.id_subgrup;


--
-- TOC entry 208 (class 1259 OID 51085)
-- Name: tipusalerta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipusalerta (
    id_tipusalerta character varying(20) NOT NULL
);


ALTER TABLE public.tipusalerta OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 51088)
-- Name: tipusmoviment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipusmoviment (
    id_tipusmoviment character varying(20) NOT NULL
);


ALTER TABLE public.tipusmoviment OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 51091)
-- Name: transferencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transferencia (
    id_transferencia integer NOT NULL,
    datatransferencia date
);


ALTER TABLE public.transferencia OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 51094)
-- Name: transferencia_id_transferencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE transferencia_id_transferencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transferencia_id_transferencia_seq OWNER TO postgres;

--
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 211
-- Name: transferencia_id_transferencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE transferencia_id_transferencia_seq OWNED BY transferencia.id_transferencia;


--
-- TOC entry 212 (class 1259 OID 51096)
-- Name: usuari; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuari (
    id_usuari character varying(20) NOT NULL,
    password character varying(20),
    login character varying(20),
    nif character varying(9),
    nomusuari character varying(20),
    cognomusuari character varying(30),
    idioma_id character varying(20),
    "adreça" character varying(20),
    poblacio character varying(20),
    codpost character varying(6),
    local_id character varying(20),
    dataalta date,
    vigentsn boolean,
    databaixa date,
    correuem character varying(20),
    telefonfix character varying(20),
    telefonmobil character varying(20),
    provincia_id character varying(20),
    pais character varying(20),
    rol character varying(20),
    CONSTRAINT chk_user_rol CHECK (((rol)::text = ANY ((ARRAY['Administrador'::character varying, 'Operador Taller'::character varying, 'Operador Oficina'::character varying])::text[])))
);


ALTER TABLE public.usuari OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 51100)
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE venda (
    id_venda integer NOT NULL,
    datavenda date,
    client_id character varying(20)
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 51103)
-- Name: venda_id_venda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE venda_id_venda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venda_id_venda_seq OWNER TO postgres;

--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 214
-- Name: venda_id_venda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE venda_id_venda_seq OWNED BY venda.id_venda;


--
-- TOC entry 1979 (class 2604 OID 51105)
-- Name: id_albara; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY albara ALTER COLUMN id_albara SET DEFAULT nextval('albara_id_albara_seq'::regclass);


--
-- TOC entry 1980 (class 2604 OID 51106)
-- Name: id_alerta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alerta ALTER COLUMN id_alerta SET DEFAULT nextval('alerta_id_alerta_seq'::regclass);


--
-- TOC entry 1981 (class 2604 OID 51107)
-- Name: ids_client; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN ids_client SET DEFAULT nextval('client_ids_client_seq'::regclass);


--
-- TOC entry 1982 (class 2604 OID 51108)
-- Name: id_compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY compra ALTER COLUMN id_compra SET DEFAULT nextval('compra_id_compra_seq'::regclass);


--
-- TOC entry 1983 (class 2604 OID 51109)
-- Name: id_entradadevolucio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entradadevolucio ALTER COLUMN id_entradadevolucio SET DEFAULT nextval('entradadevolucio_id_entradadevolucio_seq'::regclass);


--
-- TOC entry 1999 (class 2604 OID 51437)
-- Name: id_grup; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grup ALTER COLUMN id_grup SET DEFAULT nextval('grup_id_grup_seq'::regclass);


--
-- TOC entry 1990 (class 2604 OID 51116)
-- Name: id_liniaalbara; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linalbara ALTER COLUMN id_liniaalbara SET DEFAULT nextval('lineaalbara_id_liniaalbara_seq'::regclass);


--
-- TOC entry 1984 (class 2604 OID 51110)
-- Name: id_lincompra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lincompra ALTER COLUMN id_lincompra SET DEFAULT nextval('lincompra_id_lincompra_seq'::regclass);


--
-- TOC entry 1985 (class 2604 OID 51111)
-- Name: id_linentradadevolucio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio ALTER COLUMN id_linentradadevolucio SET DEFAULT nextval('linentradadevolucio_id_linentradadevolucio_seq'::regclass);


--
-- TOC entry 1986 (class 2604 OID 51112)
-- Name: id_linsortdevolucio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linsortidadevolucio ALTER COLUMN id_linsortdevolucio SET DEFAULT nextval('linsortidadevolucio_id_linsortdevolucio_seq'::regclass);


--
-- TOC entry 1987 (class 2604 OID 51113)
-- Name: id_lintransferencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lintransferencia ALTER COLUMN id_lintransferencia SET DEFAULT nextval('lintransferencia_id_lintransferencia_seq'::regclass);


--
-- TOC entry 1988 (class 2604 OID 51114)
-- Name: transferencia_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lintransferencia ALTER COLUMN transferencia_id SET DEFAULT nextval('lintransferencia_transferencia_id_seq'::regclass);


--
-- TOC entry 1989 (class 2604 OID 51115)
-- Name: id_linvenda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linvenda ALTER COLUMN id_linvenda SET DEFAULT nextval('linvenda_id_linvenda_seq'::regclass);


--
-- TOC entry 1991 (class 2604 OID 51117)
-- Name: ids_local; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY local ALTER COLUMN ids_local SET DEFAULT nextval('local_ids_local_seq'::regclass);


--
-- TOC entry 1992 (class 2604 OID 51118)
-- Name: id_moviment; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY moviment ALTER COLUMN id_moviment SET DEFAULT nextval('moviment_id_moviment_seq'::regclass);


--
-- TOC entry 1993 (class 2604 OID 51119)
-- Name: id_producteproveidor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY producteproveidor ALTER COLUMN id_producteproveidor SET DEFAULT nextval('producteproveidor_id_producteproveidor_seq'::regclass);


--
-- TOC entry 1994 (class 2604 OID 51120)
-- Name: ids_proveidor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proveidor ALTER COLUMN ids_proveidor SET DEFAULT nextval('proveidor_ids_proveidor_seq'::regclass);


--
-- TOC entry 1995 (class 2604 OID 51121)
-- Name: id_sortidadevol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sortidadevolucio ALTER COLUMN id_sortidadevol SET DEFAULT nextval('sortidadevolucio_id_sortidadevol_seq'::regclass);


--
-- TOC entry 2000 (class 2604 OID 51438)
-- Name: id_subgrup; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subgrup ALTER COLUMN id_subgrup SET DEFAULT nextval('subgrup_id_subgrup_seq'::regclass);


--
-- TOC entry 1996 (class 2604 OID 51122)
-- Name: id_transferencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transferencia ALTER COLUMN id_transferencia SET DEFAULT nextval('transferencia_id_transferencia_seq'::regclass);


--
-- TOC entry 1998 (class 2604 OID 51123)
-- Name: id_venda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ALTER COLUMN id_venda SET DEFAULT nextval('venda_id_venda_seq'::regclass);


--
-- TOC entry 2036 (class 2606 OID 51125)
-- Name: fk_linvenda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linvenda
    ADD CONSTRAINT fk_linvenda PRIMARY KEY (id_linvenda);


--
-- TOC entry 2002 (class 2606 OID 51127)
-- Name: pk_albara; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY albara
    ADD CONSTRAINT pk_albara PRIMARY KEY (id_albara);


--
-- TOC entry 2004 (class 2606 OID 51129)
-- Name: pk_alerta; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alerta
    ADD CONSTRAINT pk_alerta PRIMARY KEY (id_alerta);


--
-- TOC entry 2012 (class 2606 OID 51131)
-- Name: pk_compra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT pk_compra PRIMARY KEY (id_compra);


--
-- TOC entry 2014 (class 2606 OID 51133)
-- Name: pk_entradadevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY entradadevolucio
    ADD CONSTRAINT pk_entradadevolucio PRIMARY KEY (id_entradadevolucio);


--
-- TOC entry 2078 (class 2606 OID 51408)
-- Name: pk_grup; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grup
    ADD CONSTRAINT pk_grup PRIMARY KEY (id_grup);


--
-- TOC entry 2018 (class 2606 OID 51137)
-- Name: pk_idioma; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY idioma
    ADD CONSTRAINT pk_idioma PRIMARY KEY (id_idioma);


--
-- TOC entry 2006 (class 2606 OID 51173)
-- Name: pk_ids_client; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT pk_ids_client PRIMARY KEY (ids_client);


--
-- TOC entry 2042 (class 2606 OID 51175)
-- Name: pk_ids_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY local
    ADD CONSTRAINT pk_ids_local PRIMARY KEY (ids_local);


--
-- TOC entry 2058 (class 2606 OID 51171)
-- Name: pk_idsproveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY proveidor
    ADD CONSTRAINT pk_idsproveidor PRIMARY KEY (ids_proveidor);


--
-- TOC entry 2020 (class 2606 OID 51139)
-- Name: pk_lincompra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lincompra
    ADD CONSTRAINT pk_lincompra PRIMARY KEY (id_lincompra);


--
-- TOC entry 2024 (class 2606 OID 51141)
-- Name: pk_linentradadevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT pk_linentradadevolucio PRIMARY KEY (id_linentradadevolucio);


--
-- TOC entry 2040 (class 2606 OID 51147)
-- Name: pk_liniaalbara; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linalbara
    ADD CONSTRAINT pk_liniaalbara PRIMARY KEY (id_liniaalbara);


--
-- TOC entry 2028 (class 2606 OID 51143)
-- Name: pk_linsortdevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linsortidadevolucio
    ADD CONSTRAINT pk_linsortdevolucio PRIMARY KEY (id_linsortdevolucio);


--
-- TOC entry 2032 (class 2606 OID 51145)
-- Name: pk_lintransferencia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lintransferencia
    ADD CONSTRAINT pk_lintransferencia PRIMARY KEY (id_lintransferencia);


--
-- TOC entry 2048 (class 2606 OID 51149)
-- Name: pk_motiudevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY motiudevolucio
    ADD CONSTRAINT pk_motiudevolucio PRIMARY KEY (id_motiudevolucio);


--
-- TOC entry 2050 (class 2606 OID 51151)
-- Name: pk_moviment; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY moviment
    ADD CONSTRAINT pk_moviment PRIMARY KEY (id_moviment);


--
-- TOC entry 2054 (class 2606 OID 51153)
-- Name: pk_producte; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY producte
    ADD CONSTRAINT pk_producte PRIMARY KEY (id_producte);


--
-- TOC entry 2016 (class 2606 OID 51177)
-- Name: pk_producte_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY existencies
    ADD CONSTRAINT pk_producte_local PRIMARY KEY (producte_id, local_id);


--
-- TOC entry 2056 (class 2606 OID 51155)
-- Name: pk_producteproveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY producteproveidor
    ADD CONSTRAINT pk_producteproveidor PRIMARY KEY (id_producteproveidor);


--
-- TOC entry 2064 (class 2606 OID 51157)
-- Name: pk_provincia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY provincia
    ADD CONSTRAINT pk_provincia PRIMARY KEY (id_provincia);


--
-- TOC entry 2066 (class 2606 OID 51159)
-- Name: pk_sortidadevolucio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sortidadevolucio
    ADD CONSTRAINT pk_sortidadevolucio PRIMARY KEY (id_sortidadevol);


--
-- TOC entry 2080 (class 2606 OID 51410)
-- Name: pk_subgrup; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subgrup
    ADD CONSTRAINT pk_subgrup PRIMARY KEY (id_subgrup);


--
-- TOC entry 2068 (class 2606 OID 51161)
-- Name: pk_tipusalerta; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipusalerta
    ADD CONSTRAINT pk_tipusalerta PRIMARY KEY (id_tipusalerta);


--
-- TOC entry 2070 (class 2606 OID 51163)
-- Name: pk_tipusmoviment; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipusmoviment
    ADD CONSTRAINT pk_tipusmoviment PRIMARY KEY (id_tipusmoviment);


--
-- TOC entry 2072 (class 2606 OID 51165)
-- Name: pk_transferencia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY transferencia
    ADD CONSTRAINT pk_transferencia PRIMARY KEY (id_transferencia);


--
-- TOC entry 2074 (class 2606 OID 51167)
-- Name: pk_usuari; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuari
    ADD CONSTRAINT pk_usuari PRIMARY KEY (id_usuari);


--
-- TOC entry 2076 (class 2606 OID 51169)
-- Name: pk_venda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT pk_venda PRIMARY KEY (id_venda);


--
-- TOC entry 2008 (class 2606 OID 51197)
-- Name: un_id_clinet; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT un_id_clinet UNIQUE (id_client);


--
-- TOC entry 2044 (class 2606 OID 51199)
-- Name: un_id_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY local
    ADD CONSTRAINT un_id_local UNIQUE (id_local);


--
-- TOC entry 2060 (class 2606 OID 51195)
-- Name: un_idproveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY proveidor
    ADD CONSTRAINT un_idproveidor UNIQUE (id_proveidor);


--
-- TOC entry 2038 (class 2606 OID 51179)
-- Name: un_mov_linvenda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linvenda
    ADD CONSTRAINT un_mov_linvenda UNIQUE (moviment_id);


--
-- TOC entry 2026 (class 2606 OID 51181)
-- Name: un_moviment_entrdevol; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT un_moviment_entrdevol UNIQUE (moviment_id);


--
-- TOC entry 2022 (class 2606 OID 51183)
-- Name: un_moviment_lincompra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lincompra
    ADD CONSTRAINT un_moviment_lincompra UNIQUE (moviment_id);


--
-- TOC entry 2030 (class 2606 OID 51185)
-- Name: un_moviment_sortdevol; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY linsortidadevolucio
    ADD CONSTRAINT un_moviment_sortdevol UNIQUE (moviment_id);


--
-- TOC entry 2034 (class 2606 OID 51187)
-- Name: un_moviment_transferencia; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lintransferencia
    ADD CONSTRAINT un_moviment_transferencia UNIQUE (moviment_id);


--
-- TOC entry 2010 (class 2606 OID 51189)
-- Name: un_nomclient; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT un_nomclient UNIQUE (nomclient);


--
-- TOC entry 2046 (class 2606 OID 51191)
-- Name: un_nomlocal; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY local
    ADD CONSTRAINT un_nomlocal UNIQUE (nomlocal);


--
-- TOC entry 2062 (class 2606 OID 51193)
-- Name: un_nomproveidor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY proveidor
    ADD CONSTRAINT un_nomproveidor UNIQUE (nomproveidor);


--
-- TOC entry 2051 (class 1259 OID 51428)
-- Name: fki_producte_grup; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_producte_grup ON producte USING btree (productegrup_id);


--
-- TOC entry 2052 (class 1259 OID 51434)
-- Name: fki_producte_subgrup; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_producte_subgrup ON producte USING btree (productesubgrup_id);


--
-- TOC entry 2120 (class 2620 OID 51391)
-- Name: setclientid; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER setclientid BEFORE INSERT OR UPDATE ON client FOR EACH ROW EXECUTE PROCEDURE setclientid();


--
-- TOC entry 2121 (class 2620 OID 51393)
-- Name: setlocalid; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER setlocalid BEFORE INSERT OR UPDATE ON local FOR EACH ROW EXECUTE PROCEDURE setlocalid();


--
-- TOC entry 2122 (class 2620 OID 51395)
-- Name: setproviderid; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER setproviderid BEFORE INSERT OR UPDATE ON proveidor FOR EACH ROW EXECUTE PROCEDURE setproviderid();


--
-- TOC entry 2097 (class 2606 OID 51200)
-- Name: f_sortidadevolucio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linsortidadevolucio
    ADD CONSTRAINT f_sortidadevolucio FOREIGN KEY (sortidadevolucio_id) REFERENCES sortidadevolucio(id_sortidadevol) ON UPDATE CASCADE;


--
-- TOC entry 2108 (class 2606 OID 51305)
-- Name: fk_albara; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linalbara
    ADD CONSTRAINT fk_albara FOREIGN KEY (id_liniaalbara) REFERENCES albara(id_albara) ON UPDATE CASCADE;


--
-- TOC entry 2088 (class 2606 OID 51205)
-- Name: fk_compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lincompra
    ADD CONSTRAINT fk_compra FOREIGN KEY (compra_id) REFERENCES compra(id_compra) ON UPDATE CASCADE;


--
-- TOC entry 2091 (class 2606 OID 51210)
-- Name: fk_entradadevolucio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT fk_entradadevolucio FOREIGN KEY (entradadevolucio_id) REFERENCES entradadevolucio(id_entradadevolucio) ON UPDATE CASCADE;


--
-- TOC entry 2119 (class 2606 OID 51315)
-- Name: fk_id_client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_id_client FOREIGN KEY (client_id) REFERENCES client(id_client) ON UPDATE CASCADE;


--
-- TOC entry 2095 (class 2606 OID 51320)
-- Name: fk_id_client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT fk_id_client FOREIGN KEY (client_id) REFERENCES client(id_client) ON UPDATE CASCADE;


--
-- TOC entry 2086 (class 2606 OID 51325)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY existencies
    ADD CONSTRAINT fk_id_local FOREIGN KEY (local_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2117 (class 2606 OID 51330)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuari
    ADD CONSTRAINT fk_id_local FOREIGN KEY (local_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2106 (class 2606 OID 51335)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linvenda
    ADD CONSTRAINT fk_id_local FOREIGN KEY (origlocal_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2096 (class 2606 OID 51340)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT fk_id_local FOREIGN KEY (destilocal_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2083 (class 2606 OID 51345)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alerta
    ADD CONSTRAINT fk_id_local FOREIGN KEY (local_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2090 (class 2606 OID 51350)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lincompra
    ADD CONSTRAINT fk_id_local FOREIGN KEY (destilocal_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2104 (class 2606 OID 51355)
-- Name: fk_id_localorigen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lintransferencia
    ADD CONSTRAINT fk_id_localorigen FOREIGN KEY (origlocal_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2100 (class 2606 OID 51360)
-- Name: fk_id_localorigen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linsortidadevolucio
    ADD CONSTRAINT fk_id_localorigen FOREIGN KEY (origlocal_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2103 (class 2606 OID 51310)
-- Name: fk_idlocaldesti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lintransferencia
    ADD CONSTRAINT fk_idlocaldesti FOREIGN KEY (destilocal_id) REFERENCES local(id_local) ON UPDATE CASCADE;


--
-- TOC entry 2092 (class 2606 OID 51220)
-- Name: fk_lineaalbaradev; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT fk_lineaalbaradev FOREIGN KEY (lineaalbaradev_id) REFERENCES linalbara(id_liniaalbara) ON UPDATE CASCADE;


--
-- TOC entry 2093 (class 2606 OID 51225)
-- Name: fk_motiuentrdevol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT fk_motiuentrdevol FOREIGN KEY (motiuentrdevol_id) REFERENCES motiudevolucio(id_motiudevolucio) ON UPDATE CASCADE;


--
-- TOC entry 2098 (class 2606 OID 51230)
-- Name: fk_motiusortdevol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linsortidadevolucio
    ADD CONSTRAINT fk_motiusortdevol FOREIGN KEY (motiusortdevol_id) REFERENCES motiudevolucio(id_motiudevolucio) ON UPDATE CASCADE;


--
-- TOC entry 2107 (class 2606 OID 51235)
-- Name: fk_moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linalbara
    ADD CONSTRAINT fk_moviment FOREIGN KEY (moviment_id) REFERENCES moviment(id_moviment) ON UPDATE CASCADE;


--
-- TOC entry 2105 (class 2606 OID 51240)
-- Name: fk_moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linvenda
    ADD CONSTRAINT fk_moviment FOREIGN KEY (moviment_id) REFERENCES moviment(id_moviment) ON UPDATE CASCADE;


--
-- TOC entry 2101 (class 2606 OID 51245)
-- Name: fk_moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lintransferencia
    ADD CONSTRAINT fk_moviment FOREIGN KEY (moviment_id) REFERENCES moviment(id_moviment) ON UPDATE CASCADE;


--
-- TOC entry 2094 (class 2606 OID 51250)
-- Name: fk_moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linentradadevolucio
    ADD CONSTRAINT fk_moviment FOREIGN KEY (moviment_id) REFERENCES moviment(id_moviment) ON UPDATE CASCADE;


--
-- TOC entry 2099 (class 2606 OID 51255)
-- Name: fk_moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY linsortidadevolucio
    ADD CONSTRAINT fk_moviment FOREIGN KEY (moviment_id) REFERENCES moviment(id_moviment) ON UPDATE CASCADE;


--
-- TOC entry 2089 (class 2606 OID 51260)
-- Name: fk_moviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lincompra
    ADD CONSTRAINT fk_moviment FOREIGN KEY (moviment_id) REFERENCES moviment(id_moviment) ON UPDATE CASCADE;


--
-- TOC entry 2114 (class 2606 OID 51265)
-- Name: fk_producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY producteproveidor
    ADD CONSTRAINT fk_producte FOREIGN KEY (producte_id) REFERENCES producte(id_producte) ON UPDATE CASCADE;


--
-- TOC entry 2111 (class 2606 OID 51365)
-- Name: fk_producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY moviment
    ADD CONSTRAINT fk_producte FOREIGN KEY (producte_id) REFERENCES producte(id_producte) ON UPDATE CASCADE;


--
-- TOC entry 2084 (class 2606 OID 51370)
-- Name: fk_producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alerta
    ADD CONSTRAINT fk_producte FOREIGN KEY (producte_id) REFERENCES producte(id_producte) ON UPDATE CASCADE;


--
-- TOC entry 2112 (class 2606 OID 51423)
-- Name: fk_producte_grup; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY producte
    ADD CONSTRAINT fk_producte_grup FOREIGN KEY (productegrup_id) REFERENCES grup(id_grup);


--
-- TOC entry 2113 (class 2606 OID 51429)
-- Name: fk_producte_subgrup; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY producte
    ADD CONSTRAINT fk_producte_subgrup FOREIGN KEY (productesubgrup_id) REFERENCES subgrup(id_subgrup);


--
-- TOC entry 2115 (class 2606 OID 51270)
-- Name: fk_proveidor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY producteproveidor
    ADD CONSTRAINT fk_proveidor FOREIGN KEY (proveidor_id) REFERENCES proveidor(id_proveidor) ON UPDATE CASCADE;


--
-- TOC entry 2085 (class 2606 OID 51275)
-- Name: fk_proveidor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_proveidor FOREIGN KEY (proveidor_id) REFERENCES proveidor(id_proveidor) ON UPDATE CASCADE;


--
-- TOC entry 2109 (class 2606 OID 51280)
-- Name: fk_provincia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY local
    ADD CONSTRAINT fk_provincia FOREIGN KEY (provincia_id) REFERENCES provincia(id_provincia);


--
-- TOC entry 2116 (class 2606 OID 51285)
-- Name: fk_provincia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuari
    ADD CONSTRAINT fk_provincia FOREIGN KEY (provincia_id) REFERENCES provincia(id_provincia) ON UPDATE CASCADE;


--
-- TOC entry 2082 (class 2606 OID 51290)
-- Name: fk_tipusalerta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alerta
    ADD CONSTRAINT fk_tipusalerta FOREIGN KEY (tipusalerta_id) REFERENCES tipusalerta(id_tipusalerta) ON UPDATE CASCADE;


--
-- TOC entry 2110 (class 2606 OID 51295)
-- Name: fk_tipusmoviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY moviment
    ADD CONSTRAINT fk_tipusmoviment FOREIGN KEY (tipusmoviment_id) REFERENCES tipusmoviment(id_tipusmoviment) ON UPDATE CASCADE;


--
-- TOC entry 2081 (class 2606 OID 51375)
-- Name: fk_tipusmoviment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY albara
    ADD CONSTRAINT fk_tipusmoviment FOREIGN KEY (tipusmoviment_id) REFERENCES tipusmoviment(id_tipusmoviment) ON UPDATE RESTRICT;


--
-- TOC entry 2102 (class 2606 OID 51300)
-- Name: fk_transferencia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lintransferencia
    ADD CONSTRAINT fk_transferencia FOREIGN KEY (transferencia_id) REFERENCES transferencia(id_transferencia) ON UPDATE CASCADE;


--
-- TOC entry 2118 (class 2606 OID 51380)
-- Name: idioma_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuari
    ADD CONSTRAINT idioma_fk FOREIGN KEY (idioma_id) REFERENCES idioma(id_idioma) ON UPDATE CASCADE;


--
-- TOC entry 2087 (class 2606 OID 51385)
-- Name: producte_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY existencies
    ADD CONSTRAINT producte_fk FOREIGN KEY (producte_id) REFERENCES producte(id_producte) ON UPDATE CASCADE;


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-12-01 14:17:55

--
-- PostgreSQL database dump complete
--

