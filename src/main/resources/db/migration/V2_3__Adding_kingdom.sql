INSERT INTO user_table (user_id, user_name, password, first_name, last_name, email, enabled, last_pwd_reset_date, points) VALUES (4, 'olaf','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 1000),(5, 'tom','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 1500),(6, 'linda','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC' , 'user', 'user', 'user@user.com', true, current_date, 2000);
INSERT INTO kingdom_table(kingdom_id, kingdom_name, last_update_time, user_id) VALUES (7, 'sweden', 1486986121259, 4 ),(8, 'england', 1486986121259, 5 ),(9, 'france', 1486986121259, 6);
INSERT INTO building_table(building_id, building_type, building_level,  hp, kingdom_id) VALUES (10, 'farm', 1, 100, 7 ),(11, 'mine',1, 100, 7 ),(12, 'barack',2, 100, 7 ),(13, 'townhall', 1, 100, 7 ),(14, 'farm', 2, 100, 8 ),(15, 'mine', 2, 100, 8 ),(16,  'barack', 1, 100, 8 ),(17, 'townhall', 1, 100, 8 ),(18, 'farm', 1, 100, 9 ),(19, 'mine',1, 100,  9 ),(20, 'barack', 1, 100, 9 ),(21, 'townhall',2, 100, 9 );
INSERT INTO resource_table(resource_id, amount, resource_type, kingdom_id) VALUES (22, 150, 'food', 7),(23, 150, 'gold', 7),(24, 200, 'food', 8),(25, 200, 'gold', 8),(26, 250, 'food', 9),(27, 250, 'gold', 9);
INSERT INTO troop_table(troop_id, attack, defense, hp, troop_level, kingdom_id) VALUES (28, 10, 5, 20, 1, 7),(29, 10, 5, 20, 2, 7),(30, 10, 5, 20, 2, 7),(31, 10, 5, 20, 3, 7),(32, 10, 5, 20, 1, 7),(33, 10, 5, 20, 1, 8),(34, 10, 5, 20, 3, 8),(35, 10, 5, 20, 3, 8),(36, 10, 5, 20, 2, 8),(37, 10, 5, 20, 1, 8),(38, 10, 5, 20, 2, 9),(39, 10, 5, 20, 2, 9),(40, 10, 5, 20, 2, 9),(41, 10, 5, 20, 3, 9),(42, 10, 5, 20, 3, 9);