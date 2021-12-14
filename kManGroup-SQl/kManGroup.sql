DROP DATABASE kManGroup_gillon;
CREATE DATABASE kManGroup_gillon;

use kManGroup_gillon;

create table jobRoles (
jobRoleID int auto_increment NOT NULL,
jobRole varchar(150) NOT NULL,
jobSpec text NOT NULL,
CONSTRAINT pk_Job_Role_ID PRIMARY KEY (jobRoleID));

insert into jobRoles(jobRole, jobSpec) values('Software Engineer', 'As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.');
insert into jobRoles(jobRole, jobSpec) values('Front-End Engineer', 'As a Front-end Engineer in Kainos, you will have the opportunity to use your expertise in developing high quality user interface solutions which delight our customers and impact the lives of users worldwide.   The projects you will join are varied, and often highly visible. You will be working in fast-paced, agile environments, so it is important for you to make sound, reasoned decisions, and recommendations on front-end and user interfaces with your colleagues. You are determined, flexible and always constructive; proactive in improving things and are always inclusive and respectful in your interactions with your team. You will be working alongside talented, diverse, enthusiastic colleagues, who will help you learn and develop as you, in turn, mentor those around you.');
insert into jobRoles(jobRole, jobSpec) values('Test Engineer', 'As a Test Engineer (Associate) in Kainos, you’ll work within a multi-skilled agile team, developing and executing functional automated and manual tests to help the team deliver working application software that meets user needs. You’ll do this whilst learning about new technologies and approaches, with talented colleagues who will help you learn, develop and grow.');


