INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES
  (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com',
   TRUE, current_date, 0);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES
  (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', TRUE, current_date, 0);
INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES
  (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', FALSE, current_date, 0);
INSERT INTO authority_table (authority_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO authority_table (authority_id, authority_name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_authority_table (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority_table (user_id, authority_id) VALUES (3, 1);

INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES (12, 'olaf','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 1000),(13, 'tom','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 1500),(14, 'linda','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 2000);
INSERT INTO kingdom_table(kingdom_id, kingdom_name, last_update_time, user_id) VALUES (15, 'sweden', 1486986121259, 12 ),(16, 'england', 1486986121259, 13 ),(17, 'france', 1486986121259, 14 );
INSERT INTO building_table(building_id, hp, building_level, building_type, kingdom_kingdom_id) VALUES (18, 100, 1, 'farm', 15 ),(19, 100, 1, 'mine', 15 ),(20, 100, 2, 'barack', 15 ),(21, 100, 1, 'townhall', 15 ),(22, 100, 2, 'farm', 16 ),(23, 100, 2, 'mine', 16 ),(24, 100, 1, 'barack', 16 ),(25, 100, 1, 'townhall', 16 ),(26, 100, 1, 'farm', 17 ),(27, 100, 1, 'mine', 17 ),(28, 100, 1, 'barack', 17 ),(29, 100, 2, 'townhall', 17 );
INSERT INTO resource_table(resource_id, amount, resource_type, kingdom_kingdom_id) VALUES (30, 150, 'food', 15),(31, 150, 'gold', 15),(32, 200, 'food', 16),(33, 200, 'gold', 16),(34, 250, 'food', 17),(35, 250, 'gold', 17);
INSERT INTO troop_table(troop_id, attack, defense, hp, troop_level, kingdom_kingdom_id) VALUES (36, 10, 5, 20, 1, 15),(37, 10, 5, 20, 2, 15),(38, 10, 5, 20, 2, 15),(39, 10, 5, 20, 3, 15),(40, 10, 5, 20, 1, 15),(41, 10, 5, 20, 1, 16),(42, 10, 5, 20, 3, 16),(43, 10, 5, 20, 3, 16),(44, 10, 5, 20, 2, 16),(45, 10, 5, 20, 1, 16),(46, 10, 5, 20, 2, 17),(47, 10, 5, 20, 2, 17),(48, 10, 5, 20, 2, 17),(49, 10, 5, 20, 3, 17),(50, 10, 5, 20, 3, 17);