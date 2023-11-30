insert into users
(
   FIRST_NAME,
   LAST_NAME,
   EMAIL,
   PASSWORD
)
values
(   
   'Petra',
   'Meyer',
   'Petra@Meyer.de',
   '$2a$10$oEIF1UxQGGeyabMpLozetesFxEji9NFi6JUrUrBbjZGfVADOkPVpC'
);

insert into roles
(
   USER_ID,
   ROLE
)
values
(
   (
      select
         id
      from
         users
      where email =
         'Petra@Meyer.de'
   ),
   'ROLE_ADMIN'
);

insert into users
(  
   FIRST_NAME,
   LAST_NAME,
   EMAIL,
   PASSWORD
)
values
(   
   'Frank',
   'Schuhmacher',
   'Frank@Schuhmacher.de',
   '$2a$10$UnxwBlVyptkR4zizSuvbY.dbMVaWi90aUAyqRqybiw/f5v4/QJtPy'
);
insert into roles
(
   USER_ID,
   ROLE
)
values
(
   (
      select
         id
      from
         users
      where email =
         'Frank@Schuhmacher.de'
   ),
   'ROLE_USER'
);