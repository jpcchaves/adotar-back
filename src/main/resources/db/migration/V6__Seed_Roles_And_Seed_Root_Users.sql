insert into "public"."roles" ("name")
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into "public"."users" ("created_at", "deleted_at", "email", "first_name", "is_active", "is_admin",
                              "last_name", "password", "updated_at", "username")
values ('2023-06-28', DEFAULT, 'adotar@admin.com', 'Admin', true, true, 'Adotar',
        '$2a$12$dESCyPL02lbPBH4Mt8Pc5.iwmq/Gmw6fAh/i1RukJG76aS0MMbDz6', '2023-06-28', 'admin'),
       ('2023-06-28 00:00:00', NULL, 'root@root.com', 'Root', true, false, 'Root',
        '$2a$12$dESCyPL02lbPBH4Mt8Pc5.iwmq/Gmw6fAh/i1RukJG76aS0MMbDz6',
        '2023-06-28 00:00:00', 'root');

insert into "public"."users_roles" ("role_id", "user_id")
values ('1', '1'),
       ('2', '2');