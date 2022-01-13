DROP DATABASE kManGroup_gillon;
CREATE DATABASE kManGroup_gillon;

use kManGroup_gillon;

create table bandLevels (
jobBandLevelID int auto_increment PRIMARY KEY NOT NULL,
jobBandLevel varchar(50) NOT NULL,
importance int NOT NULL
);

create table users (
username varchar(60) PRIMARY KEY,
passwordHash varchar(128) NOT NULL,
salt varchar(128) NOT NULL,
isAdmin BOOLEAN NOT NULL
);

INSERT INTO users VALUES ("admin","2Iy7caZut0pNFtG2oUbZbQ==" ,"o2BesGj8elWLslkVfdT5n5MbDvD3AxNbAgwwFBaGUCYfs6S45mHhdT1/sOVa53qfrxR49coy0YzXAH99jl9XvA==",true);
INSERT INTO users VALUES ("employee","cFHpC1WqVAMiVxIF3iCi/Q==" ,"WBFTDwP5p4R0bEyAmFy1hFPI5XQyjIz3+vLjkMtHOYVsyVhMgS3p50C1hUPxNHq6OKKx+771APpFHYgU4GEYGg==",false);

create table training (
trainingID int auto_increment PRIMARY KEY NOT NULL,
trainingName varchar(100) NOT NULL,
trainingLink text NOT NULL,
trainingGroup varchar(100) NOT NULL
);

create table bandLevelsTraining (
jobBandLevelID int NOT NULL,
trainingID int NOT NULL,

PRIMARY KEY(jobBandLevelID, trainingID),
CONSTRAINT fk_jobBandLevelIDtraining FOREIGN KEY (jobBandLevelID) REFERENCES bandLevels(jobBandLevelID),
CONSTRAINT fk_trainingID FOREIGN KEY (trainingID) REFERENCES training(trainingID)
);

create table capabilities (
jobCapabilityID int auto_increment PRIMARY KEY NOT NULL,
jobCapability varchar(50) NOT NULL
);

create table competenciesData(
competencyDataID int Primary Key Not Null,
competencyStage text NOT NULL
);


create table capabilityLead(
leadID int Primary Key Not Null,
leadFname varchar(25) Not Null,
leadSname varchar(30) Not Null,
leadPhoto text Not Null,
leadMessage text Not Null,
jobCapabilityID int not null,

CONSTRAINT fk_jobCapabilityID_capabilityLead FOREIGN KEY (jobCapabilityID) REFERENCES capabilities(jobCapabilityID)					
);

create table jobFamilies(
jobFamilyID int auto_increment PRIMARY KEY NOT NULL,
jobCapabilityID int NOT NULL,
jobFamilyName varchar(150) NOT NULL,

CONSTRAINT fk_jobCapabilityID_JobFamilies FOREIGN KEY (jobCapabilityID) REFERENCES capabilities(jobCapabilityID)
);

create table jobRoles (
jobRoleID int auto_increment NOT NULL,
jobRole varchar(150) NOT NULL,
jobBandLevelID int NOT NULL,
jobSpec text NOT NULL,
jobLink text NOT NULL,
jobResponsibilities text NOT NULL,
jobFamilyID int NOT NULL,

CONSTRAINT pk_Job_Role_ID PRIMARY KEY (jobRoleID),
CONSTRAINT fk_JobBandLevelID FOREIGN KEY (jobBandLevelID) REFERENCES bandLevels(jobBandLevelID),
CONSTRAINT fk_JobFamilyid_JobRoles FOREIGN KEY (jobFamilyID) REFERENCES jobFamilies(jobFamilyID));


create table competencies (
jobBandLevelID int NOT NULL,
competencyDataID int Not Null
);

insert into bandLevels(jobBandLevel,importance) values('Apprentice',8);
insert into bandLevels(jobBandLevel,importance) values('Trainee',7);
insert into bandLevels(jobBandLevel,importance) values('Associate',6);
insert into bandLevels(jobBandLevel,importance) values('Senior Associate',5);
insert into bandLevels(jobBandLevel,importance) values('Consultant',4);
insert into bandLevels(jobBandLevel,importance) values('Manager',3);
insert into bandLevels(jobBandLevel,importance) values('Principal',2);
insert into bandLevels(jobBandLevel,importance) values('Leadership Community',1);

insert into capabilities(jobCapability) values('Engineering');
insert into capabilities(jobCapability) values('Cyber Security');
insert into capabilities(jobCapability) values('Data');
insert into capabilities(jobCapability) values('Operations');
insert into capabilities(jobCapability) values('Workday');
insert into capabilities(jobCapability) values('Business Development and Marketing');
insert into capabilities(jobCapability) values('Artificial Intelligence');
insert into capabilities(jobCapability) values('Delivery');

-- Engineering Families
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Engineering ​Strategy and Planning',1);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Engineering',1);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Architecture',1);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Testing and Quality Assurance',1);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Product Specialist',1); -- 5

-- Cyber sec families
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Security Strategy and Planning',2);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Security Engineering',2);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Corporate Security',2); -- 8

insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Data Engineering',3);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Integrations',4);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Operational Management',5);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Business Development',6);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Artificial Intelligence (AI) Engineering',7);
insert into jobFamilies(jobFamilyName,jobCapabilityID) values('Delivery Strategy & Planning',8);





insert into competenciesData (competencyDataID, competencyStage) values(1, "Reflects on how factors such as own values, prejudices and emotions influences own judgement");
insert into competenciesData (competencyDataID, competencyStage) values(2,"behaviour, and self-belief. Uses feedback from appraisals and other sources to consider personal impact and changes behaviour. Understands personal sources of stress and well being.");
insert into competenciesData (competencyDataID, competencyStage) values(3,"Plans and manages own time effectively and fulfils work requirements and commitments to a high standard, without compromising own health and well being. Remains calm and focused under pressure.");
insert into competenciesData (competencyDataID, competencyStage) values(4,"Proactively manages self and career and identifies personal learning needs plan and completes a broad range of formal and informal learning opportunities by taking responsibility for own personal development and seeking opportunities for learning. Strives to put learning into practice.Clearly demonstrates that Encourages a broad range of formal and informal learning opportunities, supporting development and career management by putting self forward for challenging assignments and projects which will develop strengths and address development areas. Seeks and provides constructive feedback from a variety of sources to support personal growth.Acts as an exemplar for others in managing continuous personal development. Facilitates the development of a learning culture.Identifies tangible, objective business improvements and benefits when planning development activities and identifies and tracks these through metrics, to demonstrate impact on business results.Role models continuous learning and self-development, evaluating own effectiveness and growth and planning further development. Progresses in the right direction for self and Kainos through strong personal ownership.Develops through systematically scanning the external environment and exploring leading edge thinking and best practice.Applies learning to build and refresh the business. Treats challenge as a positive force for improvement.");
insert into competenciesData (competencyDataID, competencyStage) values(5,"Behaves in an open, honest, and inclusive manner, upholding personal and organisational ethics and values. Shows respect for the needs of others and promotes equality and diversity.");
insert into competenciesData (competencyDataID, competencyStage) values(6, "Appreciates the impact of self on others and the impact others have on self. Routinely seeks feedback and adapts own behaviour appropriately. Identifying the impact others have on self and others.Reflects on own interactions with a wide and diverse range of individuals and groups from within and beyond immediate service/organisation.Challenges and refreshes own values, beliefs,");
insert into competenciesData (competencyDataID, competencyStage) values(7,"Ensures that own work plans and priorities fit with the needs of others involved in delivering services. Demonstrates flexibility and sensitivity and remains assertive in pursuing service goals.Builds and managed own personal brand both internally and externally.");
insert into competenciesData (competencyDataID, competencyStage) values(8,"Encourages a broad range of formal and informal learning opportunities, supporting development and career management by putting self forward for challenging assignments and projects which will develop strengths and address development areas. Seeks and provides constructive feedback from a variety of sources to support personal growth.");
insert into competenciesData (competencyDataID, competencyStage) values(9,".Acts as a role model for others in demonstrating integrity and inclusiveness in all aspects of own work.Challenges where organisational values are compromised.");
insert into competenciesData (competencyDataID, competencyStage) values(10,"Reflects on own interactions with a wide and diverse range of individuals and groups from within and beyond immediate service/organisation.Challenges and refreshes own values, beliefs, leadership styles and approaches. Overtly role models the giving and receiving of feedback.");
insert into competenciesData (competencyDataID, competencyStage) values(11,"Successfully manages a range of personal and organisational demands and pressures. Demonstrates tenacity and resilience. Overcomes setbacks where goals cannot be achieved and quickly refocuses. Is visible and accessible to others.");
insert into competenciesData (competencyDataID, competencyStage) values(12,"Acts as an exemplar for others in managing continuous personal development. Facilitates the development of a learning culture.Identifies tangible, objective business improvements and benefits when planning development activities and identifies and tracks these through metrics, to demonstrate impact on business results.");
insert into competenciesData (competencyDataID, competencyStage) values(13,"Creates an open, honest, and inclusive culture in accordance with clear principles and values. Ensures equity of access to services and creates an environment where people from all backgrounds can excel.");
insert into competenciesData (competencyDataID, competencyStage) values(14,"An exemplary role model of self-awareness and welcomes feedback openly. A strong advocate and voice for building a culture of self-development and seen to set the tone for effective leadership at a senior level.Uses sophisticated tools and sources to continuously learn about ownleadership impact across the wider business community and improve personal effectiveness as a senior leader. Understands how pressures associated with ");
insert into competenciesData (competencyDataID, competencyStage) values(15,"Confident and independent in own personal impact and recognises an influence on others beyond immediate teams. Goals are aligned to strategic objectives and Kainos values. Champions self and others for equality, diversity, and inclusion.");
insert into competenciesData (competencyDataID, competencyStage) values(16,"Role models continuous learning and self-development, evaluating own effectiveness and growth and planning further development. Progresses in the right direction for self and Kainos through strong personal ownership.");
insert into competenciesData (competencyDataID, competencyStage) values(17,"Assures standards of integrity are maintained across the business and communicates the importance of always adopting an ethical and inclusive approach.");
insert into competenciesData (competencyDataID, competencyStage) values(18, "Uses sophisticated tools and sources to continuously learn about own leadership impact across the wider business community and improve personal effectiveness as a senior leader. Understands how pressures associated with carrying out a high-profile role impacts personally and own performance.");
insert into competenciesData (competencyDataID, competencyStage) values(19,"Remains focused on the wider Kainos strategic goals when faced with competing and, at times, conflicting demands arising from differing priorities. Identifies where the need to personally get involved to achieve the most benefit for the wider organisation.");
insert into competenciesData (competencyDataID, competencyStage) values(20,"Develops through systematically scanning the external environment and exploring leading edge thinking and best practice.Applies learning to build and refresh the business. Treats challenge as a positive force for improvement.");
insert into competenciesData (competencyDataID, competencyStage) values(21,"Actively develops and protects Kainos through promoting and role modelling our values and competencies to achieve desired culture. Ensuring the business priorities maximise sustainability, equality, and diversity.");

insert into competencies(jobBandLevelID,competencyDataID) Values(1,1);
insert into competencies(jobBandLevelID,competencyDataID) Values(1,2);
insert into competencies(jobBandLevelID,competencyDataID) Values(1,3);
insert into competencies(jobBandLevelID,competencyDataID) Values(2,4);
insert into competencies(jobBandLevelID,competencyDataID) Values(6,5);
insert into competencies(jobBandLevelID,competencyDataID) Values(5,5);
insert into competencies(jobBandLevelID,competencyDataID) Values(4,5);
insert into competencies(jobBandLevelID,competencyDataID) Values(3,5);
insert into competencies(jobBandLevelID,competencyDataID) Values(2,5);
insert into competencies(jobBandLevelID,competencyDataID) Values(3,15);
insert into competencies(jobBandLevelID,competencyDataID) Values(7,15);
insert into competencies(jobBandLevelID,competencyDataID) Values(8,20);



insert into capabilityLead(leadID,leadFname,leadSname,leadPhoto,leadMessage,jobCapabilityID) value(1,"Dave","Boats","https://memegenerator.net/img/images/15109657/fat-warcraft-guy-from-south-park.jpg","wow code is great",1);
insert into capabilityLead(leadID,leadFname,leadSname,leadPhoto,leadMessage,jobCapabilityID) value(2,"Lee","Brown","https://cdn.vox-cdn.com/thumbor/BxA3f-dxx4UmGxOCNE_7P4V7fAs=/0x0:5157x3438/1200x800/filters:focal(880x1246:1704x2070)/cdn.vox-cdn.com/uploads/chorus_image/image/69106641/1201476988.0.jpg","i love Kainos",2);


insert into training(trainingName, trainingLink, trainingGroup) values('Mindset', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Mindset.aspx', 'Development programmes'); -- All staff
insert into training(trainingName, trainingLink, trainingGroup) values('Intro to Remote Working', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Intro-to-Remote-Working.aspx', 'Development programmes'); -- entry level to senior associate
insert into training(trainingName, trainingLink, trainingGroup) values('Interpersonal Skills', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Interpersonal-Skills.aspx', 'Professional skills'); -- entry level to senior associate
insert into training(trainingName, trainingLink, trainingGroup) values('Powerpoint 101', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/PowerPoint-101.aspx', 'Technical skills'); -- All staff
insert into training(trainingName, trainingLink, trainingGroup) values('Effective Decision Making', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Effective-Decision-Making.aspx', 'Development programmes'); -- senior associate plus
insert into training(trainingName, trainingLink, trainingGroup) values('Developing your Presentation Skills', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Developing-your-Presentation-Skills.aspx', 'Professional skills'); -- entry level to consultant
insert into training(trainingName, trainingLink, trainingGroup) values('Discovering your Personal Brand', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Discovering-your-Personal-Brand.aspx', 'Development programmes'); -- All staff
insert into training(trainingName, trainingLink, trainingGroup) values('Crucial Conversation', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Crucial-Conversations.aspx', 'Development programmes'); -- Senior associate plus
insert into training(trainingName, trainingLink, trainingGroup) values('Leading Change', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Leading-Change.aspx', 'Development programmes'); -- principal to leader
insert into training(trainingName, trainingLink, trainingGroup) values('Managing Change', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Managing-Change.aspx', 'Development programmes'); -- consultant to manager
insert into training(trainingName, trainingLink, trainingGroup) values('Effective Decision Making', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Effective-Decision-Making.aspx', 'Development programmes'); -- consultant to leader


insert into bandLevelsTraining values(1, 1);
insert into bandLevelsTraining values(2, 1);
insert into bandLevelsTraining values(3, 1);
insert into bandLevelsTraining values(4, 1);
insert into bandLevelsTraining values(5, 1);
insert into bandLevelsTraining values(6, 1);
insert into bandLevelsTraining values(7, 1);
insert into bandLevelsTraining values(8, 1);

insert into bandLevelsTraining values(1, 2);
insert into bandLevelsTraining values(2, 2);
insert into bandLevelsTraining values(3, 2);
insert into bandLevelsTraining values(4, 2);

insert into bandLevelsTraining values(1, 3);
insert into bandLevelsTraining values(2, 3);
insert into bandLevelsTraining values(3, 3);
insert into bandLevelsTraining values(4, 3);

insert into bandLevelsTraining values(1, 4);
insert into bandLevelsTraining values(2, 4);
insert into bandLevelsTraining values(3, 4);
insert into bandLevelsTraining values(4, 4);
insert into bandLevelsTraining values(5, 4);
insert into bandLevelsTraining values(6, 4);
insert into bandLevelsTraining values(7, 4);
insert into bandLevelsTraining values(8, 4);

insert into bandLevelsTraining values(4, 5);
insert into bandLevelsTraining values(5, 5);
insert into bandLevelsTraining values(6, 5);
insert into bandLevelsTraining values(7, 5);
insert into bandLevelsTraining values(8, 5);

insert into bandLevelsTraining values(1, 6);
insert into bandLevelsTraining values(2, 6);
insert into bandLevelsTraining values(3, 6);
insert into bandLevelsTraining values(4, 6);
insert into bandLevelsTraining values(5, 6);

insert into bandLevelsTraining values(1, 7);
insert into bandLevelsTraining values(2, 7);
insert into bandLevelsTraining values(3, 7);
insert into bandLevelsTraining values(4, 7);
insert into bandLevelsTraining values(5, 7);
insert into bandLevelsTraining values(6, 7);
insert into bandLevelsTraining values(7, 7);
insert into bandLevelsTraining values(8, 7);

insert into bandLevelsTraining values(4, 8);
insert into bandLevelsTraining values(5, 8);
insert into bandLevelsTraining values(6, 8);
insert into bandLevelsTraining values(7, 8);
insert into bandLevelsTraining values(8, 8);

insert into bandLevelsTraining values(7, 9);
insert into bandLevelsTraining values(8, 9);

insert into bandLevelsTraining values(5, 10);
insert into bandLevelsTraining values(6, 10);

insert into bandLevelsTraining values(5, 11);
insert into bandLevelsTraining values(6, 11);
insert into bandLevelsTraining values(7, 11);
insert into bandLevelsTraining values(8, 11);





insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Software Engineer', 3, 'As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true', '', 2);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Front-End Engineer',  3, 'As a Front-end Engineer in Kainos, you will have the opportunity to use your expertise in developing high quality user interface solutions which delight our customers and impact the lives of users worldwide.',' The projects you will join are varied, and often highly visible. You will be working in fast-paced, agile environments, so it is important for you to make sound, reasoned decisions, and recommendations on front-end and user interfaces with your colleagues. You are determined, flexible and always constructive; proactive in improving things and are always inclusive and respectful in your interactions with your team. You will be working alongside talented, diverse, enthusiastic colleagues, who will help you learn and develop as you, in turn, mentor those around you.', 'https://kainossoftwareltd.sharepoint.com/sites/PeopleTeam-SharedDrive/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering%2FJob%20Profile%20%2D%20Front%2DEnd%20Engineer%20%28A%29%2Epdf&parent=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering&p=true', '', 2);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Test Engineer',  3, 'As a Test Engineer (Associate) in Kainos, you’ll work within a multi-skilled agile team, developing and executing functional automated and manual tests to help the team deliver working application software that meets user needs. You’ll do this whilst learning about new technologies and approaches, with talented colleagues who will help you learn, develop and grow.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FTesting%2FJob%20profile%20%2D%20Test%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FTesting&p=true', '',4);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Security Engineer',  3, 'As a Security Engineer (Associate) in Kainos, you will be responsible for the security of technical solutions they are working on, by identifying and introducing appropriate security controls, training the team in secure development practices, and sharing knowledge on threats and vulnerabilities. You’ll work with Agile delivery teams to develop good security practices throughout the software development journey. You’ll learn about and apply new technologies and approaches, with talented colleagues who will help you develop and grow. You’ll share knowledge and help educate people – both customers and Kainos team members.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FCyber%20Security%2FJob%20profile%20%2D%20Security%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FCyber%20Security&p=true', '',7);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Software Engineer',  1, 'Are you looking for the bright future that a fantastic career in IT will bring, but don`t think that full time study is for you? Do you want to get a degree in Computing Systems while earning a salary at a Sunday Times Top 100 company.The Kainos Earn as you Learn Programme is back!Our highly-regarded scheme gives the opportunity to work for us as an Apprentice Software Engineer four days per week, while studying for a part time degree in Computing Systems one day per week, with Ulster University, Jordanstown, completing your degree in just four and a half years.The best of both worlds! This is an excellent opportunity to combine real work and technical experience within our fast-changing company, with the formal studies that you need to progress your career in IT.   It’s a no brainer! We’ll pay all of your University fees, provide you with a brand-new laptop as well as a full-time salary throughout. Oh, and it doesn’t end there. You will be offered a permanent position with us from day one, and we’ll help you to quickly progress up our ranks with our excellent (and award-winning!) in-house training programme Kainos MAP. It’s a great way to compliment your studies.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Apprentice%20Software%20Engineer%20%28Apprentice%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true', '', 2);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Software Engineer',  2, 'As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true', 'Contribute to developing high quality solutions which impact the lives of users worldwide. You’ll work as part of a team to solve problems and produce innovative software solutions. Learn about new technologies and approaches, with talented colleagues who will help you learn, develop and grow. Based in our Kainos office and often on our customer sites, you will work on a project teams to learn how to develop and unit test developing and unit testing straightforward or low complexity components, and then moving on to more complex elements as you increase your knowledge. Work with other developers in working through designs and user stories and to produce real development solutions. Will be fully supported by experienced colleagues in the team to follow designs,and then progress to assist in any other aspect of the project life-cycle under supervision. Develop excellent technical, team-working and Agile project experience.', 2);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Senior Data Engineer',  4, 'As a Senior Data Engineer (Senior Associate) at Kainos, is responsible or designing and developing data processing and data persistence software components for solutions which handle data at scale. Working in agile teams, Lead Data Engineers providing strong development leadership for team members and take responsibility for the quality of the codebase as well as the match to user needs. You will work within a multi-skilled agile team to design and develop large-scale data processing software to meet user needs in demanding production environments.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FData%2FJob%20profile%20%2D%20Senior%20Data%20Engineer%20%28SA%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FData&p=true', 'Working to develop data processing software primarily for deployment in Big Data technologies. The role encompasses the full software lifecycle including design, code, test and defect resolution.•Working with architects to ensure the software supports non-functional needs.•Collaborating with colleagues to resolve implementation challenges and ensure code quality and maintainability remains high. Leads by example in code quality.•Working with operations teams to ensure operational readiness•Advising customers and managers on the estimated effort and technical implications of user stories and user journeys.  •Coaching and mentoring more junior technical staff.',9);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Integrations Solution Architect',  7, 'As a Workday Integrations Solution Architect (Principal) in Kainos, you’ll be fully accountable for the quality of how we deliver, implement and configure Workday solutions for our global customer base. You’ll use your expertise to engage directly on high profile projects, to handle any significant integration challenges, and will be an important senior liaison and escalation for our clients.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FWorkday%2FIntegrations%2FJob%20Profile%20%2D%20Workday%20Integrations%20Consultant%20Solution%20Architect%20%28Principal%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FWorkday%2FIntegrations&p=true', '', 10);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Operations Manager',  6, 'As an Operations Manager (Manager) at Kainos, you will be responsible managing and improving the day to day operational processes within the Business Unit (BU) which supports  the management team and wider BU including sales, revenue and staff forecasting, staff allocation and recruitment forecasting, month-end closure, profitabilityand working capital.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FOperations%2FJob%20Profile%20%2D%20Operations%20Manager%20%28Manager%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FOperations&p=true', 'Working with theHead of Operationsproviding input to thesector/regionbudgeting process and helps them ensure costs remain in line with the budget and overall scale of the business.•Responsibility for supporting the management teamto achieve or exceed targeted profitabilitywhileensuring the accuracy of the underlying data.•Responsibilityfor operational processes within thesector/region within the BUsuch as revenue and staff forecasting, staff allocation,recruitment,or profitability.•Working with thesector/regionLeadership team toaccurately forecastrevenue andstaff withinthesector/region.•Responsibilityfor the allocation ofstaffwithin a sector/regionwithstrongawareness of the needs of both the project and individual.•Managing and controlling staff availability while minimisingdowntime.•Tracking staff utilisation initiating corrective action with Delivery Managers/Client Directorswhere appropriate.•Supportingthe BU recruitment process, forecasting and identifying staff requirements for all roles and levels withintheirsector/regionworking closely with the Kainos Recruitment team to meet demand.•Working closely with thewider Sector/Region Leadership Teamand Capability Leads to develop staff, ensuring there are sufficient appropriately skilled staff available to meet current and forecasted demands from both the Delivery and Sales teams.•Enforcing compliance of the month end closure process within their sector/regionincluding timesheet submission and approval as well as review of revenue and costs,invoicing,and debt collection.•Approvalof all non-project time ensuring accurate recording,challenging where necessary.•Continuous improvement and refinement of operational processes and communicating and presenting these processes to the wider BU.•Sharing and promoting operational and commercial best practice across the BU and wider Kainos.',11);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Business Development Consultant',  5, 'As a Business DevelopmentConsultant in Kainos, you will be responsible for buildingKainos’ new business by forging into new accounts, based on building an extensive network of industry contacts.You will be working collaboratively with other members of the business development team as well as colleagues from other areas of the business including delivery, legal, marketing and operations.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FBusiness%20Development%20and%20Marketing%2FBusiness%20Development%2FJob%20profile%20%2D%20Business%20Development%20Consultant%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FBusiness%20Development%20and%20Marketing%2FBusiness%20Development&p=true', 'Developing Kainos as a business – using a combination of new and existing accounts, (where applicable), you will build and maintain a pipeline of opportunities. You will also forecast accurately across future quarters to achieve agreed sales targets and maintain predictability of future revenue.•Putting deals together – these deals will need to meet sales, revenue and profitability objectives, and ensure that the work contracted is compatible with Kainos’ strategy and goals.•Being a trusted advisor for our customers– you will be expected to utilise a consultative selling approach based on taking the time to properly understand ourcustomers and their challenges/opportunities. Empathy, active listening, being responsive and creativity all play a part here.•Developing and strengthening partnerships – you will develop and strengthen our existing strategic partnerships to enable Kainos to be positioned as go-to partner that is trusted and continually adds value.•Negotiating and managing senior stakeholders – you will be a strong negotiator and strong in senior stakeholder management with experience of presenting and refining proposals to achieve the expected outcome for Kainos.•Being an external Kainos ambassador – with an external customer focus, you must ensure that you embrace and promote the culture, values and behaviours that make Kainos unique.•Working as part of a team – B2B enterprise deals are usually complex and require a business development professional to lead and leverage a wider multi-disciplinary You will work closely with colleagues from other business units as well as industry partners to ensure that cross-selling opportunities are maximised.•Putting people first and developing others –you’ll may be required to manage, coach and develop a small number of staff, with a focus on managing employee performance and assisting in their career development.', 12);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Principal AI Engineer',  7, 'As a Principal Artificial Intelligence (AI) Engineer in Kainos, you’ll be accountable for successful delivery of large-scale high-quality solutions that use AI and ML technologies that delight our customers and impact the lives of users worldwide.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FArtificial%20Intelligence%2FJob%20Profile%20%2D%20Principal%20AI%20Engineer%20%28Principal%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FArtificial%20Intelligence&p=true', '', 13);
insert into jobRoles(jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities,jobFamilyID) values('Delivery Lead', 8, 'As a Delivery Lead (Leader) at Kainos, you will be accountable for leading the delivery capability for the Sector/Regionin the Digital Services/Workdaybusiness unit, working closely with delivery managers to ensure successful and efficient delivery of multiple engagements across a ~£20+m portfolio. Take a hands-on role in key accounts leading client relationships from a delivery perspective as a trusted advisor to address business problems through digital delivery.  Owns a sector view of demand for people and works with BU Operations to scale our delivery capability to enable the high annual growth in revenue (20% plus).', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FDelivery%2FJob%20profile%20%2D%20Delivery%20Lead%20%28Leader%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FDelivery&p=true', 'Setting the goals and strategic direction for senior delivery managers to ensure that the business delivers an operationally efficient and successful delivery across all Sector/Region engagements •Contributing to the overall sector strategy, advising and executing specifically on delivery related matters •Working with the Delivery Managers to ensure that all aspects of the Sector/Regionand operational delivery requirements for all accounts within the sector•Ensuring successful and referenceable delivery to clients, employing strong governance and assurance•Ensuring delivery models are sustainable, profitable and in line with the company culture•Working with the wider business unit delivery capability to ensure the Sector/Regioncontinuously improves delivery ways of working to excel for our clients •Coaching, mentoring and developing the Delivery Managers working in the Sector/Region•Developing and maintaining strong relationships with senior stakeholders, influencing them for the benefit of Kainos, project assurance and delivery reputation.•Building a strong business network in the industry•Working closely with other sectors to share improvements in use of technology and processes.•In conjunction with the other Delivery Leads actively contributing and promoting a consistent culture of delivery across all of the Kainos businesses.•Fostering and maintaining strong relationships with selected partners.', 14);





