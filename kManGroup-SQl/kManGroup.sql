
use kManGroup_gillon;

drop table jobRoles;

create table jobRoles (
jobRoleID int auto_increment NOT NULL,
jobRole varchar(150) NOT NULL,
CONSTRAINT pk_Job_Role_ID PRIMARY KEY (jobRoleID));

insert into jobRoles(jobRole) values('Software Engineer');
insert into jobRoles(jobRole) values('Front-End Engineer');
insert into jobRoles(jobRole) values('Test Engineer');

select * from jobRoles;


