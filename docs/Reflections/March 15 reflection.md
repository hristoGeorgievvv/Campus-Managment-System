Reflection 16 March

How we did as a group:
This week we put all of our issues onto the issue board and assigned them at the beginning of the
week. Everyone knew exactly what they had to work on and we were able to balance the workload between
the different members of the group better. This is something that we definitely need to continue doing.

How we improved compared to last week:
- All people put a good amount of work on the project.
- Everything was distributed equally.
- Working on more branches instead of one.
- Not merging everything 20 min before the meeting but the day before.

What we learned this week:
- Issue board can be really important.
- We should for help if we encounter a problem. More eyes can fix the problem faster.

How we can improve next week:
- Definitely use the issue board again and divide tasks equally.
- More people should look into testing.


Individual reflections:

- Areti
This week I fixed some buttons, changed the entity user, and added more functionality to the admin
page. I now understand the frontend a lot better, especially the fxml files. I tried to create a new
mapping to the database to return only the type of a user but couldn't get it to work, so I used the
method we already have that sends all of the user information. For next week I need to start working
on my issues sooner so that I am not doing everything last minute again.

- Floris
This week I worked on adding a proper security system to the app. It took a lot of time to properly learn
how to set everything up and to fully understand how to utilise everything, so it took a while to instantiate
everything. The new security works fine, but because the authentication must be checked for every request,
it slows down the app when several requests are made. The way to solve it is to keep the session constant
for the user, but currently every request makes a new session, so the encrypted details must be sent every time.
I have tried to keep the session constant but have not been able to do so yet. Once that is done I can make
several improvements to the security, which can be done quite quickly. I want to be able to do more next week, as
learning how to implement basic security took a lot of time, and I can work more on it (hopefully).

- Horia
This week I implemented a map that shows the location of each building and a timetable that shows a user's current reservations.
The maps were a bit tricky since the tutorial for the javafx Google Maps API that I intended to use did not mention that Google Maps went pay-to-use a while ago, and the free version has a horrible watermark. Instead what I did was to use a WebView and inject an HTML code into it that searches online Google Maps just like in a regular search and has all the functionalities of Google Maps, although rendering places further than 300km is buggy on that map. The timetable went smooth. I used another javafx open library called jfxstras that adds a great timetable view. Figuring out how to populate it was the more difficult part, but having the view already was a huge help. I guess that for the next week I'll look into adding more functionalities for the GUI, little nitpicks and whatnot.

- Hristo
This week I worked on making a filtering feature for the rooms and adding more information
about the rooms in the backend entities and database such as is there whiteboard,projector, etc. in the room.
I ran into some bug that I could not solve for 2 hours so I asked Horia for some help and
we find an alternative solution. At the moment the filtering feature has option to select rooms only
with specific options, filter the minimum amount of people the client needs and to choose
to sort the rooms on capacity ascending or descending.

- Luc
This week I changed the way Users are created so that their type (student/staff) is determined by the email
I also added a bunch of buildings into the database, their images, and made the proper image load dynamically with
the proper building. Lastly, I implimented a run method in RoomReservationController that will start when the server 
starts, calculate the time to the closest hour and then at that time, and each hour after, it will check the reservations 
in the database and compare them with the current time, removing them if they are expired. Having the issue board helped
to give me concrete tasks, and is definitely something that we should keep doing.

- Milo
This week I added tests for every server side entity, their methods and constructors. I also added tests for all controllers
except UserController on the server side. Unfortunately new methods were added during merges, which was to be expected however
still means I will need to revist the issues. I feel that using the issue board at the start of the week like we did this week 
greatly helped organisation and effciency, I hope we continue to use this strategy going forward.

- Peter
This week I worked on adjusting the room selection and the reservation page to the CTA feedback. I've remodelled how user is
selecting the room. Instead of choosing one from a long list, user can now just pick a building and then the room from the separate
list also more information about the building is now shown there. On the reservation screen, all the data we have about the room in the
database is now shown there. I've changed the date picker and the timeslots, so they should be now much easier and obvious to use.
I no longer feel like lack of organization I felt quite a bit when the issue board wasn't used. Now it's much clearer who should I
contact when I need to ask about specific thing, or for whom should I wait if my is work is dependent on theirs. We should definitely
continue using it.
