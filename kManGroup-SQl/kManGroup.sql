DROP DATABASE kManGroup_gillon;
CREATE DATABASE kManGroup_gillon;

use kManGroup_gillon;

create table jobRoles (
jobRoleID int auto_increment NOT NULL,
jobRole varchar(150) NOT NULL,
jobCapability varchar(50) NOT NULL,
jobBandLevel varchar(50) NOT NULL,
jobSpec text NOT NULL,
jobLink text NOT NULL,
jobResponsibilities text,
CONSTRAINT pk_Job_Role_ID PRIMARY KEY (jobRoleID));

insert into jobRoles(jobRole, jobCapability, jobBandLevel, jobSpec, jobLink,jobResponsibilities) values('Software Engineer', 'Engineering', 'Associate', 'As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true', '');
insert into jobRoles(jobRole, jobCapability, jobBandLevel, jobSpec, jobLink,jobResponsibilities) values('Front-End Engineer', 'Engineering', 'Associate', 'As a Front-end Engineer in Kainos, you will have the opportunity to use your expertise in developing high quality user interface solutions which delight our customers and impact the lives of users worldwide.   The projects you will join are varied, and often highly visible. You will be working in fast-paced, agile environments, so it is important for you to make sound, reasoned decisions, and recommendations on front-end and user interfaces with your colleagues. You are determined, flexible and always constructive; proactive in improving things and are always inclusive and respectful in your interactions with your team. You will be working alongside talented, diverse, enthusiastic colleagues, who will help you learn and develop as you, in turn, mentor those around you.', 'https://kainossoftwareltd.sharepoint.com/sites/PeopleTeam-SharedDrive/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering%2FJob%20Profile%20%2D%20Front%2DEnd%20Engineer%20%28A%29%2Epdf&parent=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering&p=true', '');
insert into jobRoles(jobRole, jobCapability, jobBandLevel, jobSpec, jobLink,jobResponsibilities) values('Test Engineer', 'Engineering', 'Associate', 'As a Test Engineer (Associate) in Kainos, you’ll work within a multi-skilled agile team, developing and executing functional automated and manual tests to help the team deliver working application software that meets user needs. You’ll do this whilst learning about new technologies and approaches, with talented colleagues who will help you learn, develop and grow.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FTesting%2FJob%20profile%20%2D%20Test%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FTesting&p=true', '');
insert into jobRoles(jobRole, jobCapability, jobBandLevel, jobSpec, jobLink,jobResponsibilities) values('Security Engineer', 'Cyber Security', 'Associate', 'As a Security Engineer (Associate) in Kainos, you will be responsible for the security of technical solutions they are working on, by identifying and introducing appropriate security controls, training the team in secure development practices, and sharing knowledge on threats and vulnerabilities. You’ll work with Agile delivery teams to develop good security practices throughout the software development journey. You’ll learn about and apply new technologies and approaches, with talented colleagues who will help you develop and grow. You’ll share knowledge and help educate people – both customers and Kainos team members.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FCyber%20Security%2FJob%20profile%20%2D%20Security%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FCyber%20Security&p=true', '');
insert into jobRoles(jobRole, jobCapability, jobBandLevel, jobSpec, jobLink,jobResponsibilities) values('Software Engineer', 'Engineering', 'Apprentice', 'Are you looking for the bright future that a fantastic career in IT will bring, but don`t think that full time study is for you? Do you want to get a degree in Computing Systems while earning a salary at a Sunday Times Top 100 company.The Kainos Earn as you Learn Programme is back!Our highly-regarded scheme gives the opportunity to work for us as an Apprentice Software Engineer four days per week, while studying for a part time degree in Computing Systems one day per week, with Ulster University, Jordanstown, completing your degree in just four and a half years.The best of both worlds! This is an excellent opportunity to combine real work and technical experience within our fast-changing company, with the formal studies that you need to progress your career in IT.   It’s a no brainer! We’ll pay all of your University fees, provide you with a brand-new laptop as well as a full-time salary throughout. Oh, and it doesn’t end there. You will be offered a permanent position with us from day one, and we’ll help you to quickly progress up our ranks with our excellent (and award-winning!) in-house training programme Kainos MAP. It’s a great way to compliment your studies.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Apprentice%20Software%20Engineer%20%28Apprentice%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true', '');
insert into jobRoles(jobRole, jobCapability, jobBandLevel, jobSpec, jobLink,jobResponsibilities) values('Software Engineer', 'Engineering', 'Trainee', 'As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true', 'Contribute to developing high quality solutions which impact the lives of users worldwide. You’ll work as part of a team to solve problems and produce innovative software solutions. Learn about new technologies and approaches, with talented colleagues who will help you learn, develop and grow. Based in our Kainos office and often on our customer sites, you will work on a project teams to learn how to develop and unit test developing and unit testing straightforward or low complexity components, and then moving on to more complex elements as you increase your knowledge. Work with other developers in working through designs and user stories and to produce real development solutions. Will be fully supported by experienced colleagues in the team to follow designs,and then progress to assist in any other aspect of the project life-cycle under supervision. Develop excellent technical, team-working and Agile project experience ');


select * from jobRoles;

create table competencies ( competencyName varchar(100) unique, competencyData text);

insert into competencies (competencyName, competencyData) Values("Apprentice","Understands own strengths and areas of development. Self-aware of own wellbeingand seeks support where appropriate.Works with People Manager to sets and achieve goals by monitoring progress regularly against performance.Flexible and willingness to learnon the jobwhile proactively keeping up to date with the knowledge and skills needed.Understands the company values and applies this to own principles. Is open and honest and acts respectfully with others and customers.");
insert into competencies (competencyName, competencyData) Values("Trainee","Understands others strengths and areas for development. Recognisingdiversity and its value within self andteam.Proactively uses wellbeing tools to support self-regulation.Seeksout new challenges and opportunitiesthat may stretchcurrentabilities.Builds on own self-awareness of overall wellbeing.Understandsand confidently articulatesown learning and developmental needs and proactively seek opportunities to gain experience.Has a strong Able to identify own goals and discusses these with People Manager. Understands the need to work conscientiously to achieve tasks on schedule.Identifies learning gaps and seeks out opportunities to improve skills. Open to developmental feedback from others.Demonstrates positive behaviours when dealing with others and ensures application of the values while working and representing Kainos to our customers.");
insert into competencies (competencyName, competencyData) Values("Associate","Seeksout new challenges and opportunitiesthat may stretchcurrentabilities.Builds on own self-awareness of overall wellbeing.Recognises the need for SMART goals, anddemonstratesa “can do” attitude, through having own internal standards of performance.Seeksout opportunities to improve skills beyond the role scopewhilst also seekingregular feedback.Encourages positive behaviours in others, while role modelling the Kainos values.");
insert into competencies (competencyName, competencyData) Values("Senior Associate","Understandsand confidently articulatesown learning and developmental needs and proactively seek opportunities to gain experience.Has a strong level of self-awareness and in tune with own wellbeing needsand intuitive of others.Understandsown personal preferences, biases and valuesdifferent cultures, backgrounds and circumstances in decision making and takes actions.Champions Kainos wellbeing offerings and provides direction to the various wellbeing guides and support available for. Seeksand respondspositively to feedback regarding own learning and development. Approachwith a willingness to take on tasks that are outside role scope.Looks for opportunities to take on new challenges while proactivelysupporting and encouraging others in identifying learning needs.Recognises inappropriate behaviours and challenges constructively while promoting the Kainos values. Adapting behaviours and actingin the most appropriate way ");
insert into competencies (competencyName, competencyData) Values("Consultant ","Understandsown personal preferences, biases and valuesdifferent cultures, backgrounds and circumstances in decision making and takes actions.Champions Kainos wellbeing offerings and provides direction to the various wellbeing guides and supportavailablefor our people.Consistently sets own goals and manages this independently. Makingautonomousdecisions and are able to ‘get on with the job’ escalating decisions only when appropriate.Identifies and addressesteam or individual capability requirements and gaps to deliver current and future work. Consistently identifies and developsself and othersto support talent development.Demonstrates professional and organisational values through actions and behaviours. Behaves in an inclusive manner and respects equality and diversity.")


