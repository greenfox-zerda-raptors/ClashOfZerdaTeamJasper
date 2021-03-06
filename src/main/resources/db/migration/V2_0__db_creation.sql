CREATE TABLE user_table(user_id BIGSERIAL NOT NULL, user_name VARCHAR(50) NOT NULL, password VARCHAR(100) NOT NULL, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, enabled BOOLEAN NOT NULL, last_pwd_reset_date DATE NOT NULL, points INTEGER NOT NULL, PRIMARY KEY(user_id));
CREATE TABLE authority_table (authority_id BIGSERIAL NOT NULL, authority_name VARCHAR(50) NOT NULL, PRIMARY KEY (authority_id));
CREATE TABLE user_authority_table(user_id BIGINT REFERENCES user_table(user_id), authority_id BIGINT REFERENCES authority_table(authority_id), PRIMARY KEY (user_id, authority_id));
CREATE TABLE kingdom_table(kingdom_id BIGSERIAL NOT NULL, kingdom_name VARCHAR(255), user_id BIGINT REFERENCES user_table(user_id), last_update_time BIGINT, pos_x INTEGER, pos_y INTEGER, PRIMARY KEY (kingdom_id));
CREATE TABLE building_table(building_id BIGSERIAL NOT NULL, kingdom_id BIGINT REFERENCES kingdom_table(kingdom_id), building_type VARCHAR(255), building_level INTEGER, hp INTEGER, levelup_time BIGINT, PRIMARY KEY (building_id));
CREATE TABLE resource_table(resource_id BIGSERIAL NOT NULL, resource_type VARCHAR(255) NOT NULL , kingdom_id BIGINT REFERENCES kingdom_table(kingdom_id), amount DOUBLE PRECISION, PRIMARY KEY (resource_id));
CREATE TABLE troop_table(troop_id BIGSERIAL NOT NULL, attack INTEGER, defense INTEGER, hp INTEGER, troop_level INTEGER, upgrade_time BIGINT, kingdom_id BIGINT REFERENCES kingdom_table(kingdom_id), PRIMARY KEY (troop_id));
CREATE TABLE timed_event_table(dtype VARCHAR(255) NOT NULL, timed_event_id BIGSERIAL NOT NULL, execution_time BIGINT NOT NULL, was_executed INTEGER, kingdom_id BIGINT, building_id BIGINT, troop_id BIGINT, attacker_id BIGINT, defender_id BIGINT, PRIMARY KEY (timed_event_id));
CREATE TABLE battle_result_table(battle_id BIGSERIAL, attacker_kingdom BIGINT REFERENCES kingdom_table(kingdom_id), defender_kingdom BIGINT REFERENCES kingdom_table(kingdom_id), attacker_attack_power INTEGER, defender_attack_power INTEGER, attacker_defense_power INTEGER, defender_defense_power INTEGER, PRIMARY KEY (battle_id));
CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 200
  CACHE 1;
--ALTER TABLE hibernate_sequence;
 -- OWNER TO postgres;
CREATE SEQUENCE user_seq
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 50
  CACHE 1;
ALTER TABLE user_table ALTER COLUMN user_id SET DEFAULT nextval('user_seq');
CREATE SEQUENCE kingdom_seq
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 100
  CACHE 1;
ALTER TABLE kingdom_table ALTER COLUMN kingdom_id SET DEFAULT nextval('kingdom_seq');
