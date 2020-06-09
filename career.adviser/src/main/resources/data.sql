insert into preferences (id, description, is_active, field_id) values (1, "Working on computer", true, 1) on duplicate key update id = 1;--administration
insert into preferences (id, description, is_active, field_id) values (2, "Keeping things in order", true, 1) on duplicate key update id = 2;
insert into preferences (id, description, is_active, field_id) values (3, "Comparing prices and buying where it is cheaper.", true, 1) on duplicate key update id = 3;
insert into preferences (id, description, is_active, field_id) values (4, "Having order or system in my activities.", true, 1) on duplicate key update id = 4;
insert into preferences (id, description, is_active, field_id) values (5, "Saving money and thinking carefully how to use it.", true, 1) on duplicate key update id = 5; 

insert into preferences (id, description, is_active, field_id) values (6, "Finding technical documetation quite amusing and being good at understanding and using it.", true, 2) on duplicate key update id = 6;--programming
insert into preferences (id, description, is_active, field_id) values (7, "Enjoy learning on your own.", true, 2) on duplicate key update id = 7;
insert into preferences (id, description, is_active, field_id) values (8, "Would like to design, construct and test software.", true, 2) on duplicate key update id = 8;

insert into preferences (id, description, is_active, field_id) values (9, "Possesing creativity and a sense of aesthetics.", true, 6) on duplicate key update id = 9;--graphical design
insert into preferences (id, description, is_active, field_id) values (10, "Keeping track of world's trends and modern design achievements.", true, 6) on duplicate key update id = 10;
insert into preferences (id, description, is_active, field_id) values (11, "Eager for constant improvement.", true, 6) on duplicate key update id = 11;
insert into preferences (id, description, is_active, field_id) values (12, "Painting with watercolors or something else.", true, 6) on duplicate key update id = 12;


insert into preferences (id, description, is_active, field_id) values (13, "Would enjoy profession that involves counseling others to solve their problems.", true, 9) on duplicate key update id = 13;--psychology
insert into preferences (id, description, is_active, field_id) values (14, "Enjoy listening, observing and studying people.", true, 9) on duplicate key update id = 14;
insert into preferences (id, description, is_active, field_id) values (15, "Would describe yourself as highly emotionally intellectual person.", true, 9) on duplicate key update id = 15;

insert into preferences (id, description, is_active, field_id) values (16, "Making people very easily convinced in something.", true, 3) on duplicate key update id = 16;
insert into preferences (id, description, is_active, field_id) values (17, "Negotiating.", true, 3) on duplicate key update id = 17;
insert into preferences (id, description, is_active, field_id) values (18, "Enjoy reading laws and applying it in everyday world.", true, 3) on duplicate key update id = 18;
insert into preferences (id, description, is_active, field_id) values (19, "Would describe yourself as justice fighter.", true, 3) on duplicate key update id = 19;
insert into preferences (id, description, is_active, field_id) values (20, "Enjoy taking part in arguments.", true, 3) on duplicate key update id = 20;
insert into preferences (id, description, is_active, field_id) values (21, "Investigating and solving problems.", true, 3) on duplicate key update id = 21;

insert into preferences (id, description, is_active, field_id) values (22, "Love helping people.", true, 4) on duplicate key update id = 22;
insert into preferences (id, description, is_active, field_id) values (23, "Would describe yourself as extremely responsible and emotionally mature and stable.", true, 4) on duplicate key update id = 23;
insert into preferences (id, description, is_active, field_id) values (24, "Not queasy when it comes to deformations, fluids etc.", true, 4) on duplicate key update id = 24;
insert into preferences (id, description, is_active, field_id) values (25, "Bodies always fascinated you and appealed to you as something extraordinary, especially their possibility to heal and regenerate", true, 4) on duplicate key update id = 25;
insert into preferences (id, description, is_active, field_id) values (26, "Being very patient with people and very good at understanding and calming them when necessary.", true, 4) on duplicate key update id = 26;

insert into preferences (id, description, is_active, field_id) values (27, "Love travelling.", true, 12) on duplicate key update id = 27;
insert into preferences (id, description, is_active, field_id) values (28, "Enjoy reading travel blogs, biographies of great people.", true, 12) on duplicate key update id = 28;
insert into preferences (id, description, is_active, field_id) values (29, "Would consider yourself as a good leader.", true, 12) on duplicate key update id = 29;
insert into preferences (id, description, is_active, field_id) values (30, "Speaking several different languages.", true, 12) on duplicate key update id = 30;
insert into preferences (id, description, is_active, field_id) values (31, "Would consider yourself as person with great communicational skills.", true, 12) on duplicate key update id = 31;
insert into preferences (id, description, is_active, field_id) values (32, "Having a good orientation.", true, 12) on duplicate key update id = 32;

insert into preferences (id, description, is_active, field_id) values (33, "Investigating, solving mathematical problems.", true, 13) on duplicate key update id = 33;
insert into preferences (id, description, is_active, field_id) values (34, "Collecting data and searching for patterns in it.", true, 13) on duplicate key update id = 34;
insert into preferences (id, description, is_active, field_id) values (35, "Having analytical thinking approach.", true, 13) on duplicate key update id = 35;
insert into preferences (id, description, is_active, field_id) values (36, "Having extremely good communication skills, both vocally and in written format.", true, 13) on duplicate key update id = 36;
insert into preferences (id, description, is_active, field_id) values (37, "Would consider yourself as organised, proactive and self – initiated person.", true, 13) on duplicate key update id = 37;


insert into traits (id, personality_field, target) values (1, "mind", "extraverted") on duplicate key update id = 1;
insert into traits (id, personality_field, target) values (2, "mind", "introverted") on duplicate key update id = 2;
insert into traits (id, personality_field, target) values (3, "identity", "assertive") on duplicate key update id = 3;
insert into traits (id, personality_field, target) values (4, "identity", "turbulent") on duplicate key update id = 4;
insert into traits (id, personality_field, target) values (5, "nature", "thinking") on duplicate key update id = 5;
insert into traits (id, personality_field, target) values (6, "nature", "feeling") on duplicate key update id = 6;
insert into traits (id, personality_field, target) values (7, "energy", "realist") on duplicate key update id = 7;
insert into traits (id, personality_field, target) values (8, "energy", "visionary") on duplicate key update id = 8;
insert into traits (id, personality_field, target) values (9, "tactics", "judging") on duplicate key update id = 9;
insert into traits (id, personality_field, target) values (10, "tactics", "prospecting") on duplicate key update id = 10;


insert into professional_fields (id, name) values (1, "Economy") on duplicate key update id = 1;
insert into professional_fields (id, name) values (2, "Enigneering") on duplicate key update id = 2;
insert into professional_fields (id, name) values (3, "Law and politics") on duplicate key update id = 3;
insert into professional_fields (id, name) values (4, "Medicine") on duplicate key update id = 4;
insert into professional_fields (id, name) values (5, "Architecture, construction, geodesy") on duplicate key update id = 5;
insert into professional_fields (id, name) values (6, "Art") on duplicate key update id = 6;
insert into professional_fields (id, name) values (7, "Languages") on duplicate key update id = 7;
insert into professional_fields (id, name) values (8, "Education") on duplicate key update id = 8;
insert into professional_fields (id, name) values (9, "Pshychology") on duplicate key update id = 9;
insert into professional_fields (id, name) values (10, "Media") on duplicate key update id = 10;
insert into professional_fields (id, name) values (11, "Beauty") on duplicate key update id = 11;
insert into professional_fields (id, name) values (12, "Toursim and catering") on duplicate key update id = 12;
insert into professional_fields (id, name) values (13, "Science") on duplicate key update id = 13;

insert into tests_done_in_a_day(id, test_date, number) values (1, '2020-06-01 08:00', 10) on duplicate key update id = 1;
insert into tests_done_in_a_day(id, test_date, number) values (2, '2020-06-02 08:00', 20) on duplicate key update id = 2;
insert into tests_done_in_a_day(id, test_date, number) values (3, '2020-06-03 08:00', 30) on duplicate key update id = 3;

insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (1, "Administration", "Opšta uloga administratora i računovodstva je u svim kompanijama uglavnom administrativne prirode i postoji u mnogim industrijama. Poslovi iz ove kategorije obično uključuju brigu o finansijama i pomaganje menadžerima u efikasnom upravljanju. Dužnosti mogu uključiti briga o finansijama, različitoj dokumentaciji, javljanje na telefon, fotokopiranje, pisanje elektronske pošte i zakazivanje sastanaka i drugih aktivnosti. Radna mesta iz ove kategorije se dosta i nude od strane poslodavaca, ali su radna mesta tražena od strane kandidata. Za sva radna mesta iz ove kategorije je bitno znanje MS Office-a i engleskog jezika. Najtraženija zanimanja od strane poslodavaca u okviru ove kategorije su poslovni sekretar i knjigovođa.", 100000, 0, 50, true, "administration.jpeg") on duplicate key update id = 1;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (2, "Programming", "Programer je osoba koja pravi kompjuterske programe.
Termin „programer“ može da se odnosi na stručnjaka u nekoj od oblasti kompjuterskog programiranja ili na osobu opšteg kompjuterskog znanja koja piše kodove za više vrsta programa.
Programer piše, testira, otklanja neispravnosti, održava i upućuje detaljne instrukcije preko „kompjuterskih programa“ kompjuteru, koje izvršava kako bi rešio neku funkciju. Programer takođe osmišljava, dizajnira i testira logičku strukturu za rešenje problema od strane kompjutera.
Mnoge tehničke inovacije u programiranju – napredne kompjuterske tehnologije, sofisticirani programski jezici i alati su redefinisali ulogu programera u savremenom programiranju. Naziv njegove pozicije i opis posla kojim se bavi danas varira u mnogo većoj amplitudi u odnosu na raniji period.", 90000, 0, 15, true, "programming.jpeg") on duplicate key update id = 2;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (3, "Graphycal design", "Neki opis za graficki dizajn", 80000, 0, 5, true, "graphycalDesign.jpeg") on duplicate key update id = 3;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (4, "Psychology", "Neki opis za psihologiju", 70000, 0, 5, true, "psychology.jpeg") on duplicate key update id = 4;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (5, "Law", "Lawyers, also known as Attorneys, are certified professionals who advise and represent natural and juristic persons in legal matters. They counsel clients, perform legal research, prepare legal documents and represent clients in criminal and civil court proceedings.", 1500000, 0, 10, true, "lawyer.jpg") on duplicate key update id = 5;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (6, "Medicine", "The healthcare workforce comprises a wide variety of professions and occupations who provide some type of healthcare service, including such direct care practitioners as physicians, respiratory therapists, nurses, surgeons, dentists, physical and behavior therapists, as well as allied health professionals such as phlebotomists, medical laboratory scientists, dieticians, and social workers. They often work in hospitals, healthcare centres and other service delivery points, but also in academic training, research, and administration. Some provide care and treatment services for patients in private homes. Many countries have a large number of community health workers who work outside formal healthcare institutions. Managers of healthcare services, health information technicians, and other assistive personnel and support workers are also considered a vital part of health care teams", 100000, 0, 15, true, "medicine.jpg") on duplicate key update id = 6;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (7, "Travel and tourism", "monuments, cultural centres and beauty spots, and provide them with background information to help them make the most of their visit. They may work with day-trippers or on walking tours, or support tourists on longer visits that involve overnight stays, perhaps to rural or remote locations. Tour guide jobs sometimes also call for chauffeuring and language skills.Potential tour guides should be fit and healthy with lots of energy and confidence, be able to work effectively without supervision, possess a calm 'customer focused' manner, and have excellent interpersonal skills.", 50000, 0, 20, true, "tourism.png") on duplicate key update id = 7;
insert into professions(id, name, description, payment, employment_score, employment, is_active, image) values (8, "Statistics", "Statistics is the discipline that concerns the collection, organization, analysis, interpretation and presentation of data. In applying statistics to a scientific, industrial, or social problem, it is conventional to begin with a statistical population or a statistical model to be studied. Populations can be diverse groups of people or objects such as all people living in a country or every atom composing a crystal. Statistics deals with every aspect of data, including the planning of data collection in terms of the design of surveys and experiments.", 90000, 0, 15, true, "statistics.png") on duplicate key update id = 8;

insert into professions_traits(profession_id, traits_id) values (1, 1) on duplicate key update profession_id = 1, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (1, 3) on duplicate key update profession_id = 1, traits_id = 3;
insert into professions_traits(profession_id, traits_id) values (1, 6) on duplicate key update profession_id = 1, traits_id = 6;
insert into professions_traits(profession_id, traits_id) values (1, 8) on duplicate key update profession_id = 1, traits_id = 8;
insert into professions_traits(profession_id, traits_id) values (1, 9) on duplicate key update profession_id = 1, traits_id = 9;

insert into professions_traits(profession_id, traits_id) values (2, 1) on duplicate key update profession_id = 2, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (2, 6) on duplicate key update profession_id = 2, traits_id = 6;
insert into professions_traits(profession_id, traits_id) values (2, 8) on duplicate key update profession_id = 2, traits_id = 8;


insert into professions_traits(profession_id, traits_id) values (3, 1) on duplicate key update profession_id = 3, traits_id = 1;

insert into professions_activities(profession_id, activities_id) values (1, 1) on duplicate key update profession_id=1, activities_id =1;
insert into professions_activities(profession_id, activities_id) values (1, 2) on duplicate key update profession_id=1, activities_id =2;
insert into professions_activities(profession_id, activities_id) values (1, 3) on duplicate key update profession_id=1, activities_id =3;
insert into professions_activities(profession_id, activities_id) values (1, 4) on duplicate key update profession_id=1, activities_id =4;
insert into professions_activities(profession_id, activities_id) values (1, 5) on duplicate key update profession_id=1, activities_id =5;

insert into professions_activities(profession_id, activities_id) values (6, 22) on duplicate key update profession_id=6, activities_id =22;
insert into professions_activities(profession_id, activities_id) values (6, 23) on duplicate key update profession_id=6, activities_id =23;
insert into professions_activities(profession_id, activities_id) values (6, 24) on duplicate key update profession_id=6, activities_id =24;
insert into professions_activities(profession_id, activities_id) values (6, 25) on duplicate key update profession_id=6, activities_id =25;
insert into professions_activities(profession_id, activities_id) values (6, 26) on duplicate key update profession_id=6, activities_id =26;

insert into professions_activities(profession_id, activities_id) values (7, 27) on duplicate key update profession_id=7, activities_id =27;
insert into professions_activities(profession_id, activities_id) values (7, 28) on duplicate key update profession_id=7, activities_id =28;
insert into professions_activities(profession_id, activities_id) values (7, 29) on duplicate key update profession_id=7, activities_id =29;
insert into professions_activities(profession_id, activities_id) values (7, 30) on duplicate key update profession_id=7, activities_id =30;
insert into professions_activities(profession_id, activities_id) values (7, 31) on duplicate key update profession_id=7, activities_id =31;
insert into professions_activities(profession_id, activities_id) values (7, 32) on duplicate key update profession_id=7, activities_id =32;


insert into professions_activities(profession_id, activities_id) values (2, 6) on duplicate key update profession_id=2, activities_id =6;
insert into professions_activities(profession_id, activities_id) values (2, 7) on duplicate key update profession_id=2, activities_id =7;
insert into professions_activities(profession_id, activities_id) values (2, 8) on duplicate key update profession_id=2, activities_id =8;

insert into professions_activities(profession_id, activities_id) values (3, 9) on duplicate key update profession_id=3, activities_id =9;
insert into professions_activities(profession_id, activities_id) values (3, 10) on duplicate key update profession_id=3, activities_id =10;
insert into professions_activities(profession_id, activities_id) values (3, 11) on duplicate key update profession_id=3, activities_id =11;
insert into professions_activities(profession_id, activities_id) values (3, 12) on duplicate key update profession_id=3, activities_id =12;

insert into professions_activities(profession_id, activities_id) values (4, 13) on duplicate key update profession_id=4, activities_id =13;
insert into professions_activities(profession_id, activities_id) values (4, 14) on duplicate key update profession_id=4, activities_id =14;
insert into professions_activities(profession_id, activities_id) values (4, 15) on duplicate key update profession_id=4, activities_id =15;

insert into professions_activities(profession_id, activities_id) values (5, 16) on duplicate key update profession_id=5, activities_id =16;
insert into professions_activities(profession_id, activities_id) values (5, 17) on duplicate key update profession_id=5, activities_id =17;
insert into professions_activities(profession_id, activities_id) values (5, 18) on duplicate key update profession_id=5, activities_id =18;
insert into professions_activities(profession_id, activities_id) values (5, 19) on duplicate key update profession_id=5, activities_id =19;
insert into professions_activities(profession_id, activities_id) values (5, 20) on duplicate key update profession_id=5, activities_id =20;
insert into professions_activities(profession_id, activities_id) values (5, 21) on duplicate key update profession_id=5, activities_id =21;

insert into professions_activities(profession_id, activities_id) values (8, 33) on duplicate key update profession_id=5, activities_id =33;
insert into professions_activities(profession_id, activities_id) values (8, 34) on duplicate key update profession_id=5, activities_id =34;
insert into professions_activities(profession_id, activities_id) values (8, 35) on duplicate key update profession_id=5, activities_id =35;
insert into professions_activities(profession_id, activities_id) values (8, 36) on duplicate key update profession_id=5, activities_id =36;
insert into professions_activities(profession_id, activities_id) values (8, 37) on duplicate key update profession_id=5, activities_id =37;
insert into professions_activities(profession_id, activities_id) values (8, 38) on duplicate key update profession_id=5, activities_id =38;

insert into professions_traits(profession_id, traits_id) values (4, 1) on duplicate key update profession_id = 4, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (4, 4) on duplicate key update profession_id = 4, traits_id = 4;
insert into professions_traits(profession_id, traits_id) values (4, 6) on duplicate key update profession_id = 4, traits_id = 6;

insert into professions_traits(profession_id, traits_id) values (5, 1) on duplicate key update profession_id = 5, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (5, 3) on duplicate key update profession_id = 5, traits_id = 3;
insert into professions_traits(profession_id, traits_id) values (5, 5) on duplicate key update profession_id = 5, traits_id = 5;
insert into professions_traits(profession_id, traits_id) values (5, 7) on duplicate key update profession_id = 5, traits_id = 7;
insert into professions_traits(profession_id, traits_id) values (5, 10) on duplicate key update profession_id = 5, traits_id = 9;

insert into professions_traits(profession_id, traits_id) values (6, 3) on duplicate key update profession_id = 6, traits_id = 3;
insert into professions_traits(profession_id, traits_id) values (6, 7) on duplicate key update profession_id = 6, traits_id = 7;
insert into professions_traits(profession_id, traits_id) values (6, 10) on duplicate key update profession_id = 6, traits_id = 10;
insert into professions_traits(profession_id, traits_id) values (6, 5) on duplicate key update profession_id = 6, traits_id = 5;

insert into professions_traits(profession_id, traits_id) values (7, 1) on duplicate key update profession_id = 7, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (7, 3) on duplicate key update profession_id = 7, traits_id = 3;
insert into professions_traits(profession_id, traits_id) values (7, 8) on duplicate key update profession_id = 7, traits_id = 8;
insert into professions_traits(profession_id, traits_id) values (7, 9) on duplicate key update profession_id = 7, traits_id = 9;

insert into professions_traits(profession_id, traits_id) values (8, 1) on duplicate key update profession_id = 8, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (8, 5) on duplicate key update profession_id = 8, traits_id = 5;
insert into professions_traits(profession_id, traits_id) values (8, 7) on duplicate key update profession_id = 8, traits_id = 7;
insert into professions_traits(profession_id, traits_id) values (8, 8) on duplicate key update profession_id = 8, traits_id = 9;

insert into trait_questions(id, text, trait_id, is_active) value(1, "You enjoy vibrant social events with lots of people.", 1, true) on duplicate key update id = 1;
insert into trait_questions(id, text, trait_id, is_active) value(2, "You feel comfortable just walking up to someone you find interesting and striking u a conversation.", 1, true) on duplicate key update id = 2;
insert into trait_questions(id, text, trait_id, is_active) value(3, "You often rely of other people to be the ones to start a conversation and keep it going.", 2, true) on duplicate key update id = 3;

insert into trait_questions(id, text, trait_id, is_active) value(4, "You often think about what you should have said in a conversation long after it has taken place.", 3, true) on duplicate key update id = 4;
insert into trait_questions(id, text, trait_id, is_active) value(5, "You always know exactly what you want.", 3, true) on duplicate key update id = 5;
insert into trait_questions(id, text, trait_id, is_active) value(6, "If you make a mistake you tend to start doubting your knowledge.", 4, true) on duplicate key update id = 6;

insert into trait_questions(id, text, trait_id, is_active) value(7, "You often have a hard time understanding other people’s feelings.", 5, true) on duplicate key update id = 7;
insert into trait_questions(id, text, trait_id, is_active) value(8, "Your friend is sad about something, your first instinct is to support them emotionally, not try to solve their problem.", 6, true) on duplicate key update id = 8;
insert into trait_questions(id, text, trait_id, is_active) value(9, "You are very affectionate with people you care about.", 6, true) on duplicate key update id = 9;

insert into trait_questions(id, text, trait_id, is_active) value(10, "You see yourself as a more a realist than a visionary.", 7, true) on duplicate key update id = 10;
insert into trait_questions(id, text, trait_id, is_active) value(11, "You often spend time exploring unrealistic yet intriguing ideas.", 8, true) on duplicate key update id = 11;
insert into trait_questions(id, text, trait_id, is_active) value(12, "When you sleep your dreams tend to be bizarre and fantastical.", 8, true) on duplicate key update id = 12;

insert into trait_questions(id, text, trait_id, is_active) value(13, "You are dedicated and focused on your goals, only rarely getting sidetracked.", 9, true) on duplicate key update id = 13;
insert into trait_questions(id, text, trait_id, is_active) value(14, "Even when you have planned a particular daily routine, you usually end up doing what you feel like at any given moment.", 10, true) on duplicate key update id = 14;
insert into trait_questions(id, text, trait_id, is_active) value(15, "Your personal work style is closer to spontaneous bursts of energy than to organized and consistent efforts.", 10, true) on duplicate key update id = 15;

insert into authority (id, role) VALUES (1, 'ROLE_ADMIN') on duplicate key update id = 1;
insert into users(id, username, email, password, name, surname, allowed, new_pers_test, new_prof_test) values (1, "maki", "marina.vojnovic1997@gmail.com", "$2a$10$xMipTNv6mB4FdLt52YK4KuzVVFx891Pr0cnWySeko67UbjbZcIAK2", "Marina", "Vojnovic", true, true, true) on duplicate key update id = 1;
insert into user_authority (user_id, authority_id) VALUES (1, 1) ON DUPLICATE KEY UPDATE user_id = 1;

insert into authority (id, role) VALUES (2, 'ROLE_USER') on duplicate key update id = 2;
insert into users(id, username, email, password, name, surname, allowed, new_pers_test, new_prof_test) values (2, "nebojsa", "marina.vojnovic1997@gmail.com", "$2a$10$xMipTNv6mB4FdLt52YK4KuzVVFx891Pr0cnWySeko67UbjbZcIAK2", "Nebojsa", "Lalic", true, true, true) on duplicate key update id = 2;
insert into user_authority (user_id, authority_id) VALUES (2, 2) ON DUPLICATE KEY UPDATE user_id = 2;


insert into employment_score_template(id, min_perc, max_perc, score) value(1, 0, 10, 0.25) on duplicate key update id = 1;
insert into employment_score_template(id, min_perc, max_perc, score) value(2, 11, 20, 0.50) on duplicate key update id = 2;
insert into employment_score_template(id, min_perc, max_perc, score) value(3, 21, 30, 0.75) on duplicate key update id = 3;
insert into employment_score_template(id, min_perc, max_perc, score) value(4, 31, 100, 1.0) on duplicate key update id = 4;
