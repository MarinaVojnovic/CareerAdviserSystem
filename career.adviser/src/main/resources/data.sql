insert into preferences (id, description, is_active) values (1, "Working on computer", true) on duplicate key update id = 1;--administration
insert into preferences (id, description, is_active) values (2, "Keeping things in order", true) on duplicate key update id = 2;
insert into preferences (id, description, is_active) values (3, "Comparing prices and buying where it is cheaper.", true) on duplicate key update id = 3;
insert into preferences (id, description, is_active) values (4, "Having order or system in my activities.", true) on duplicate key update id = 4;
insert into preferences (id, description, is_active) values (5, "Saving money and thinking carefully how to use it.", true) on duplicate key update id = 5; 

insert into preferences (id, description, is_active) values (6, "Finding technical documetation quite amusing and being good at understanding and using it.", true) on duplicate key update id = 6;--programming
insert into preferences (id, description, is_active) values (7, "Enjoy learning on your own.", true) on duplicate key update id = 7;
insert into preferences (id, description, is_active) values (8, "Would like to design, construct and test software.", true) on duplicate key update id = 8;

insert into preferences (id, description, is_active) values (9, "Possesing creativity and a sense of aesthetics.", true) on duplicate key update id = 9;--graphical design
insert into preferences (id, description, is_active) values (10, "Keeping track of world's trends and modern design achievements.", true) on duplicate key update id = 10;
insert into preferences (id, description, is_active) values (11, "Eager for constant improvement.", true) on duplicate key update id = 11;
insert into preferences (id, description, is_active) values (12, "Painting with watercolors or something else.", true) on duplicate key update id = 12;


insert into preferences (id, description, is_active) values (13, "Would enjoy profession that involves counseling others to solve their problems.", true) on duplicate key update id = 13;--psychology
insert into preferences (id, description, is_active) values (14, "Enjoy listening, observing and studying people.", true) on duplicate key update id = 14;
insert into preferences (id, description, is_active) values (15, "Would describe yourself as highly emotionally intellectual person.", true) on duplicate key update id = 15;

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




insert into professions(id, name, description, field_id, employment, payment, is_active) values (1, "Administration", "Neki opis za administratora", 1, 100000, 0.5, true) on duplicate key update id = 1;
insert into professions(id, name, description, field_id, employment, payment, is_active) values (2, "Programming", "Neki opis za programiranje", 2, 90000, 0.4, true) on duplicate key update id = 2;
insert into professions(id, name, description, field_id, employment, payment, is_active) values (3, "Graphycal design", "Neki opis za graficki dizajn", 0.3, 80000, 30, true) on duplicate key update id = 3;
insert into professions(id, name, description, field_id, employment, payment, is_active) values (4, "Pshychology", "Neki opis za psihologiju", 9, 70000, 0.2, true) on duplicate key update id = 4;


insert into professions_traits(profession_id, traits_id) values (1, 1) on duplicate key update profession_id = 1, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (1, 3) on duplicate key update profession_id = 1, traits_id = 3;
insert into professions_traits(profession_id, traits_id) values (1, 6) on duplicate key update profession_id = 1, traits_id = 6;
insert into professions_traits(profession_id, traits_id) values (1, 8) on duplicate key update profession_id = 1, traits_id = 8;
insert into professions_traits(profession_id, traits_id) values (1, 9) on duplicate key update profession_id = 1, traits_id = 9;

insert into professions_traits(profession_id, traits_id) values (2, 1) on duplicate key update profession_id = 2, traits_id = 1;
insert into professions_traits(profession_id, traits_id) values (2, 6) on duplicate key update profession_id = 2, traits_id = 6;
insert into professions_traits(profession_id, traits_id) values (2, 8) on duplicate key update profession_id = 2, traits_id = 8;


insert into professions_traits(profession_id, traits_id) values (3, 1) on duplicate key update profession_id = 3, traits_id = 1;


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