insert into users
values('ADMIN1','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3001);
insert into users
values('PATIENT001','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3002);
insert into users
values('PATIENT002','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3007);
insert into users
values('DOCTOR001','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3004);
insert into users
values('NURSE001','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3005);
insert into users
values('EM001','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3006);
insert into users
values('PHARM001','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3003);
insert into users
values('DOCTOR002','$2a$10$b5z.eBOHOkPHwSdGEWBGquGvvMjpKqSZwghc2ag7mpOt4LDTSRmSe',3008);

insert into role
values(1,'ADMIN');
insert into role
values(2,'PATIENT');
insert into role
values(3,'DOCTOR');
insert into role
values(4,'NURSE');
insert into role
values(5,'EMPLOYER');
insert into role
values(6,'PHARMACY');

insert into users_role
values('ADMIN1',1);
insert into users_role
values('PATIENT001', 2);
insert into users_role
values('PATIENT002', 2);
insert into users_role
values('DOCTOR001', 3);
insert into users_role
values('NURSE001', 4);
insert into users_role
values('EM001', 5);
insert into users_role
values('PHARM001', 6);
insert into users_role
values('DOCTOR002', 3);