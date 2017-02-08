DROP TABLE timed_event_table;
CREATE TABLE mainevent_table(dtype VARCHAR(255) NOT NULL, mainevent_id BIGSERIAL NOT NULL, execution_time BIGINT NOT NULL, was_executed BIT NOT NULL, building_id BIGINT, troop_id BIGINT, attacker_id BIGINT, troop BIGINT, defender_id BIGINT, PRIMARY KEY (mainevent_id));

