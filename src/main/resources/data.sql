insert into user_details(id,birth_date,name)
values(10001,current_date(),'Jack');

insert into user_details(id,birth_date,name)
values(10002,current_date(),'Jill');

insert into user_details(id,birth_date,name)
values(10003,current_date(),'John');

insert into user_details(id,birth_date,name)
values(10004,current_date(),'Jenny');

insert into post(id,description,user_id)
values(20001,'I passed my Spring Certification',10001);

insert into post(id,description,user_id)
values(20002,'I passed my Java Certification',10001);

insert into post(id,description,user_id)
values(20003,'I passed my Java Certification',10002);

insert into post(id,description,user_id)
values(20004,'I passed my Java Certification',10003);

insert into post(id,description,user_id)
values(20005,'I passed my Web Service Certification',10004);