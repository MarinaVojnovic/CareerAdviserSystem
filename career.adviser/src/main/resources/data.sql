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




insert into professions(id, name, description, employment, payment, is_active, image) values (1, "Administration", "Opšta uloga administratora i računovodstva je u svim kompanijama uglavnom administrativne prirode i postoji u mnogim industrijama. Poslovi iz ove kategorije obično uključuju brigu o finansijama i pomaganje menadžerima u efikasnom upravljanju. Dužnosti mogu uključiti briga o finansijama, različitoj dokumentaciji, javljanje na telefon, fotokopiranje, pisanje elektronske pošte i zakazivanje sastanaka i drugih aktivnosti. Radna mesta iz ove kategorije se dosta i nude od strane poslodavaca, ali su radna mesta tražena od strane kandidata. Za sva radna mesta iz ove kategorije je bitno znanje MS Office-a i engleskog jezika. Najtraženija zanimanja od strane poslodavaca u okviru ove kategorije su poslovni sekretar i knjigovođa.", 100000, 0.5, true, "administration.jpeg") on duplicate key update id = 1;
insert into professions(id, name, description, employment, payment, is_active, image) values (2, "Programming", "Programer je osoba koja pravi kompjuterske programe.
Termin „programer“ može da se odnosi na stručnjaka u nekoj od oblasti kompjuterskog programiranja ili na osobu opšteg kompjuterskog znanja koja piše kodove za više vrsta programa.
Programer piše, testira, otklanja neispravnosti, održava i upućuje detaljne instrukcije preko „kompjuterskih programa“ kompjuteru, koje izvršava kako bi rešio neku funkciju. Programer takođe osmišljava, dizajnira i testira logičku strukturu za rešenje problema od strane kompjutera.
Mnoge tehničke inovacije u programiranju – napredne kompjuterske tehnologije, sofisticirani programski jezici i alati su redefinisali ulogu programera u savremenom programiranju. Naziv njegove pozicije i opis posla kojim se bavi danas varira u mnogo većoj amplitudi u odnosu na raniji period.", 90000, 0.4, true, "programming.jpeg") on duplicate key update id = 2;
insert into professions(id, name, description, employment, payment, is_active, image) values (3, "Graphycal design", "Neki opis za graficki dizajn", 80000, 0.3, true, "graphycalDesign.jpeg") on duplicate key update id = 3;
insert into professions(id, name, description, employment, payment, is_active, image) values (4, "Psychology", "Neki opis za psihologiju", 70000, 0.2, true, "psychology.jpeg") on duplicate key update id = 4;


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

insert into professions_traits(profession_id, traits_id) values (4, 1) on duplicate key update profession_id = 4, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (4, 4) on duplicate key update profession_id = 4, traits_id = 4;
insert into professions_traits(profession_id, traits_id) values (4, 6) on duplicate key update profession_id = 4, traits_id = 6;

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




insert into users(id, username, email, password, name, surname) values (1, "maki", "marina.vojnovic1997@gmail.com", "maki", "Marina", "Vojnovic") on duplicate key update id = 1;