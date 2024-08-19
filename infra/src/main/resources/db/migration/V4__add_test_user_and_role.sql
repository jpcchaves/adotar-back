INSERT INTO users (id, first_name, last_name, email, password)
VALUES (nextval('seq_user'), 'Test', 'Test', 'testesadotar@outlook.com', 'test');

INSERT INTO roles (id, name)
VALUES (nextval('seq_role'), 'ROLE_TEST');

INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE email = 'testesadotar@outlook.com'),
    (SELECT id FROM roles WHERE name = 'ROLE_TEST')
);
