CREATE DATABASE IF NOT EXISTS BOOKSHOP DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS bs_users;
CREATE TABLE bs_users (
  id INT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(255) NOT NULL DEFAULT '',
  lastname VARCHAR(255) NOT NULL DEFAULT '',
  email VARCHAR(255) NOT NULL DEFAULT '',
  mobilephone VARCHAR(255) NOT NULL DEFAULT '',
  address VARCHAR(255) NOT NULL DEFAULT '',
  password VARCHAR(255) NOT NULL DEFAULT '',
  regtime TIMESTAMP NOT NULL DEFAULT NOW(),
  logintime TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS bs_categories;
CREATE TABLE bs_categories (
  category_id INT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  categor_name VARCHAR(100) NOT NULL DEFAULT '',
  PRIMARY KEY (category_id)
);

DROP TABLE IF EXISTS bs_books;
CREATE TABLE bs_books (
  book_id INT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL DEFAULT '',
  category_id INT(5) UNSIGNED NULL,
  publisher VARCHAR(255) NOT NULL DEFAULT '',
  authors VARCHAR(255) NOT NULL DEFAULT '',
  sale_type VARCHAR(255) NOT NULL DEFAULT '',
  price DECIMAL(8,2) UNSIGNED NOT NULL DEFAULT '0.0',
  saleprice DECIMAL(8,2) UNSIGNED NOT NULL DEFAULT '0.0',
  saledate  DATE NULL,
  introduction VARCHAR(255) NOT NULL DEFAULT '',
  quantity INT(5) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (book_id)
);

DROP TABLE IF EXISTS bs_cart;
CREATE TABLE bs_cart (
  user_id INT(8) UNSIGNED NULL,
  book_id INT(8) UNSIGNED NULL,
  quantity INT(5) UNSIGNED NOT NULL DEFAULT 0,
  fee DECIMAL(8,2) UNSIGNED NOT NULL DEFAULT '0.0'
);

DROP TABLE IF EXISTS bs_order;
CREATE TABLE bs_order (
  order_number VARCHAR(50) NOT NULL DEFAULT '',
  order_time TIMESTAMP NOT NULL DEFAULT NOW(),
  user_id INT(8) UNSIGNED NULL,
  book_id INT(8) UNSIGNED NULL,
  quantity INT(5) UNSIGNED NOT NULL DEFAULT 0,
  fee DECIMAL(8,2) UNSIGNED NOT NULL DEFAULT '0.0'
);

DROP TABLE IF EXISTS bs_comments;
CREATE TABLE bs_comments (
  id INT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT(8) UNSIGNED NULL,
  book_id INT(8) UNSIGNED NULL,
  firstname VARCHAR(255) NOT NULL DEFAULT '',
  createdtime TIMESTAMP NOT NULL DEFAULT NOW(),
  content VARCHAR(512) NOT NULL DEFAULT '',
  upvote_count INT(8) UNSIGNED NULL,
  PRIMARY KEY (id)
);

INSERT INTO bs_categories (categor_name)
VALUES
  ('Arts & Photography'),
  ('Biographies'),
  ('Business'),
  ('Children’s Book'),
  ('Cookbooks'),
  ('History'),
  ('Literature'),
  ('Mystery'),
  ('Romance'),
  ('Sci - Fi'),
  ('Teens');
  

INSERT INTO bs_books (title, authors, category_id, price, saledate, publisher)
VALUES
  ('Steven Universe: End of an Era', 'Chris McDonnell', 1, 7.50, "2020-09-01", "Alfred A. Knopf"),
  ('How to Write One Song', 'Jeff Tweedy', 1, 6.20, "2020-05-21", 'Victor Gollancz'),
  ('The Anatomy Coloring Book', 'Wynn Kapit', 1, 17.60, "2019-02-09", 'Henry Holt and Company'),
  ('Barnyard Dance!', 'Sandra Boynton', 1, 6.29, "2018-09-01", "Alfred A. Knopf"),
  ('Humans', 'Brandon Stanton', 1, 11.07, "2015-12-01", 'Victor Gollancz'),
  ('My Own Words', 'Ruth Bader Ginsburg', 2, 20.99, "2016-09-01", 'Henry Holt and Company'),
  ('How to Be an Antiracist', 'Ibram X. Kendi', 2, 13.70, "2017-08-01", "Alfred A. Knopf"),
  ('Between the World and Me', 'Ta-Nehisi Coates', 2, 9.17, "2018-06-01", 'Henry Holt and Company'),
  ('Be Water, My Friend', 'Shannon Lee', 2, 9.99, "2019-04-01", 'Victor Gollancz'),
  ("Nala's World", 'Dean Nicholson', 2, 13.70, "2017-08-01", "Alfred A. Knopf");

  

INSERT INTO bs_books (title, authors, category_id, price, saledate, publisher)
VALUES
  ('The Book Thief', 'Markus Zusak', 4, 7.50, "2020-09-01", "Alfred A. Knopf"),
  ('I Am the Messenger', 'Brandon Sanderson', 4, 6.20, "2020-05-21", 'Victor Gollancz'),
  ('Words of Radiance', 'Brandon Sanderson', 8, 17.60, "2019-02-09", 'Henry Holt and Company'),
  ('A Memory of Light', 'Brandon Sanderson', 8, 6.29, "2018-09-01", "Alfred A. Knopf"),
  ('Steelheart', 'Brandon Sanderson', 8, 11.07, "2015-12-01", 'Victor Gollancz'),
  ('Duty: Memoirs of a Secretary at War', 'Robert Gates', 6, 20.99, "2016-09-01", 'Henry Holt and Company'),
  ('From the Shadows', 'Robert Gates', 6, 13.70, "2017-08-01", "Alfred A. Knopf"),
  ('In a Fishbone Church', 'Catherine Chidgey', 7, 9.17, "2018-06-01", 'Henry Holt and Company'),
  ('The Transformation', 'Catherine Chidgey', 7, 9.99, "2019-04-01", 'Victor Gollancz');
