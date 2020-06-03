-- Database: Create initial data
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id bigserial primary key,
    name varchar(100) NOT NULL,
    role integer NOT NULL,
    phone varchar(12) NOT NULL,
    email varchar(100) NOT NULL
);

INSERT INTO users (name, role, phone, email)
VALUES
   ('Charles Xavier', 1, '+79111111111', 'ProfessorX@mail.ru'),
   ('Eric Lensherr', 1, '+79111111111', 'Magneto@mail.ru'),
   ('Logan', 2, '+79009998877', 'Wolverine@mail.ru'),
   ('Peter Parker', 2, '+79100002233', 'Spidy@mail.ru'),
   ('Wade Wilson', 2, '+79997778866', 'Deadpool@mail.ru'),
   ('Raven Darkholme', 2, '+79008889922', 'Mystic@mail.ru');

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id integer primary key,
    description varchar(100)
);

INSERT INTO roles (id, description)
VALUES
    (0, 'Administrator'),
    (1, 'Professor'),
    (2, 'Student');

DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles (
                       id bigserial primary key,
                       user_id integer NOT NULL,
                       role_id integer NOT NULL
);

INSERT INTO user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2);
