INSERT INTO roles
    (id, "name")
VALUES (nextval('seq_role'), 'ROLE_USER'),
       (nextval('seq_role'), 'ROLE_ADMIN');