insert into "public"."roles" ("name")
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into "public"."users" ("created_at", "deleted_at", "email", "first_name", "is_active", "is_admin",
                              "last_name", "password", "updated_at", "username")
values ('2023-06-28', DEFAULT, 'adotar@admin.com', 'Admin', true, true, 'Adotar',
        '$2a$08$Q70R5ayL294S4fYRfnQE/eCWs81SDDKvg3cZMC4lyI9yhWJcmqN5y', '2023-06-28', 'admin'),
       ('2023-06-28 00:00:00', NULL, 'root@root.com', 'Root', true, false, 'Root',
        '$2a$08$Q70R5ayL294S4fYRfnQE/$2a$08$j8yet9Cv4m4UKNXFHOjcSuRpOO/.Hm6aXv6rc0Ks01mZ9RoBFIj5O',
        '2023-06-28 00:00:00', 'root');

insert into "public"."users_roles" ("role_id", "user_id")
values ('1', '1'),
       ('2', '2');