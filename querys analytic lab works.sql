/** esquema de prueba*/
create table Concessionaire(id integer, name varchar(100));
insert into Concessionaire(id, name) values(1, "Concessionaire 1");
insert into Concessionaire(id, name) values(1, "Concessionaire 2");
insert into Concessionaire(id, name) values(1, "Concessionaire 3");
create table Bus(id integer, type varchar(100), motor varchar(100), brakes varchar(100), concessionaireId integer);
insert into Bus(id, type, motor, brakes, concessionaireId) values(1,"Bi-articulado", "Diesel","Air-brakes",1);
insert into Bus(id, type, motor, brakes, concessionaireId) values(2,"Bi-articulado", "Diesel","Air-brakes",2);
insert into Bus(id, type, motor, brakes, concessionaireId) values(3,"Bi-articulado", "Diesel","Air-brakes",3);
insert into Bus(id, type, motor, brakes, concessionaireId) values(4,"Bi-padron", "Gas","Air-brakes",3);
insert into Bus(id, type, motor, brakes, concessionaireId) values(5,"Bi-articulado", "Gas","Air-brakes",2);
insert into Bus(id, type, motor, brakes, concessionaireId) values(6,"Bi-articulado", "Gas","Carbon Disk 65'",1);
insert into Bus(id, type, motor, brakes, concessionaireId) values(7,"Bi-padron", "Electric","Carbon Disk 65'",2);
insert into Bus(id, type, motor, brakes, concessionaireId) values(8,"Bi-articulado", "Electric","Air-brakes",3);
insert into Bus(id, type, motor, brakes, concessionaireId) values(9,"Bi-articulado", "Hybrid","Air-brakes",1);
insert into Bus(id, type, motor, brakes, concessionaireId) values(10,"Bi-articulado", "Hybrid","Air-brakes",1);
insert into Bus(id, type, motor, brakes, concessionaireId) values(11,"Bi-padron", "Hybrid","Air-brakes",1);
insert into Bus(id, type, motor, brakes, concessionaireId) values(12,"Bi-articulado", "Hybrid","Air-brakes",1);
create table DeviceType(id integer, name varchar(100));
insert into DeviceType(id, name) values(1, "CANBUS");
insert into DeviceType(id, name) values(2, "NVR");
create table Device(id integer, ip varchar(50), deviceTypeId integer, busId integer, status varchar(100));
insert into Device(id,ip,deviceTypeId,busId,status) values (1,"172.10.116.19",2,1,"Inactive");
insert into Device(id,ip,deviceTypeId,busId,status) values (2,"172.10.116.36",2,2,"Active");
insert into Device(id,ip,deviceTypeId,busId,status) values (3,"172.10.116.20",2,3,"Active");
insert into Device(id,ip,deviceTypeId,busId,status) values (4,"172.10.116.100",2,4,"Inactive");
insert into Device(id,ip,deviceTypeId,busId,status) values (5,"172.10.119.50",2,5,"Inactive");
insert into Device(id,ip,deviceTypeId,busId,status) values (6,"172.10.119.45",2,6,"Active");
insert into Device(id,ip,deviceTypeId,busId,status) values (7,"172.10.200.32",2,7,"Inactive");
insert into Device(id,ip,deviceTypeId,busId,status) values (8,"172.10.56.33",2,8,"Active");
insert into Device(id,ip,deviceTypeId,busId,status) values (9,'172.10.65.100',2,9,"Active");
insert into Device(id,ip,deviceTypeId,busId,status) values (10,'172.10.58.66',2,10,"Active");
insert into Device(id,ip,deviceTypeId,busId,status) values (11,'172.10.65.3',2,11,"Inactive");
insert into Device(id,ip,deviceTypeId,busId,status) values (12,'172.10.65.3',2,12,"Active");

/**¨Punto a  	*/
select a.* from Bus a inner join Concessionaire b on a.concessionaireId = b.id where b.name = 'Concessionaire 1';

/** Punto b 	*/
select a.* from Device a inner join DeviceType b on a.deviceTypeId = b.id inner join Bus c on a.busId = c.id where c.type = 'Bi-articulado'; 

/** Punto c 	*/
select a.status, '-', count(*) from Device a inner join Bus b on a.busId = b.id group by a.status;

select a.status, b.motor, count(*) as total from Device a inner join Bus b on a.busId = b.id group by a.status, b.motor order by a.status;

select status as estado, ' - ' as motor, count(*) as total from Device group by status having status = 'Active'
union all
select '-' as estado, a.motor as motor, count(*) as total from Bus as a inner join Device as b on a.id = b.busId where b.status = 'Active' group by a.motor
union all
select status as estado, ' - ' as motor, count(*) as total from Device group by status having status = 'Inactive'
union all
select '-' as estado, a.motor as motor, count(*) as total from Bus as a inner join Device as b on a.id = b.busId where b.status = 'Inactive' group by a.motor;