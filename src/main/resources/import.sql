INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', TRUE, current_date, 0);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', TRUE, current_date, 0);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', FALSE, current_date, 0);
INSERT INTO authority_table (authority_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO authority_table (authority_id, authority_name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_authority_table (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (3, 1);

INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset, points) VALUES
  (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com',
   TRUE, current_date, 0);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset, points) VALUES
  (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', TRUE, current_date, 0);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset, points) VALUES
  (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', FALSE, current_date, 0);
INSERT INTO authority_table (authority_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO authority_table (authority_id, authority_name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_authority_table (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (3, 1);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES (4, 'olaf','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 1000),(5, 'tom','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 1500),(6, 'linda','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 2000);
INSERT INTO kingdom_table(kingdom_id, kingdom_name, last_update_time, user_id) VALUES (7, 'sweden', 1486986121259, 4 ),(8, 'england', 1486986121259, 5 ),(9, 'france', 1486986121259, 6);
INSERT INTO building_table(building_id, hp, building_level, building_type, kingdom_kingdom_id) VALUES (10, 100, 1, 'farm', 7 ),(11, 100, 1, 'mine', 7 ),(12, 100, 2, 'barack', 15 ),(13, 100, 1, 'townhall', 7 ),(14, 100, 2, 'farm', 8 ),(15, 100, 2, 'mine', 8 ),(16, 100, 1, 'barack', 8 ),(17, 100, 1, 'townhall', 8 ),(18, 100, 1, 'farm', 9 ),(19, 100, 1, 'mine', 9 ),(20, 100, 1, 'barack', 9 ),(21, 100, 2, 'townhall', 9 );
INSERT INTO resource_table(resource_id, amount, resource_type, kingdom_kingdom_id) VALUES (22, 150, 'food', 7),(23, 150, 'gold', 7),(24, 200, 'food', 8),(25, 200, 'gold', 8),(26, 250, 'food', 9),(27, 250, 'gold', 9);
INSERT INTO troop_table(troop_id, attack, defense, hp, troop_level, kingdom_kingdom_id) VALUES (28, 10, 5, 20, 1, 15),(29, 10, 5, 20, 2, 7),(30, 10, 5, 20, 2, 7),(31, 10, 5, 20, 3, 7),(32, 10, 5, 20, 1, 7),(33, 10, 5, 20, 1, 8),(34, 10, 5, 20, 3, 8),(35, 10, 5, 20, 3, 8),(36, 10, 5, 20, 2, 8),(37, 10, 5, 20, 1, 8),(38, 10, 5, 20, 2, 9),(39, 10, 5, 20, 2, 9),(40, 10, 5, 20, 2, 9),(41, 10, 5, 20, 3, 9),(42, 10, 5, 20, 3, 9);