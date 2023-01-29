INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (1,'GB350',800,1,'空冷',550000,'エンジン音がいい','01',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (2,'Z900RS',800,4,'水冷',1260000,'紳士的','02',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (3,'W800 CAFE',790,2,'水冷',1100000,'スポーツタイプ','02',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (4,'YZF-R1',100,4,'水冷',1500000,'見た目かっこいい','03',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (5,'Rebel250',690,1,'水冷',599500,'見た目クルーザー','01',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (6,'Rebel500',690,2,'水冷',799700,'まだよくわからない','01',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (7,'SR400 Final Edition',790,1,'空冷',605000,'エンジンのトルク感がいい','03',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (8,'Z900RS CAFE',820,4,'水冷',1290000,'後部シートが小さい','02',1,now(),null);
INSERT INTO m_motorcycle(moto_no,moto_name,seat_height,cylinder,cooling,price,comment,brand_id,version,ins_dt,upd_dt)VALUES (9,'V7 III Racer 10th ANNIVERSARY',770,2,'空冷',1260000,'珍しい見た目','05',1,now(),null);

INSERT INTO m_brand (brand_id, brand_name) VALUES ('01', 'HONDA');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('02', 'Kawasaki');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('03', 'YAMAHA');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('04', 'Suzuki');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('05', 'moto guzzi');

-- ユーザーのデータ
INSERT INTO m_user (username, password) VALUES ('test', '$2a$10$6nxIrFGcF1SlLq1B5P/Bn.vKBjM4K8/OqqS9C.F6JpI4OvgZvoNf6');
INSERT INTO m_user (username, password) VALUES ('admin', '$2a$10$6nxIrFGcF1SlLq1B5P/Bn.vKBjM4K8/OqqS9C.F6JpI4OvgZvoNf6');
