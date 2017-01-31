CREATE TABLE user_table(user_id BIGSERIAL NOT NULL , user_name VARCHAR(255), points BIGINT, PRIMARY KEY (user_id));
CREATE TABLE kingdom_table(kingdom_id BIGSERIAL NOT NULL, kingdom_name VARCHAR(255), user_id BIGINT REFERENCES user_table(user_id), PRIMARY KEY (kingdom_id));
CREATE TABLE building_table(building_id BIGSERIAL NOT NULL, kingdom_id BIGINT REFERENCES kingdom_table(kingdom_id), building_type VARCHAR(255), building_level INT, PRIMARY KEY (building_id));
CREATE TABLE resource_table(resource_type VARCHAR(255) NOT NULL , kingdom_id BIGINT REFERENCES kingdom_table(kingdom_id), amount INTEGER, PRIMARY KEY (resource_type, kingdom_id));
CREATE TABLE troop_table(troop_id BIGSERIAL NOT NULL, kingdom_id BIGINT REFERENCES kingdom_table(kingdom_id), troop_level INTEGER, hp INTEGER, PRIMARY KEY (troop_id));
CREATE TABLE timed_event_table(timed_event_id BIGSERIAL, building_id BIGINT REFERENCES building_table(building_id), was_executed BIT, execution_time BIGINT, PRIMARY KEY (timed_event_id));
